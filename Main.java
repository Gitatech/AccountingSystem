import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        System.out.println("Выберите действие");
        System.out.println("0 - выйти из программы");
        System.out.println("1 - добавить дом");
        System.out.println("2 - добавить квартиру");
        System.out.println("3 - удалить дом");
        System.out.println("4 - удалить квартиру");
        System.out.println("5 - сравнить дома");
        System.out.println("6 - сравнить квартиры");
        System.out.println("7 - получить информацию о доме");
        System.out.println("8 - получить информацию о квартире");

        boolean work = true;
        AccountingSystem accountingSystem = new AccountingSystem();
        Scanner input = new Scanner(System.in);
        while (work) {
            System.out.println("Выберите действие");
            int query = input.nextInt();


            switch (query) {
                case 0 -> {
                    work = false;
                }
                case 1 -> {
                    System.out.println("введите номер дома");
                    int number = input.nextInt();
                    accountingSystem.addHome(number);

                }
                case 2 -> {
                    System.out.println("Введите номер дома, в который хотите добавить квартиру");
                    int number = input.nextInt();
                    accountingSystem.addApartment(number);
                }
                case 3 -> {
                    System.out.println("Введите номер дома");
                    accountingSystem.removeHome(input.nextInt());
                }
                case 4 -> {
                    System.out.println("Введите номер дома и квартиры");
                    accountingSystem.removeApartment(input.nextInt(),input.nextInt());
                }
                case 5 -> {
                    System.out.println("Введите номер дома");
                    int numberOfHomeOne = input.nextInt();
                    System.out.println("Введите номер дома");
                    int numberOfHomeTwo = input.nextInt();
                    accountingSystem.compareHome(numberOfHomeOne,numberOfHomeTwo);
                }
                case 6 -> {
                    System.out.println("Введите номер дома и квартиры");
                    int numberOfHome1 = input.nextInt();
                    int numberOfApartment1 = input.nextInt();
                    System.out.println("Введите номер дома и квартиры");
                    int numberOfHome2 = input.nextInt();
                    int numberOfApartment2 = input.nextInt();
                    accountingSystem.compareApartment(numberOfHome1,numberOfHome2,numberOfApartment1,numberOfApartment2);
                }
                case 7 -> {
                    System.out.println("Введите номер дома");
                    accountingSystem.getHomeInformation(input.nextInt());
                }
                case 8 -> {
                    System.out.println("Введите номер дома и квартиры");
                    accountingSystem.getApartmentInformation(input.nextInt(), input.nextInt());
                }

            }
        }
    }

}
