package userInterfaces.consoleUI;

import entities.apartmentBuilder.ApartmentBuilder;
import entities.apartmentBuilder.Director;
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

    public AccountingSystem getAccountingSystem() {
        return accountingSystem;
    }

    public void setAccountingSystem(AccountingSystem accountingSystem) {
        this.accountingSystem = accountingSystem;
    }

    public void launch() {
        boolean inProcess = true;
        while (inProcess) {
            System.out.println(ANSIColors.BLUE + "Доступные команды (система учёта)" + ANSIColors.RESET);
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
            System.out.print(ANSIColors.YELLOW + "Введите команду: " + ANSIColors.RESET);
            String command = in.next();
            System.out.println();

            switch (command.toLowerCase()) {
                case "add" -> goToAddHouseCase();
                case "choose" -> goToChooseHouseCase();
                case "compare" -> goToAccountingSystemCompareCase();
                case "show" -> goToShowHousesCase();
                case "info" -> goToShowAccountingSystemInfoCase();
                case "load" -> goToLoadAccountingSystemCase();
                case "save" -> goToSaveAccountingSystemCase();
                case "exit" -> inProcess = false;
                default -> System.out.println(ANSIColors.RED + "Неизвестная команда\n" + ANSIColors.RESET);
            }
        }
    }

    private void goToAccountingSystemCompareCase() {
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println(ANSIColors.BLUE + "Доступные сущности для сравнения" + ANSIColors.RESET);
            System.out.println("""
                    house - сравнить дома
                    apartment - сравнить квартиры
                    . - назад
                    """);
            System.out.print(ANSIColors.YELLOW + "Введите сущность: ");
            String entity = in.next();
            switch (entity) {
                case "apartment" -> goToApartmentCompareCase();
                case "house" -> goToHouseCompareCase();
                case "." -> continueLoop = false;
                default -> System.out.println(ANSIColors.RED + "Неизвестная сущность" + ANSIColors.RESET);
            }
        }
    }

    private void goToApartmentCompareCase() {
        if (accountingSystem.getNumberOfApartments() > 0) {
            System.out.println(ANSIColors.CYAN + "_СРАВНЕНИЕ КВАРТИР ПО ПАРАМЕТРАМ_" + ANSIColors.RESET);
            System.out.println(ANSIColors.BLUE + "_ПОИСК ПЕРВОЙ КВАРТИРЫ" + ANSIColors.RESET);
            Apartment apartment1 = getApartmentFromAccountingSystem();
            System.out.println(ANSIColors.BLUE + "_ПОИСК ВТОРОЙ КВАРТИРЫ_" + ANSIColors.RESET);
            Apartment apartment2 = getApartmentFromAccountingSystem();

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
            System.out.println(ANSIColors.RED + "В системе нет квартир" + ANSIColors.RESET);
        }
    }

    private Apartment getApartmentFromAccountingSystem() {
        goToShowHousesCase();
        House house = null;
        boolean continueHouseChoosing = true;
        while (continueHouseChoosing) {
            System.out.print(ANSIColors.YELLOW + "Введите номер дома, где находится квартира: " + ANSIColors.RESET);
            int number = in.nextInt();
            while ((house = accountingSystem.getHouseByNumber(number)) == null) {
                System.out.println(ANSIColors.RED + "Нет дома с таким номером" + ANSIColors.RESET);
                System.out.print(ANSIColors.YELLOW + "Введите номер дома, где находится квартира: "
                        + ANSIColors.RESET);
                number = in.nextInt();
            }
            if (house.getApartments().size() == 0) {
                System.out.println(ANSIColors.RED + "В доме " + house.getNumber() + " нет квартир"
                        + ANSIColors.RESET);
            } else continueHouseChoosing = false;
        }

        goToShowApartmentsCase(house);
        System.out.print(ANSIColors.YELLOW + "Введите номер квартиры: " + ANSIColors.RESET);
        int number = in.nextInt();
        Apartment apartment;
        while ((apartment = house.getApartmentByNumber(number)) == null) {
            System.out.println(ANSIColors.RED + "Нет квартиры с таким номером" + ANSIColors.RESET);
            System.out.print(ANSIColors.YELLOW + "Введите номер квартиры: " + ANSIColors.RESET);
            number = in.nextInt();
        }
        return apartment;
    }

    private House getHouseFromAccountingSystem() {
        System.out.println(ANSIColors.YELLOW + "Введите номер дома: " + ANSIColors.RESET);
        int number = in.nextInt();
        House house;
        while ((house = accountingSystem.getHouseByNumber(number)) == null) {
            System.out.println(ANSIColors.RED + "Нет дома с таким номером" + ANSIColors.RESET);
            System.out.println(ANSIColors.YELLOW + "Введите номер дома: " + ANSIColors.RESET);
            number = in.nextInt();
        }
        return house;
    }

    private void goToHouseCompareCase() {
        if (accountingSystem.getNumberOfHouses() > 0) {
            System.out.println(ANSIColors.CYAN + "_СРАВНЕНИЕ ДОМОВ ПО ПАРАМЕТРАМ_" + ANSIColors.RESET);
            goToShowHousesCase();
            System.out.println(ANSIColors.BLUE + "_ПОИСК ПЕРВОГО ДОМА_" + ANSIColors.RESET);
            House house1 = getHouseFromAccountingSystem();
            System.out.println(ANSIColors.BLUE + "_ПОИСК ВТОРОГО ДОМА_" + ANSIColors.RESET);
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
            System.out.println(ANSIColors.RED + "В системе нет домов" + ANSIColors.RESET);
        }
    }

    private void goToAddHouseCase() {
        System.out.println(ANSIColors.CYAN + "_ДОБАВЛЕНИЕ ДОМА В СИСТЕМУ_" + ANSIColors.RESET);
        System.out.print(ANSIColors.YELLOW + "Введите номер дома, который вы хотите добавить: " + ANSIColors.RESET);
        int number = in.nextInt();
        if (accountingSystem.containsHouse(number)) {
            System.out.println(ANSIColors.RED + "Дом с таким номером уже есть в системе" + ANSIColors.RESET);
        } else {
            try {
                accountingSystem.addHouse(new House(number));
                System.out.println(ANSIColors.GREEN + "Дом " + number + " был добавлен в систему" + ANSIColors.RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(ANSIColors.RED + e.getMessage());
            }

        }
        System.out.println();
    }

    private void goToShowHousesCase() {
        System.out.println(ANSIColors.CYAN + "Дома: " + ANSIColors.RESET);
        for (var house : accountingSystem.getHouses()) {
            System.out.println(house);
        }
        System.out.println();
    }

    private void goToShowAccountingSystemInfoCase() {
        System.out.println(ANSIColors.CYAN + "_ОБЩАЯ ИНФОРМАЦИЯ О СИСТЕМЕ_" + ANSIColors.RESET);
        System.out.println("Кол-во домов: " + accountingSystem.getNumberOfHouses());
        System.out.println("Кол-во квартир: " + accountingSystem.getNumberOfApartments());
        System.out.println();
    }

    private void goToLoadAccountingSystemCase() {
        System.out.println(ANSIColors.CYAN + "_ЗАГРУЗКА СИСТЕМЫ УЧЁТА ИЗ ФАЙЛА_" + ANSIColors.RESET);
        System.out.print(ANSIColors.YELLOW + "Введите путь к файлу: " + ANSIColors.RESET);
        Path path = Paths.get(in.next());
        try {
            accountingSystem.load(path.toString());
            System.out.println(ANSIColors.GREEN + "Система была загружена из файла " + path.getFileName()
                    + ANSIColors.RESET);
        } catch (FileNotFoundException e) {
            System.out.println(ANSIColors.RED + "Файл не найден" + ANSIColors.RESET);
        } catch (ClassNotFoundException e) {
            System.out.println(ANSIColors.RED + "Не удалось прочитать файл" + ANSIColors.RESET);
        } catch (IOException e) {
            System.out.println(ANSIColors.RED + "Ошибка потока ввода/вывода" + ANSIColors.RESET);
        }
        System.out.println();
    }

    private void goToSaveAccountingSystemCase() {
        System.out.println(ANSIColors.CYAN + "_СОХРАНЕНИЕ СИСТЕМЫ УЧЁТА В ФАЙЛ_" + ANSIColors.RESET);
        System.out.print(ANSIColors.YELLOW + "Введите путь к файлу: " + ANSIColors.RESET);
        Path path = Paths.get(in.next());
        try {
            accountingSystem.save(path.toString());
            System.out.println(ANSIColors.GREEN + "Система была сохранена в файл " + path.getFileName()
                    + ANSIColors.RESET);
        } catch (IOException e) {
            System.out.println(ANSIColors.RED + "Ошибка потока ввода/вывода" + ANSIColors.RESET);
        }
        System.out.println();
    }

    private void goToChooseHouseCase() {
        System.out.println(ANSIColors.CYAN + "_ВЫБОР ДОМА В СИСТЕМЕ_" + ANSIColors.RESET);
        goToShowHousesCase();
        System.out.print(ANSIColors.YELLOW + "Введите номер дома: " + ANSIColors.RESET);
        int number = in.nextInt();
        System.out.println();
        House chosenHouse = accountingSystem.getHouseByNumber(number);
        if (chosenHouse == null) {
            System.out.println(ANSIColors.RED + "Дома " + number + " нет в системе" + ANSIColors.RESET);
        } else {
            boolean continueHouseLoop = true;
            while (continueHouseLoop) {
                System.out.println(ANSIColors.BLUE + "Доступные команды (дом)" + ANSIColors.RESET);
                System.out.println("""
                        add - добавить квартиру в дом
                        generate - сгенерировать квартиры
                        remove - удалить дом
                        choose - выбрать квартиру в доме
                        show - показать все квартиры в доме
                        info - вывести общую информацию о доме
                        . - назад
                        """);
                System.out.print(ANSIColors.YELLOW + "Введите команду: " + ANSIColors.RESET);
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
                    default -> System.out.println(ANSIColors.RED + "Неизвестная команда\n" + ANSIColors.RESET);
                }
            }
        }
        System.out.println();
    }

    private void goToGenerateApartmentsCase(House house) {
        System.out.println(ANSIColors.CYAN + "_ГЕНЕРИРОВАНИЕ КВАРТИР В ДОМЕ " + house.getNumber() + "_");
        System.out.print(ANSIColors.YELLOW + "Введите кол-во квартир, которое вы хотите сгенерировать: "
                + ANSIColors.RESET);
        int numberOfApartments = in.nextInt();
        if (numberOfApartments > 0) {
            house.clear();
            System.out.print(ANSIColors.YELLOW + "Введите кол-во квартир на этаж: " + ANSIColors.RESET);
            int apartmentsInFloor = in.nextInt();
            if (apartmentsInFloor > 0) {
                ApartmentBuilder builder = new ApartmentBuilder();
                for (int number = 1; number <= numberOfApartments; number++) {
                    int floor = number / apartmentsInFloor + 1;
                    Director.generateApartmentWithNumberAndFloor(builder, number, floor);
                    house.addApartment(builder.getResult());
                }
                System.out.println(ANSIColors.GREEN.code + numberOfApartments + " квартир было добавлено в " + house
                        + ANSIColors.RESET);
            } else {
                System.out.println(ANSIColors.RED + "Ошибка! Введено отрицательное либо нулевое число квартир на этаж!"
                        + ANSIColors.RESET);
            }
        } else {
            System.out.println(ANSIColors.RED + "Ошибка! Введено отрицательное либо нулевое число квартир!"
                    + ANSIColors.RESET);
        }
        System.out.println();
    }

    private void goToAddApartmentCase(House house) {
        System.out.println(ANSIColors.CYAN + "_ДОБАВЛЕНИЕ КВАРТИРЫ В ДОМ " + house.getNumber() + ANSIColors.RESET);
        System.out.print(ANSIColors.YELLOW + "Введите номер квартиры: " + ANSIColors.RESET);
        int number = in.nextInt();
        if (house.containsApartment(number)) {
            System.out.println(ANSIColors.RED + "Квартира с таким номером уже есть в доме" + ANSIColors.RESET);
            return;
        }
        System.out.print(ANSIColors.YELLOW + "Введите этаж: " + ANSIColors.RESET);
        int floor = in.nextInt();
        System.out.print(ANSIColors.YELLOW + "Введите кол-во жителей: " + ANSIColors.RESET);
        int residents = in.nextInt();
        System.out.print(ANSIColors.YELLOW + "Введите кол-во комнат: " + ANSIColors.RESET);
        int rooms = in.nextInt();
        System.out.print(ANSIColors.YELLOW + "Введите площадь квартиры (м^2): " + ANSIColors.RESET);
        float square = in.nextFloat();
        try {
            Apartment apartment = new Apartment(number, floor, rooms, residents, square);
            house.addApartment(apartment);
            System.out.println(ANSIColors.GREEN + "Квартира была добавлена в дом " + house.getNumber()
                    + ANSIColors.RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(ANSIColors.RED + "Ошибка! " + e.getMessage() + ANSIColors.RESET);
        }
        System.out.println();
    }

    private void goToRemoveHouseCase(House house) {
        accountingSystem.removeHouse(house);
        System.out.println(ANSIColors.RED.code + house + " был удалён\n" + ANSIColors.RESET);
    }

    private void goToShowApartmentsCase(House house) {
        System.out.println(ANSIColors.CYAN + "Квартиры дома " + house.getNumber() + ":" + ANSIColors.RESET);
        for (var apartment : house.getApartments()) {
            System.out.println(apartment);
        }
        System.out.println();
    }

    private void goToShowHouseInfoCase(House house) {
        System.out.println(ANSIColors.CYAN + "_ОБЩАЯ ИНФОРМАЦИЯ О ДОМЕ " + house.getNumber() + "_" + ANSIColors.RESET);
        System.out.println("Этажность: " + HouseService.calculateNumberOfFloors(house));
        System.out.println("Кол-во квартир: " + house.getApartments().size());
        System.out.printf(Locale.US, "Общая площадь: %.1f м^2\n", HouseService.calculateFullSquare(house));
        System.out.println("Кол-во жильцов: " + HouseService.calculatePopulation(house));
        System.out.println();
    }

    private void goToChooseApartmentCase(House house) {
        System.out.println(ANSIColors.CYAN + "_ВЫБОР КВАРТИРЫ ДОМА " + house.getNumber() + "_" + ANSIColors.RESET);
        goToShowApartmentsCase(house);
        System.out.print(ANSIColors.YELLOW + "Введите номер квартиры: " + ANSIColors.RESET);
        int number = in.nextInt();
        System.out.println();
        Apartment apartment = house.getApartmentByNumber(number);
        if (apartment == null) {
            System.out.println(ANSIColors.RED + "В доме " + house.getNumber() + " нет квартиры с таким номером");
        } else {
            boolean continueApartmentLoop = true;
            while (continueApartmentLoop) {
                System.out.println(ANSIColors.BLUE + "Доступные команды (квартира)" + ANSIColors.RESET);
                System.out.println("""
                        remove - удалить квартиру
                        info - вывести общую информацию о квартире
                        change - изменить параметр квартиры
                        . - назад
                        """);
                System.out.print(ANSIColors.YELLOW + "Введите команду: " + ANSIColors.RESET);
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
                    default -> System.out.println(ANSIColors.RED + "Неизвестная команда\n" + ANSIColors.RESET);
                }
            }
        }
        System.out.println();
    }

    private void goToRemoveApartmentCase(House house, Apartment apartment) {
        house.removeApartment(apartment);
        System.out.println(ANSIColors.GREEN + "Квартира " + apartment.getNumber() + " была удалена из дома "
                + house.getNumber() + "\n" + ANSIColors.RESET);
    }

    private void goToShowApartmentInfoCase(House house, Apartment apartment) {
        System.out.println(ANSIColors.CYAN + "_ОБЩАЯ ИНФОРМАЦИЯ О КВАРТИРЕ " + apartment.getNumber() + "_"
                + ANSIColors.RESET);
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
            System.out.println(ANSIColors.CYAN + "_ИЗМЕНЕНИЕ ПАРАМЕТРОВ КВАРТИРЫ_" + ANSIColors.RESET);
            System.out.println(apartment);
            System.out.println("""
                    floor - изменить этаж
                    rooms - изменить кол-во комнат
                    square - изменить площадь
                    residents - изменить кол-во жителей
                    . - назад
                    """);

            System.out.print(ANSIColors.YELLOW + "Введите параметр: " + ANSIColors.RESET);
            String parameter = in.next();
            System.out.println();
            try {
                switch (parameter.toLowerCase()) {
                    case "floor" -> {
                        System.out.print(ANSIColors.YELLOW + "Введите новый этаж: " + ANSIColors.RESET);
                        int floor = in.nextInt();
                        apartment.setFloor(floor);
                        System.out.println(ANSIColors.GREEN + "Номер этажа квартиры был изменён" + ANSIColors.RESET);
                    }
                    case "rooms" -> {
                        System.out.print(ANSIColors.YELLOW + "Введите новое кол-во комнат: " + ANSIColors.RESET);
                        int rooms = in.nextInt();
                        apartment.setRoomsNumber(rooms);
                        System.out.println(ANSIColors.GREEN + "Кол-во комнат квартиры было изменено"
                                + ANSIColors.RESET);
                    }
                    case "square" -> {
                        System.out.print(ANSIColors.YELLOW + "Введите новое значение площади: " + ANSIColors.RESET);
                        float square = in.nextFloat();
                        apartment.setSquare(square);
                        System.out.println(ANSIColors.GREEN + "Значение площади квартиры был изменено"
                                + ANSIColors.RESET);
                    }
                    case "residents" -> {
                        System.out.print(ANSIColors.YELLOW + "Введите новое кол-во жителей: " + ANSIColors.RESET);
                        int residents = in.nextInt();
                        apartment.setResidentsNumber(residents);
                        System.out.println(ANSIColors.GREEN + "Кол-во жителей квартиры было изменено"
                                + ANSIColors.RESET);
                    }
                    case "." -> continueLoop = false;
                    default -> System.out.println(ANSIColors.RED + "Неизвестный параметр" + ANSIColors.RESET);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ANSIColors.RED + "Ошибка! " + e.getMessage() + ANSIColors.RESET);
            }
            System.out.println();
        }
    }
}