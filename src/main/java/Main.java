
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

    public class Main {

        public static  void compare_houses(House house1,House house2)
        {
            if(house1.House_area() > house2.House_area()){
                System.out.println("House 1 bigger with area "+ house1.House_area());
            }
            else  if(house1.House_area() < house2.House_area()){
                System.out.println("House 2 bigger with area "+ house2.House_area());
            }
            else{
                System.out.println("Areas are the same: " +house2.House_area());
            }


            if(house1.get_N_man() > house2.get_N_man()){
                System.out.println("There are more people in the first house: "+ house1.get_N_man() +" vs "+house2.get_N_man());
            }
            else  if(house1.get_N_man() < house2.get_N_man()){
                System.out.println("There are more people in the first house: "+ house2.get_N_man() +" vs "+house1.get_N_man());
            }
            else{
                System.out.println("Equal number of residents: " +house2.get_N_man());
            }
        }




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
                        System.out.println("Enter ground of the first flat and number of this flat on the ground");
                        int GROUND1 = in.nextInt();
                        int FLAT1 = in.nextInt();
                        System.out.println("Enter ground of the second flat and number of this flat on the ground");
                        int GROUND2 = in.nextInt();
                        int FLAT2 = in.nextInt();
                        houses.get(HOUSE).Compare_flats(GROUND1,FLAT1,GROUND2,FLAT2);
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
