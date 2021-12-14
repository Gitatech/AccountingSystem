package com.shakhnitski.accsystem.validator;

import com.shakhnitski.accsystem.entity.House;

public class HouseValidator {

    public boolean validate(House house) {
        return house.getNumber() > 0;
    }
}
