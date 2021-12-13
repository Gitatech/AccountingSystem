package com.bsu.accounting.system.service;

import com.bsu.accounting.system.dao.ApartmentDao;
import com.bsu.accounting.system.dao.ApartmentDaoImpl;
import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.Floor;
import com.bsu.accounting.system.model.House;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HouseService implements Comparator<House> {

    private static final Logger LOGGER = LogManager.getLogger(HouseService.class);

    private static final String THE_PROBLEM_IN_CREATING_A_HOME =
            "The height(m) of the house must be at least equal to %s metres to create a house";
    private static final String NUMBER_OF_RESIDENTS_IN_THE_HOUSE = "Number of residents in the house: %s";
    private static final String APARTMENTS = "Apartments in the \"%s\":%n";
    private static final String NUMBER_OF_FLOORS_IN_THE_HOUSE = "Number of residential floors in the house: %s";
    private static final String HOUSE_AREA = "Total living house area: %.2f";
    private static final String PATH_TO_FILE = "AccountingSystem/university-lab/src/main/resources/results.txt";

    private HouseService() {

    }

    private static class SingletonHolder {
        private static final HouseService HOUSE_SERVICE = new HouseService();
    }

    public static HouseService getInstance() {
        return SingletonHolder.HOUSE_SERVICE;
    }

    public int numberOfFloors(House house, double floorHeight) {
        int amount;
        amount = (int) (house.getHeight() / floorHeight);
        if (amount == 0) {
            throw new ArithmeticException(String.format(THE_PROBLEM_IN_CREATING_A_HOME, house.getFirstFloor().getFloorHeight()));
        }
        try {
            FileWriter outputStream = new FileWriter(PATH_TO_FILE, true);
            outputStream.write(String.valueOf(amount) + '\n');
            outputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return amount;
    }

    public int numberOfResidents(House house) {
        int residents = 0;
        for (int i = 0; i < house.getFirstFloor().getApartments().size(); i++) {
            residents += house.getFirstFloor().getApartments().get(i).getNumberOfResidents();
        }
        final int numberOfResidents = residents * numberOfFloors(house, house.getFirstFloor().getFloorHeight());

        try {
            FileWriter outputStream = new FileWriter(PATH_TO_FILE, true);
            outputStream.write(String.valueOf(numberOfResidents) + '\n');
            outputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return numberOfResidents;
    }

    public void fillTheFloors(House house, Floor currentFloor) {
        ApartmentDao apartmentDao = new ApartmentDaoImpl();
        Floor floor = currentFloor.clone();

        List<Apartment> apartmentList = new ArrayList<>();

        for (int i = 0; i < floor.getApartments().size(); i++) {
            Apartment apartment = apartmentDao.create(floor.getApartment(i));
            apartmentList.add(apartment);
        }

        floor.setApartments(apartmentList);
        house.setFloors(floor);
    }

    public double totalHouseArea(House house){
        double area = 0;
        ApartmentService apartmentService = ApartmentService.getInstance();
        for (int i = 0; i < house.getFirstFloor().getApartments().size(); i++) {
            area += apartmentService.getTotalApartmentArea(house.getFirstFloor().getApartments().get(i));
        }
        try {
            FileWriter outputStream = new FileWriter(PATH_TO_FILE, true);
            outputStream.write(String.valueOf(area) + '\n');
            outputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return area;
    }

    public void viewAllApartments(House house) {
        System.out.printf(APARTMENTS, house.getName());
        for (int i = 0; i < numberOfFloors(house, house.getFirstFloor().getFloorHeight()); i++) {
            LOGGER.info(i + 1 + " " + house.getFirstFloor() + ": " + house.getFirstFloor().getApartments());
        }
    }

    public void viewHouse(House house) {
        System.out.println(house);
        viewAllApartments(house);
        System.out.printf(NUMBER_OF_RESIDENTS_IN_THE_HOUSE + "%n", numberOfResidents(house));
        System.out.printf(NUMBER_OF_FLOORS_IN_THE_HOUSE + "%n", numberOfFloors(house, house.getFirstFloor().getFloorHeight()));
        System.out.printf(HOUSE_AREA + "%n", totalHouseArea(house));
    }

    @Override
    public int compare(House firstHouse, House secondHouse) {
        if (firstHouse == null || secondHouse == null) {
            throw new NullPointerException("House must be a not null value");
        }
        LOGGER.info("\nCompare the {}(house) with {}(house) in terms of parameters", firstHouse.getName(), secondHouse.getName());

        if (firstHouse.getLength() > secondHouse.getLength()) {
            LOGGER.info("{} longer than {}", firstHouse.getName(), secondHouse.getName());
        } else if (firstHouse.getLength() == secondHouse.getLength()) {
            LOGGER.info("The length of the houses are equal");
        } else {
            LOGGER.info("{} less in length than {}", firstHouse.getName(), secondHouse.getName());
        }

        if (firstHouse.getWidth() > secondHouse.getWidth()) {
            LOGGER.info("{} wider than {}", firstHouse.getName(), secondHouse.getName());
        } else if (firstHouse.getWidth() == secondHouse.getWidth()) {
            LOGGER.info("The width of the houses are equal");
        } else {
            LOGGER.info("{} less in width than {}", firstHouse.getName(), secondHouse.getName());
        }

        if (firstHouse.getHeight() > secondHouse.getHeight()) {
            LOGGER.info("{} higher than {}", firstHouse.getName(), secondHouse.getName());
        } else if (firstHouse.getHeight() == secondHouse.getHeight()) {
            LOGGER.info("The height of the houses are equal");
        } else {
            LOGGER.info("{} lower than {}", firstHouse.getName(), secondHouse.getName());
        }

        if (this.numberOfFloors(firstHouse, firstHouse.getFirstFloor().getFloorHeight())
                > this.numberOfFloors(secondHouse, secondHouse.getFirstFloor().getFloorHeight())) {
            LOGGER.info("The number of floors of the {} is more than that of the {}", firstHouse.getName(), secondHouse.getName());
        } else if (this.numberOfFloors(firstHouse, firstHouse.getFirstFloor().getFloorHeight())
                == this.numberOfFloors(secondHouse, secondHouse.getFirstFloor().getFloorHeight())) {
            LOGGER.info("The number of floors in the houses are equal");
        } else {
            LOGGER.info("The number of floors of the {} is less than that of the {}", firstHouse.getName(), secondHouse.getName());
        }

        return 0;
    }
}