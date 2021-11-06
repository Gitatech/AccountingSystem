import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class ACController {
    public AccountingSystem accountingSystem;

    public ACController(AccountingSystem accountingSystem) {
        this.accountingSystem = accountingSystem;
    }

    public static void main(String[] args) {
        ACController controller = new ACController(new AccountingSystem("src/main/resources/data.bin"));
        controller.launch();
    }

    public void launch() {
        accountingSystem.load();

        boolean continueLoop = true;
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);

        while (continueLoop) {
            System.out.println(ColorScheme.ANSI_CYAN + "Доступные команды (система учёта):" + ColorScheme.ANSI_RESET);
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
                case "add" -> addHouseCase(in);
                case "choose" -> chooseHouseCase(in);
                case "compare" -> comparingCase(in);
                case "output" -> accountingSystem.printHouses();
                case "exit" -> continueLoop = false;
                default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная команда\n" + ColorScheme.ANSI_RESET);
            }

        }
        accountingSystem.save();
    }

    private void addHouseCase(Scanner in) {
        System.out.println(ColorScheme.ANSI_YELLOW + "Добавление дома" + ColorScheme.ANSI_RESET);
        System.out.print("Введите номер дома: ");
        int number = in.nextInt();
        if (accountingSystem.containsHouse(number)) {
            System.out.println(ColorScheme.ANSI_RED + "Дом " + number + " уже есть в системе\n" + ColorScheme.ANSI_RESET);
        } else {
            accountingSystem.addHouse(number);
            System.out.println(ColorScheme.ANSI_GREEN + "Дом " + number + " был успешно добавлен в систему\n" + ColorScheme.ANSI_RESET);
        }
    }

    private void chooseHouseCase(Scanner in) {
        accountingSystem.printHouses();
        System.out.print("Введите номер дома: ");
        House chosenHouse = accountingSystem.getHouseByNumber(in.nextInt());
        if (chosenHouse != null) chooseActionsInHouse(chosenHouse, in);
        else
            System.out.println(ColorScheme.ANSI_RED + "Дома с таким номером не было найдено\n" + ColorScheme.ANSI_RESET);
    }

    private void comparingCase(Scanner in) {
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
            switch (choice.toLowerCase()) {
                case "house" -> continueCompareLoop = compareHouses(in);
                case "apartment" -> continueCompareLoop = compareApartments(in);
                case "." -> continueCompareLoop = false;
                default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная команда\n" + ColorScheme.ANSI_RESET);
            }
        }
    }

    private boolean compareHouses(Scanner in) {
        boolean continueCompareLoop = true;
        accountingSystem.printHouses();
        if (accountingSystem.getHouses().length < 1) {
            System.out.println(ColorScheme.ANSI_RED + "В системе нет домов\n" + ColorScheme.ANSI_RESET);
            continueCompareLoop = false;
        } else {
            House house1 = getHouseFromAccountingSystem("первого", in);
            House house2 = getHouseFromAccountingSystem("второго", in);

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
        return continueCompareLoop;
    }

    private boolean compareApartments(Scanner in) {
        boolean continueCompareLoop = true;
        if (Arrays.stream(accountingSystem.getHouses()).mapToInt(e -> e.getApartments().length).sum() == 0) {
            System.out.println(ColorScheme.ANSI_RED + "В систему учёта не было добавлено ни одной квартиры!\n" + ColorScheme.ANSI_RESET);
            continueCompareLoop = false;
        } else {
            accountingSystem.printHouses();

            Object[] app1 = getApartmentFromAccountingSystem("первая", in);
            Apartment apartment1 = (Apartment) app1[0];
            int ap1HouseNumber = (Integer) app1[1];

            Object[] app2 = getApartmentFromAccountingSystem("вторая", in);
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
            System.out.printf(Locale.US, """
                            %20s %6s %4s %6s
                            %20s %6d %4s %6d
                            %20s %6d %4s %6d
                            %20s %6.1f %4s %6.1f
                                                                    
                            """,
                    "Квартира", ap1HouseNumber + "/" + apartment1.getNumber(), " ", ap2HouseNumber + "/" + apartment2.getNumber(),
                    "Этаж", apartment1.getFloor(), floors, apartment2.getFloor(),
                    "Количество жителей", apartment1.getResidentsNumber(), population, apartment2.getResidentsNumber(),
                    "Площадь", apartment1.getSquare(), square, apartment2.getSquare());
        }
        return continueCompareLoop;
    }

    private void chooseActionsInHouse(House chosenHouse, Scanner in) {
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
                case "info" -> chosenHouse.printInformationAboutHouse();
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
                    chosenHouse.printApartments();
                    System.out.println();
                }
                case "choose" -> chooseApartmentCase(chosenHouse, in);
                case "." -> continueHouseLoop = false;
                default -> System.out.println(ColorScheme.ANSI_RED + "Неизвестная команда\n" + ColorScheme.ANSI_RESET);
            }
        }
    }

    private void chooseApartmentCase(House chosenHouse, Scanner in) {
        chosenHouse.printApartments();
        System.out.println();

        System.out.print("Введите номер квартиры: ");
        Apartment chosenApartment = chosenHouse.getApartmentByNumber(in.nextInt());
        if (chosenApartment != null) {
            boolean continueApartmentLoop = true;
            while (continueApartmentLoop) {
                System.out.println(ColorScheme.ANSI_CYAN + "Квартира " + chosenApartment.getNumber() + ":" + ColorScheme.ANSI_RESET);
                System.out.println("""
                        info - информация о квартире
                        сhange - изменить кол-во жителей
                        remove - удалить квартиру
                        . - назад
                        """);
                System.out.print(ColorScheme.ANSI_YELLOW + "Введите команду: " + ColorScheme.ANSI_RESET);
                String choice = in.next();
                System.out.println();
                switch (choice.toLowerCase()) {
                    case "info" -> {
                        System.out.println(ColorScheme.ANSI_CYAN + "Информация о квартире: " + ColorScheme.ANSI_RESET);
                        System.out.println(chosenApartment + "\n");
                    }
                    case "change" -> {
                        System.out.print("Введите новое кол-во жильцов: ");
                        int newResidentsNumber = in.nextInt();
                        chosenApartment.setResidentsNumber(newResidentsNumber);
                        System.out.println(ColorScheme.ANSI_GREEN + "Кол-во жителей было изменено\n" + ColorScheme.ANSI_RESET);
                    }
                    case "remove" -> {
                        chosenHouse.removeApartment(chosenApartment);
                        System.out.println(ColorScheme.ANSI_GREEN + "Квартира была удалена\n" + ColorScheme.ANSI_RESET);
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

    private House getHouseFromAccountingSystem(String num, Scanner in) {
        House house = House.templateHouse();
        boolean continueHouseLoop = true;
        while (continueHouseLoop) {
            System.out.print("Введите номер " + num + " дома:");
            house = accountingSystem.getHouseByNumber(in.nextInt());
            if (house != null) continueHouseLoop = false;
            else System.out.println(ColorScheme.ANSI_RED + "Нет дома с таким номером\n" + ColorScheme.ANSI_RESET);
        }
        return house;
    }

    private Object[] getApartmentFromAccountingSystem(String num, Scanner in) {   //возвращает массив из двух элементов:
        boolean continueHouseLoop = true;                             //1 - квартира, 2 - номер дома в котором расположена квартира
        Apartment apartment = Apartment.templateApartment();
        int houseNumber = 0;
        while (continueHouseLoop) {
            System.out.print("Выберите дом где расположена " + num + " квартира:");
            House chosenHouse = accountingSystem.getHouseByNumber(in.nextInt());
            if (chosenHouse != null) {
                if (chosenHouse.getApartments().length == 0) {
                    System.out.println(ColorScheme.ANSI_RED + "Дом " + chosenHouse.getNumber() +
                            " не содержит квартир" + ColorScheme.ANSI_RESET);
                    continue;
                } else {
                    chosenHouse.printApartments();
                    System.out.println();
                }
                boolean continueApartmentLoop = true;
                while (continueApartmentLoop) {
                    System.out.print("Введите номер квартиры: ");
                    int apartmentNumber = in.nextInt();
                    apartment = chosenHouse.getApartmentByNumber(apartmentNumber);
                    if (apartment != null) {
                        continueApartmentLoop = false;
                        continueHouseLoop = false;
                    } else
                        System.out.println(ColorScheme.ANSI_RED + "Нет квартиры с таким номером" + ColorScheme.ANSI_RESET);
                }
            } else
                System.out.println(ColorScheme.ANSI_RED + "Дома с таким номером не было найдено" + ColorScheme.ANSI_RESET);
        }
        return new Object[]{apartment, houseNumber};
    }
}
