package com.bsu.accounting.system.service;

import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.Floor;
import com.bsu.accounting.system.model.House;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class FloorService {

    private static final Logger LOGGER = LogManager.getLogger(FloorService.class);

    private static final String HEIGHT_OF_THE_FLOOR_MSG = "Set the height(m) of the floor: ";

    Scanner scanner = new Scanner(System.in);

    public Floor createFloor(House house) {
        System.out.print(HEIGHT_OF_THE_FLOOR_MSG);
        double height = scanner.nextDouble();
        System.out.println();
        return new Floor(height, house.getLength(), house.getWidth());
    }

    public void addApartment(Floor floor, Apartment apartment) {
        final boolean comparison = floor.getFloorLength() > apartment.getTotalApartmentLength()
                && floor.getFloorWidth() > apartment.getTotalApartmentWidth();
        if (comparison) {
            floor.apartments.add(apartment);
        } else {
            LOGGER.error(apartment);
        }
    }
}
