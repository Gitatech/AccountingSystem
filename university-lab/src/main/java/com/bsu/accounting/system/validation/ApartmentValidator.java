package com.bsu.accounting.system.validation;

import com.bsu.accounting.system.model.Apartment;

public class ApartmentValidator {

    private static final String CHECKING_THE_VALID_VALUE_FOR_THE_FLOOR_LENGTH =
            "The total floor length of the apartment must be > 0";
    private static final String CHECKING_THE_VALID_VALUE_FOR_THE_FLOOR_WIDTH =
            "The total floor width of the apartment must be > 0";
    private static final String CHECKING_THE_VALID_VALUE_OF_RESIDENTS = "The number of residents must be >= 0";
    private static final String ILLEGAL_ID = "id must be > 0";

    public void checkWholeApartment(Apartment apartment) {
        checkIdValue(apartment.getId());
        checkNumberOfResidentsValue(apartment.getNumberOfResidents());
        checkLengthValue(apartment.getTotalApartmentLength());
        checkWidthValue(apartment.getTotalApartmentWidth());
    }

    public void checkWholeApartment(int id, int residents, double length, double width) {
        checkIdValue(id);
        checkNumberOfResidentsValue(residents);
        checkLengthValue(length);
        checkWidthValue(width);
    }

    public void checkIdValue(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(ILLEGAL_ID);
        }
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
}
