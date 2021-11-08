import java.util.ArrayList;

public class printHouse implements Command {
    private static final String helpInformation = "Print house. You must give 1 arg: house-index";
    @Override
    public String getHelpInformation() {
        return helpInformation;
    }
    @Override
    public int execute(ArrayList<House> houses, String[] args){
        //args : index
        if(args.length == 0){
            throw new IllegalStateException("Wrong arguments. Info about the command: \n" + helpInformation);
        }
        //floors
        House house;
        try {
            house = houses.get(Integer.parseInt(args[0]));
        }catch (Exception e){
            throw new IllegalStateException("Wrong index!");
        }
        house.print();
        return 0;
    }
}
