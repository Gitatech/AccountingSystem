import java.util.*;

public class AccountingSystem {
    private ArrayList<Home> homes = new ArrayList<Home>();

    public Home findHomeByNumber(int number) {
        for (Home i : homes) {
            if (i.getNumber() == number) {
                return i;
            }
        }
    return null;
    }

    public Apartment findApartmentByNumber(Home home, int n) {
        for (Apartment i : home.getApartments()) {
            if (i.getNumber() == n) {
                return i;
            }
        }
        return null;
    }

    public void addHome(int n) {
        for (Home i : homes) {
            if (i.getNumber() == n) {
                System.out.println("Дом с таким номером существует");
                return;
            }
        }
        Home home = new Home(n);
        homes.add(home);
        System.out.println("Дом успешно добавлен");
    }

    public void addApartment(int numOfHome) {
        Home temp = findHomeByNumber(numOfHome);
        if (temp.getApartmentCount() == -1) {
            System.out.println("Дома с таким номером не существует");
            return;
        } else {
            Apartment tempApartment = new Apartment();
            temp.addApartment(tempApartment);
            System.out.println("Квартира добавлена");
        }
    }

    public void compareApartment(int numberOfHome1, int numberOfHome2, int numberOfApartment1, int numberOfApartment2) {

        Home home1 = findHomeByNumber(numberOfHome1);
        if (home1.getNumber() == -1) {
            System.out.println("Такого дома нет");
            return;
        }
        Apartment apartment1 = findApartmentByNumber(home1, numberOfApartment1);
        if (apartment1.getNumber() == -1) {
            System.out.println("Такой квартиры нет");
            return;
        }
        Home home2 = findHomeByNumber(numberOfHome2);
        if (home2.getNumber() == -1) {
            System.out.println("Такого дома нет");
            return;
        }
        Apartment apartment2 = findApartmentByNumber(home2, numberOfApartment2);
        if (apartment2.getNumber() == -1) {
            System.out.println("Такой квартиры нет");
            return;
        }
        apartment1.compareBy(apartment2);
    }

    public void compareHome(int numberOfHomeOne, int numberOfHomeTwo) {
        Home homeOne = findHomeByNumber(numberOfHomeOne);
        if (homeOne.getNumber() == -1) {
            System.out.println("Такого дома не существует");
            return;
        }
        Home homeTwo = findHomeByNumber(numberOfHomeTwo);
        if (homeTwo.getNumber() == -1) {
            System.out.println("Такого дома не существует");
            return;
        }
        homeOne.compareBy(homeTwo);
    }
    public void ApartmentInfo(int HomeNum, int ApartmentNum){
        Home home1 = findHomeByNumber(HomeNum);
        Apartment apart = findApartmentByNumber(home1, ApartmentNum);
        System.out.println("Площадь: "+apart.getsquare()+" Кол-во жильцов: "+apart.getpeople()+ " Кол-во комнат: "+apart.getrooms()+
                " Этаж: "+apart.getFloor());
    }
    public void HomeInfo(int HomeNum){
        Home home1 = findHomeByNumber(HomeNum);
        System.out.println("Площадь: "+ Home.countsquare(home1) +" Кол-во жильцов: "+Home.countpeople(home1)+
                " Кол-во комнат: "+home1.getApartmentCount()+" Кол-во этажей: "+ home1.getFloorCount());
    }

}