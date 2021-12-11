package com.prokopchyk.userInterface;

import com.prokopchyk.building.House;
import com.prokopchyk.dao.HouseDao;
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
        HouseDao houseDao = new HouseDao();
        String listOfHouses = "src\\main\\resources\\houseList.txt";
        System.out.println(MENU);
        if (houseDao.getAll().isEmpty() && new File(listOfHouses).length() != 0) {
            HouseListService.getHouseListService().readHouseList(houseDao.getAll(), listOfHouses);
        }
        int k = in.nextInt();
        while (k != 6) {
            switch (k) {
                case (1):
                    houseDao.save(Operations.сreateNewHouse());
                    HouseListService.getHouseListService().writeHouseList(houseDao.getAll(),listOfHouses);
                    break;
                case (2):
                    Operations.compareHouses(houseDao);
                    break;
                case (3):
                    Operations.compareFlats(houseDao.getAll());
                    break;
                case (4):
                    Operations.getHouseInfo(houseDao.getAll());
                    break;
                case (5):
                    Operations.removeHouse(houseDao);
                    HouseListService.getHouseListService().writeHouseList(houseDao.getAll(), listOfHouses);
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
