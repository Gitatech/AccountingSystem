package com.bsu.accounting.system.service;

import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.Floor;
import com.bsu.accounting.system.model.House;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class FloorService {

    private static final Logger LOGGER = LogManager.getLogger(FloorService.class);

    private static final String HEIGHT_OF_THE_FLOOR_MSG = "Set the height(m) of the floor: ";
    private static final String AVAILABLE_FLOOR_AREA = "Available floor length: %.2f, width: %.2f\n\n";
    private static final double PERCENTAGE_OF_NON_RESIDENTIAL_AREA = 0.9;

    private static final String PATH_TO_FILE = "AccountingSystem/university-lab/src/main/resources/results.ob";

    private FloorService() {

    }

    private static class SingletonHolder {
        private static final FloorService FLOOR_SERVICE = new FloorService();
    }

    public static FloorService getInstance() {
        return SingletonHolder.FLOOR_SERVICE;
    }

    public Floor createFloor(House house, double height) {
        Floor savingFloor = new Floor(height, house.getLength(), house.getWidth());

        keepFloorOnFile(savingFloor);

        return savingFloor;
    }

    public Floor addApartment(Floor floor, Apartment apartment) {
        final boolean comparison = floor.getFloorLength() > apartment.getTotalApartmentLength()
                && floor.getFloorWidth() > apartment.getTotalApartmentWidth();

        if (comparison) {
            floor.setApartment(apartment);
        } else {
            LOGGER.error(apartment);
        }

        return floor;
    }

    public double availableFloorArea(House arbitraryHouse, double tempLength) {
        double availableLength = arbitraryHouse.getLength() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA - tempLength;
        double availableWidth = arbitraryHouse.getWidth() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA / 2;

        System.out.printf(AVAILABLE_FLOOR_AREA, availableLength, availableWidth);

        return availableLength;
    }

    public List<Apartment> fillTheSecondPartOfTheHouse(Floor currentFloor) {
        int countApartmentsOnSecondHalf = currentFloor.getApartments().size();
        for (int i = 0; i < countApartmentsOnSecondHalf; i++) {
            this.addApartment(currentFloor, currentFloor.getApartment(i));
        }

        return currentFloor.getApartments();
    }

    private void keepFloorOnFile(Floor savingFloor) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PATH_TO_FILE, true))) {
            outputStream.writeObject(savingFloor);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
