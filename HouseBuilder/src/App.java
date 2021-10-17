import java.util.Scanner;

/*in this program the construction of a house
  is based on the total budget, the price per square meter
  and the number of apartments per floor
*/

public class App {
    public static void main(String[] args){
        Command command = new Command();
        command.program =new Program();
        Scanner in=new Scanner(System.in);
        String[] commands=command.getCommands();
        System.out.println("Possible commands:");
        for(String cmnd : commands)
            System.out.println(cmnd);
        do{
            String inputs = in.nextLine();
            if (inputs.isBlank()) {
                continue;
            }
            command.command(inputs);
            if(command.command!=null){
                command.accept();
            }
            else{
                System.err.println("Incorrect command!Try again.");
            }
            //program is true until the exit command is entered
        }while(command.program.isRunning());
    }
}