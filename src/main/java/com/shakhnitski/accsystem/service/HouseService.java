package com.shakhnitski.accsystem.service;

import com.shakhnitski.accsystem.entity.Apartment;
import com.shakhnitski.accsystem.entity.House;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HouseService {
    public static Logger logger = LogManager.getLogger();

    private static HouseService instance;

    private HouseService() {}

    public static HouseService getInstance() {
        if (instance == null) {
            instance = new HouseService();
        }
        return instance;
    }

    public int compareByPopulation(House house1, House house2) {
        int result = Integer.compare(calculatePopulation(house1), calculatePopulation(house2));
        logger.info("Result of comparison houses by population is {}", result);
        return result;
    }

    public int compareByFloors(House house1, House house2) {
        int result = Integer.compare(calculateNumberOfFloors(house1), calculateNumberOfFloors(house2));
        logger.info("Result of comparison houses by floors is {}", result);
        return result;
    }

    public int compareByFullSquare(House house1, House house2) {
        int result = Float.compare(calculateFullSquare(house1), calculateFullSquare(house2));
        logger.info("Result of comparison houses by full square is {}", result);
        return result;
    }

    public int compareByApartmentsNumber(House house1, House house2) {
        int result = Integer.compare(house1.getApartments().size(), house2.getApartments().size());
        logger.info("Result of comparison houses by apartments number is {}", result);
        return result;
    }

    public int compareByNumber(House house1, House house2) {
        int result = Integer.compare(house1.getNumber(), house2.getNumber());
        logger.info("Result of comparison houses by number is {}", result);
        return result;
    }

    public float calculateFullSquare(House house) {
        float square = 0.0f;
        for (Apartment apartment : house.getApartments()) {
            square += apartment.getSquare();
        }
        logger.info("Result of calculating full square of house is {}", square);
        return square;
    }

    public int calculatePopulation(House house) {
        int population = house.getApartments().parallelStream().mapToInt(Apartment::getResidentsNumber).sum();
        logger.info("Result of calculating population of house is {}", population);
        return population;
    }

    public int calculateNumberOfFloors(House house) {
        int floors = house.getApartments().isEmpty() ? 0 : house.getApartments().last().getFloor();
        logger.info("Result of calculating floors in house is {}", floors);
        return floors;
    }

    public int calculateNumberOfApartments(House house) {
        int apartments = house.getApartments().size();
        logger.info("Result of calculating apartments in house is {}", apartments);
        return apartments;
    }
}
