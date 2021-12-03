package com.bsu.accounting.system.factory;

import com.bsu.accounting.system.builder.ApartmentBuilderImpl;
import com.bsu.accounting.system.builder.Director;
import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.ApartmentType;

public class ApartmentFactory {

    private static final String APARTMENT_TYPE_NOT_NULL = "Apartment type can't be a null value";

    public Apartment createApartment(ApartmentType type) {
        if (type == null) {
            throw new IllegalArgumentException(APARTMENT_TYPE_NOT_NULL);
        }

        Apartment currentApartment = null;
        Director director = new Director();
        ApartmentBuilderImpl builder = new ApartmentBuilderImpl();

        switch (type) {
            case ONE_ROOM_APARTMENT:
                Apartment apartment = getApartment(director, builder);
                apartment.type = ApartmentType.ONE_ROOM_APARTMENT;
                currentApartment = apartment;
                break;
            case TWO_ROOM_APARTMENT:
                apartment = getApartment(director, builder);
                apartment.type = ApartmentType.TWO_ROOM_APARTMENT;
                currentApartment = apartment;
                break;
            case THREE_ROOM_APARTMENT:
                apartment = getApartment(director, builder);
                apartment.type = ApartmentType.THREE_ROOM_APARTMENT;
                currentApartment = apartment;
                break;
            case FOUR_ROOM_APARTMENT:
                apartment = getApartment(director, builder);
                apartment.type = ApartmentType.FOUR_ROOM_APARTMENT;
                currentApartment = apartment;
                break;
        }

        return currentApartment;
    }

    private Apartment getApartment(Director director, ApartmentBuilderImpl builder) {
        Apartment tmp;
        director.constructArbitraryApartment(builder);
        tmp = builder.getResult();
        return tmp;
    }
}
