package com.bsu.accounting.system.service;

import com.bsu.accounting.system.repository.ApartmentRepository;
import com.bsu.accounting.system.repository.ApartmentRepositoryImpl;
import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.Floor;
import com.bsu.accounting.system.model.House;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HouseService {

    private static final Logger LOGGER = LogManager.getLogger(HouseService.class);

    private static final String THE_PROBLEM_IN_CREATING_A_HOME =
            "The height(m) of the house must be at least equal to %s metres to create a house";
    private static final String NUMBER_OF_RESIDENTS_IN_THE_HOUSE = "Number of residents in the house: %s";
    private static final String APARTMENTS = "Apartments in the \"%s\":%n";
    private static final String NUMBER_OF_FLOORS_IN_THE_HOUSE = "Number of residential floors in the house: %s";
    private static final String HOUSE_AREA = "Total living house area: %.2f";
    private static final String AVAILABLE_HOUSE_AREA = "Available house area: %.2f \n";

    private static final double PERCENTAGE_OF_NON_RESIDENTIAL_AREA = 0.9;

    private static final String PATH_TO_FILE = "AccountingSystem/university-lab/src/main/resources/results.txt";
    private static final String FLOOR_OB = "AccountingSystem/university-lab/src/main/resources/floor.ob";

    private HouseService() {

    }

    private static class SingletonHolder {
        private static final HouseService HOUSE_SERVICE = new HouseService();
    }

    public static HouseService getInstance() {
        return SingletonHolder.HOUSE_SERVICE;
    }

    public int numberOfFloors(House house, double floorHeight) {
        int amount = (int) (house.getHeight() / floorHeight);

        if (amount == 0) {
            throw new ArithmeticException(String.format(THE_PROBLEM_IN_CREATING_A_HOME, house.getHeight()));
        }

        keepOnFile(String.valueOf(amount));
        return amount;
    }

    public int numberOfResidents(House house) {
        int residents = 0;
        for (int i = 0; i < house.getFloor(0).getApartments().size(); i++) {
            residents += house.getFloor(0).getApartments().get(i).getNumberOfResidents();
        }

        final int numberOfResidents = residents * numberOfFloors(house, house.getFloor(0).getFloorHeight());

        keepOnFile(String.valueOf(numberOfResidents));

        return numberOfResidents;
    }

    public House fillTheFloors(House house, Floor currentFloor, long floorId) {
        ApartmentRepository apartmentRepository = new ApartmentRepositoryImpl();
        List<Apartment> apartmentList = new ArrayList<>();

        Floor floor = new Floor();

        floor = deepCloning(currentFloor, floor);
        floor = floor.withId(floorId);

        for (int i = 1; i <= floor.getApartments().size(); i++) {
            if (currentFloor.getApartment(i - 1).getId() == null) {
                Apartment apartment = apartmentRepository.create(i, floor.getApartment(i - 1));
                apartmentList.add(apartment);
            } else {
                Apartment apartment = apartmentRepository.create(
                        i + currentFloor.getApartment(floor.getApartments().size() - 1).getId(),
                        floor.getApartment(i - 1)
                );
                apartmentList.add(apartment);
            }
        }

        floor.setApartments(apartmentList);
        house.setFloors(floor);

        return house;
    }

    public double totalHouseArea(House house) {
        double area = 0;
        ApartmentService apartmentService = ApartmentService.getInstance();
        for (int i = 0; i < house.getFloor(0).getApartments().size(); i++) {
            area += apartmentService.getTotalApartmentArea(house.getFloor(0).getApartments().get(i));
        }
        keepOnFile(String.valueOf(area));
        return area;
    }

    public double livingArea(House house) {
        return house.getLength() * house.getWidth() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA;
    }

    public double availableArea(double currentArea, double length, double width) {
        double area = currentArea - length * width * PERCENTAGE_OF_NON_RESIDENTIAL_AREA;
        System.out.printf(AVAILABLE_HOUSE_AREA, area);
        return area;
    }

    public void viewAllApartments(House house) {
        System.out.printf(APARTMENTS, house.getName());
        for (int i = 0; i < numberOfFloors(house, house.getFloor(0).getFloorHeight()); i++) {
            LOGGER.info(house.getFloor(i).getFloorId() + " " + house.getFloor(0) + ": " + house.getFloor(i).getApartments());
        }
    }

    public void viewHouse(House house) {
        System.out.println(house);
        viewAllApartments(house);
        System.out.printf(NUMBER_OF_RESIDENTS_IN_THE_HOUSE + "%n", numberOfResidents(house));
        System.out.printf(NUMBER_OF_FLOORS_IN_THE_HOUSE + "%n", numberOfFloors(house, house.getFloor(0).getFloorHeight()));
        System.out.printf(HOUSE_AREA + "%n", totalHouseArea(house));
    }

    private void keepOnFile(String numberOfResidents) {
        try {
            FileWriter outputStream = new FileWriter(PATH_TO_FILE, true);
            outputStream.write(numberOfResidents + '\n');
            outputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private Floor deepCloning(Floor currentFloor, Floor floor) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FLOOR_OB, false))) {
            LOGGER.info("serialization start");
            outputStream.writeObject(currentFloor);
        } catch (IOException e) {
            LOGGER.error("Object output error: {}", e.getMessage());
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(FLOOR_OB)))) {
            LOGGER.info("deserialization start");
            floor = (Floor) inputStream.readObject();
            LOGGER.info("{} {}", floor, floor.getApartments());
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error("Object input error: {}", e.getMessage());
        }
        return floor;
    }
}