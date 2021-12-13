package com.bsu.accounting.system.service;

import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.Floor;
import com.bsu.accounting.system.model.House;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class FloorService {

    private static final Logger LOGGER = LogManager.getLogger(FloorService.class);

    private static final String HEIGHT_OF_THE_FLOOR_MSG = "Set the height(m) of the floor: ";
    private static final String PATH_TO_FILE = "AccountingSystem/university-lab/src/main/resources/results.ob";

    Scanner scanner = new Scanner(System.in);

    private FloorService() {

    }

    private static class SingletonHolder {
        private static final FloorService FLOOR_SERVICE = new FloorService();
    }

    public static FloorService getInstance() {
        return SingletonHolder.FLOOR_SERVICE;
    }

    public Floor createFloor(House house) {
        System.out.print(HEIGHT_OF_THE_FLOOR_MSG);
        double height = scanner.nextDouble();
        System.out.println();

        final Floor savingFloor = new Floor(height, house.getLength(), house.getWidth());

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PATH_TO_FILE, true))) {
            outputStream.writeObject(savingFloor);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return savingFloor;
    }

    public void addApartment(Floor floor, Apartment apartment) {

        final boolean comparison = floor.getFloorLength() > apartment.getTotalApartmentLength()
                && floor.getFloorWidth() > apartment.getTotalApartmentWidth();

        if (comparison) {
            floor.setApartment(apartment);
        } else {
            LOGGER.error(apartment);
        }
    }
}
