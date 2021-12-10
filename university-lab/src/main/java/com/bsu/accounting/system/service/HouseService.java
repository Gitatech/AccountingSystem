package com.bsu.accounting.system.service;

import com.bsu.accounting.system.model.House;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;

public class HouseService implements Comparator<House> {

    private static final Logger LOGGER = LogManager.getLogger(HouseService.class);

    private static final String THE_PROBLEM_IN_CREATING_A_HOME =
            "The height(m) of the house must be at least equal to %s metres to create a house";
    private static final String NUMBER_OF_RESIDENTS_IN_THE_HOUSE = "Number of residents in the house: %s";
    private static final String APARTMENTS = "Apartments in the \"%s\":%n";
    private static final String NUMBER_OF_FLOORS_IN_THE_HOUSE = "Number of residential floors in the house: %s";
    private static final String HOUSE_AREA = "Total living house area: %.2f";


    public int numberOfFloors(House house, double floorHeight) {
        int amount;
        amount = (int) (house.getHeight() / floorHeight);
        if (amount == 0) {
            throw new ArithmeticException(String.format(THE_PROBLEM_IN_CREATING_A_HOME, house.getOneFloor().getFloorHeight()));
        }
        return amount;
    }

    public int numberOfResidents(House house) {
        int residents = 0;
        for (int i = 0; i < house.getOneFloor().getApartments().size(); i++) {
            residents += house.getOneFloor().getApartments().get(i).getNumberOfResidents();
        }
        return residents * numberOfFloors(house, house.getOneFloor().getFloorHeight());
    }

    public double totalHouseArea(House house) {
        double area = 0;
        ApartmentService apartmentService = new ApartmentService();
        for (int i = 0; i < house.getOneFloor().getApartments().size(); i++) {
            area += apartmentService.getTotalApartmentArea(house.getOneFloor().getApartments().get(i));
        }
        return area;
    }

    public void viewAllApartments(House house) {
        System.out.printf(APARTMENTS, house.getName());
        for (int i = 0; i < numberOfFloors(house, house.getOneFloor().getFloorHeight()); i++) {
            LOGGER.info(i + 1 + " " + house.getOneFloor() + ": " + house.getOneFloor().getApartments());
        }
    }

    public void viewHouse(House house) {
        System.out.println(house);
        viewAllApartments(house);
        System.out.printf(NUMBER_OF_RESIDENTS_IN_THE_HOUSE + "%n", numberOfResidents(house));
        System.out.printf(NUMBER_OF_FLOORS_IN_THE_HOUSE + "%n", numberOfFloors(house, house.getOneFloor().getFloorHeight()));
        System.out.printf(HOUSE_AREA + "%n", totalHouseArea(house));
    }

    @Override
    public int compare(House firstHouse, House secondHouse) {
        LOGGER.info("\nCompare the {}s(house) with {}s(house) in terms of parameters", firstHouse.getName(), secondHouse.getName());

        if (firstHouse.getLength() > secondHouse.getLength()) {
            LOGGER.info("{}s longer than {}s", firstHouse.getName(), secondHouse.getName());
        } else if (firstHouse.getLength() == secondHouse.getLength()) {
            LOGGER.info("The length of the houses are equal");
        } else {
            LOGGER.info("{}s less in length than {}s", firstHouse.getName(), secondHouse.getName());
        }

        if (firstHouse.getWidth() > secondHouse.getWidth()) {
            LOGGER.info("{}s wider than {}s", firstHouse.getName(), secondHouse.getName());
        } else if (firstHouse.getWidth() == secondHouse.getWidth()) {
            LOGGER.info("The width of the houses are equal");
        } else {
            LOGGER.info("{}s less in width than {}s", firstHouse.getName(), secondHouse.getName());
        }

        if (firstHouse.getHeight() > secondHouse.getHeight()) {
            LOGGER.info("{}s higher than {}s", firstHouse.getName(), secondHouse.getName());
        } else if (firstHouse.getHeight() == secondHouse.getHeight()) {
            LOGGER.info("The height of the houses are equal");
        } else {
            LOGGER.info("{}s lower than {}s", firstHouse.getName(), secondHouse.getName());
        }

        if (this.numberOfFloors(firstHouse, firstHouse.getOneFloor().getFloorHeight())
                > this.numberOfFloors(secondHouse, secondHouse.getOneFloor().getFloorHeight())) {
            LOGGER.info("The number of floors of the {}s is more than that of the {}s", firstHouse.getName(), secondHouse.getName());
        } else if (this.numberOfFloors(firstHouse, firstHouse.getOneFloor().getFloorHeight())
                == this.numberOfFloors(secondHouse, secondHouse.getOneFloor().getFloorHeight())) {
            LOGGER.info("The number of floors in the houses are equal");
        } else {
            LOGGER.info("The number of floors of the {}s is less than that of the {}s", firstHouse.getName(), secondHouse.getName());
        }

        return Integer.parseInt(String.valueOf(true));
    }
}