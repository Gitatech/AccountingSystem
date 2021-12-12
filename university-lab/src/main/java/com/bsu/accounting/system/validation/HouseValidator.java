package com.bsu.accounting.system.validation;

import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.House;

public class HouseValidator {

    private static final double PERCENTAGE_OF_NON_RESIDENTIAL_AREA = 0.9;
    private static final String AVAILABLE_HOUSE_AREA = "Available area: %.2f length(m) and %s width(m)\n\n";

    public boolean checkApartmentWidthValue(House arbitraryHouse, Apartment apartment) {
        return apartment.getTotalApartmentWidth() > arbitraryHouse.getWidth() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA;
    }

    public boolean checkApartmentLengthValue(House arbitraryHouse, double tempLength, Apartment apartment) {
        return apartment.getTotalApartmentLength() > arbitraryHouse.getLength() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA - tempLength;
    }

    public void availableArea(House arbitraryHouse, double tempLength) {
        System.out.printf(AVAILABLE_HOUSE_AREA,
                (arbitraryHouse.getLength() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA - tempLength),
                arbitraryHouse.getWidth() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA);
    }
}
