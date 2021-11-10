package Interface;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import Bilding.*;

public class Interface implements Operations {
    public void begin() {
        Scanner in = new Scanner(System.in);
        List<House> houses = new ArrayList<House>();
        System.out.println("What do you want to do:" +
                 "\n1.Create new house" +
                 "\n2.Compare" +
                 "\n3.Compare flats" +
                 "\n4.Al information about house" +
                 "\n5.Delete house" +
                 "\n6.Exit");
        int k = in.nextInt();
        while (k != 6) {
            switch (k) {
                case (1):
                    Operations.Create_new_house(houses);
                    break;
                case (2):
                    Operations.Compare_houses(houses);
                    break;
                case (3):
                    Operations.Compare_flats(houses);
                    break;
                case (4):
                    Operations.Info_house(houses);
                    break;
                case (5):
                    Operations.Delete_house(houses);
                    break;
                case (6):
                    break;
                default:
                    System.out.println("Try again");
                    break;
            }
            System.out.println("What you want to do:" +
                    "\n1.Create new house" +
                    "\n2.Compare" +
                    "\n3.Compare flats" +
                    "\n4.Al information about house" +
                    "\n5.Delete house" +
                    "\n6.Exit");
            k = in.nextInt();

        }
    }
}
