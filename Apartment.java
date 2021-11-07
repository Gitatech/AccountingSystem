import java.util.Scanner;
import java.util.Objects;

public class Apartment {
    private int square;
    private int people;
    private int rooms;
    private int number;
    private int floor;

    public Apartment() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите площадь, кол-во жильцов, комнат, номер квартиры и этаж\n");
        this.square = input.nextInt();
        this.people = input.nextInt();
        this.rooms = input.nextInt();
        this.number = input.nextInt();
        this.floor = input.nextInt();
    }

    Apartment(String empty) {
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

    public void Compare(int x, int y, int apartmentNumber) {
        System.out.println("Квартира" + this.number + getSign(x,y) + "Квартира" + apartmentNumber);

    }

    public void compareBy( Apartment apartment) {

        System.out.println("Площадь: ");
        Compare(this.square, apartment.square, apartment.number);

        System.out.println("Количество жильцов: ");
       Compare(this.people, apartment.people, apartment.number);

        System.out.println("Количество комнат: ");
       Compare(this.rooms, apartment.rooms, apartment.number);

        System.out.println("Номер квартиры: ");
       Compare(this.number, apartment.number, apartment.number);

        System.out.println("Этаж: ");
        Compare(this.floor, apartment.floor, apartment.number);
    }


    public int getsquare() {
        return square;
    }


    public int getpeople() {
        return people;
    }


    public int getrooms() {
        return rooms;
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