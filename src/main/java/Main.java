import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

    public class Main {
        public static void main(String[] args){
            Scanner in = new Scanner(System.in);
            ArrayList<House> houses = new ArrayList<House>();
            System.out.println("What do you want to do:" +
                    "\n1.Create new house" +
                    "\n2.Check number of residents" +
                    "\n3.Check area of house" +
                    "\n4.Compare" +
                    "\n5.Compare flats" +
                    "\n6.Exit");
            int k = in.nextInt();
            while(k != 6){
                switch (k) {
                    case(1):
                        House house = new House();
                        houses.add(house);
                        break;
                    case(2):
                        if(houses.isEmpty()){
                            System.out.println("There is no one house");
                            break;
                        }
                        System.out.println("What number of house:");
                        int l = in.nextInt();
                        l--;
                        if(l>houses.size())
                        {
                            System.out.println("Too much:");
                            break;
                        }
                        System.out.println("Number of residents in the " + ++l +" house: " + houses.get(--l).get_N_man());
                        break;
                    case(3):
                        if(houses.isEmpty()){
                            System.out.println("There is no one house");
                            break;
                        }
                        System.out.println("What number of house:");
                        int p = in.nextInt();
                        p--;
                        if(p>houses.size())
                        {
                            System.out.println("Too much:");
                            break;
                        }
                        System.out.println("Area of " + ++p +" House: " + houses.get(--p).House_area());
                        break;
                    case(4):
                        if(houses.isEmpty()){
                            System.out.println("There is no one house");
                            break;
                        }
                        System.out.print("What numbers of house for compare\nFirst house:");
                        int m = in.nextInt();
                        System.out.print("Second house:");
                        int f = in.nextInt();
                        if(m>houses.size() || f> houses.size()){
                            System.out.println("Too mach:");
                            break;
                        }
                        houses.get(--m).compare_houses(houses.get(--f));
                        break;
                    case(5):
                        if(houses.isEmpty()){
                            System.out.println("There is no one house");
                            break;
                        }
                        System.out.println("Enter number of House");
                        int HOUSE = in.nextInt();
                        HOUSE--;
                        System.out.println("Enter Number of the first flat");
                        int NUMBER1 = in.nextInt();
                        System.out.println("Enter Number of the second flat");
                        int NUMBER2 = in.nextInt();
                        houses.get(HOUSE).Compare_flats(NUMBER1,NUMBER2);
                        break;
                    case(6):
                        break;
                    default:
                        System.out.println("Try again");
                }
                System.out.println("What you want to do:" +
                        "\n1.Create new house" +
                        "\n2.Check number of residents" +
                        "\n3.Check area of house" +
                        "\n4.Compare" +
                        "\n5.Compare flats" +
                        "\n6.Exit");
                k = in.nextInt();

            }
        }
}
