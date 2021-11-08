import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        //register commands
        ArrayList<House> houses = new ArrayList<House>();
        Command addHouse = new addHouse();
        Command printHouse = new printHouse();
        Command compare = new Compare();

        Commands commands = new Commands();

        commands.register("addHouse", addHouse);
        commands.register("printHouse", printHouse);
        commands.register("compare", compare);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello! Use prefix \".\" with commands: addHouse, printHouse, compare. Use .h COMMAND to get " +
                "more information about this command");
        while (true) {
            System.out.print("> ");
            String command = reader.readLine();
            if(!command.startsWith(".")){
                System.out.println("Pleas read the first line. I don't know what are you want");
            }else {
                String[] margs = command.split(" ");
                if(!Objects.equals(margs[0], ".h")){
                    try {
                        commands.execute(margs[0].substring(1), houses, Arrays.copyOfRange(margs, 1, margs.length));
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }else {
                    try {
                        System.out.println(commands.get(margs[1]).getHelpInformation());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}