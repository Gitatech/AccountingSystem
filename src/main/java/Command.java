import java.util.ArrayList;

interface Command {
    String getHelpInformation();
    int execute(ArrayList<House> houses, String[] args);
}