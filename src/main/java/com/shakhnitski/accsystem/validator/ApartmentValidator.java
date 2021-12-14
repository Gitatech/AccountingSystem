package com.shakhnitski.accsystem.validator;

import com.shakhnitski.accsystem.entity.Apartment;

public class ApartmentValidator {

    public boolean validate(Apartment apartment) {
        return apartment.getFloor() > 0 && apartment.getNumber() > 0 && apartment.getRoomsNumber() > 0
                && apartment.getSquare() > 0.0 && apartment.getResidentsNumber() >= 0;
    }
}
