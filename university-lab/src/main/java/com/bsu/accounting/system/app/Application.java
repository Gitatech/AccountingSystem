package com.bsu.accounting.system.app;

import com.bsu.accounting.system.builder.Director;
import com.bsu.accounting.system.builder.HouseBuilderImpl;
import com.bsu.accounting.system.factory.ApartmentFactory;
import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.ApartmentType;
import com.bsu.accounting.system.model.Floor;
import com.bsu.accounting.system.model.House;
import com.bsu.accounting.system.service.FloorService;
import com.bsu.accounting.system.service.HouseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Application {

    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    private static final String WRONG_MSG = "Something wrong...Try again";
    private static final String MENU = "1 - CREATE your own HOUSE\n" +
            "2 - GENERATE a random HOUSE(it's not working yet)";
    private static final String APARTMENT_TYPE_MENU = "1 - create a %s\n" +
            "2 - create a %s\n" +
            "3 - create a %s\n" +
            "4 - create a %s\n%n";
    private static final double PERCENTAGE_OF_NON_RESIDENTIAL_AREA = 0.9;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Director director = new Director();
        HouseBuilderImpl builder = new HouseBuilderImpl();
        HouseService houseService = new HouseService();
        FloorService floorService = new FloorService();
        ApartmentFactory apartmentFactory = new ApartmentFactory();

        int method = getMethod(scanner);

        switch (method) {
            case 1:
                System.out.println("\nEnter the parameters of the house:");

                director.constructArbitraryHouse(builder);
                House arbitraryHouse = builder.getResult();

                Floor floor = floorService.createFloor();
                int amountOfFloors = houseService.numberOfFloors(arbitraryHouse, floor);

                for (int i = 1; i < amountOfFloors + 1; i++) {

                    double tempLength = 0.0;
                    double tempWidth = 0.0;

                    while (tempWidth < arbitraryHouse.getWidth() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA
                            && tempLength < arbitraryHouse.getLength() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA) {

                        availableArea(arbitraryHouse, tempLength, "Available area: %.2f length(m) and %s width(m)\n\n");

                        ApartmentType type = getApartmentType(scanner);
                        Apartment apartment = apartmentFactory.createApartment(type);

                        if (checkApartmentWidthValue(arbitraryHouse, apartment)){
                            LOGGER.error("The length of the apartment exceeds the available length of the house");
                            break;
                        }
                        if (checkApartmentLengthValue(arbitraryHouse, tempLength, apartment)) {
                            LOGGER.error("The width of the apartment exceeds the available length of the house");
                            break;
                        }

                        tempLength += apartment.getTotalApartmentLength();
                        tempWidth = apartment.getTotalApartmentWidth();

                        if (tempLength > arbitraryHouse.getLength() || tempWidth > arbitraryHouse.getWidth()) break;

                        houseService.addApartment(arbitraryHouse, apartment);
                        System.out.println();
                    }

                    System.out.println("The " + i + " floor has been created\n");
                }

                houseService.viewHouse(arbitraryHouse, floor);

                break;
            case 2:

                break;
        }
    }

    private static boolean checkApartmentWidthValue(House arbitraryHouse, Apartment apartment) {
        return apartment.getTotalApartmentWidth() > arbitraryHouse.getWidth() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA;
    }

    private static boolean checkApartmentLengthValue(House arbitraryHouse, double tempLength, Apartment apartment) {
        return apartment.getTotalApartmentLength() > arbitraryHouse.getLength() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA - tempLength;
    }

    private static void availableArea(House arbitraryHouse, double tempLength, String s) {
        System.out.printf(s,
                (arbitraryHouse.getLength() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA - tempLength),
                arbitraryHouse.getWidth() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA);
    }

    private static ApartmentType getApartmentType(Scanner scanner) {
        System.out.printf(APARTMENT_TYPE_MENU, ApartmentType.ONE_ROOM_APARTMENT, ApartmentType.TWO_ROOM_APARTMENT,
                ApartmentType.THREE_ROOM_APARTMENT, ApartmentType.FOUR_ROOM_APARTMENT);

        ApartmentType type = null;

        int value = menuSelection(scanner);

        if (value == 1) {
            type = ApartmentType.ONE_ROOM_APARTMENT;
        } else if (value == 2) {
            type = ApartmentType.TWO_ROOM_APARTMENT;
        } else if (value == 3) {
            type = ApartmentType.THREE_ROOM_APARTMENT;
        } else if (value == 4) {
            type = ApartmentType.FOUR_ROOM_APARTMENT;
        }
        return type;
    }

    private static int menuSelection(Scanner scanner) {
        int number = scanner.nextInt();
        while (number != 1 && number != 2 && number != 3 && number != 4) {
            LOGGER.error(WRONG_MSG);
            number = scanner.nextInt();
        }
        return number;
    }

    private static int getMethod(Scanner scanner) {
        System.out.println("Choose a method:");
        System.out.println(MENU);
        return menuSelection(scanner);
    }

}