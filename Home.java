package AccountingSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Home {

    private int number;
    private int apartmentCount;
    private int floorCount;
    private ArrayList<Apartment> apartments = new ArrayList<Apartment>();

    public Home() {
    }

    public Home(String console) {
        System.out.println("Введите номер дома, количество квартир, количество этажей");
        Scanner input = new Scanner(System.in);
        this.number = input.nextInt();
        this.apartmentCount = input.nextInt();
        this.floorCount = input.nextInt();
    }


    public Home(int number) {
        System.out.println("Введите количество квартир, количество этажей");
        this.number = number;
        Scanner input = new Scanner(System.in);
        this.apartmentCount = input.nextInt();
        this.floorCount = input.nextInt();
    }

    public Apartment getApartment(int number) {
        for (Apartment i : apartments) {
            if (i.getNumber() == number) {
                return i;
            }
        }
        Apartment temp = new Apartment();
        temp.setNumber(-1);
        return temp;
    }

    public void printInfo() {
        System.out.println("Информация о доме :");
        System.out.println("Номер дома -" + this.number);
        System.out.println("Количество квартир - " + this.apartmentCount);
        System.out.println("Количество этажей - " + this.floorCount);
    }

    public static int countSqare(Home home) {
        int result = 0;
        for (Apartment i : home.getApartments()) {
            result += i.getSqare();
        }
        return result;
    }

    public String getSign(int x, int y) {
        switch (Integer.compare(x, y)) {
            case 1 -> {
                return ">";
            }
            case 0 -> {
                return "=";
            }
            case -1 -> {
                return "<";
            }
        }
        return "";
    }

    public void compareBy(Home home) {
        System.out.println("Дом1      Дом2");
        String sign = new String("");

        System.out.println("Номер");
        sign = getSign(this.number, home.number);
        System.out.println("     " + sign);

        System.out.println("Количество квартир");
        sign = getSign(this.apartmentCount, home.apartmentCount);
        System.out.println("     " + sign);

        System.out.println("Количество этажей");
        sign = getSign(this.floorCount, home.floorCount);
        System.out.println("     " + sign);

        int sqareOne = countSqare(this);
        int sqareTwo = countSqare(home);
        System.out.println("Общая площадь");
        sign = getSign(sqareOne, sqareTwo);
        System.out.println("     " + sign);
    }

    public void removeApartment(Apartment temp) {
        apartments.remove(temp);
    }

    public void addApartment(Apartment newApartment) {
        apartments.add(newApartment);
    }

    public int getNumber() {
        return number;
    }

    public int getApartmentCount() {
        return apartmentCount;
    }

    public void setApartmentCount(int apartmentCount) {
        this.apartmentCount = apartmentCount;
    }

    public int getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(int floorCount) {
        this.floorCount = floorCount;
    }

    public ArrayList<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(ArrayList<Apartment> apartments) {
        this.apartments = apartments;
    }
}
