package com.university.lab.service;

import com.university.lab.app.Application;
import com.university.lab.model.Floor;
import com.university.lab.model.House;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HouseService {

    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    private static final String THE_PROBLEM_IN_CREATING_A_HOME =
            "The height of the house must be at least equal to %s metres to create a house";
    private static final String NUMBER_OF_RESIDENTS_IN_THE_HOUSE = "Number of residents in the house: %s";
    private static final String APARTMENTS = "Apartments in the \"%s\":%n";
    private static final String NUMBER_OF_FLOORS_IN_THE_HOUSE = "Number of floors in the house: %s";

    public int numberOfFloors(House house, Floor floor) {
        int amount;
        amount = (int) (house.getHeight() / floor.getFloorHeight());
        if (amount == 0) {
            throw new ArithmeticException(String.format(THE_PROBLEM_IN_CREATING_A_HOME, floor.getFloorHeight()));
        }
        return amount;
    }

    public int numberOfResidents(House house) {
        int residents = 0;
        for (int i = 0; i < house.apartments.size(); i++) {
            residents += house.apartments.get(i).getNumberOfResidents();
        }
        return residents;
    }

    public void viewAllApartments(House house) {
        System.out.printf(APARTMENTS, house.getName());
        for (int i = 0; i < house.apartments.size(); i++) {
            LOGGER.info(house.apartments.get(i));
        }
    }

    public void viewHouse(House house, Floor floor) {
        System.out.println(house);
        viewAllApartments(house);
        System.out.printf((NUMBER_OF_RESIDENTS_IN_THE_HOUSE) + "%n", numberOfResidents(house));
        System.out.printf(NUMBER_OF_FLOORS_IN_THE_HOUSE, numberOfFloors(house, floor));
    }

}