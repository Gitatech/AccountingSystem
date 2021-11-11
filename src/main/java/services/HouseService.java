package services;

import entities.Apartment;
import entities.House;

public class HouseService {
    public static int compareByPopulation(House house1, House house2) {
        return Integer.compare(calculatePopulation(house1), calculatePopulation(house2));
    }

    public static int compareByFloors(House house1, House house2) {
        return Integer.compare(calculateNumberOfFloors(house1), calculateNumberOfFloors(house2));
    }

    public static int compareByFullSquare(House house1, House house2) {
        return Float.compare(calculateFullSquare(house1), calculateFullSquare(house2));
    }

    public static int compareByApartmentsNumber(House house1, House house2) {
        return Integer.compare(house1.getApartments().size(), house2.getApartments().size());
    }

    public static float calculateFullSquare(House house) {
        float square = 0.0f;
        for (Apartment apartment : house.getApartments()) {
            square += apartment.getSquare();
        }
        return square;
    }

    public static int calculatePopulation(House house) {
        return house.getApartments().parallelStream().mapToInt(Apartment::getResidentsNumber).sum();
    }

    public static int calculateNumberOfFloors(House house) {
        return house.getApartments().isEmpty() ? 0 : house.getApartments().last().getFloor();
    }
}
