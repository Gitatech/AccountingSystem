import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Apartment implements Comparable<Apartment>, Serializable {
    private final int number;
    private final int floor;
    private final float square;
    private int residentsNumber;

    public Apartment(int number, int floor, float square, int residentsNumber) {
        this.number = number;
        this.floor = floor;
        this.residentsNumber = residentsNumber;
        this.square = square;
    }

    public static int compareByFloor(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.floor, apartment2.floor);
    }

    public static int compareBySquare(Apartment apartment1, Apartment apartment2) {
        return Float.compare(apartment1.square, apartment2.square);
    }

    public static int compareByResidentsNumber(Apartment apartment1, Apartment apartment2) {
        return Integer.compare(apartment1.residentsNumber, apartment2.residentsNumber);
    }

    public static Apartment templateApartment() {
        return new Apartment(Integer.MAX_VALUE, Integer.MAX_VALUE, Float.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static Apartment createByConsole() {
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);
        int number;
        int floor;
        int residentsNumber;
        float square;
        System.out.print("Введите номер квартиры: ");
        number = in.nextInt();
        System.out.print("Введите этаж: ");
        floor = in.nextInt();
        System.out.print("Введите площадь квартиры: ");
        square = in.nextFloat();
        System.out.print("Введите кол-во жителей: ");
        residentsNumber = in.nextInt();
        return new Apartment(number, floor, square, residentsNumber);
    }

    public int getNumber() {
        return number;
    }

    public int getFloor() {
        return floor;
    }

    public float getSquare() {
        return square;
    }


    public int getResidentsNumber() {
        return residentsNumber;
    }

    public void setResidentsNumber(int residentsNumber) {
        this.residentsNumber = residentsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return number == apartment.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, floor, residentsNumber);
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Этаж: %d, номер: %d, кол-во жителей: %d, площадь: %.1f м^2",
                floor, number, residentsNumber, square);
    }

    @Override
    public int compareTo(Apartment o) {
        return Integer.compare(number, o.number);
    }
}
