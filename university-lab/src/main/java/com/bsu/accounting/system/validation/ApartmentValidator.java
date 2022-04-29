package com.bsu.accounting.system.validation;

import com.bsu.accounting.system.model.Apartment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApartmentValidator {

    private static final Logger LOGGER = LogManager.getLogger(ApartmentValidator.class);

    private static final String CHECKING_THE_VALID_VALUE_FOR_THE_FLOOR_LENGTH =
            "The total floor length of the apartment must be > 0";
    private static final String CHECKING_THE_VALID_VALUE_FOR_THE_FLOOR_WIDTH =
            "The total floor width of the apartment must be > 0";
    private static final String CHECKING_THE_VALID_VALUE_OF_RESIDENTS = "The number of residents must be >= 0";
    public static final int MINIMUM_LIVING_AREA = 25;


    public void checkWholeApartment(Apartment apartment) {
        checkNumberOfResidentsValue(apartment.getNumberOfResidents());
        checkLengthValue(apartment.getTotalApartmentLength());
        checkWidthValue(apartment.getTotalApartmentWidth());
    }

    public void checkWholeApartment(Integer id, int residents, double length, double width) {
        checkNumberOfResidentsValue(residents);
        checkLengthValue(length);
        checkWidthValue(width);
    }

    public void checkNumberOfResidentsValue(int numberOfResidents) {
        if (numberOfResidents < 0) {
            throw new IllegalArgumentException(CHECKING_THE_VALID_VALUE_OF_RESIDENTS);
        }
    }

    public void checkLengthValue(double length) {
        if (length <= 0) {
            throw new IllegalArgumentException(CHECKING_THE_VALID_VALUE_FOR_THE_FLOOR_LENGTH);
        }
    }

    public void checkWidthValue(double width) {
        if (width < 0) {
            throw new IllegalArgumentException(CHECKING_THE_VALID_VALUE_FOR_THE_FLOOR_WIDTH);
        }
    }

    public boolean checkMinimumAvailableLivingArea(Apartment apartment) {
        return apartment.getTotalApartmentLength() * apartment.getTotalApartmentWidth() > MINIMUM_LIVING_AREA;
    }

    public void checkCompareContent (Apartment firstApartment, Apartment secondApartment){
        if (firstApartment == null || secondApartment == null) {
            throw new NullPointerException("Apartment must be a not null value");
        }

        LOGGER.info("\nCompare the {}(apartment id) with {}(apartment id) in terms of parameters",
                firstApartment.getId(), secondApartment.getId());

        if (firstApartment.getTotalApartmentLength() > secondApartment.getTotalApartmentLength()) {
            LOGGER.info("Apartment {} longer than apartment {}", firstApartment.getId(), secondApartment.getId());
        } else if (firstApartment.getTotalApartmentLength() == secondApartment.getTotalApartmentLength()) {
            LOGGER.info("The length of the apartments are equal");
        } else {
            LOGGER.info("apartment {} less in length than apartment {}", firstApartment.getId(), secondApartment.getId());
        }

        if (firstApartment.getTotalApartmentWidth() > secondApartment.getTotalApartmentWidth()) {
            LOGGER.info("Apartment {} wider than apartment {}", firstApartment.getId(), secondApartment.getId());
        } else if (firstApartment.getTotalApartmentWidth() == firstApartment.getTotalApartmentWidth()) {
            LOGGER.info("The width of the apartments are equal");
        } else {
            LOGGER.info("Apartment {} less in width than apartment {}", firstApartment.getId(), secondApartment.getId());
        }

        if (firstApartment.getNumberOfResidents() > secondApartment.getNumberOfResidents()) {
            LOGGER.info("The {} apartment has more residents that the {} apartment", firstApartment.getId(), secondApartment.getId());
        } else if (firstApartment.getNumberOfResidents() == secondApartment.getNumberOfResidents()) {
            LOGGER.info("The number of people living in the apartment is the same");
        } else {
            LOGGER.info("The number of people living in the {} apartment is less than in the {} apartment", firstApartment.getId(), secondApartment.getId());
        }
    }
}
