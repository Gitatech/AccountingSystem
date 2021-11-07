import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("Выберите действие");
        System.out.println("0 - выйти из программы");
        System.out.println("1 - добавить дом");
        System.out.println("2 - добавить квартиру");
        System.out.println("3 - сравнить дома");
        System.out.println("4 - сравнить квартиры");
        System.out.println("5 - информация о квартире");
        System.out.println("6 - информация о доме");
        boolean work = true;
        AccountingSystem accSys = new AccountingSystem();
        Scanner input = new Scanner(System.in);
        while (work) {
            System.out.println("Выберите действие");
            int action = input.nextInt();
            switch (action) {
                case 0 -> {
                    work = false;
                }
                case 1 -> {
                    System.out.println("Введите номер дома");
                    int num = input.nextInt();
                    accSys.addHome(num);
                }
                case 2 -> {
                    System.out.println("Введите номер дома, в который хотите добавить квартиру");
                    int num = input.nextInt();
                    accSys.addApartment(num);
                }

                case 3 -> {
                    System.out.println("Введите номер дома");
                    int n1 = input.nextInt();
                    System.out.println("Введите номер дома");
                    int n2 = input.nextInt();
                    accSys.compareHome(n1, n2);
                }
                case 4 -> {
                    System.out.println("Введите номер дома и квартиры");
                    int n1 = input.nextInt();
                    int na1 = input.nextInt();
                    System.out.println("Введите номер дома и квартиры");
                    int n2 = input.nextInt();
                    int na2 = input.nextInt();
                    accSys.compareApartment(n1, n2, na1, na2);
                }
                case 5 -> {
                    System.out.println("Введите номер дома и квартиры");
                    int n = input.nextInt();
                    int na = input.nextInt();
                    accSys.ApartmentInfo(n, na);
                }
                case 6 -> {
                    System.out.println("Введите номер дома");
                    int n = input.nextInt();
                    accSys.HomeInfo(n);
                }
            }
        }
    }
}