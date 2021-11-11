package services;

import entities.Apartment;

public class ApartmentService {
    public static int compareByFloor(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.getFloor(), apartment2.getFloor());
    }

    public static int compareBySquare(Apartment apartment1, Apartment apartment2) {
        return Float.compare(apartment1.getSquare(), apartment2.getSquare());
    }

    public static int compareByResidents(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.getResidentsNumber(), apartment2.getResidentsNumber());
    }

    public static int compareByNumberOfRooms(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.getRoomsNumber(), apartment2.getRoomsNumber());
    }
}
