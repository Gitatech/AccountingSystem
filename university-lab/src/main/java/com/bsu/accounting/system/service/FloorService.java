package com.bsu.accounting.system.service;

import com.bsu.accounting.system.model.Floor;

import java.util.Scanner;

public class FloorService {

    private static final String HEIGHT_OF_THE_FLOOR_MSG = "Set the height(m) of the floor: ";

    Scanner scanner = new Scanner(System.in);

    public Floor createFloor() {
        System.out.print(HEIGHT_OF_THE_FLOOR_MSG);
        double height = scanner.nextDouble();
        System.out.println();
        return new Floor(height);
    }


}
