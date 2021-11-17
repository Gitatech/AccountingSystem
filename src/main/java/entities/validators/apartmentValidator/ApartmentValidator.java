package entities.validators.apartmentValidator;

import entities.Apartment;

public class ApartmentValidator {

    public static boolean isNumberValid(int number) {
        return number > 0;
    }

    public static boolean isFloorValid(int floor) {
        return floor > 0;
    }

    public static boolean isSquareValid(float square) {
        return square > 0.0f;
    }

    public static boolean isResidentsValid(int residents) {
        return residents >= 0;
    }

    public static boolean isRoomsValid(int rooms) {
        return rooms > 0;
    }

    public static void validate(Apartment apartment) throws IllegalArgumentException {
        if (!isNumberValid(apartment.getNumber()))
            throw new IllegalArgumentException(ValidationFail.ILLEGAL_NUMBER.message);
        if (!isFloorValid(apartment.getFloor()))
            throw new IllegalArgumentException(ValidationFail.ILLEGAL_FLOOR.message);
        if (!isRoomsValid(apartment.getRoomsNumber()))
            throw new IllegalArgumentException(ValidationFail.ILLEGAL_ROOMS.message);
        if (!isResidentsValid(apartment.getResidentsNumber()))
            throw new IllegalArgumentException(ValidationFail.ILLEGAL_RESIDENTS.message);
        if (!isSquareValid(apartment.getSquare()))
            throw new IllegalArgumentException(ValidationFail.ILLEGAL_SQUARE.message);
    }
}
