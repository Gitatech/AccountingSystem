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
import com.bsu.accounting.system.validation.HouseValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Application {

    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    private static final String WRONG_MSG = "Something wrong...Try again";
    private static final String APARTMENT_TYPE_MENU = "1 - create a %s\n" +
            "2 - create a %s\n" +
            "3 - create a %s\n" +
            "4 - create a %s\n%n";
    private static final double PERCENTAGE_OF_NON_RESIDENTIAL_AREA = 0.9;
    private static final String AVAILABLE_HOUSE_AREA = "Available area: %.2f length(m) and %s width(m)\n\n";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Director director = new Director();
        HouseBuilderImpl builder = new HouseBuilderImpl();
        HouseService houseService = new HouseService();
        FloorService floorService = new FloorService();
        ApartmentFactory apartmentFactory = new ApartmentFactory();
        HouseValidator houseValidator = new HouseValidator();

        LOGGER.info("Enter the parameters of the house:");

        director.constructArbitraryHouse(builder);
        House arbitraryHouse = builder.getResult();

        Floor floor = floorService.createFloor(arbitraryHouse);
        int amountOfFloors = houseService.numberOfFloors(arbitraryHouse, floor.getFloorHeight());

        double tempLength = 0.0;
        double tempWidth = 0.0;

        while (tempWidth <= arbitraryHouse.getWidth() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA
                && tempLength < arbitraryHouse.getLength() * PERCENTAGE_OF_NON_RESIDENTIAL_AREA) {

           houseValidator.availableArea(arbitraryHouse, tempLength);

            ApartmentType type = getApartmentType(scanner);
            Apartment apartment = apartmentFactory.createApartment(type);

            if (houseValidator.checkApartmentWidthValue(arbitraryHouse, apartment)) {
                LOGGER.error("The width of the apartment exceeds the available width of the house");
                break;
            }
            if (houseValidator.checkApartmentLengthValue(arbitraryHouse, tempLength, apartment)) {
                LOGGER.error("The length of the apartment exceeds the available length of the house");
                break;
            }

            tempLength += apartment.getTotalApartmentLength();
            tempWidth = apartment.getTotalApartmentWidth();

            if (tempLength > arbitraryHouse.getLength() || tempWidth > arbitraryHouse.getWidth()) break;

            floorService.addApartment(floor, apartment);
            System.out.println();
        }

        for (int i = 1; i <= amountOfFloors; i++) {
            arbitraryHouse.setFloors(floor);
        }

        System.out.println("The floors has been created\n");


        houseService.viewHouse(arbitraryHouse);


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
}