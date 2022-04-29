package com.bsu.accounting.system.validation;

import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.House;
import com.bsu.accounting.system.service.HouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HouseValidator {

    private static final Logger LOGGER = LogManager.getLogger(HouseValidator.class);

    private static final double PERCENTAGE_OF_NON_RESIDENTIAL_AREA = 0.9;

    public boolean checkApartmentWidthValue(House arbitraryHouse, Apartment apartment) {
        return apartment.getTotalApartmentWidth() > arbitraryHouse.getWidth() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA / 2;
    }

    public boolean checkApartmentLengthValue(House arbitraryHouse, double tempLength, Apartment apartment) {
        return apartment.getTotalApartmentLength() > arbitraryHouse.getLength() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA - tempLength;
    }

    public void checkCompareContent(House firstHouse, House secondHouse) {

        HouseService houseService = HouseService.getInstance();

        if (firstHouse == null || secondHouse == null) {
            throw new NullPointerException("House must be a not null value");
        }
        LOGGER.info("\nCompare the {}(house) with {}(house) in terms of parameters", firstHouse.getName(), secondHouse.getName());

        if (firstHouse.getLength() > secondHouse.getLength()) {
            LOGGER.info("{} longer than {}", firstHouse.getName(), secondHouse.getName());
        } else if (firstHouse.getLength() == secondHouse.getLength()) {
            LOGGER.info("The length of the houses are equal");
        } else {
            LOGGER.info("{} less in length than {}", firstHouse.getName(), secondHouse.getName());
        }

        if (firstHouse.getWidth() > secondHouse.getWidth()) {
            LOGGER.info("{} wider than {}", firstHouse.getName(), secondHouse.getName());
        } else if (firstHouse.getWidth() == secondHouse.getWidth()) {
            LOGGER.info("The width of the houses are equal");
        } else {
            LOGGER.info("{} less in width than {}", firstHouse.getName(), secondHouse.getName());
        }

        if (firstHouse.getHeight() > secondHouse.getHeight()) {
            LOGGER.info("{} higher than {}", firstHouse.getName(), secondHouse.getName());
        } else if (firstHouse.getHeight() == secondHouse.getHeight()) {
            LOGGER.info("The height of the houses are equal");
        } else {
            LOGGER.info("{} lower than {}", firstHouse.getName(), secondHouse.getName());
        }

        if (houseService.numberOfFloors(firstHouse, firstHouse.getFloor(0).getFloorHeight())
                > houseService.numberOfFloors(secondHouse, secondHouse.getFloor(0).getFloorHeight())) {
            LOGGER.info("The number of floors of the {} is more than that of the {}", firstHouse.getName(), secondHouse.getName());
        } else if (houseService.numberOfFloors(firstHouse, firstHouse.getFloor(0).getFloorHeight())
                == houseService.numberOfFloors(secondHouse, secondHouse.getFloor(0).getFloorHeight())) {
            LOGGER.info("The number of floors in the houses are equal");
        } else {
            LOGGER.info("The number of floors of the {} is less than that of the {}", firstHouse.getName(), secondHouse.getName());
        }
    }
}
