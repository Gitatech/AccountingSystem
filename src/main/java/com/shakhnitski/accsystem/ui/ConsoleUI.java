package com.shakhnitski.accsystem.ui;

import com.shakhnitski.accsystem.builder.ApartmentBuilder;
import com.shakhnitski.accsystem.builder.Director;
import com.shakhnitski.accsystem.entity.AccountingSystem;
import com.shakhnitski.accsystem.entity.Apartment;
import com.shakhnitski.accsystem.entity.House;
import com.shakhnitski.accsystem.serializer.AccountingSystemSerializer;
import com.shakhnitski.accsystem.service.AccountingSystemService;
import com.shakhnitski.accsystem.service.ApartmentService;
import com.shakhnitski.accsystem.service.ComparatorService;
import com.shakhnitski.accsystem.service.HouseService;

import java.io.FileNotFoundException;
import java.io.IOException;
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
            System.out.println("Доступные команды (система учёта)");
            System.out.println("""
                    add - добавить дом в систему
                    choose - выбрать дом
                    compare - сравнить дома/квартиры в системе
                    show - показать дома, находящиеся в системе
                    info - общая информация о системе
                    save - сохранить системы в файл
                    load - прочитать систему из файла
                    exit - выход
                    """);
            System.out.print("Введите команду: ");
            String command = in.next();
            System.out.println();

            switch (command.toLowerCase()) {
                case "add" -> goToAddHouseCase();
                case "choose" -> goToChooseHouseCase();
                case "compare" -> goToAccountingSystemCompareCase();
                case "show" -> goToShowHousesCase();
                case "info" -> goToShowAccountignSystemInfoCase();
                case "save" -> goToSaveAccountingSystemCase();
                case "load" -> goToLoadAccountingSystemCase();
                case "exit" -> inProcess = false;
                default -> System.out.println("Неизвестная команда\n");
            }
        }
    }

    private void goToLoadAccountingSystemCase() {
        AccountingSystemSerializer serializer = new AccountingSystemSerializer();
        System.out.print("Введите путь к файлу, в который вы хотите загрузить систему: ");
        String path = in.next();
        try {
            accountingSystem = serializer.read(path);
            System.out.println("Система была успешно прочитана из файла");
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл не найден");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Ошибка потока ввода/вывода");
        }
        System.out.println();
    }

    private void goToSaveAccountingSystemCase() {
        AccountingSystemSerializer serializer = new AccountingSystemSerializer();
        System.out.print("Введите путь к файлу, в который вы хотитие сохранить систему: ");
        String path = in.next();
        try {
            serializer.write(accountingSystem, path);
            System.out.println("Система была сохранена в файл");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не был найден");
        } catch (IOException e) {
            System.out.println("Ошибка потока ввода/вывода");
        }
        System.out.println();
    }

    private void goToAccountingSystemCompareCase() {
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println("Доступные сущности для сравнения");
            System.out.println("""
                    house - сравнить дома
                    apartment - сравнить квартиры
                    . - назад
                    """);
            System.out.print("Введите сущность: ");
            String entity = in.next();
            switch (entity) {
                case "apartment" -> goToApartmentCompareCase();
                case "house" -> goToHouseCompareCase();
                case "." -> continueLoop = false;
                default -> System.out.println("Неизвестная сущность");
            }
        }
    }

    private void goToApartmentCompareCase() {
        AccountingSystemService accountingSystemService = AccountingSystemService.getInstance();
        if (accountingSystemService.calculateNumberOfApartments(accountingSystem) > 0) {
            System.out.println("_СРАВНЕНИЕ КВАРТИР ПО ПАРАМЕТРАМ_");
            System.out.println("_ПОИСК ПЕРВОЙ КВАРТИРЫ");
            Apartment apartment1 = getApartmentFromAccountingSystem();
            System.out.println("_ПОИСК ВТОРОЙ КВАРТИРЫ_");
            Apartment apartment2 = getApartmentFromAccountingSystem();

            ApartmentService apartmentService = ApartmentService.getInstance();
            ComparatorService comparatorService = ComparatorService.getInstance();
            char floorSign = comparatorService.getComparisonSign(apartment1, apartment2,
                    apartmentService::compareByFloor);
            char squareSign = comparatorService.getComparisonSign(apartment1, apartment2,
                    apartmentService::compareBySquare);
            char residentsSign = comparatorService.getComparisonSign(apartment1, apartment2,
                    apartmentService::compareByResidents);
            char roomsSign = comparatorService.getComparisonSign(apartment1, apartment2,
                    apartmentService::compareByNumberOfRooms);
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
            System.out.println("В системе нет квартир");
        }
    }

    private Apartment getApartmentFromAccountingSystem() {
        goToShowHousesCase();
        House house = null;
        boolean continueHouseChoosing = true;
        while (continueHouseChoosing) {
            System.out.print("Введите номер дома, где находится квартира: ");
            int number = in.nextInt();
            while ((house = accountingSystem.getHouseByNumber(number)) == null) {
                System.out.println("Нет дома с таким номером");
                System.out.print("Введите номер дома, где находится квартира: ");
                number = in.nextInt();
            }
            if (house.getApartments().size() == 0) {
                System.out.println("В доме " + house.getNumber() + " нет квартир");
            } else continueHouseChoosing = false;
        }

        goToShowApartmentsCase(house);
        System.out.print("Введите номер квартиры: ");
        int number = in.nextInt();
        Apartment apartment;
        while ((apartment = house.getApartmentByNumber(number)) == null) {
            System.out.println("Нет квартиры с таким номером");
            System.out.print("Введите номер квартиры: ");
            number = in.nextInt();
        }
        return apartment;
    }

    private House getHouseFromAccountingSystem() {
        System.out.println("Введите номер дома: ");
        int number = in.nextInt();
        House house;
        while ((house = accountingSystem.getHouseByNumber(number)) == null) {
            System.out.println("Нет дома с таким номером");
            System.out.println("Введите номер дома: ");
            number = in.nextInt();
        }
        return house;
    }

    private void goToHouseCompareCase() {
        AccountingSystemService accountingSystemService = AccountingSystemService.getInstance();
        if (accountingSystemService.calculateNumberOfHouses(accountingSystem) > 0) {
            System.out.println("_СРАВНЕНИЕ ДОМОВ ПО ПАРАМЕТРАМ_");
            goToShowHousesCase();
            System.out.println("_ПОИСК ПЕРВОГО ДОМА_");
            House house1 = getHouseFromAccountingSystem();
            System.out.println("_ПОИСК ВТОРОГО ДОМА_");
            House house2 = getHouseFromAccountingSystem();
            HouseService houseService = HouseService.getInstance();
            ComparatorService comparatorService = ComparatorService.getInstance();
            char populationSign = comparatorService.getComparisonSign(house1, house2,
                    houseService::compareByPopulation);
            char squareSign = comparatorService.getComparisonSign(house1, house2,
                    houseService::compareByFullSquare);
            char floorSign = comparatorService.getComparisonSign(house1, house2,
                    houseService::compareByFloors);
            char apartmentsSign = comparatorService.getComparisonSign(house1, house2,
                    houseService::compareByApartmentsNumber);
            System.out.printf(Locale.US, """
                            %-20s %-7d %1s %5d
                            %-20s %-7d %1s %5d
                            %-20s %-7d %1s %5d
                            %-20s %-7d %1s %5d
                            %-20s %-7.1f %1s %5.1f
                                                
                            """,
                    "Номер дома", house1.getNumber(), ' ', house2.getNumber(),
                    "Этажность", houseService.calculateNumberOfFloors(house1), floorSign,
                    houseService.calculateNumberOfFloors(house2),
                    "Кол-во квартир", house1.getApartments().size(), apartmentsSign, house2.getApartments().size(),
                    "Кол-во жителей", houseService.calculatePopulation(house1), populationSign,
                    houseService.calculatePopulation(house2),
                    "Площадь", houseService.calculateFullSquare(house1), squareSign,
                    houseService.calculateFullSquare(house2));
        } else {
            System.out.println("В системе нет домов");
        }
    }

    private void goToAddHouseCase() {
        System.out.println("_ДОБАВЛЕНИЕ ДОМА В СИСТЕМУ_");
        System.out.print("Введите номер дома, который вы хотите добавить: ");
        int number = in.nextInt();
        if (accountingSystem.containsHouse(number)) {
            System.out.println("Дом с таким номером уже есть в системе");
        } else {
            try {
                accountingSystem.addHouse(new House(number));
                System.out.println("Дом " + number + " был добавлен в систему");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
        System.out.println();
    }

    private void goToShowHousesCase() {
        System.out.println("Дома: ");
        for (var house : accountingSystem.getHouses()) {
            System.out.println(house);
        }
        System.out.println();
    }

    private void goToShowAccountignSystemInfoCase() {
        AccountingSystemService accountingSystemService = AccountingSystemService.getInstance();
        System.out.println("_ОБЩАЯ ИНФОРМАЦИЯ О СИСТЕМЕ_");
        System.out.println("Кол-во домов: " + accountingSystemService.calculateNumberOfHouses(accountingSystem));
        System.out.println("Кол-во квартир: " + accountingSystemService.calculateNumberOfApartments(accountingSystem));
        System.out.println();
    }

    private void goToChooseHouseCase() {
        System.out.println("_ВЫБОР ДОМА В СИСТЕМЕ_");
        goToShowHousesCase();
        System.out.print("Введите номер дома: ");
        int number = in.nextInt();
        System.out.println();
        House chosenHouse = accountingSystem.getHouseByNumber(number);
        if (chosenHouse == null) {
            System.out.println("Дома " + number + " нет в системе");
        } else {
            boolean continueHouseLoop = true;
            while (continueHouseLoop) {
                System.out.println("Доступные команды (дом)");
                System.out.println("""
                        add - добавить квартиру в дом
                        generate - сгенерировать квартиры
                        remove - удалить дом
                        choose - выбрать квартиру в доме
                        show - показать все квартиры в доме
                        info - вывести общую информацию о доме
                        . - назад
                        """);
                System.out.print("Введите команду: ");
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
                    default -> System.out.println("Неизвестная команда\n");
                }
            }
        }
        System.out.println();
    }

    private void goToGenerateApartmentsCase(House house) {
        System.out.println("_ГЕНЕРИРОВАНИЕ КВАРТИР В ДОМЕ " + house.getNumber() + "_");
        System.out.print("Введите кол-во квартир, которое вы хотите сгенерировать: ");
        int numberOfApartments = in.nextInt();
        if (numberOfApartments > 0) {
            house.clear();
            System.out.print("Введите кол-во квартир на этаж: ");
            int apartmentsInFloor = in.nextInt();
            if (apartmentsInFloor > 0) {
                ApartmentBuilder builder = new ApartmentBuilder();
                for (int number = 1; number <= numberOfApartments; number++) {
                    int floor = number / apartmentsInFloor + 1;
                    Director.generateApartmentWithNumberAndFloor(builder, number, floor);
                    house.addApartment(builder.getResult());
                }
                System.out.println(numberOfApartments + " квартир было добавлено в " + house);
            } else {
                System.out.println("Ошибка! Введено отрицательное либо нулевое число квартир на этаж!");
            }
        } else {
            System.out.println("Ошибка! Введено отрицательное либо нулевое число квартир!");
        }
        System.out.println();
    }

    private void goToAddApartmentCase(House house) {
        System.out.println("_ДОБАВЛЕНИЕ КВАРТИРЫ В ДОМ " + house.getNumber());
        System.out.print("Введите номер квартиры: ");
        int number = in.nextInt();
        if (house.containsApartment(number)) {
            System.out.println("Квартира с таким номером уже есть в доме");
            return;
        }
        System.out.println("Введите этаж: ");
        int floor = in.nextInt();
        System.out.print("Введите кол-во жителей: ");
        int residents = in.nextInt();
        System.out.print("Введите кол-во комнат: ");
        int rooms = in.nextInt();
        System.out.print("Введите площадь квартиры (м^2): ");
        float square = in.nextFloat();
        try {
            Apartment apartment = new Apartment(number, floor, rooms, residents, square);
            house.addApartment(apartment);
            System.out.println("Квартира была добавлена в дом " + house.getNumber());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка! " + e.getMessage());
        }
        System.out.println();
    }

    private void goToRemoveHouseCase(House house) {
        accountingSystem.removeHouse(house);
        System.out.println(house + " был удалён\n");
    }

    private void goToShowApartmentsCase(House house) {
        System.out.println("Квартиры дома " + house.getNumber() + ":");
        for (var apartment : house.getApartments()) {
            System.out.println(apartment);
        }
        System.out.println();
    }

    private void goToShowHouseInfoCase(House house) {
        HouseService houseService = HouseService.getInstance();
        System.out.println("_ОБЩАЯ ИНФОРМАЦИЯ О ДОМЕ " + house.getNumber() + "_");
        System.out.println("Этажность: " + houseService.calculateNumberOfFloors(house));
        System.out.println("Кол-во квартир: " + house.getApartments().size());
        System.out.printf(Locale.US, "Общая площадь: %.1f м^2\n", houseService.calculateFullSquare(house));
        System.out.println("Кол-во жильцов: " + houseService.calculatePopulation(house));
        System.out.println();
    }

    private void goToChooseApartmentCase(House house) {
        System.out.println("_ВЫБОР КВАРТИРЫ ДОМА " + house.getNumber() + "_");
        goToShowApartmentsCase(house);
        System.out.print("Введите номер квартиры: ");
        int number = in.nextInt();
        System.out.println();
        Apartment apartment = house.getApartmentByNumber(number);
        if (apartment == null) {
            System.out.println("В доме " + house.getNumber() + " нет квартиры с таким номером");
        } else {
            boolean continueApartmentLoop = true;
            while (continueApartmentLoop) {
                System.out.println("Доступные команды (квартира)");
                System.out.println("""
                        remove - удалить квартиру
                        info - вывести общую информацию о квартире
                        change - изменить параметр квартиры
                        . - назад
                        """);
                System.out.print("Введите команду: ");
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
                    default -> System.out.println("Неизвестная команда\n");
                }
            }
        }
        System.out.println();
    }

    private void goToRemoveApartmentCase(House house, Apartment apartment) {
        house.removeApartment(apartment);
        System.out.println("Квартира " + apartment.getNumber() + " была удалена из дома "
                + house.getNumber());
        System.out.println();
    }

    private void goToShowApartmentInfoCase(House house, Apartment apartment) {
        System.out.println("_ОБЩАЯ ИНФОРМАЦИЯ О КВАРТИРЕ " + apartment.getNumber() + "_");
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
            System.out.println("_ИЗМЕНЕНИЕ ПАРАМЕТРОВ КВАРТИРЫ_");
            System.out.println(apartment);
            System.out.println("""
                    floor - изменить этаж
                    rooms - изменить кол-во комнат
                    square - изменить площадь
                    residents - изменить кол-во жителей
                    . - назад
                    """);

            System.out.print("Введите параметр: ");
            String parameter = in.next();
            System.out.println();
            try {
                switch (parameter.toLowerCase()) {
                    case "floor" -> {
                        System.out.print("Введите новый этаж: ");
                        int floor = in.nextInt();
                        apartment.setFloor(floor);
                        System.out.println("Номер этажа квартиры был изменён");
                    }
                    case "rooms" -> {
                        System.out.print("Введите новое кол-во комнат: ");
                        int rooms = in.nextInt();
                        apartment.setRoomsNumber(rooms);
                        System.out.println("Кол-во комнат квартиры было изменено");
                    }
                    case "square" -> {
                        System.out.print("Введите новое значение площади: ");
                        float square = in.nextFloat();
                        apartment.setSquare(square);
                        System.out.println("Значение площади квартиры был изменено");
                    }
                    case "residents" -> {
                        System.out.print("Введите новое кол-во жителей: ");
                        int residents = in.nextInt();
                        apartment.setResidentsNumber(residents);
                        System.out.println("Кол-во жителей квартиры было изменено");
                    }
                    case "." -> continueLoop = false;
                    default -> System.out.println("Неизвестный параметр");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка! " + e.getMessage());
            }
            System.out.println();
        }
    }
}