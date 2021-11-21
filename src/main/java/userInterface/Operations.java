package userInterface;

import builder.HouseBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import building.*;

import javax.imageio.IIOException;

public interface Operations {
    static void сreateNewHouse(@NotNull List<House> houses) throws FileNotFoundException {
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
                    house.initPersonsRandom();
                }
                else if(p == 2) {
                    house.initPersons();
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
        houses.add(house);
    }

    static void compareHouses(@NotNull List<House> houses){
        Scanner in = new Scanner(System.in);
        if (houses.size() < 2) {
            System.out.println("Not enough houses to compare");
            return;
        }
        System.out.print("What numbers of house for compare\nFirst house:");
        int m = in.nextInt();
        System.out.print("Second house:");
        int f = in.nextInt();
        if (m > houses.size() || f > houses.size()) {
            System.out.println("Too mach:");
            return;
        }
        houses.get(--m).compareHouses(houses.get(--f));
    }

    static void compareFlats(@NotNull List<House> houses){
        Scanner in = new Scanner(System.in);
        if (houses.isEmpty()) {
            System.out.println("There is no one house");
            return;
        }
        System.out.println("Enter number of House");
        int HOUSE = in.nextInt();
        HOUSE--;
        System.out.println("Enter Number of the first flat " + "(" + (houses.get(HOUSE).getNumberOfFlatsInHouse()-1) + " flats in this house)");
        int NUMBER1 = in.nextInt();
        System.out.println("Enter Number of the second flat " + "(" + (houses.get(HOUSE).getNumberOfFlatsInHouse()-1) + " flats in this house)");
        int NUMBER2 = in.nextInt();
        if (NUMBER1 >= houses.get(HOUSE).getNumberOfFlatsInHouse() || NUMBER2 >= houses.get(HOUSE).getNumberOfFlatsInHouse()) {
            System.out.println("Can't compare");
            return;
        }
        houses.get(HOUSE).compareFlats(NUMBER1, NUMBER2);
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
        System.out.println("House #" + ++l + " has " + houses.get(--l).getNumberOfGroundsInHouse() + " floors");
        System.out.println("House #" + ++l + " has " + houses.get(--l).getNumberOfFlatsInHouse() + " flats");
        System.out.println("Number of residents in the " + ++l + " house: " + houses.get(--l).getNumberOfHuman());
        System.out.println("Area of the " + ++l + " House: " + houses.get(--l).getHouseArea());
    }

    static void removeHouse(@NotNull List<House> houses){
        Scanner in = new Scanner(System.in);
        if (houses.isEmpty()) {
            System.out.println("There is no one house");
            return;
        }
        System.out.println("-----------------------------------------------------------------------------");
        for(int i =0;i<houses.size();i++)
        {
            System.out.println("HOUSE #" + ++i + ": " + houses.get(--i).getHouseName());
        }
        System.out.println("House number you want to delete(Press 0 to exit):");
        int del = in.nextInt();
        del--;
        while((del <-1) || (del >= houses.size())){
            System.out.println("Incorrect value.Try again");
            System.out.println("House number you want to delete(Press 0 to exit):");
            del = in.nextInt();
            del--;
        }
        if(del == -1){
            return;
        }
        houses.remove(del);

    }
}
