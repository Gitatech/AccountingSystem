import java.util.Scanner;

/*in this program the construction of a house
  is based on the total budget, the price per square meter
  and the number of apartments per floor
*/

public class App {
    public static void main(String[] args){
        Program program=new Program();
        program.command=new Command();
        Scanner in=new Scanner(System.in);
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
        do{
            String inputs = in.nextLine();
            if (inputs.isBlank()) {
                continue;
            }
            program.command.command(inputs);
            if(program.command.command!=null){
                program.command.accept(inputs,program);
            }
            else{
                System.err.println("Incorrect command!Try again.");
            }
            //program is true until the exit command is entered
        }while(program.isRunning());
    }
}