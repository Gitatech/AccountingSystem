package Services;

import Entities.Apartment;

public class ApartmentService {
    public static int compareByFloor(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.getFloor(), apartment2.getFloor());
    }

    public static int compareBySquare(Apartment apartment1, Apartment apartment2) {
        return Float.compare(apartment1.getSquare(), apartment2.getSquare());
    }

    public static int compareByResidentsNumber(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.getResidentsNumber(), apartment2.getResidentsNumber());
    }

    public static int compareByNumberOfRooms(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.getNumberOfRooms(), apartment2.getNumberOfRooms());
    }
}
