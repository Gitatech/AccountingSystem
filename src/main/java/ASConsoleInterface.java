import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class ASConsoleInterface {

    AccountingSystem accountingSystem;
    Scanner in;

    public ASConsoleInterface(AccountingSystem accountingSystem) {
        this.accountingSystem = accountingSystem;
        in = new Scanner(System.in);
        in.useLocale(Locale.US);
    }

    public static void main(String[] args) {
        ASConsoleInterface ui = new ASConsoleInterface(new AccountingSystem());
        ui.launch();
    }

    public void launch() {
        boolean inProcess = true;
        while (inProcess) {
            System.out.println(ColorScheme.ANSI_BLUE + "Доступные команды (система учёта)" + ColorScheme.ANSI_RESET);
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
            System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
            String command = in.next();
            System.out.println();

            switch (command.toLowerCase()) {
                case "add" -> goToAddHouseCase();
                case "choose" -> goToChooseHouseCase();
                case "compare" -> goToASCompareCase();
                case "show" -> goToShowHousesCase();
                case "info" -> goToShowASInfoCase();
                case "load" -> goToLoadASCase();
                case "save" -> goToSaveASCase();
                case "exit" -> inProcess = false;
                default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная команда\n" + ColorScheme.ANSI_RESET);
            }
        }
    }

    private void goToASCompareCase() {
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println(ColorScheme.ANSI_BLUE + "Доступные сущности для сравнения" + ColorScheme.ANSI_RESET);
            System.out.println("""
                    house - сравнить дома
                    apartment - сравнить квартиры
                    . - назад
                    """);
            System.out.print(ColorScheme.ANSI_YELLOW + "Введите сущность: ");
            String entity = in.next();
            switch (entity) {
                case "apartment" -> goToApartmentCompareCase();
                case "house" -> goToHouseCompareCase();
                case "." -> continueLoop = false;
                default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная сущность" + ColorScheme.ANSI_RESET);
            }
        }
    }

    private void goToApartmentCompareCase() {
        if (accountingSystem.getNumberOfApartments() > 0) {
            System.out.println(ColorScheme.ANSI_CYAN + "_СРАВНЕНИЕ КВАРТИР ПО ПАРАМЕТРАМ_" + ColorScheme.ANSI_RESET);
            System.out.println(ColorScheme.ANSI_BLUE + "_ПОИСК ПЕРВОЙ КВАРТИРЫ" + ColorScheme.ANSI_RESET);
            Apartment apartment1 = getApartmentFromAS();
            System.out.println(ColorScheme.ANSI_BLUE + "_ПОИСК ВТОРОЙ КВАРТИРЫ_" + ColorScheme.ANSI_RESET);
            Apartment apartment2 = getApartmentFromAS();
            char floorSign = getComparisonSign(apartment1, apartment2, Apartment::compareByFloor),
                    squareSign = getComparisonSign(apartment1, apartment2, Apartment::compareBySquare),
                    residentsSign = getComparisonSign(apartment1, apartment2, Apartment::compareByResidentsNumber),
                    roomsSign = getComparisonSign(apartment1, apartment2, Apartment::compareByNumberOfRooms);
            System.out.printf(Locale.US, """
                            %-20s %-7d %1s %7d
                            %-20s %-7d %1s %7d
                            %-20s %-7d %1s %7d
                            %-20s %-7.1f %1s %7.1f
                            %-20s %-7d %1s %7d
                                                
                            """,
                    "Номер квартиры", apartment1.getNumber(), ' ', apartment2.getNumber(),
                    "Этаж", apartment1.getFloor(), floorSign, apartment2.getFloor(),
                    "Кол-во комнат", apartment1.getNumberOfRooms(), roomsSign, apartment2.getNumberOfRooms(),
                    "Площадь", apartment1.getSquare(), squareSign, apartment2.getSquare(),
                    "Кол-во жителей", apartment1.getResidentsNumber(), residentsSign, apartment2.getResidentsNumber());
        } else {
            System.out.println(ColorScheme.ANSI_RED + "В системе нет квартир" + ColorScheme.ANSI_RESET);
        }
    }

    private <T> char getComparisonSign(T a, T b, Comparator<T> comparator) {
        return switch (comparator.compare(a, b)) {
            case 1 -> '>';
            case 0 -> '=';
            case -1 -> '<';
            default -> throw new IllegalStateException("Unexpected value: " + comparator.compare(a, b));
        };
    }

    private Apartment getApartmentFromAS() {
        goToShowHousesCase();
        House house = null;
        boolean continueHouseChoosing = true;
        int number;
        while (continueHouseChoosing) {
            System.out.print(ColorScheme.ANSI_YELLOW + "Введите номер дома, где находится квартира: " + ColorScheme.ANSI_RESET);
            number = in.nextInt();
            while ((house = accountingSystem.getHouseByNumber(number)) == null) {
                System.out.println(ColorScheme.ANSI_RED + "Нет дома с таким номером" + ColorScheme.ANSI_RESET);
                System.out.print(ColorScheme.ANSI_YELLOW + "Введите номер дома, где находится квартира: " + ColorScheme.ANSI_RESET);
                number = in.nextInt();
            }
            if (house.getApartments().length == 0) {
                System.out.println(ColorScheme.ANSI_RED + "В доме " + house.getNumber() + " нет квартир" + ColorScheme.ANSI_RESET);
            } else continueHouseChoosing = false;
        }

        goToShowApartmentsCase(house);
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите номер квартиры: " + ColorScheme.ANSI_RESET);
        number = in.nextInt();
        Apartment apartment;
        while ((apartment = house.getApartmentByNumber(number)) == null) {
            System.out.println(ColorScheme.ANSI_RED + "Нет квартиры с таким номером" + ColorScheme.ANSI_RESET);
            System.out.print(ColorScheme.ANSI_YELLOW + "Введите номер квартиры: " + ColorScheme.ANSI_RESET);
            number = in.nextInt();
        }
        return apartment;
    }

    private House getHouseFromAS() {
        System.out.println(ColorScheme.ANSI_YELLOW + "Введите номер дома: " + ColorScheme.ANSI_RESET);
        int number = in.nextInt();
        House house;
        while ((house = accountingSystem.getHouseByNumber(number)) == null) {
            System.out.println(ColorScheme.ANSI_RED + "Нет дома с таким номером" + ColorScheme.ANSI_RESET);
            System.out.println(ColorScheme.ANSI_YELLOW + "Введите номер дома: " + ColorScheme.ANSI_RESET);
            number = in.nextInt();
        }
        return house;
    }

    private void goToHouseCompareCase() {
        if (accountingSystem.getNumberOfHouses() > 0) {
            System.out.println(ColorScheme.ANSI_CYAN + "_СРАВНЕНИЕ ДОМОВ ПО ПАРАМЕТРАМ_" + ColorScheme.ANSI_RESET);
            goToShowHousesCase();
            System.out.println(ColorScheme.ANSI_BLUE + "_ПОИСК ПЕРВОГО ДОМА_" + ColorScheme.ANSI_RESET);
            House house1 = getHouseFromAS();
            System.out.println(ColorScheme.ANSI_BLUE + "_ПОИСК ВТОРОГО ДОМА_" + ColorScheme.ANSI_RESET);
            House house2 = getHouseFromAS();
            char populationSign = getComparisonSign(house1, house2, House::compareByPopulation),
                    squareSign = getComparisonSign(house1, house2, House::compareByFullSquare),
                    floorSign = getComparisonSign(house1, house2, House::compareByFloors),
                    apartmentsSign = getComparisonSign(house1, house2, House::compareByApartmentsNumber);
            System.out.printf(Locale.US, """
                            %-20s %-7d %1s %5d
                            %-20s %-7d %1s %5d
                            %-20s %-7d %1s %5d
                            %-20s %-7d %1s %5d
                            %-20s %-7.1f %1s %5.1f
                                                
                            """,
                    "Номер дома", house1.getNumber(), ' ', house2.getNumber(),
                    "Этажность", house1.calculateNumberOfFloors(), floorSign, house2.calculateNumberOfFloors(),
                    "Кол-во квартир", house1.getApartments().length, apartmentsSign, house2.getApartments().length,
                    "Кол-во жителей", house1.calculatePopulation(), populationSign, house2.calculatePopulation(),
                    "Площадь", house1.calculateFullSquare(), squareSign, house2.calculateFullSquare());
        } else {
            System.out.println(ColorScheme.ANSI_RED + "В системе нет домов" + ColorScheme.ANSI_RESET);
        }
    }

    private void goToAddHouseCase() {
        System.out.println(ColorScheme.ANSI_CYAN + "_ДОБАВЛЕНИЕ ДОМА В СИСТЕМУ_" + ColorScheme.ANSI_RESET);
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите номер дома, который вы хотите добавить: " + ColorScheme.ANSI_RESET);
        int number = in.nextInt();
        if (accountingSystem.containsHouse(number)) {
            System.out.println(ColorScheme.ANSI_RED + "Дом с таким номером уже есть в системе" + ColorScheme.ANSI_RESET);
        } else {
            try {
                accountingSystem.addHouse(new House(number));
                System.out.println(ColorScheme.ANSI_GREEN + "Дом " + number + " был добавлен в систему" + ColorScheme.ANSI_RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(ColorScheme.ANSI_RED + e.getMessage());
            }

        }
        System.out.println();
    }

    private void goToShowHousesCase() {
        System.out.println(ColorScheme.ANSI_CYAN + "Дома: " + ColorScheme.ANSI_RESET);
        for (var house : accountingSystem.getHouses()) {
            System.out.println(house);
        }
        System.out.println();
    }

    private void goToShowASInfoCase() {
        System.out.println(ColorScheme.ANSI_CYAN + "_ОБЩАЯ ИНФОРМАЦИЯ О СИСТЕМЕ_" + ColorScheme.ANSI_RESET);
        System.out.println("Кол-во домов: " + accountingSystem.getNumberOfHouses());
        System.out.println("Кол-во квартир: " + accountingSystem.getNumberOfApartments());
        System.out.println();
    }

    private void goToLoadASCase() {
        System.out.println(ColorScheme.ANSI_CYAN + "_ЗАГРУЗКА СИСТЕМЫ УЧЁТА ИЗ ФАЙЛА_" + ColorScheme.ANSI_RESET);
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите путь к файлу: " + ColorScheme.ANSI_RESET);
        Path path = Paths.get(in.next());
        try {
            accountingSystem.load(path.toString());
            System.out.println(ColorScheme.ANSI_GREEN + "Система была загружена из файла " + path.getFileName() + ColorScheme.ANSI_RESET);
        } catch (FileNotFoundException e) {
            System.out.println(ColorScheme.ANSI_RED + "Файл не найден" + ColorScheme.ANSI_RESET);
        } catch (ClassNotFoundException e) {
            System.out.println(ColorScheme.ANSI_RED + "Не удалось прочитать файл" + ColorScheme.ANSI_RESET);
        } catch (IOException e) {
            System.out.println(ColorScheme.ANSI_RED + "Ошибка потока ввода/вывода" + ColorScheme.ANSI_RESET);
        }
        System.out.println();
    }

    private void goToSaveASCase() {
        System.out.println(ColorScheme.ANSI_CYAN + "_СОХРАНЕНИЕ СИСТЕМЫ УЧЁТА В ФАЙЛ_" + ColorScheme.ANSI_RESET);
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите путь к файлу: " + ColorScheme.ANSI_RESET);
        Path path = Paths.get(in.next());
        try {
            accountingSystem.save(path.toString());
            System.out.println(ColorScheme.ANSI_GREEN + "Система была сохранена в файл " + path.getFileName() + ColorScheme.ANSI_RESET);
        } catch (IOException e) {
            System.out.println(ColorScheme.ANSI_RED + "Ошибка потока ввода/вывода" + ColorScheme.ANSI_RESET);
        }
        System.out.println();
    }

    private void goToChooseHouseCase() {
        System.out.println(ColorScheme.ANSI_CYAN + "_ВЫБОР ДОМА В СИСТЕМЕ_" + ColorScheme.ANSI_RESET);
        goToShowHousesCase();
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите номер дома: " + ColorScheme.ANSI_RESET);
        int number = in.nextInt();
        System.out.println();
        House chosenHouse = accountingSystem.getHouseByNumber(number);
        if (chosenHouse == null) {
            System.out.println(ColorScheme.ANSI_RED + "Дома " + number + " нет в системе" + ColorScheme.ANSI_RESET);
        } else {
            boolean continueHouseLoop = true;
            while (continueHouseLoop) {
                System.out.println(ColorScheme.ANSI_BLUE + "Доступные команды (дом)" + ColorScheme.ANSI_RESET);
                System.out.println("""
                        add - добавить квартиру в дом
                        generate - сгенерировать квартиры
                        remove - удалить дом
                        choose - выбрать квартиру в доме
                        show - показать все квартиры в доме
                        info - вывести общую информацию о доме
                        . - назад
                        """);
                System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
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
                    default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная команда\n" + ColorScheme.ANSI_RESET);
                }
            }
        }
        System.out.println();
    }

    private void goToGenerateApartmentsCase(House house) {
        System.out.println(ColorScheme.ANSI_CYAN + "_ГЕНЕРИРОВАНИЕ КВАРТИР В ДОМЕ " + house.getNumber() + "_");
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите кол-во квартир, которое вы хотите сгенерировать: " + ColorScheme.ANSI_RESET);
        int numberOfApartments = in.nextInt();
        if (numberOfApartments > 0) {
            house.clear();
            System.out.print(ColorScheme.ANSI_YELLOW + "Введите кол-во квартир на этаж: ");
            int apartmentsInFloor = in.nextInt();
            if (apartmentsInFloor > 0) {
                Random r = new Random(System.currentTimeMillis());
                for (int number = 1; number <= numberOfApartments; number++) {
                    house.addApartment(new Apartment(number, number / apartmentsInFloor + 1, r.nextInt(5) + 1,
                            r.nextInt(6), (r.nextInt(500) + 250) / 10.0f));
                }
                System.out.println(ColorScheme.ANSI_GREEN + numberOfApartments + " квартир было добавлено в " + house + ColorScheme.ANSI_RESET);
            } else {
                System.out.println(ColorScheme.ANSI_RED + "Ошибка! Введено отрицательное либо нулевое число квартир на этаж!");
            }
        } else {
            System.out.println(ColorScheme.ANSI_RED + "Ошибка! Введено отрицательное либо нулевое число квартир!");
        }
        System.out.println();
    }

    private void goToAddApartmentCase(House house) {
        System.out.println(ColorScheme.ANSI_CYAN + "_ДОБАВЛЕНИЕ КВАРТИРЫ В ДОМ " + house.getNumber() + ColorScheme.ANSI_RESET);
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите номер квартиры: " + ColorScheme.ANSI_RESET);
        int number = in.nextInt();
        if (house.containsApartment(number)) {
            System.out.println(ColorScheme.ANSI_RED + "Квартира с таким номером уже есть в доме" + ColorScheme.ANSI_RESET);
            return;
        }
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите этаж: " + ColorScheme.ANSI_RESET);
        int floor = in.nextInt();
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите кол-во жителей: " + ColorScheme.ANSI_RESET);
        int residents = in.nextInt();
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите кол-во комнат: " + ColorScheme.ANSI_RESET);
        int rooms = in.nextInt();
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите площадь квартиры (м^2): " + ColorScheme.ANSI_RESET);
        float square = in.nextFloat();
        try {
            Apartment apartment = new Apartment(number, floor, rooms, residents, square);
            house.addApartment(apartment);
            System.out.println(ColorScheme.ANSI_GREEN + "Квартира была добавлена в дом " + house.getNumber() + ColorScheme.ANSI_RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(ColorScheme.ANSI_RED + "Ошибка! " + e.getMessage() + ColorScheme.ANSI_RESET);
        }
        System.out.println();
    }

    private void goToRemoveHouseCase(House house) {
        accountingSystem.removeHouse(house);
        System.out.println(ColorScheme.ANSI_RED + house + " был удалён\n");
    }

    private void goToShowApartmentsCase(House house) {
        System.out.println(ColorScheme.ANSI_CYAN + "Квартиры дома " + house.getNumber() + ":" + ColorScheme.ANSI_RESET);
        for (var apartment : house.getApartments()) {
            System.out.println(apartment);
        }
        System.out.println();
    }

    private void goToShowHouseInfoCase(House house) {
        System.out.println(ColorScheme.ANSI_CYAN + "_ОБЩАЯ ИНФОРМАЦИЯ О ДОМЕ " + house.getNumber() + "_" + ColorScheme.ANSI_RESET);
        System.out.println("Этажность: " + house.calculateNumberOfFloors());
        System.out.println("Кол-во квартир: " + house.getApartments().length);
        System.out.printf(Locale.US, "Общая площадь: %.1f м^2\n", house.calculateFullSquare());
        System.out.println("Кол-во жильцов: " + house.calculatePopulation());
        System.out.println();
    }

    private void goToChooseApartmentCase(House house) {
        System.out.println(ColorScheme.ANSI_CYAN + "_ВЫБОР КВАРТИРЫ ДОМА " + house.getNumber() + "_" + ColorScheme.ANSI_RESET);
        goToShowApartmentsCase(house);
        System.out.print(ColorScheme.ANSI_YELLOW + "Введите номер квартиры: " + ColorScheme.ANSI_RESET);
        int number = in.nextInt();
        System.out.println();
        Apartment apartment = house.getApartmentByNumber(number);
        if (apartment == null) {
            System.out.println(ColorScheme.ANSI_RED + "В доме " + house.getNumber() + " нет квартиры с таким номером");
        } else {
            boolean continueApartmentLoop = true;
            while (continueApartmentLoop) {
                System.out.println(ColorScheme.ANSI_BLUE + "Доступные команды (квартира)" + ColorScheme.ANSI_RESET);
                System.out.println("""
                        remove - удалить квартиру
                        info - вывести общую информацию о квартире
                        change - изменить параметр квартиры
                        . - назад
                        """);
                System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
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
                    default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная команда\n" + ColorScheme.ANSI_RESET);
                }
            }
        }
        System.out.println();
    }

    private void goToRemoveApartmentCase(House house, Apartment apartment) {
        house.removeApartment(apartment);
        System.out.println(ColorScheme.ANSI_GREEN + "Квартира " + apartment.getNumber() + " была удалена из дома " + house.getNumber() + "\n" + ColorScheme.ANSI_RESET);
    }

    private void goToShowApartmentInfoCase(House house, Apartment apartment) {
        System.out.println(ColorScheme.ANSI_CYAN + "_ОБЩАЯ ИНФОРМАЦИЯ О КВАРТИРЕ " + apartment.getNumber() + "_" + ColorScheme.ANSI_RESET);
        System.out.printf(Locale.US, """
                        Номер дома: %d
                        Этаж: %d
                        Номер квартиры: %d
                        Кол-во комнат: %d
                        Площадь: %.1f м^2
                        Кол-во жителей: %d
                                                
                        """, house.getNumber(), apartment.getFloor(), apartment.getNumber(), apartment.getNumberOfRooms(),
                apartment.getSquare(), apartment.getResidentsNumber());
    }

    private void goToChangeApartmentParameterCase(Apartment apartment) {
        boolean continueLoop = true;
        while (continueLoop) {
            System.out.println(ColorScheme.ANSI_CYAN + "_ИЗМЕНЕНИЕ ПАРАМЕТРОВ КВАРТИРЫ_" + ColorScheme.ANSI_RESET);
            System.out.println(apartment);
            System.out.println("""
                    floor - изменить этаж
                    rooms - изменить кол-во комнат
                    square - изменить площадь
                    residents - изменить кол-во жителей
                    . - назад
                    """);

            System.out.print(ColorScheme.ANSI_YELLOW + "Введите параметр: " + ColorScheme.ANSI_RESET);
            String parameter = in.next();
            System.out.println();
            try {
                switch (parameter.toLowerCase()) {
                    case "floor" -> {
                        System.out.print(ColorScheme.ANSI_YELLOW + "Введите новый этаж: " + ColorScheme.ANSI_RESET);
                        int floor = in.nextInt();
                        apartment.setFloor(floor);
                        System.out.println(ColorScheme.ANSI_GREEN + "Номер этажа квартиры был изменён" + ColorScheme.ANSI_RESET);
                    }
                    case "rooms" -> {
                        System.out.print(ColorScheme.ANSI_YELLOW + "Введите новое кол-во комнат: " + ColorScheme.ANSI_RESET);
                        int rooms = in.nextInt();
                        apartment.setNumberOfRooms(rooms);
                        System.out.println(ColorScheme.ANSI_GREEN + "Кол-во комнат квартиры было изменено" + ColorScheme.ANSI_RESET);
                    }
                    case "square" -> {
                        System.out.print(ColorScheme.ANSI_YELLOW + "Введите новое значение площади: " + ColorScheme.ANSI_RESET);
                        float square = in.nextFloat();
                        apartment.setSquare(square);
                        System.out.println(ColorScheme.ANSI_GREEN + "Значение площади квартиры был изменено" + ColorScheme.ANSI_RESET);
                    }
                    case "residents" -> {
                        System.out.print(ColorScheme.ANSI_YELLOW + "Введите новое кол-во жителей: " + ColorScheme.ANSI_RESET);
                        int residents = in.nextInt();
                        apartment.setResidentsNumber(residents);
                        System.out.println(ColorScheme.ANSI_GREEN + "Кол-во жителей квартиры было изменено" + ColorScheme.ANSI_RESET);
                    }
                    case "." -> continueLoop = false;
                    default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестный параметр" + ColorScheme.ANSI_RESET);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ColorScheme.ANSI_RED + "Ошибка! " + e.getMessage() + ColorScheme.ANSI_RESET);
            }
            System.out.println();
        }
    }
}