package com.shakhnitski.accsystem.service;

import com.shakhnitski.accsystem.entity.Apartment;
import com.shakhnitski.accsystem.entity.House;

public class HouseService {
    private static HouseService instance;

    private HouseService() {}

    public static HouseService getInstance() {
        if (instance == null) {
            instance = new HouseService();
        }
        return instance;
    }

    public int compareByPopulation(House house1, House house2) {
        return Integer.compare(calculatePopulation(house1), calculatePopulation(house2));
    }

    public int compareByFloors(House house1, House house2) {
        return Integer.compare(calculateNumberOfFloors(house1), calculateNumberOfFloors(house2));
    }

    public int compareByFullSquare(House house1, House house2) {
        return Float.compare(calculateFullSquare(house1), calculateFullSquare(house2));
    }

    public int compareByApartmentsNumber(House house1, House house2) {
        return Integer.compare(house1.getApartments().size(), house2.getApartments().size());
    }

    public int compareByNumber(House house1, House house2) {
        return Integer.compare(house1.getNumber(), house2.getNumber());
    }

    public float calculateFullSquare(House house) {
        float square = 0.0f;
        for (Apartment apartment : house.getApartments()) {
            square += apartment.getSquare();
        }
        return square;
    }

    public int calculatePopulation(House house) {
        return house.getApartments().parallelStream().mapToInt(Apartment::getResidentsNumber).sum();
    }

    public int calculateNumberOfFloors(House house) {
        return house.getApartments().isEmpty() ? 0 : house.getApartments().last().getFloor();
    }

    public int calculateNumberOfApartments(House house) {
        return house.getApartments().size();
    }
}
