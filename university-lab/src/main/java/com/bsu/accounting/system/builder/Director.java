package com.bsu.accounting.system.builder;

import java.util.Scanner;

public class Director {

    Scanner scanner = new Scanner(System.in);

    public void constructArbitraryHouse(HouseBuilder builder) {
        System.out.print("Name(Street and number of the house): ");
        builder.setHouseName(scanner.nextLine(), scanner.next());
        System.out.print("House length(m): ");
        builder.setHouseLength(scanner.nextDouble());
        System.out.print("House width(m): ");
        builder.setHouseWidth(scanner.nextDouble());
        System.out.print("House height(m): ");
        builder.setHouseHeight(scanner.nextDouble());
    }

    public void constructArbitraryApartment(ApartmentBuilder builder) {
        System.out.print("Number of residents in the apartment: ");
        builder.setNumberOfResidents(scanner.nextInt());
        System.out.print("The total length of the apartment(m): ");
        builder.setTotalApartmentLength(scanner.nextDouble());
        System.out.print("The total width of the apartment(m): ");
        builder.setTotalApartmentWidth(scanner.nextDouble());
    }
}
