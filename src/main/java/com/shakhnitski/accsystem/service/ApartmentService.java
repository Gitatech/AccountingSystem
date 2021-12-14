package com.shakhnitski.accsystem.service;

import com.shakhnitski.accsystem.entity.Apartment;

public class ApartmentService {
    private static ApartmentService instance;

    private ApartmentService() {}

    public static ApartmentService getInstance() {
        if (instance == null) {
            instance = new ApartmentService();
        }
        return instance;
    }

    public int compareByNumber(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.getNumber(), apartment2.getNumber());
    }

    public int compareByFloor(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.getFloor(), apartment2.getFloor());
    }

    public int compareBySquare(Apartment apartment1, Apartment apartment2) {
        return Float.compare(apartment1.getSquare(), apartment2.getSquare());
    }

    public int compareByResidents(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.getResidentsNumber(), apartment2.getResidentsNumber());
    }

    public int compareByNumberOfRooms(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.getRoomsNumber(), apartment2.getRoomsNumber());
    }
}
