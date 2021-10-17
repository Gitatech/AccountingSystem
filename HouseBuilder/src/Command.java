import java.util.Scanner;

public class Command extends Program{
    String[] commands={"build house","delete house","compare houses", "compare apartments","house information","city information", "help", "exit"};
    String[] houseParameters={"Square","Population","Floors"};
    String[] apartParameters={"Square","Population"};
    protected  String command;

    public Command(){
        this.command=null;
    }

    protected void command(String command){
        this.command=command;
    }

    private void exit(Program program){
        program.status=false;
    }

    private void help(){
        System.out.println("""
                                 Possible commands 
                                 build house
                                 delete house
                                 compare houses
                                 compare apartments
                                 house information
                                 city information
                                 help
                                 exit
                                 """
        );
    }

    private void buildHouse(){
        Scanner in = new Scanner(System.in);
        System.out.print("Input a budget: ");
        double budget = in.nextDouble();
        System.out.print("Input a price for per square metre: ");
        double sqrMPrice = in.nextDouble();
        System.out.print("Input a number of apartments per floor: ");
        int apartments = in.nextInt();
        House house = new House(budget, sqrMPrice, apartments);
        city.add(house);
        System.out.println("You build house");
    }

    private void deleteHouse() {
        System.out.print("Put houses number ");
        Scanner in=new Scanner(System.in);
        int number=in.nextInt();
        int floors = city.get(number).getFloors();
        int apartPerFloor = city.get(number).apartPerFloor;
        for (int i = floors-1; i >=0; i--) {
            for(int j=apartPerFloor-1;j>=0;j--) {
                city.get(number).floors.get(i).apart.remove(j);
            }
            city.get(number).floors.remove(i);
        }
        city.remove(number);
        System.out.println("You delete house №"+number);
    }

    private void compareHouses()  {
            Scanner in = new Scanner(System.in);
            System.out.println("Put houses numbers:");
            int number1 = in.nextInt();
            int number2 = in.nextInt();
            System.out.println("""
                    Select a parameter and enter its number
                    1.Square
                    2.Floors
                    3.Tenants""");
            int parameter = in.nextInt();
                switch (parameter) {
                    case (1) -> {
                        int i = city.get(number1).compHousePop(city.get(number2));
                        if (i == 1) {
                            System.out.println("First house has more number of tenants than second house");
                        } else if (i == -1) {
                            System.out.println("First house has less number of tenants than second house");
                        } else
                            System.out.println("First and second houses have the same number of tenants");

                    }
                    case (2) -> {
                        int i = city.get(number1).compHouseSqr(city.get(number2));
                        if (i == 1) {
                            System.out.println("First house has more square than second house");
                        } else if (i == -1) {
                            System.out.println("First house has less square than second house");
                        } else
                            System.out.println("First and second houses have the same square");
                    }
                    case (3) -> {
                        int i = city.get(number1).compHouseFlor(city.get(number2));
                        if (i == 1) {
                            System.out.println("First house has more numbers of floors than second house");
                        } else if (i == -1) {
                            System.out.println("First house has less number of floors than second house");
                        } else
                            System.out.println("First and second houses have the same number of floors");
                    }
                }
    }

    private void compareApartments() {
        System.out.print("Put house number:");
        Scanner in = new Scanner(System.in);
        int houseNumber = in.nextInt();
        System.out.print("Put apartments numbers:");
        int apart1Number = in.nextInt() - 1;
        int apart2Number = in.nextInt() - 1;
        System.out.println("""
                Select a parameter and enter its number
                1.Square
                2.Tenants""");
        int parameter = in.nextInt();
        switch (parameter) {
            case (1) -> {
                int i = city.get(houseNumber).getApart(apart1Number).compApartSqr(city.get(houseNumber).getApart(apart2Number));
                if (i == 1) {
                    System.out.println("The square of the first apartment is larger than that of the second");
                } else if (i == -1) {
                    System.out.println("First apartment has less number of tenants than second");
                } else
                    System.out.println("First and second apartments have the same square");

            }
            case (2) -> {
                int i = city.get(houseNumber).getApart(apart1Number).compApartTen(city.get(houseNumber).getApart(apart2Number));
                if (i == 1) {
                    System.out.println("The number of tenants of the first apartment is larger than that of the second");
                } else if (i == -1) {
                    System.out.println("First apartment has less number of tenants than second");
                } else
                    System.out.println("First and second apartments have the same number of tenants");
            }
        }
    }


    private void houseInformation(){
        System.out.print("Put houses number:");
        Scanner in=new Scanner(System.in);
        int number=in.nextInt();
            System.out.println("Number: " + city.get(number).getNumber());
            System.out.println("Square: " + city.get(number).getSqr());
            System.out.println("Population: " + city.get(number).getTenants());
            System.out.println("Floors: " + city.get(number).getFloors());
            System.out.println("Apartments: " + city.get(number).getApartments());
    }

    private void cityInformation(){
        System.out.println("Number of house is "+city.size());
    }

    protected void accept(String command,Program program){
        switch (command) {
            case ("build house") -> this.buildHouse();
            case ("delete house") -> this.deleteHouse();
            case ("compare houses") -> this.compareHouses();
            case ("compare apartments") -> this.compareApartments();
            case ("house information") -> this.houseInformation();
            case ("city information") -> this.cityInformation();
            case ("help") -> this.help();
            case ("exit") -> this.exit(program);
        }
    }
}