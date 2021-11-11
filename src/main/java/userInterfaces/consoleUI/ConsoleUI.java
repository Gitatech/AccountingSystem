package userInterfaces.consoleUI;

import builders.apartmentBuilder.ApartmentBuilder;
import builders.apartmentBuilder.Director;
import entities.AccountingSystem;
import entities.Apartment;
import entities.House;
import services.ApartmentService;
import services.ComparatorService;
import services.HouseService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner in;
    private AccountingSystem accountingSystem;

    public ConsoleUI(AccountingSystem accountingSystem) {
        this.accountingSystem = accountingSystem;
        in = new Scanner(System.in);
        in.useLocale(Locale.US);
    }

    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI(new AccountingSystem());
        ui.launch();
    }

    public AccountingSystem getAccountingSystem() {
        return accountingSystem;
    }

    public void setAccountingSystem(AccountingSystem accountingSystem) {
        this.accountingSystem = accountingSystem;
    }

    public void launch() {
        boolean inProcess = true;
        while (inProcess) {
            System.out.println(Colors.ANSI_BLUE + "Доступные команды (система учёта)" + Colors.ANSI_RESET);
            System.out.println("""
                    add - добавить дом в систему
                    choose - выбрать дом
                    compare - сравнить дома/квартиры в системе
                    show - показать дома, находящиеся в системе
                    info - общая информация о системе
                    load - загрузить систему из файла
                    save - сохранить систему в файл
                    exit - выход
                    """);
            System.out.print(Colors.ANSI_YELLOW + "Введите команду: " + Colors.ANSI_RESET);
            String command = in.next();
            System.out.println();

            switch (command.toLowerCase()) {
                case "add" -> goToAddHouseCase();
                case "choose" -> goToChooseHouseCase();
                case "compare" -> goToAccountingSystemCompareCase();
                case "show" -> goToShowHousesCase();
                case "info" -> goToShowAccountignSystemInfoCase();
                case "load" -> goToLoadAccountingSystemCase();
                case "save" -> goToSaveAccountingSystemCase();
                case "exit" -> inProcess = false;
                default -> System.out.println(Colors.ANSI_RED + "Неизвестная команда\n" + Colors.ANSI_RESET);
            }
        }
    }

    private void goToAccountingSystemCompareCase() {
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println(Colors.ANSI_BLUE + "Доступные сущности для сравнения" + Colors.ANSI_RESET);
            System.out.println("""
                    house - сравнить дома
                    apartment - сравнить квартиры
                    . - назад
                    """);
            System.out.print(Colors.ANSI_YELLOW + "Введите сущность: ");
            String entity = in.next();
            switch (entity) {
                case "apartment" -> goToApartmentCompareCase();
                case "house" -> goToHouseCompareCase();
                case "." -> continueLoop = false;
                default -> System.out.println(Colors.ANSI_RED + "Неизвестная сущность" + Colors.ANSI_RESET);
            }
        }
    }

    private void goToApartmentCompareCase() {
        if (accountingSystem.getNumberOfApartments() > 0) {
            System.out.println(Colors.ANSI_CYAN + "_СРАВНЕНИЕ КВАРТИР ПО ПАРАМЕТРАМ_" + Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_BLUE + "_ПОИСК ПЕРВОЙ КВАРТИРЫ" + Colors.ANSI_RESET);
            Apartment apartment1 = getApartmentFromAccountignSystem();
            System.out.println(Colors.ANSI_BLUE + "_ПОИСК ВТОРОЙ КВАРТИРЫ_" + Colors.ANSI_RESET);
            Apartment apartment2 = getApartmentFromAccountignSystem();

            char floorSign = ComparatorService.getComparisonSign(apartment1, apartment2,
                    ApartmentService::compareByFloor);
            char squareSign = ComparatorService.getComparisonSign(apartment1, apartment2,
                    ApartmentService::compareBySquare);
            char residentsSign = ComparatorService.getComparisonSign(apartment1, apartment2,
                    ApartmentService::compareByResidents);
            char roomsSign = ComparatorService.getComparisonSign(apartment1, apartment2,
                    ApartmentService::compareByNumberOfRooms);
            System.out.printf(Locale.US, """
                            %-20s %-7d %1s %7d
                            %-20s %-7d %1s %7d
                            %-20s %-7d %1s %7d
                            %-20s %-7.1f %1s %7.1f
                            %-20s %-7d %1s %7d
                                                
                            """,
                    "Номер квартиры", apartment1.getNumber(), ' ', apartment2.getNumber(),
                    "Этаж", apartment1.getFloor(), floorSign, apartment2.getFloor(),
                    "Кол-во комнат", apartment1.getRoomsNumber(), roomsSign, apartment2.getRoomsNumber(),
                    "Площадь", apartment1.getSquare(), squareSign, apartment2.getSquare(),
                    "Кол-во жителей", apartment1.getResidentsNumber(), residentsSign, apartment2.getResidentsNumber());
        } else {
            System.out.println(Colors.ANSI_RED + "В системе нет квартир" + Colors.ANSI_RESET);
        }
    }

    private Apartment getApartmentFromAccountignSystem() {
        goToShowHousesCase();
        House house = null;
        boolean continueHouseChoosing = true;
        while (continueHouseChoosing) {
            System.out.print(Colors.ANSI_YELLOW + "Введите номер дома, где находится квартира: " + Colors.ANSI_RESET);
            int number = in.nextInt();
            while ((house = accountingSystem.getHouseByNumber(number)) == null) {
                System.out.println(Colors.ANSI_RED + "Нет дома с таким номером" + Colors.ANSI_RESET);
                System.out.print(Colors.ANSI_YELLOW + "Введите номер дома, где находится квартира: "
                        + Colors.ANSI_RESET);
                number = in.nextInt();
            }
            if (house.getApartments().size() == 0) {
                System.out.println(Colors.ANSI_RED + "В доме " + house.getNumber() + " нет квартир"
                        + Colors.ANSI_RESET);
            } else continueHouseChoosing = false;
        }

        goToShowApartmentsCase(house);
        System.out.print(Colors.ANSI_YELLOW + "Введите номер квартиры: " + Colors.ANSI_RESET);
        int number = in.nextInt();
        Apartment apartment;
        while ((apartment = house.getApartmentByNumber(number)) == null) {
            System.out.println(Colors.ANSI_RED + "Нет квартиры с таким номером" + Colors.ANSI_RESET);
            System.out.print(Colors.ANSI_YELLOW + "Введите номер квартиры: " + Colors.ANSI_RESET);
            number = in.nextInt();
        }
        return apartment;
    }

    private House getHouseFromAccountingSystem() {
        System.out.println(Colors.ANSI_YELLOW + "Введите номер дома: " + Colors.ANSI_RESET);
        int number = in.nextInt();
        House house;
        while ((house = accountingSystem.getHouseByNumber(number)) == null) {
            System.out.println(Colors.ANSI_RED + "Нет дома с таким номером" + Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_YELLOW + "Введите номер дома: " + Colors.ANSI_RESET);
            number = in.nextInt();
        }
        return house;
    }

    private void goToHouseCompareCase() {
        if (accountingSystem.getNumberOfHouses() > 0) {
            System.out.println(Colors.ANSI_CYAN + "_СРАВНЕНИЕ ДОМОВ ПО ПАРАМЕТРАМ_" + Colors.ANSI_RESET);
            goToShowHousesCase();
            System.out.println(Colors.ANSI_BLUE + "_ПОИСК ПЕРВОГО ДОМА_" + Colors.ANSI_RESET);
            House house1 = getHouseFromAccountingSystem();
            System.out.println(Colors.ANSI_BLUE + "_ПОИСК ВТОРОГО ДОМА_" + Colors.ANSI_RESET);
            House house2 = getHouseFromAccountingSystem();
            char populationSign = ComparatorService.getComparisonSign(house1, house2,
                    HouseService::compareByPopulation);
            char squareSign = ComparatorService.getComparisonSign(house1, house2,
                    HouseService::compareByFullSquare);
            char floorSign = ComparatorService.getComparisonSign(house1, house2,
                    HouseService::compareByFloors);
            char apartmentsSign = ComparatorService.getComparisonSign(house1, house2,
                    HouseService::compareByApartmentsNumber);
            System.out.printf(Locale.US, """
                            %-20s %-7d %1s %5d
                            %-20s %-7d %1s %5d
                            %-20s %-7d %1s %5d
                            %-20s %-7d %1s %5d
                            %-20s %-7.1f %1s %5.1f
                                                
                            """,
                    "Номер дома", house1.getNumber(), ' ', house2.getNumber(),
                    "Этажность", HouseService.calculateNumberOfFloors(house1), floorSign,
                    HouseService.calculateNumberOfFloors(house2),
                    "Кол-во квартир", house1.getApartments().size(), apartmentsSign, house2.getApartments().size(),
                    "Кол-во жителей", HouseService.calculatePopulation(house1), populationSign,
                    HouseService.calculatePopulation(house2),
                    "Площадь", HouseService.calculateFullSquare(house1), squareSign,
                    HouseService.calculateFullSquare(house2));
        } else {
            System.out.println(Colors.ANSI_RED + "В системе нет домов" + Colors.ANSI_RESET);
        }
    }

    private void goToAddHouseCase() {
        System.out.println(Colors.ANSI_CYAN + "_ДОБАВЛЕНИЕ ДОМА В СИСТЕМУ_" + Colors.ANSI_RESET);
        System.out.print(Colors.ANSI_YELLOW + "Введите номер дома, который вы хотите добавить: " + Colors.ANSI_RESET);
        int number = in.nextInt();
        if (accountingSystem.containsHouse(number)) {
            System.out.println(Colors.ANSI_RED + "Дом с таким номером уже есть в системе" + Colors.ANSI_RESET);
        } else {
            try {
                accountingSystem.addHouse(new House(number));
                System.out.println(Colors.ANSI_GREEN + "Дом " + number + " был добавлен в систему" + Colors.ANSI_RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(Colors.ANSI_RED + e.getMessage());
            }

        }
        System.out.println();
    }

    private void goToShowHousesCase() {
        System.out.println(Colors.ANSI_CYAN + "Дома: " + Colors.ANSI_RESET);
        for (var house : accountingSystem.getHouses()) {
            System.out.println(house);
        }
        System.out.println();
    }

    private void goToShowAccountignSystemInfoCase() {
        System.out.println(Colors.ANSI_CYAN + "_ОБЩАЯ ИНФОРМАЦИЯ О СИСТЕМЕ_" + Colors.ANSI_RESET);
        System.out.println("Кол-во домов: " + accountingSystem.getNumberOfHouses());
        System.out.println("Кол-во квартир: " + accountingSystem.getNumberOfApartments());
        System.out.println();
    }

    private void goToLoadAccountingSystemCase() {
        System.out.println(Colors.ANSI_CYAN + "_ЗАГРУЗКА СИСТЕМЫ УЧЁТА ИЗ ФАЙЛА_" + Colors.ANSI_RESET);
        System.out.print(Colors.ANSI_YELLOW + "Введите путь к файлу: " + Colors.ANSI_RESET);
        Path path = Paths.get(in.next());
        try {
            accountingSystem.load(path.toString());
            System.out.println(Colors.ANSI_GREEN + "Система была загружена из файла " + path.getFileName()
                    + Colors.ANSI_RESET);
        } catch (FileNotFoundException e) {
            System.out.println(Colors.ANSI_RED + "Файл не найден" + Colors.ANSI_RESET);
        } catch (ClassNotFoundException e) {
            System.out.println(Colors.ANSI_RED + "Не удалось прочитать файл" + Colors.ANSI_RESET);
        } catch (IOException e) {
            System.out.println(Colors.ANSI_RED + "Ошибка потока ввода/вывода" + Colors.ANSI_RESET);
        }
        System.out.println();
    }

    private void goToSaveAccountingSystemCase() {
        System.out.println(Colors.ANSI_CYAN + "_СОХРАНЕНИЕ СИСТЕМЫ УЧЁТА В ФАЙЛ_" + Colors.ANSI_RESET);
        System.out.print(Colors.ANSI_YELLOW + "Введите путь к файлу: " + Colors.ANSI_RESET);
        Path path = Paths.get(in.next());
        try {
            accountingSystem.save(path.toString());
            System.out.println(Colors.ANSI_GREEN + "Система была сохранена в файл " + path.getFileName()
                    + Colors.ANSI_RESET);
        } catch (IOException e) {
            System.out.println(Colors.ANSI_RED + "Ошибка потока ввода/вывода" + Colors.ANSI_RESET);
        }
        System.out.println();
    }

    private void goToChooseHouseCase() {
        System.out.println(Colors.ANSI_CYAN + "_ВЫБОР ДОМА В СИСТЕМЕ_" + Colors.ANSI_RESET);
        goToShowHousesCase();
        System.out.print(Colors.ANSI_YELLOW + "Введите номер дома: " + Colors.ANSI_RESET);
        int number = in.nextInt();
        System.out.println();
        House chosenHouse = accountingSystem.getHouseByNumber(number);
        if (chosenHouse == null) {
            System.out.println(Colors.ANSI_RED + "Дома " + number + " нет в системе" + Colors.ANSI_RESET);
        } else {
            boolean continueHouseLoop = true;
            while (continueHouseLoop) {
                System.out.println(Colors.ANSI_BLUE + "Доступные команды (дом)" + Colors.ANSI_RESET);
                System.out.println("""
                        add - добавить квартиру в дом
                        generate - сгенерировать квартиры
                        remove - удалить дом
                        choose - выбрать квартиру в доме
                        show - показать все квартиры в доме
                        info - вывести общую информацию о доме
                        . - назад
                        """);
                System.out.print(Colors.ANSI_YELLOW + "Введите команду: " + Colors.ANSI_RESET);
                String command = in.next();
                System.out.println();
                switch (command.toLowerCase()) {
                    case "add" -> goToAddApartmentCase(chosenHouse);
                    case "generate" -> goToGenerateApartmentsCase(chosenHouse);
                    case "remove" -> {
                        goToRemoveHouseCase(chosenHouse);
                        continueHouseLoop = false;
                    }
                    case "choose" -> goToChooseApartmentCase(chosenHouse);
                    case "show" -> goToShowApartmentsCase(chosenHouse);
                    case "info" -> goToShowHouseInfoCase(chosenHouse);
                    case "." -> continueHouseLoop = false;
                    default -> System.out.println(Colors.ANSI_RED + "Неизвестная команда\n" + Colors.ANSI_RESET);
                }
            }
        }
        System.out.println();
    }

    private void goToGenerateApartmentsCase(House house) {
        System.out.println(Colors.ANSI_CYAN + "_ГЕНЕРИРОВАНИЕ КВАРТИР В ДОМЕ " + house.getNumber() + "_");
        System.out.print(Colors.ANSI_YELLOW + "Введите кол-во квартир, которое вы хотите сгенерировать: "
                + Colors.ANSI_RESET);
        int numberOfApartments = in.nextInt();
        if (numberOfApartments > 0) {
            house.clear();
            System.out.print(Colors.ANSI_YELLOW + "Введите кол-во квартир на этаж: " + Colors.ANSI_RESET);
            int apartmentsInFloor = in.nextInt();
            if (apartmentsInFloor > 0) {
                ApartmentBuilder builder = new ApartmentBuilder();
                for (int number = 1; number <= numberOfApartments; number++) {
                    int floor = number / apartmentsInFloor + 1;
                    Director.generateApartmentWithNumberAndFloor(builder, number, floor);
                    house.addApartment(builder.getResult());
                }
                System.out.println(Colors.ANSI_GREEN + "" + numberOfApartments + " квартир было добавлено в " + house
                        + Colors.ANSI_RESET);
            } else {
                System.out.println(Colors.ANSI_RED + "Ошибка! Введено отрицательное либо нулевое число квартир на этаж!"
                        + Colors.ANSI_RESET);
            }
        } else {
            System.out.println(Colors.ANSI_RED + "Ошибка! Введено отрицательное либо нулевое число квартир!"
                    + Colors.ANSI_RESET);
        }
        System.out.println();
    }

    private void goToAddApartmentCase(House house) {
        System.out.println(Colors.ANSI_CYAN + "_ДОБАВЛЕНИЕ КВАРТИРЫ В ДОМ " + house.getNumber() + Colors.ANSI_RESET);
        System.out.print(Colors.ANSI_YELLOW + "Введите номер квартиры: " + Colors.ANSI_RESET);
        int number = in.nextInt();
        if (house.containsApartment(number)) {
            System.out.println(Colors.ANSI_RED + "Квартира с таким номером уже есть в доме" + Colors.ANSI_RESET);
            return;
        }
        System.out.print(Colors.ANSI_YELLOW + "Введите этаж: " + Colors.ANSI_RESET);
        int floor = in.nextInt();
        System.out.print(Colors.ANSI_YELLOW + "Введите кол-во жителей: " + Colors.ANSI_RESET);
        int residents = in.nextInt();
        System.out.print(Colors.ANSI_YELLOW + "Введите кол-во комнат: " + Colors.ANSI_RESET);
        int rooms = in.nextInt();
        System.out.print(Colors.ANSI_YELLOW + "Введите площадь квартиры (м^2): " + Colors.ANSI_RESET);
        float square = in.nextFloat();
        try {
            Apartment apartment = new Apartment(number, floor, rooms, residents, square);
            house.addApartment(apartment);
            System.out.println(Colors.ANSI_GREEN + "Квартира была добавлена в дом " + house.getNumber()
                    + Colors.ANSI_RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(Colors.ANSI_RED + "Ошибка! " + e.getMessage() + Colors.ANSI_RESET);
        }
        System.out.println();
    }

    private void goToRemoveHouseCase(House house) {
        accountingSystem.removeHouse(house);
        System.out.println(Colors.ANSI_RED + "" + house + " был удалён\n" + Colors.ANSI_RESET);
    }

    private void goToShowApartmentsCase(House house) {
        System.out.println(Colors.ANSI_CYAN + "Квартиры дома " + house.getNumber() + ":" + Colors.ANSI_RESET);
        for (var apartment : house.getApartments()) {
            System.out.println(apartment);
        }
        System.out.println();
    }

    private void goToShowHouseInfoCase(House house) {
        System.out.println(Colors.ANSI_CYAN + "_ОБЩАЯ ИНФОРМАЦИЯ О ДОМЕ " + house.getNumber() + "_" + Colors.ANSI_RESET);
        System.out.println("Этажность: " + HouseService.calculateNumberOfFloors(house));
        System.out.println("Кол-во квартир: " + house.getApartments().size());
        System.out.printf(Locale.US, "Общая площадь: %.1f м^2\n", HouseService.calculateFullSquare(house));
        System.out.println("Кол-во жильцов: " + HouseService.calculatePopulation(house));
        System.out.println();
    }

    private void goToChooseApartmentCase(House house) {
        System.out.println(Colors.ANSI_CYAN + "_ВЫБОР КВАРТИРЫ ДОМА " + house.getNumber() + "_" + Colors.ANSI_RESET);
        goToShowApartmentsCase(house);
        System.out.print(Colors.ANSI_YELLOW + "Введите номер квартиры: " + Colors.ANSI_RESET);
        int number = in.nextInt();
        System.out.println();
        Apartment apartment = house.getApartmentByNumber(number);
        if (apartment == null) {
            System.out.println(Colors.ANSI_RED + "В доме " + house.getNumber() + " нет квартиры с таким номером");
        } else {
            boolean continueApartmentLoop = true;
            while (continueApartmentLoop) {
                System.out.println(Colors.ANSI_BLUE + "Доступные команды (квартира)" + Colors.ANSI_RESET);
                System.out.println("""
                        remove - удалить квартиру
                        info - вывести общую информацию о квартире
                        change - изменить параметр квартиры
                        . - назад
                        """);
                System.out.print(Colors.ANSI_YELLOW + "Введите команду: " + Colors.ANSI_RESET);
                String command = in.next();
                System.out.println();
                switch (command.toLowerCase()) {
                    case "remove" -> {
                        goToRemoveApartmentCase(house, apartment);
                        continueApartmentLoop = false;
                    }
                    case "info" -> goToShowApartmentInfoCase(house, apartment);
                    case "change" -> goToChangeApartmentParameterCase(apartment);
                    case "." -> continueApartmentLoop = false;
                    default -> System.out.println(Colors.ANSI_RED + "Неизвестная команда\n" + Colors.ANSI_RESET);
                }
            }
        }
        System.out.println();
    }

    private void goToRemoveApartmentCase(House house, Apartment apartment) {
        house.removeApartment(apartment);
        System.out.println(Colors.ANSI_GREEN + "Квартира " + apartment.getNumber() + " была удалена из дома "
                + house.getNumber() + "\n" + Colors.ANSI_RESET);
    }

    private void goToShowApartmentInfoCase(House house, Apartment apartment) {
        System.out.println(Colors.ANSI_CYAN + "_ОБЩАЯ ИНФОРМАЦИЯ О КВАРТИРЕ " + apartment.getNumber() + "_"
                + Colors.ANSI_RESET);
        System.out.printf(Locale.US, """
                        Номер дома: %d
                        Этаж: %d
                        Номер квартиры: %d
                        Кол-во комнат: %d
                        Площадь: %.1f м^2
                        Кол-во жителей: %d
                                                
                        """,
                house.getNumber(), apartment.getFloor(), apartment.getNumber(), apartment.getRoomsNumber(),
                apartment.getSquare(), apartment.getResidentsNumber());
    }

    private void goToChangeApartmentParameterCase(Apartment apartment) {
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println(Colors.ANSI_CYAN + "_ИЗМЕНЕНИЕ ПАРАМЕТРОВ КВАРТИРЫ_" + Colors.ANSI_RESET);
            System.out.println(apartment);
            System.out.println("""
                    floor - изменить этаж
                    rooms - изменить кол-во комнат
                    square - изменить площадь
                    residents - изменить кол-во жителей
                    . - назад
                    """);

            System.out.print(Colors.ANSI_YELLOW + "Введите параметр: " + Colors.ANSI_RESET);
            String parameter = in.next();
            System.out.println();
            try {
                switch (parameter.toLowerCase()) {
                    case "floor" -> {
                        System.out.print(Colors.ANSI_YELLOW + "Введите новый этаж: " + Colors.ANSI_RESET);
                        int floor = in.nextInt();
                        apartment.setFloor(floor);
                        System.out.println(Colors.ANSI_GREEN + "Номер этажа квартиры был изменён" + Colors.ANSI_RESET);
                    }
                    case "rooms" -> {
                        System.out.print(Colors.ANSI_YELLOW + "Введите новое кол-во комнат: " + Colors.ANSI_RESET);
                        int rooms = in.nextInt();
                        apartment.setRoomsNumber(rooms);
                        System.out.println(Colors.ANSI_GREEN + "Кол-во комнат квартиры было изменено"
                                + Colors.ANSI_RESET);
                    }
                    case "square" -> {
                        System.out.print(Colors.ANSI_YELLOW + "Введите новое значение площади: " + Colors.ANSI_RESET);
                        float square = in.nextFloat();
                        apartment.setSquare(square);
                        System.out.println(Colors.ANSI_GREEN + "Значение площади квартиры был изменено"
                                + Colors.ANSI_RESET);
                    }
                    case "residents" -> {
                        System.out.print(Colors.ANSI_YELLOW + "Введите новое кол-во жителей: " + Colors.ANSI_RESET);
                        int residents = in.nextInt();
                        apartment.setResidentsNumber(residents);
                        System.out.println(Colors.ANSI_GREEN + "Кол-во жителей квартиры было изменено"
                                + Colors.ANSI_RESET);
                    }
                    case "." -> continueLoop = false;
                    default -> System.out.println(Colors.ANSI_RED + "Неизвестный параметр" + Colors.ANSI_RESET);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(Colors.ANSI_RED + "Ошибка! " + e.getMessage() + Colors.ANSI_RESET);
            }
            System.out.println();
        }
    }
}