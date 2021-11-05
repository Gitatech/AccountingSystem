import java.io.*;
import java.util.*;

public class AccountingSystem {

    private SortedSet<House> houses;
    private final String path = "src/main/resources/data.bin";

    public AccountingSystem() {
        this.houses = new TreeSet<>();
    }

    public void addHouse(int number) {
        houses.add(new House(number));
    }

    public void removeHouse(int number) {
        houses.remove(new House(number));
    }

    public boolean containsHouse(int number) {
        return houses.contains(new House(number));
    }

    public House[] getHouses() {
        return houses.toArray(House[]::new);
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
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");

        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.load();

        boolean continueLoop = true;
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        while (continueLoop) {
            System.out.println(ColorScheme.ANSI_CYAN + "Доступные команды (системы учёта):" + ColorScheme.ANSI_RESET);
            System.out.println("""
                    add - добавить дом
                    choose - выбрать дом
                    compare - сравнить дома или квартиры по параметрам
                    output - вывод домов в консоль
                    exit - выход
                    """);

            System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
            String choice = in.next();
            System.out.println();

            switch (choice.toLowerCase()) {
                case "add" -> {
                    System.out.println(ColorScheme.ANSI_YELLOW + "Добавление дома" + ColorScheme.ANSI_RESET);
                    System.out.print("Введите номер дома: ");
                    int number = in.nextInt();
                    if (Arrays.stream(accountingSystem.getHouses()).anyMatch(e -> e.getNumber() == number)) {
                        System.out.println(ColorScheme.ANSI_RED + "Дом " + number + " уже есть в системе\n\n" + ColorScheme.ANSI_RESET);
                    } else {
                        accountingSystem.addHouse(number);
                        System.out.println(ColorScheme.ANSI_GREEN + "Дом " + number + " был успешно добавлен в систему\n\n" + ColorScheme.ANSI_RESET);
                    }

                }
                case "choose" -> chooseHouse(accountingSystem, in);
                case "compare" -> comparingCase(accountingSystem, in);
                case "output" -> {
                    System.out.println(ColorScheme.ANSI_CYAN + "Дома: " + ColorScheme.ANSI_RESET);
                    accountingSystem.printHouses();
                }
                case "exit" -> continueLoop = false;
                default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная команда\n" + ColorScheme.ANSI_RESET);
            }

        }
        accountingSystem.save();
    }

    private static void chooseHouse(AccountingSystem accountingSystem, Scanner in) {
        System.out.println(ColorScheme.ANSI_CYAN + "Дома: " + ColorScheme.ANSI_RESET);
        accountingSystem.printHouses();
        System.out.print("Введите номер дома: ");
        int houseNumber = in.nextInt();
        Optional<House> optChosenHouse = Arrays.stream(accountingSystem.getHouses())
                .filter(e -> e.getNumber() == houseNumber).findFirst();
        if (optChosenHouse.isPresent()) {
            House chosenHouse = optChosenHouse.get();
            chooseActionsInHouse(accountingSystem, chosenHouse, in);
        } else {
            System.out.println(ColorScheme.ANSI_RED + "Дома с таким номером не было найдено\n" + ColorScheme.ANSI_RESET);
        }
    }

