package Interface;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Bilding.*;
public interface Operations {
    static void Create_new_house(@NotNull List<House> houses)
    {
        Scanner in = new Scanner(System.in);
        House house = new House();
        System.out.println("Move in the house?" +
                "\n1.Yes" +
                "\n2.No");
        int k = in.nextInt();
        switch (k)
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

    static void Compare_houses(@NotNull List<House> houses){
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

    static void Compare_flats(@NotNull List<House> houses){
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

    static void Info_house(@NotNull List<House> houses){
        Scanner in = new Scanner(System.in);
        if (houses.isEmpty()) {
            System.out.println("There is no one house");
            return;
        }
        System.out.println("What number of house:");
        int l = in.nextInt();
        if (l > houses.size()) {
            System.out.println("Too much:");
            return;
        }
        l--;
        System.out.println("House #" + ++l + " has " + houses.get(--l).getNumberOfGroundsInHouse() + " floors");
        System.out.println("House #" + ++l + " has " + houses.get(--l).getNumberOfFlatsInHouse() + " flats");
        System.out.println("Number of residents in the " + ++l + " house: " + houses.get(--l).getNumberOfMan());
        System.out.println("Area of the " + ++l + " House: " + houses.get(--l).getHouseArea());
    }

    static void Delete_house(@NotNull List<House> houses){
        Scanner in = new Scanner(System.in);
        if (houses.isEmpty()) {
            System.out.println("There is no one house");
            return;
        }
        System.out.println("-----------------------------------------------------------------------------");
        for(int i =0;i<houses.size();i++)
        {
            System.out.println("HOUSE #" + ++i);
            System.out.println("Number of residents: " +houses.get(--i).getNumberOfMan());
            System.out.println("Area: " + houses.get(i).getHouseArea());
            System.out.println("-----------------------------------------------------------------------------");
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
