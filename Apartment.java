package AccountingSystem;

import java.util.Objects;
import java.util.Scanner;

public class Apartment {
    private int sqare;  // площадь
    private int roomer; // количество жильцов
    private int cntRooms; // количество комнат
    private int number; // номер квартиры
    private int floor; // этаж

    public Apartment() {
    }

    Apartment(String console) {
        Scanner input = new Scanner(System.in);
        System.out.println("введите площадь, количество жильцов, количество комнат, номер квартиры и этаж\n");
        this.sqare = input.nextInt();
        this.roomer = input.nextInt();
        this.cntRooms = input.nextInt();
        this.number = input.nextInt();
        this.floor = input.nextInt();
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

    public void printCompareResult(String sign, int apartmentNumber) {
        System.out.println("квартира" + this.number + sign + "квартира" + apartmentNumber);
    }

    public void compareBy(Apartment apartment) {

        String sign = new String("");

        System.out.println("Площадь :");
        sign = getSign(this.sqare, apartment.sqare);
        printCompareResult(sign, apartment.number);

        System.out.println("Количество жильцов");
        sign = getSign(this.roomer, apartment.roomer);
        printCompareResult(sign, apartment.number);

        System.out.println("Количество комнат");
        sign = getSign(this.cntRooms, apartment.cntRooms);
        printCompareResult(sign, apartment.number);

        System.out.println("Номер квартиры");
        sign = getSign(this.number, apartment.number);
        printCompareResult(sign, apartment.number);

        System.out.println("Этаж");
        sign = getSign(this.floor, apartment.floor);
        printCompareResult(sign, apartment.number);

    }

    public void printInfo() {
        System.out.println("Информация о квартире:");
        System.out.println("номер              - " + this.getNumber());
        System.out.println("площадь            - " + this.getSqare());
        System.out.println("количество жильцов - " + this.getRoomer());
        System.out.println("количество комнат  - " + this.getCntRooms());
        System.out.println("этаж               - " + this.getFloor());
    }

    public int getSqare() {
        return sqare;
    }

    public void setSqare(int sqare) {
        this.sqare = sqare;
    }

    public int getRoomer() {
        return roomer;
    }

    public void setRoomer(int roomer) {
        this.roomer = roomer;
    }

    public int getCntRooms() {
        return cntRooms;
    }

    public void setCntRooms(int cntRooms) {
        this.cntRooms = cntRooms;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }


}
