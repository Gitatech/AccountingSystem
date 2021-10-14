import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Input a budget: ");
        double budget = in.nextDouble();
        System.out.println("Input a price for per square metre: ");
        double sqrMPrice =in.nextDouble();
        System.out.println("Input a number of apartments per floor: ");
        int apartments =in.nextInt();
        House house1=new House(budget,sqrMPrice,apartments);
        System.out.println("Full square of house is " + house1.getSqr());
        System.out.println("Full population of house is  " + house1.getPopulation());
        System.out.println("Number of floors is " + house1.getFloors());
    }
}