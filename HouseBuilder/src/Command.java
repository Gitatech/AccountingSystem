import java.util.InputMismatchException;
import java.util.Scanner;

public class Command{
    Program program;
    private final String[] commands={"build house","delete house","compare houses",
            "compare apartments","house information","city information", "help", "exit"};
    protected  String command;

    public Command(){
        this.command=null;
    }

    private House get(int number){
        for(House house : program.city){
            if(house.getNumber()==number)
                return house;
        }
        return null;
    }

    protected void command(String command){
        for(String string: commands)
            if(command.equalsIgnoreCase(string)){
                this.command=string;
                return;
            }
        this.command=null;
    }

    private void exit(){
        program.status=false;
    }

    private void help(){
        System.out.println("Possible commands");
        for(String command: commands)
            System.out.println(command);
    }

    private void buildHouse(){
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Input a budget: ");
            double budget = in.nextDouble();
            System.out.print("Input a price for per square metre: ");
            double sqrMPrice = in.nextDouble();
            System.out.print("Input a number of apartments per floor: ");
            int apartments = in.nextInt();
            if(sqrMPrice!=0 && budget/sqrMPrice >= apartments && apartments!=0) {
                House house = new House(budget, sqrMPrice, apartments);
                program.city.add(house);
                System.out.println("You build house");
            }else
                System.out.println("You cant build house with that parameters.");
        }catch (InputMismatchException e){
            System.err.println("Command stopped due to incorrect data entry!");
        }
    }

    private void deleteHouse() {
        System.out.print("Put houses number ");
        Scanner in=new Scanner(System.in);
        int number=in.nextInt();
        House house=this.get(number);
        if(house!=null){
            int floors = house.getFloors();
            int apartPerFloor = house.apartPerFloor;
            for (int i = floors-1; i >=0; i--) {
                if (apartPerFloor > 0) {
                    house.floors.get(i).apart.subList(0, apartPerFloor).clear();
                }
                house.floors.remove(i);
            }
            program.city.remove(house);
            System.out.println("You delete house №"+number);
        }
        else
            System.err.println("The selected house does not exist!");
    }

    private void compareHouses()  {
        try {
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
            House house1 = this.get(number1);
            House house2 = this.get(number2);
            if (house1 != null && house2 != null) {
                switch (parameter) {
                    case (1) -> {
                        int i = house1.compHousePop(house2);
                        if (i == 1) {
                            System.out.printf("%d house has more number of tenants than %d house\n",number1,number2);
                        } else if (i == -1) {
                            System.out.printf("%d house has less number of tenants than %d house\n",number1,number2);
                        } else
                            System.out.printf("%d and %d houses have the same number of tenants\n",number1,number2);

                    }
                    case (2) -> {
                        int i = house1.compHouseSqr(house2);
                        if (i == 1) {
                            System.out.printf("%d house has more square than %d house\n",number1,number2);
                        } else if (i == -1) {
                            System.out.printf("%d house has less square than %d house\n",number1,number2);
                        } else
                            System.out.printf("%d and %d houses have the same square\n",number1,number2);
                    }
                    case (3) -> {
                        int i = house1.compHouseFlor(house2);
                        if (i == 1) {
                            System.out.printf("%d house has more numbers of floors than %d house\n",number1,number2);
                        } else if (i == -1) {
                            System.out.printf("%d house has less number of floors than %d house\n",number1,number2);
                        } else
                            System.out.printf("%d and %d houses have the same number of floors\n",number1,number2);
                    }
                    default -> System.err.println("The selected option does not exist");
                }
            } else
                System.err.println("One or both of the selected houses do not exist!");
        }catch (InputMismatchException e){
            System.err.println("Command stopped due to incorrect data entry!");
        }
    }

    private void compareApartments(){
        try {
            System.out.print("Put house number:");
            Scanner in = new Scanner(System.in);
            int houseNumber = in.nextInt();
            House house = this.get(houseNumber);
            System.out.print("Put apartments numbers:");
            int apart1Number = in.nextInt() - 1;
            int apart2Number = in.nextInt() - 1;
            System.out.println("""
                    Select a parameter and enter its number
                    1.Square
                    2.Tenants""");
            int parameter = in.nextInt();
            if (house != null) {
                switch (parameter) {
                    case (1) -> {
                        try {
                            int i = house.getApart(apart1Number).compApartSqr(house.getApart(apart2Number));
                            if (i == 1) {
                                System.out.printf("The square of the %d apartment is larger than that of the %d\n", apart1Number, apart2Number);
                            } else if (i == -1) {
                                System.out.printf("%d apartment has less number of tenants than %d\n", apart1Number, apart2Number);
                            } else
                                System.out.printf("%d and %d apartments have the same square\n", apart1Number, apart2Number);
                        } catch (InputMismatchException e) {
                            System.err.println("The selected apartments do not exist!");
                        }
                    }
                    case (2) -> {
                        try {
                            int i = house.getApart(apart1Number).compApartTen(house.getApart(apart2Number));
                            if (i == 1) {
                                System.out.printf("The number of tenants of the %d apartment is larger than that of the %d\n", apart1Number, apart2Number);
                            } else if (i == -1) {
                                System.out.printf("%d apartment has less number of tenants than %d\n", apart1Number, apart2Number);
                            } else
                                System.out.printf("%d and %d apartments have the same number of tenants\n", apart1Number, apart2Number);
                        } catch (InputMismatchException e) {
                            System.err.println("One or both of the selected apartments do not exist!");
                        }
                    }
                    default -> System.err.println("The selected option does not exist");
                }
            } else
                System.out.println("The selected house does not exist!");
        }catch(InputMismatchException e){
            System.out.println("Command stopped due to incorrect data entry!");
        }
    }

    private void houseInformation(){
        System.out.print("Put houses number:");
        Scanner in=new Scanner(System.in);
        int number=in.nextInt();
        House house=this.get(number);
        if(house!=null) {
            System.out.println("Number: " + house.getNumber());
            System.out.println("Square: " + house.getSqr());
            System.out.println("Population: " + house.getTenants());
            System.out.println("Floors: " + house.getFloors());
            System.out.println("Apartments: " + house.getApartments());
        }
        else
            System.err.println("Selected house does not exist!");
    }

    private void cityInformation(){
        System.out.println("Number of house is "+program.city.size());
        if(!program.city.isEmpty()) {
            System.out.print("House numbers are:");
            for (House house : program.city)
                System.out.println("№" + house.getNumber() + " ");
        }
    }

    protected void accept(){
        switch (this.command) {
            case ("build house") -> this.buildHouse();
            case ("delete house") -> this.deleteHouse();
            case ("compare houses") -> this.compareHouses();
            case ("compare apartments") -> this.compareApartments();
            case ("house information") -> this.houseInformation();
            case ("city information") -> this.cityInformation();
            case ("help") -> this.help();
            case ("exit") -> this.exit();
        }
    }

    public String[] getCommands(){
        return commands;
    }
}