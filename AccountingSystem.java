package AccountingSystem;

import java.util.*;

public class AccountingSystem {
    private ArrayList<Home> homes = new ArrayList<Home>();

    public Home findHomeByNumber(int number) {
        for (Home i : homes) {
            if (i.getNumber() == number) {
                return i;
            }
        }
        Home temp = new Home();
        temp.setApartmentCount(-1);
        return temp;
    }

    public Apartment findApartmentByNumber(Home home, int number) {
        for (Apartment i : home.getApartments()) {
            if (i.getNumber() == number) {
                return i;
            }
        }
        Apartment temp = new Apartment();
        temp.setNumber(-1);
        return temp;
    }

    public void addHome(int number) {
        for (Home i : homes) {
            if (i.getNumber() == number) {
                System.out.println("Дом с таким номером существует");
                return;
            }
        }
        Home home = new Home(number);
        homes.add(home);
        System.out.println("Дом успешно добавлен");
    }

    public void addApartment(int numberOfHome) {
        Home temp = findHomeByNumber(numberOfHome);
        if (temp.getApartmentCount() == -1) {
            System.out.println("Дома с таким номером не существует");
            return;
        } else {
            Apartment tempApartment = new Apartment("inputFromConsole");
            temp.addApartment(tempApartment);
            System.out.println("Квартира успешно добавлена");
        }
    }

    public void getApartmentInformation(int numberOfHome, int numberOfApartment) {
        Home temp = findHomeByNumber(numberOfHome);
        if (temp.getApartmentCount() == -1) {
            System.out.println("Такого дома нет");
            return;
        }
        Apartment temp2 = temp.getApartment(numberOfApartment);
        if (temp2.getNumber() == -1) {
            System.out.println("Такой квартиры не существует");
            return;
        }
        temp2.printInfo();
    }

    public void getHomeInformation(int number) {
        Home temp = findHomeByNumber(number);
        if (temp.getApartmentCount() == -1) {
            System.out.println("Такого дома нет");
            return;
        }
        for (Apartment i : temp.getApartments()) {
            i.printInfo();
        }
        if (temp.getApartments().size() == 0) {
            System.out.println("В доме нет квартир");
        }

    }

    public void removeHome(int number) {
        Home temp = findHomeByNumber(number);
        if (temp.getApartmentCount() == -1) {
            System.out.println("Такого дома нет");
            return;
        }
        homes.remove(temp);
    }

    public void removeApartment(int numberOfHome, int numberOfApartment) {
        Home temp = findHomeByNumber(numberOfHome);
        if (temp.getApartmentCount() == -1) {
            System.out.println("Такого дома нет");
            return;
        }
        Apartment temp1 = findApartmentByNumber(temp, numberOfApartment);
        if (temp1.getNumber() == -1) {
            System.out.println("Такой квартиры нет");
            return;
        }
        temp.removeApartment(temp1);
    }


    public void compareApartment(int numberOfHome1, int numberOfHome2, int numberOfApartment1, int numberOfApartment2) {

        Home home1 = findHomeByNumber(numberOfHome1);
        if (home1.getApartmentCount() == -1) {
            System.out.println("Такого дома нет");
            return;
        }
        Apartment apartment1 = findApartmentByNumber(home1, numberOfApartment1);
        if (apartment1.getNumber() == -1) {
            System.out.println("Такой квартиры нет");
            return;
        }
        Home home2 = findHomeByNumber(numberOfHome2);
        if (home2.getApartmentCount() == -1) {
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
        if (homeOne.getApartmentCount() == -1) {
            System.out.println("Такого дома не существует");
            return;
        }
        Home homeTwo = findHomeByNumber(numberOfHomeTwo);
        if (homeTwo.getApartmentCount() == -1) {
            System.out.println("Такого дома не существует");
            return;
        }
        homeOne.compareBy(homeTwo);
    }
}