    private static void comparingCase(AccountingSystem accountingSystem, Scanner in) {
        boolean continueCompareLoop = true;
        while (continueCompareLoop) {
            System.out.println("""
                    house - сравнить дома
                    apartment - сравнить квартиры
                    . - back
                    """);
            System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
            String choice = in.next();
            System.out.println();
            switch (choice) {
                case "house" -> {
                    System.out.println(ColorScheme.ANSI_CYAN + "Дома:" + ColorScheme.ANSI_RESET);
                    accountingSystem.printHouses();
                    if (accountingSystem.getHouses().length < 1) {
                        System.out.println(ColorScheme.ANSI_RED + "В системе нет домов\n" + ColorScheme.ANSI_RESET);
                        continueCompareLoop = false;
                    } else {

                        House house1 = accountingSystem.getHouseFromAccountingSystem("первого");
                        House house2 = accountingSystem.getHouseFromAccountingSystem("второго");

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
                        System.out.printf(Locale.US, """
                                        %20s %6s %4s %6s
                                        %20s %6d %4s %6d
                                        %20s %6d %4s %6d
                                        %20s %6.1f %4s %6.1f
                                                                        
                                        """,
                                "Дом", house1.getNumber(), " ", +house2.getNumber(),
                                "Этажность", house1.calculateFloors(), floors, house2.calculateFloors(),
                                "Количество жителей", house1.calculatePopulation(), population, house2.calculatePopulation(),
                                "Общая площадь", house1.calculateFullSquare(), square, house2.calculateFullSquare());
                    }
                }
                case "apartment" -> {
                    if (accountingSystem.getHouses().length == 0 || Arrays.stream(accountingSystem.getHouses())
                            .mapToInt(e -> e.getApartments().length).sum() == 0) {
                        System.out.println(ColorScheme.ANSI_RED + "В систему учёта не было добавлено ни одной квартиры" + ColorScheme.ANSI_RESET);
                    } else {
                        System.out.println(ColorScheme.ANSI_CYAN + "Дома:" + ColorScheme.ANSI_RESET);
                        accountingSystem.printHouses();

                        Object[] app1 = accountingSystem.getApartmentFromAccountingSystem("первая");
                        Apartment apartment1 = (Apartment) app1[0];
                        int ap1HouseNumber = (Integer) app1[1];

                        Object[] app2 = accountingSystem.getApartmentFromAccountingSystem("вторая");
                        Apartment apartment2 = (Apartment) app2[0];
                        int ap2HouseNumber = (Integer) app2[1];

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
                        System.out.printf("%20s %6s %4s %6s\n", "Квартира", ap1HouseNumber + "/" + apartment1.getNumber(),
                                " ", ap2HouseNumber + "/" + apartment2.getNumber());
                        System.out.printf("%20s %6d %4s %6d\n", "Этаж",
                                apartment1.getFloor(), floors, apartment2.getFloor());
                        System.out.printf("%20s %6d %4s %6d\n", "Количество жителей",
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

    private static void chooseActionsInHouse(AccountingSystem accountingSystem, House chosenHouse, Scanner in) {
        boolean continueHouseLoop = true;
        while (continueHouseLoop) {
            System.out.println(ColorScheme.ANSI_CYAN + "Дом " + chosenHouse.getNumber() + ":" + ColorScheme.ANSI_RESET);
            System.out.println("""
                    info - общая информация о доме\s
                    remove - удалить дом
                    add - добавить квартиру
                    show - вывод квартир в консоль\s
                    choose - выбрать квартиру
                    . - назад
                    """);
            System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
            String choice = in.next();
            System.out.println();
            switch (choice.toLowerCase()) {
                case "info" -> {
                    System.out.println(ColorScheme.ANSI_CYAN + "Общая информация о доме " + chosenHouse.getNumber() + ":" + ColorScheme.ANSI_RESET);
                    System.out.printf(Locale.US, "Этажность: %d, кол-во квартир: %d, общая площадь: %.1f м^2, кол-во жителей: %d\n\n",
                            chosenHouse.calculateFloors(), chosenHouse.getApartments().length, chosenHouse.calculateFullSquare(), chosenHouse.calculatePopulation());
                }
                case "remove" -> {
                    accountingSystem.removeHouse(chosenHouse.getNumber());
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
                                    "этаж: %d, номер: %d, кол-во жителей: %d, площадь: %.1f м^2\n", a.getFloor(),
                            a.getNumber(), a.getResidentsNumber(), a.getSquare()));
                    System.out.println();
                }
                case "choose" -> {
                    System.out.println(ColorScheme.ANSI_CYAN + "Квартиры дома " + chosenHouse.getNumber() + ":" + ColorScheme.ANSI_RESET);
                    Arrays.stream(chosenHouse.getApartments()).forEach(a -> System.out.printf(Locale.US,
                            "этаж: %d, номер: %d, кол-во жителей: %d, площадь: %.1f м^2\n", a.getFloor(),
                            a.getNumber(), a.getResidentsNumber(), a.getSquare()));
                    System.out.println();
                    System.out.print("Введите номер квартиры: ");
                    int number = in.nextInt();
                    Optional<Apartment> optionalApartment = Arrays.stream(chosenHouse.getApartments())
                            .filter(e -> e.getNumber() == number).findFirst();
                    if (optionalApartment.isPresent()) {
                        Apartment chosenApartment = optionalApartment.get();
                        boolean continueApartmentLoop = true;
                        while (continueApartmentLoop) {
                            System.out.println(ColorScheme.ANSI_CYAN + "Квартира " + chosenApartment.getNumber() + ":" + ColorScheme.ANSI_RESET);
                            System.out.println("""
                                    info - информация о квартире
                                    сhange - изменить кол-во жителей
                                    remove - удалить квартиру
                                    . - back
                                    """);
                            System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
                            choice = in.next();
                            System.out.println();
                            switch (choice) {
                                case "info" -> {
                                    System.out.println(ColorScheme.ANSI_CYAN + "Информация о квартире: " + ColorScheme.ANSI_RESET);
                                    System.out.printf(Locale.US, "Этаж: %d, номер: %d, кол*во жителей: %d, площадь: %.1f\n\n",
                                            chosenApartment.getFloor(), chosenApartment.getNumber(),
                                            chosenApartment.getResidentsNumber(), chosenApartment.getSquare());
                                }
                                case "change" -> {
                                    System.out.print("Введите новое кол-во жильцов: ");
                                    int newResidentsNumber = in.nextInt();
                                    chosenApartment.setResidentsNumber(newResidentsNumber);
                                    System.out.println(ColorScheme.ANSI_GREEN + "Кол-во жителей было изменено\n" + ColorScheme.ANSI_RESET);
                                }
                                case "remove" -> {
                                    chosenHouse.removeApartment(chosenApartment);
                                    System.out.println(ColorScheme.ANSI_GREEN + "Квартира была удалена\n\n" + ColorScheme.ANSI_RESET);
                                    continueApartmentLoop = false;
                                }
                                case "." -> continueApartmentLoop = false;
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
    }

    private Object[] getApartmentFromAccountingSystem(String num) {   //возвращает массив из двух элементов:
        boolean continueHouseLoop = true;                             //1 - квартира, 2 - номер дома в котором расположена квартира
        Scanner in = new Scanner(System.in);
        Apartment apartment = Apartment.templateApartment();
        int houseNumber = 0;
        while (continueHouseLoop) {
            System.out.print("Выберите дом где расположена " + num + " квартира:");
            houseNumber = in.nextInt();
            int finalHouseNumber = houseNumber;
            Optional<House> optChosenHouse = Arrays.stream(getHouses()).filter(e -> e.getNumber() == finalHouseNumber).findFirst();
            if (optChosenHouse.isPresent()) {
                House chosenHouse = optChosenHouse.get();
                if (chosenHouse.getApartments().length == 0) {
                    System.out.println(ColorScheme.ANSI_RED + "Дом " + chosenHouse.getNumber() +
                            " не содержит квартир" + ColorScheme.ANSI_RESET);
                    continue;
                } else {
                    System.out.println(ColorScheme.ANSI_CYAN + "Квартиры дома " + chosenHouse.getNumber()
                            + ":" + ColorScheme.ANSI_RESET);
                    Arrays.stream(chosenHouse.getApartments()).forEach(a ->
                            System.out.printf(Locale.US, "Этаж: %d, номер: %d, кол-во жителей: %d, площадь: %.1f\n",
                                    a.getFloor(), a.getNumber(), a.getResidentsNumber(), a.getSquare()));
                    System.out.println();
                }
                boolean continueApartmentLoop = true;
                while (continueApartmentLoop) {
                    System.out.print("Введите номер квартиры: ");
                    int apartmentNumber = in.nextInt();
                    Optional<Apartment> optApartment = Arrays.stream(chosenHouse.getApartments())
                            .filter(e -> e.getNumber() == apartmentNumber).findFirst();
                    if (optApartment.isPresent()) {
                        apartment = optApartment.get();
                        continueApartmentLoop = false;
                        continueHouseLoop = false;
                    } else {
                        System.out.println(ColorScheme.ANSI_RED + "Нет квартиры с таким номером" + ColorScheme.ANSI_RESET);
                    }
                }
            }
        }
        return new Object[]{apartment, houseNumber};
    }

    private House getHouseFromAccountingSystem(String num) {
        House house = House.templateHouse();
        Scanner in = new Scanner(System.in);
        boolean continueHouseLoop = true;
        while (continueHouseLoop) {
            System.out.print("Введите номер " + num + " дома:");
            int number = in.nextInt();
            Optional<House> optionalHouse = houses.stream().filter(e -> e.getNumber() == number).findFirst();
            if (optionalHouse.isPresent()) {
                house = optionalHouse.get();
                continueHouseLoop = false;
            } else
                System.out.println(ColorScheme.ANSI_RED + "Нет дома с таким номером\n" + ColorScheme.ANSI_RESET);
        }
        return house;
    }
}
