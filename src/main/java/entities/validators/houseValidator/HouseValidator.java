package entities.validators.houseValidator;

import entities.House;

public class HouseValidator {

    public static boolean isNumberValid(int number) {
        return number > 0;
    }

    public static void validate(House house) {
        if (!isNumberValid(house.getNumber()))
            throw new IllegalArgumentException(ValidationFail.ILLEGAL_NUMBER.message);
    }
}
