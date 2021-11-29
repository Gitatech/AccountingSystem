package com.prokopchyk.userInterface;

import com.prokopchyk.building.House;
import com.prokopchyk.service.HouseListService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface implements Operations {
    public static final String MENU = """
            Input value 1 to 6
            1.Create new house 
            2.Compare houses
            3.Compare flats
            4.Al information about house
            5.Remove house
            6.Exit
            """;
    public void begin() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        HouseListService houseList = new HouseListService();
        List<House> houses = new ArrayList<House>();
        String listOfHouses = "src\\main\\resources\\houseList.txt";
        System.out.println(MENU);
        if (houses.isEmpty() && new File(listOfHouses).length() != 0) {
            houseList.readHouseList(houses, listOfHouses);
        }
        int k = in.nextInt();
        while (k != 6) {
            switch (k) {
                case (1):
                    Operations.сreateNewHouse(houses);
                    houseList.writeHouseList(houses,listOfHouses);
                    break;
                case (2):
                    Operations.compareHouses(houses);
                    break;
                case (3):
                    Operations.compareFlats(houses);
                    break;
                case (4):
                    Operations.getHouseInfo(houses);
                    break;
                case (5):
                    Operations.removeHouse(houses);
                    houseList.writeHouseList(houses, listOfHouses);
                    break;
                case (6):
                    break;
                default:
                    System.out.println("Try again");
                    break;
            }
            System.out.println(MENU);
            k = in.nextInt();

        }
    }
}
