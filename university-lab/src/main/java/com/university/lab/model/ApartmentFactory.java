package com.university.lab.model;

import com.university.lab.model.room.FourRoomApartment;
import com.university.lab.model.room.OneRoomApartment;
import com.university.lab.model.room.ThreeRoomApartment;
import com.university.lab.model.room.TwoRoomApartment;
import com.university.lab.service.ApartmentBuilder;
import com.university.lab.service.Director;

public class ApartmentFactory {

    private static final String APARTMENT_TYPE_NOT_NULL = "Apartment type can't be a null value";

    public Apartment createApartment(ApartmentType type) {
        if (type == null) {
            throw new IllegalArgumentException(APARTMENT_TYPE_NOT_NULL);
        }
        Apartment apartment = null;

        Director director = new Director();
        ApartmentBuilder builder = new ApartmentBuilder();

        switch (type) {
            case ONE_ROOM_APARTMENT:
                Apartment tmp = getApartment(director, builder);
                apartment = new OneRoomApartment(tmp);
                break;
            case TWO_ROOM_APARTMENT:
                tmp = getApartment(director, builder);
                apartment = new TwoRoomApartment(tmp);
                break;
            case THREE_ROOM_APARTMENT:
                tmp = getApartment(director, builder);
                apartment = new ThreeRoomApartment(tmp);
                break;
            case FOUR_ROOM_APARTMENT:
                tmp = getApartment(director, builder);
                apartment = new FourRoomApartment(tmp);
                break;
        }

        return apartment;
    }

    private Apartment getApartment(Director director, ApartmentBuilder builder) {
        Apartment tmp;
        director.constructArbitraryApartment(builder);
        tmp = builder.getResult();
        return tmp;
    }
}
