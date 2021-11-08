import java.util.ArrayList;
import java.util.HashMap;

public class Commands {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public int execute(String commandName, ArrayList<House> houses, String[] args) {
        Command command = get(commandName);
        return command.execute(houses, args);
    }
    public Command get(String commandName){
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException("no command registered for " + commandName);
        }
        return command;
    }
}
