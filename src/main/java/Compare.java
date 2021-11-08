import java.util.ArrayList;

public class Compare implements Command {
    private static final String helpInformation = "Compare houses or flats. You must give 4 args: [0 - house, 1 - flat] " +
            "what(index of house) with(index of house) how(0 buy HumansCount, " +
            "1 by Square)";
    @Override
    public String getHelpInformation() {
        return helpInformation;
    }
    @Override
    public int execute(ArrayList<House> houses, String[] args){
        if(args.length != 4 || Integer.parseInt(args[3]) > 1){
            throw new IllegalStateException("Wrong arguments. Info about the command: \n" + helpInformation);
        }else {
            boolean compare = false;
            switch (Integer.parseInt(args[0])) {
                case 0 -> {
                    try {
                        compare = houses.get(Integer.parseInt(args[1])).compare(houses.get(Integer.parseInt(args[2])),
                                Integer.parseInt(args[3]));
                    } catch (Exception e) {
                        throw new IllegalArgumentException("I didnt find what i need to compare");
                    }
                }
                case 1 -> {
                    try {
                        compare = houses
                                .get(Integer.parseInt(args[1]))
                                .getFloors()
                                .get(0)
                                .getFlats()
                                .get(0)
                                .compare(houses
                                        .get(Integer.parseInt(args[2]))
                                        .getFloors()
                                        .get(0)
                                        .getFlats()
                                        .get(0), Integer.parseInt(args[3]));
                    } catch (Exception e) {
                        throw new IllegalArgumentException("I didnt find what i need to compare");
                    }
                }
            }
            System.out.println("Compare " +  (Integer.parseInt(args[0]) == 0 ? "houses " : "flats ") + "buy "  +
                    ( Integer.parseInt(args[3]) == 0 ?
                    "HumansCount" : "Square") + "\nResult :" + compare);
        }
        return 0;
    }
}
