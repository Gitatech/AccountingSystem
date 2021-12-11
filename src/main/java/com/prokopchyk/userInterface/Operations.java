package com.prokopchyk.userInterface;

import com.prokopchyk.builder.HouseBuilder;
import com.prokopchyk.building.House;
import com.prokopchyk.dao.HouseDao;
import com.prokopchyk.service.FlatService;
import com.prokopchyk.service.HouseService;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public interface Operations {
    static House сreateNewHouse() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter height of house:");
        int height = in.nextInt();
        System.out.print("Enter house name");
        String name = in.next();
        House house = new HouseBuilder()
                .setHouseName(name)
                .setNumOfGrounds(height)
                .FillingHouse()
                .builder();
        System.out.println("Move in the house?" +
                "\n1.Yes" +
                "\n2.No");
        int option = in.nextInt();
        switch (option)
        {
            case 1:
                System.out.println("Random or from the console?" +
                        "\n1.Random" +
                        "\n2.Console");
                int p = in.nextInt();
                if(p == 1){
                    HouseService.getHouseService().initPersonsRandom(house);
                }
                else if(p == 2) {
                    HouseService.getHouseService().initPersons(house);
                }
                else{
                    System.out.println("Incorrect value.Residents not listed");
                }
                break;
            case 2:
                System.out.println("Ok.Residents not listed");
                break;
            default:
                System.out.println("Incorrect value.Residents not listed");
                break;
        }
        return house;
    }

    static void compareHouses(HouseDao houseDao){
        Scanner in = new Scanner(System.in);
        if (houseDao.getAll().size() < 2) {
            System.out.println("Not enough houses to compare");
            return;
        }
        houseDao.printAllNames();
        System.out.print("What numbers of house for compare\nFirst house:");
        int m = in.nextInt();
        System.out.print("Second house:");
        int f = in.nextInt();
        if (m > houseDao.getAll().size() || f > houseDao.getAll().size()) {
            System.out.println("Too mach:");
            return;
        }
        HouseService.getHouseService().compareHouses(houseDao.getByNumber(--m),houseDao.getByNumber(--f));
    }

    static void compareFlats(@NotNull List<House> houses){
        Scanner in = new Scanner(System.in);
        if (houses.isEmpty()) {
            System.out.println("There is no one house");
            return;
        }
        System.out.println("Enter number of House");
        int houseNum = in.nextInt();
        houseNum--;
        System.out.println("Enter Number of the first flat "
                + "(" + (HouseService.getHouseService().getNumberOfFlats(houses.get(houseNum)) - 1)
                + " flats in this house)");
        int number1 = in.nextInt();
        System.out.println("Enter Number of the second flat "
                + "(" + (HouseService.getHouseService().getNumberOfFlats(houses.get(houseNum)) - 1)
                + " flats in this house)");
        int number2 = in.nextInt();
        if (number1 >= HouseService.getHouseService().getNumberOfFlats(houses.get(houseNum)) ||
            number2 >= HouseService.getHouseService().getNumberOfFlats(houses.get(houseNum))) {
            System.out.println("Can't compare");
            return;
        }
        System.out.println (FlatService.getFlatService().compareFlats
                (HouseService.getHouseService().getFlatByNumber(houses.get(houseNum),number1),
                 HouseService.getHouseService().getFlatByNumber(houses.get(houseNum),number2))
        );
    }

    static void getHouseInfo(@NotNull List<House> houses){
        Scanner in = new Scanner(System.in);
        if (houses.isEmpty()) {
            System.out.println("There is no one house");
            return;
        }
        for(int i =0;i<houses.size();i++)
        {
            System.out.println("HOUSE #" + ++i + ": " + houses.get(--i).getHouseName());
        }
        System.out.println("What number of house:");
        int l = in.nextInt();
        if (l > houses.size()||l < 1) {
            System.out.println("Incorrect value :");
            return;
        }
        l--;
        System.out.println("House #" + ++l + " has " + houses.get(--l).getNumberOfGrounds() + " floors");
        System.out.println("House #" + ++l + " has " + HouseService.getHouseService().getNumberOfFlats(houses.get(--l)) + " flats");
        System.out.println("Number of residents in the " + ++l + " house: " + HouseService.getHouseService().getNumberOfHuman(houses.get(--l)));
        System.out.println("Area of the " + ++l + " House: " + HouseService.getHouseService().getHouseArea(houses.get(--l)));
    }

    static void removeHouse(HouseDao houseDao){
        Scanner in = new Scanner(System.in);
        if (houseDao.getAll().isEmpty()) {
            System.out.println("There is no one house");
            return;
        }
        System.out.println("-----------------------------------------------------------------------------");
        houseDao.printAllNames();
        System.out.println("House number you want to delete(Press 0 to exit):");
        int del = in.nextInt();
        del--;
        while((del <-1) || (del >= houseDao.getAll().size())){
            System.out.println("Incorrect value.Try again");
            System.out.println("House number you want to delete(Press 0 to exit):");
            del = in.nextInt();
            del--;
        }
        if(del == -1){
            return;
        }
        houseDao.delete(houseDao.getByNumber(del));

    }
}
