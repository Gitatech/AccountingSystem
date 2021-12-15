package com.shakhnitski.accsystem.service;

import com.shakhnitski.accsystem.entity.Apartment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApartmentService {
    public static Logger logger = LogManager.getLogger();

    private static ApartmentService instance;

    private ApartmentService() {}

    public static ApartmentService getInstance() {
        if (instance == null) {
            instance = new ApartmentService();
        }
        return instance;
    }

    public int compareByNumber(Apartment apartment1, Apartment apartment2) {
        int result = Integer.compare(apartment1.getNumber(), apartment2.getNumber());
        logger.info("Result of comparison apartments by number is {}", result);
        return result;
    }

    public int compareByFloor(Apartment apartment1, Apartment apartment2) {
        int result = Integer.compare(apartment1.getFloor(), apartment2.getFloor());
        logger.info("Result of comparison apartments by floor is {}", result);
        return result;
    }

    public int compareBySquare(Apartment apartment1, Apartment apartment2) {
        int result = Float.compare(apartment1.getSquare(), apartment2.getSquare());
        logger.info("Result of comparison apartments by square is {}", result);
        return result;
    }

    public int compareByResidents(Apartment apartment1, Apartment apartment2) {
        int result = Integer.compare(apartment1.getResidentsNumber(), apartment2.getResidentsNumber());
        logger.info("Result of comparison apartments by residents number is {}", result);
        return result;
    }

    public int compareByNumberOfRooms(Apartment apartment1, Apartment apartment2) {
        int result = Integer.compare(apartment1.getRoomsNumber(), apartment2.getRoomsNumber());
        logger.info("Result of comparison apartments by rooms number is {}", result);
        return result;
    }
}
