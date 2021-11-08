import java.util.ArrayList;
import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        System.out.println("Выберите действие");
        System.out.println("0 - выйти из программы");
        System.out.println("1 - добавить дом");
        System.out.println("2 - описать квартиру");
        System.out.println("3 - сравнить дома");
        System.out.println("4 - сравнить квартиры");
        System.out.println("5 - информация о квартире");
        System.out.println("6 - информация о доме");
        boolean work = true;
        Accounting account = new Accounting();
        Scanner input = new Scanner(System.in);
        while (work) {
            System.out.println("Выберите действие");
            int action = input.nextInt();
            switch (action) {
                case 0 -> {
                    work = false;
                }
                case 1 -> {
                    System.out.println("Введите номер дома, кол-во этажей и квартир");
                    int hnum = input.nextInt();
                    int fnum = input.nextInt();
                    int anum = input.nextInt();
                    account.createHome(hnum, fnum, anum);

                }
                case 2 -> {
                    System.out.println("Введите номер дома, этажа и квартиры");
                    int nh = input.nextInt();
                    int nf = input.nextInt();
                    int na = input.nextInt();
                    System.out.println("Введите площадь квартиры, кол-во жильцов и комнат");
                    int s = input.nextInt();
                    int p = input.nextInt();
                    int r = input.nextInt();
                     account.createApart(nh,nf,na,s,p,r);
                }

                    case 3 -> {
                    System.out.println("Введите номер дома");
                    int n1 = input.nextInt();
                    System.out.println("Введите номер дома");
                    int n2 = input.nextInt();
                    account.compareHome(n1, n2);
                }
                case 4 -> {
                    System.out.println("Введите номер дома, этажа и квартиры");
                    int n1 = input.nextInt();
                    int nf1 = input.nextInt();
                    int na1 = input.nextInt();
                    System.out.println("Введите номер дома, этажа и квартиры");
                    int n2 = input.nextInt();
                    int nf2 = input.nextInt();
                    int na2 = input.nextInt();
                    account.compareApart(n1, nf1,na1,n2,nf2,na2);
                }
                case 5 -> {
                    System.out.println("Введите номер дома, этажа и квартиры");
                    int nh = input.nextInt();
                    int nf = input.nextInt();
                    int na = input.nextInt();
                    Apart apartment =account.homes[nh].floors[nf].aparts[na];
                    apartment.Info();
                }
                case 6 -> {
                    System.out.println("Введите номер дома");
                    int n = input.nextInt();
                    account.homes[n].Info();
                }
            }
        }
    }
}
