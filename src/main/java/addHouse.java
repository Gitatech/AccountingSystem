import java.util.ArrayList;

public class addHouse implements Command {
    private static final String helpInformation = "Added house. You must give 5 args: floor-count flats-on-floor humans-on-flat " +
            "rooms-in-flat-count flat-square";

    @Override
    public String getHelpInformation() {
        return helpInformation;
    }

    @Override
    public int execute(ArrayList<House> houses, String[] args){
        //args : floor-count flats-on-floor humans-on-flat rooms-in-flat-count flat-square
        if(args.length < 5){
            throw new IllegalStateException("Must be 5 args: floor-count flats-on-floor humans-on-flat" +
                    " rooms-in-flat-count" + " flat-square");
        }
        //floors
        ArrayList<Floor> floors = new ArrayList<>();
        for(int i = 0; i < Integer.parseInt(args[0]); i++){
            ArrayList<Flat> flats = new ArrayList<>();
            for(int j = 0; j < Integer.parseInt(args[1]); j++){
                flats.add(new Flat(Integer.parseInt(args[3]), Integer.parseInt(args[2]), Integer.parseInt(args[4])));
            }
            floors.add(new Floor(flats));
        }
        houses.add(new House(floors));
        int index = houses.size() - 1;
        System.out.println("Added house. Index : " + index + "\ntype .printHouse " + index + " to get information");
        return 0;
    }
}
