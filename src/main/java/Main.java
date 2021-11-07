import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

    public class Main {
        public static void main(String[] args){
            Scanner in = new Scanner(System.in);
            ArrayList<House> houses = new ArrayList<House>();
            System.out.println("What do you want to do:" +
                    "\n1.Create new house" +
                    "\n2.Compare" +
                    "\n3.Compare flats" +
                    "\n4.Al information about house"+
                    "\n5.Exit");
            int k = in.nextInt();
            while(k != 5){
                switch (k) {
                    case(1):
                        House house = new House();
                        houses.add(house);
                        break;
                    case(2):
                        if(houses.size() < 2){
                            System.out.println("Not enough houses to compare");
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
                    case(3):
                        if(houses.isEmpty()){
                            System.out.println("There is no one house");
                            break;
                        }
                        System.out.println("Enter number of House");
                        int HOUSE = in.nextInt();
                        HOUSE--;
                        System.out.println("Enter Number of the first flat " + "(" +houses.get(HOUSE).N_flats_in_house()+" flats in this house)");
                        int NUMBER1 = in.nextInt();
                        System.out.println("Enter Number of the second flat "+ "(" +houses.get(HOUSE).N_flats_in_house()+" flats in this house)");
                        int NUMBER2 = in.nextInt();
                        if(NUMBER1 >houses.get(HOUSE).N_flats_in_house() || NUMBER2 > houses.get(HOUSE).N_flats_in_house())
                        {
                            System.out.println("Can't compare");
                            break;
                        }
                        houses.get(HOUSE).Compare_flats(NUMBER1,NUMBER2);
                        break;
                    case(4):
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
                        System.out.println("House #"+ ++l +" has " + houses.get(--l).N_house_grounds() +" floors" );
                        System.out.println("House #"+ ++l +" has " + houses.get(--l).N_flats_in_house() +" flats" );
                        System.out.println("Number of residents in the " + ++l +" house: " + houses.get(--l).get_N_man());
                        System.out.println("Area of the " + ++l +" House: " + houses.get(--l).House_area());
                        break;
                    case(5):
                        break;
                    default:
                        System.out.println("Try again");
                        break;
                }
                System.out.println("What you want to do:" +
                        "\n1.Create new house" +
                        "\n2.Compare" +
                        "\n3.Compare flats" +
                        "\n4.Al information about house"+
                        "\n5.Exit");
                k = in.nextInt();

            }
        }
}
