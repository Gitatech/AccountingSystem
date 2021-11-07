import java.util.ArrayList;
import java.util.Scanner;

public class Home {
    private int number;
    private int apartmentCount;
    private int floorCount;
    private ArrayList<Apartment> apartments = new ArrayList<Apartment>();
    public Home() {
        System.out.println("Введите номер дома, количество квартир, количество этажей");
        Scanner input = new Scanner(System.in);
        this.number = input.nextInt();
        this.apartmentCount = input.nextInt();
        this.floorCount = input.nextInt();
    }

    public Home(String s) {

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
        Apartment temp = new Apartment("empty");
        temp.setNumber(-1);
        return temp;
    }

    public static int countsquare(Home home) {
        int result = 0;
        for (Apartment i : home.getApartments()) {
            result += i.getsquare();
        }
        return result;
    }

    public static int countpeople(Home home) {
        int result = 0;
        for (Apartment i : home.getApartments()) {
            result += i.getpeople();
        }
        return result;
    }

    public String getSign(int x, int y) {
        switch (Integer.compare(x,y)) {
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
    public void Compare(int x, int y, int homeNum) {
        System.out.println("Дом" + this.number + getSign(x,y) + "Дом" + homeNum);
    }

    public void compareBy(Home home) {

        int sqr1 = countsquare(this);
        int sqr2 = countsquare(home);
        System.out.println("Площадь: ");
        Compare(sqr1, sqr2, home.number);

        int ap1 = this.apartmentCount;
        int ap2 = home.apartmentCount;
        System.out.println("Количество квартир");
        Compare(ap1, ap2, home.number);
        int p1 = countpeople(this);
        int p2 = countpeople(home);
        System.out.println("Количество жильцов: ");
        Compare(p1, p2, home.number);

        int f1 = this.floorCount;
        int f2 = home.floorCount;
        System.out.println("Количество этажей");
        Compare (f1, f2, home.number);

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

    public int getFloorCount() {
        return floorCount;
    }


    public ArrayList<Apartment> getApartments() {
        return apartments;
    }

}