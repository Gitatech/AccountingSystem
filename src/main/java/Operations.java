
import java.util.ArrayList;
import java.util.Scanner;
public interface Operations {
    static void Create_new_house(ArrayList<House> houses)
    {
        House house = new House();
        houses.add(house);
    }

    static void Compare_houses(ArrayList<House> houses){
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
        houses.get(--m).compare_houses(houses.get(--f));
    }

    static void Compare_flats(ArrayList<House> houses){
        Scanner in = new Scanner(System.in);
        if (houses.isEmpty()) {
            System.out.println("There is no one house");
            return;
        }
        System.out.println("Enter number of House");
        int HOUSE = in.nextInt();
        HOUSE--;
        System.out.println("Enter Number of the first flat " + "(" + houses.get(HOUSE).N_flats_in_house() + " flats in this house)");
        int NUMBER1 = in.nextInt();
        System.out.println("Enter Number of the second flat " + "(" + houses.get(HOUSE).N_flats_in_house() + " flats in this house)");
        int NUMBER2 = in.nextInt();
        if (NUMBER1 > houses.get(HOUSE).N_flats_in_house() || NUMBER2 > houses.get(HOUSE).N_flats_in_house()) {
            System.out.println("Can't compare");
            return;
        }
        houses.get(HOUSE).Compare_flats(NUMBER1, NUMBER2);
    }

    static void Info_house(ArrayList<House> houses){
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
        System.out.println("House #" + ++l + " has " + houses.get(--l).N_house_grounds() + " floors");
        System.out.println("House #" + ++l + " has " + houses.get(--l).N_flats_in_house() + " flats");
        System.out.println("Number of residents in the " + ++l + " house: " + houses.get(--l).get_N_man());
        System.out.println("Area of the " + ++l + " House: " + houses.get(--l).House_area());
    }
}
