package AccountingSystem.classes;

import java.io.*;
import java.util.*;

public class AccountingSystem {

    private SortedSet<House> houses;
    final String path = "accountingSystemData\\data.bin";

    public AccountingSystem() {
        this.houses = new TreeSet<>();
    }

    public void addHouse(int number) {
        houses.add(new House(number));
    }

    public SortedSet<House> getHouses() {
        return houses;
    }

    public void printHouses() {
        for (House house : houses) {
            System.out.println("Дом " + house.getNumber());
        }
        System.out.println();
    }

    public void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(houses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            houses = (TreeSet<House>) in.readObject();
        }
        catch (IOException | ClassNotFoundException ignored) {}
    }

    public static void main(String[] args) {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.load();

        boolean continueLoop = true;
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        while (continueLoop) {
            System.out.println(ColorScheme.ANSI_CYAN + "Доступные команды (система учёта):" + ColorScheme.ANSI_RESET);
            System.out.println("""
                    add - добавить дом
                    choose - выбрать дом
                    compare - сравнить дом/квартиру по параметрам
                    output - вывести дома в консоль
                    exit - выход из программы
                    """);

            System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
            String choice = in.next();
            System.out.println();

            switch (choice.toLowerCase()) {
                case "add" -> {
                    System.out.println(ColorScheme.ANSI_YELLOW + "Добавление дома" + ColorScheme.ANSI_RESET);
                    System.out.print("Введите номер дома: ");
                    int number = in.nextInt();
                    accountingSystem.addHouse(number);
                    System.out.println(ColorScheme.ANSI_GREEN + "Дом " + number + " был успешно добавлен\n\n");
                }
                case "choose" -> {
                    System.out.println(ColorScheme.ANSI_CYAN + "Дома: " + ColorScheme.ANSI_RESET);
                    accountingSystem.printHouses();
                    System.out.print("Введите номер дома: ");
                    int houseNumber = in.nextInt();
                    if (accountingSystem.getHouses().contains(new House(houseNumber))) {
                        House chosenHouse = House.templateHouse();
                        for (House house : accountingSystem.getHouses()) {
                            if (house.equals(new House(houseNumber))) {
                                chosenHouse = house;
                                break;
                            }
                        }

                        boolean continueHouseLoop = true;
                        while (continueHouseLoop) {
                            System.out.println(ColorScheme.ANSI_CYAN + "Дом " + chosenHouse.getNumber() + ":" + ColorScheme.ANSI_RESET);
                            System.out.println("""
                                    info - общая информация о доме
                                    remove - удалить дом
                                    add - добавить квартиру
                                    show - вывести квартиры
                                    choose - выбрать квартиру
                                    . - назад
                                    """);
                            System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
                            choice = in.next();
                            System.out.println();
                            switch (choice.toLowerCase()) {
                                case "info" -> {
                                    System.out.println(ColorScheme.ANSI_CYAN + "Общая информация о доме " + chosenHouse.getNumber() + ":" + ColorScheme.ANSI_RESET);
                                    System.out.printf("Этажность: %d, кол-во квартир: %d, общая площадь: %.1f м^2, количество жильцов: %d\n\n",
                                            chosenHouse.calculateFloors(), chosenHouse.getApartments().length, chosenHouse.calculateFullSquare(), chosenHouse.calculatePopulation());
                                }
                                case "remove" -> {
                                    accountingSystem.getHouses().remove(chosenHouse);
                                    System.out.println(ColorScheme.ANSI_GREEN + "Дом " + chosenHouse.getNumber() + " был удалён\n\n");
                                    continueHouseLoop = false;
                                }
                                case "add" -> {
                                    System.out.println(ColorScheme.ANSI_YELLOW + "Добавление квартиры в дом " + chosenHouse.getNumber() + ColorScheme.ANSI_RESET);
                                    chosenHouse.addApartmentByConsole();
                                }
                                case "show" -> {
                                    System.out.println(ColorScheme.ANSI_CYAN + "Квартиры дома " + chosenHouse.getNumber() + ":" + ColorScheme.ANSI_RESET);
                                    Arrays.stream(chosenHouse.getApartments()).forEach(a -> System.out.printf(Locale.US, "" +
                                                    "этаж: %d, номер: %d, кол-во жильцов: %d, площадь: %.1f м^2\n", a.getFloor(),
                                            a.getNumber(), a.getResidentsNumber(), a.getSquare()));
                                    System.out.println();
                                }
                                case "choose" -> {
                                    System.out.println(ColorScheme.ANSI_CYAN + "Квартиры дома " + chosenHouse.getNumber() + ":" + ColorScheme.ANSI_RESET);
                                    Arrays.stream(chosenHouse.getApartments()).forEach(a -> System.out.printf(Locale.US,
                                                    "этаж: %d, номер: %d, кол-во жильцов: %d, площадь: %.1f м^2\n", a.getFloor(),
                                            a.getNumber(), a.getResidentsNumber(), a.getSquare()));
                                    System.out.println();
                                    System.out.print("Введите номер квартиры: ");
                                    int number = in.nextInt();
                                    if (Arrays.stream(chosenHouse.getApartments()).anyMatch(e -> e.getNumber() == number)) {
                                        Apartment chosenApartment = Apartment.templateApartment();
                                        for (Apartment apartment : chosenHouse.getApartments()) {
                                            if (apartment.getNumber() == number) {
                                                chosenApartment = apartment;
                                                break;
                                            }
                                        }

                                        boolean continueApartmentLoop = true;
                                        while (continueApartmentLoop) {
                                            System.out.println(ColorScheme.ANSI_CYAN + "Квартира " + chosenApartment.getNumber() + ":" + ColorScheme.ANSI_RESET);
                                            System.out.println("""
                                                    info - информация о квартире
                                                    сhange - изменить число жильцов
                                                    remove - удалить квартиру
                                                    . - назад
                                                    """);
                                            System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
                                            choice = in.next();
                                            System.out.println();
                                            switch (choice) {
                                                case "info" -> {
                                                    System.out.println(ColorScheme.ANSI_CYAN + "Информация о квартире: " + ColorScheme.ANSI_RESET);
                                                    System.out.printf(Locale.US, "Этаж: %d, номер: %d, кол-во жильцов: %d, площадь: %.1f\n\n",
                                                            chosenApartment.getFloor(), chosenApartment.getNumber(),
                                                            chosenApartment.getResidentsNumber(), chosenApartment.getSquare());
                                                }
                                                case "change" -> {
                                                    System.out.print("Введите новое кол-во жильцов: ");
                                                    int newResidentsNumber = in.nextInt();
                                                    chosenApartment.setResidentsNumber(newResidentsNumber);
                                                    System.out.println(ColorScheme.ANSI_GREEN + "Кол-во жильцов было изменено\n" + ColorScheme.ANSI_RESET);
                                                }
                                                case "remove" -> {
                                                    chosenHouse.removeApartment(chosenApartment);
                                                    System.out.println(ColorScheme.ANSI_GREEN + "Квартира была удалена\n\n" + ColorScheme.ANSI_RESET);
                                                    continueApartmentLoop = false;
                                                }
                                                case "." -> continueLoop = false;
                                                default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная команда\n" + ColorScheme.ANSI_RESET);
                                            }
                                        }

                                    } else {
                                        System.out.println(ColorScheme.ANSI_RED + "Квартиры с таким номером не было найдено\n" + ColorScheme.ANSI_RESET);
                                    }
                                }
                                case "." -> continueHouseLoop = false;
                                default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная команда\n" + ColorScheme.ANSI_RESET);
                            }
                        }
                    } else {
                        System.out.println(ColorScheme.ANSI_RED + "Дома с таким номером не было найдено\n" + ColorScheme.ANSI_RESET);
                    }
                }
                case "compare" -> {
                    boolean continueCompareLoop = true;
                    while (continueCompareLoop) {
                        System.out.println("""
                                house - сравнить дома
                                apartment - сравнить квартиры
                                . - назад
                                """);
                        System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
                        choice = in.next();
                        System.out.println();
                        switch (choice) {
                            case "house" -> {
                                System.out.println(ColorScheme.ANSI_CYAN + "Дома:" + ColorScheme.ANSI_RESET);
                                accountingSystem.printHouses();
                                if (accountingSystem.getHouses().size() < 2) {
                                    System.out.println(ColorScheme.ANSI_RED + "Недостаточное количество домов\n" + ColorScheme.ANSI_RESET);
                                    continueCompareLoop = false;
                                } else {

                                    House house1 = accountingSystem.getHouseFromAccountingSystem(1);
                                    House house2 = accountingSystem.getHouseFromAccountingSystem(2);

                                    char floors = ' ', population = ' ', square = ' ';
                                    switch (House.compareByFloors(house1, house2)) {
                                        case -1 -> floors = '<';
                                        case 0 -> floors = '=';
                                        case 1 -> floors = '>';
                                    }
                                    switch (House.compareByPopulation(house1, house2)) {
                                        case -1 -> population = '<';
                                        case 0 -> population = '=';
                                        case 1 -> population = '>';
                                    }
                                    switch (House.compareByFullSquare(house1, house2)) {
                                        case -1 -> square = '<';
                                        case 0 -> square = '=';
                                        case 1 -> square = '>';
                                    }

                                    System.out.println(ColorScheme.ANSI_CYAN + "Сравнение дома " + house1.getNumber() +
                                            " и дома " + house2.getNumber() + " по параметрам:" + ColorScheme.ANSI_RESET);
                                    System.out.printf("%20s %6s %4s %6s\n", "Дом", house1.getNumber(),
                                            " ", +house2.getNumber());
                                    System.out.printf("%20s %6d %4s %6d\n", "Этажность",
                                            house1.calculateFloors(), floors, house2.calculateFloors());
                                    System.out.printf("%20s %6d %4s %6d\n", "Кол-во жителей",
                                            house1.calculatePopulation(), population, house2.calculatePopulation());
                                    System.out.printf(Locale.US, "%20s %6.1f %4s %6.1f\n", "Общая площадь",
                                            house1.calculateFullSquare(), square, house2.calculateFullSquare());
                                    System.out.println();
                                }
                            }
                            case "apartment" -> {
                                if (accountingSystem.getHouses().size() == 0 || accountingSystem.getHouses().stream()
                                        .mapToInt(e -> e.getApartments().length).sum() == 0) {
                                    System.out.println(ColorScheme.ANSI_RED + "В системе учёта нет квартир" + ColorScheme.ANSI_RESET);
                                } else {
                                    System.out.println(ColorScheme.ANSI_CYAN + "Дома:" + ColorScheme.ANSI_RESET);
                                    accountingSystem.printHouses();

                                    Apartment apartment1 = accountingSystem.getApartmentFromAccountingSystem(1);
                                    Apartment apartment2 = accountingSystem.getApartmentFromAccountingSystem(2);

                                    char floors = ' ', population = ' ', square = ' ';
                                    switch (Apartment.compareByFloor(apartment1, apartment2)) {
                                        case -1 -> floors = '<';
                                        case 0 -> floors = '=';
                                        case 1 -> floors = '>';
                                    }
                                    switch (Apartment.compareByResidentsNumber(apartment1, apartment2)) {
                                        case -1 -> population = '<';
                                        case 0 -> population = '=';
                                        case 1 -> population = '>';
                                    }
                                    switch (Apartment.compareBySquare(apartment1, apartment2)) {
                                        case -1 -> square = '<';
                                        case 0 -> square = '=';
                                        case 1 -> square = '>';
                                    }

                                    System.out.println(ColorScheme.ANSI_CYAN + "Сравнение квартиры " + apartment1.getNumber() +
                                            " и квартиры " + apartment2.getNumber() + " по параметрам:" + ColorScheme.ANSI_RESET);
                                    System.out.printf("%20s %6s %4s %6s\n", "Квартира", apartment1.getNumber(),
                                            " ", apartment2.getNumber());
                                    System.out.printf("%20s %6d %4s %6d\n", "Этаж",
                                            apartment1.getFloor(), floors, apartment2.getFloor());
                                    System.out.printf("%20s %6d %4s %6d\n", "Кол-во жителей",
                                            apartment1.getResidentsNumber(), population, apartment2.getResidentsNumber());
                                    System.out.printf(Locale.US, "%20s %6.1f %4s %6.1f\n", "Площадь",
                                            apartment1.getSquare(), square, apartment2.getSquare());
                                    System.out.println();

                                }
                            }
                            case "." -> continueCompareLoop = false;
                            default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная команда" + ColorScheme.ANSI_RESET);
                        }
                    }
                }
                case "output" -> {
                    System.out.println(ColorScheme.ANSI_CYAN + "Дома: " + ColorScheme.ANSI_RESET);
                    accountingSystem.getHouses().forEach(h -> System.out.println("Дом " + h.getNumber()));
                    System.out.println();
                }
                case "exit" -> {
                    accountingSystem.save();
                    continueLoop = false;
                }
                default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная команда\n" + ColorScheme.ANSI_RESET);
            }

        }

        accountingSystem.save();

    }

    private Apartment getApartmentFromAccountingSystem(int num) {
        boolean continueHouseLoop = true;
        Scanner in = new Scanner(System.in);
        Apartment apartment = Apartment.templateApartment();
        while (continueHouseLoop) {
            System.out.print("Выберите дом в которой находится квартира " + num + ':');

            int number = in.nextInt();
            if (houses.contains(new House(number))) {
                House chosenHouse = House.templateHouse();
                for (House house : houses) {
                    if (house.getNumber() == number) {
                        chosenHouse = house;
                        break;
                    }
                }
                if (chosenHouse.getApartments().length == 0) {
                    System.out.println(ColorScheme.ANSI_RED + "Дом " + chosenHouse.getNumber() +
                            " не содержит квартир" + ColorScheme.ANSI_RESET);
                    continue;
                } else {
                    System.out.println(ColorScheme.ANSI_CYAN + "Квартиры дома " + chosenHouse.getNumber()
                            + ":" + ColorScheme.ANSI_RESET);
                    Arrays.stream(chosenHouse.getApartments()).forEach(a ->
                            System.out.printf(Locale.US, "Этаж: %d, номер: %d, кол-во жильцов: %d, площадь: %.1f\n",
                                    a.getFloor(), a.getNumber(), a.getResidentsNumber(), a.getSquare()));
                    System.out.println();
                }
                boolean continueApartmentLoop = true;
                while (continueApartmentLoop) {
                    System.out.print("Введите номер квартиры: ");
                    number = in.nextInt();
                    boolean isThere = false;
                    for (Apartment ap : chosenHouse.getApartments()) {
                        if (ap.getNumber() == number) {
                            isThere = true;
                            apartment = ap;
                            continueApartmentLoop = false;
                            continueHouseLoop = false;
                            break;
                        }
                    }
                    if (!isThere) {
                        System.out.println(ColorScheme.ANSI_RED + "Нет квартиры с таким номером" +
                                ColorScheme.ANSI_RESET);
                    }
                }
            }
        }
        return apartment;
    }

    private House getHouseFromAccountingSystem(int num) {
        House house = House.templateHouse();
        Scanner in = new Scanner(System.in);
        boolean continueHouseLoop = true;
        int number;
        while (continueHouseLoop) {
            System.out.print("Выберите номер дома " + num + ": ");
            number = in.nextInt();
            if (houses.contains(new House(number))) {
                for (House h : houses) {
                    if (h.getNumber() == number) {
                        house = h;
                        continueHouseLoop = false;
                        break;
                    }
                }
            } else {
                System.out.println(ColorScheme.ANSI_RED + "Нет дома с таким номером\n" + ColorScheme.ANSI_RESET);
            }
        }
        return house;
    }
}
