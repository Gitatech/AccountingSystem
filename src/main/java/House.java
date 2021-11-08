import java.util.ArrayList;

public class House {
    int id;
    private ArrayList<Floor> floors;

    House(ArrayList<Floor> floors) {
        this.floors = floors;
    }

    public int countFloors() {
        return floors.size();
    }

    public boolean compare(House house, int param){
        switch (param){
            case 0: return house.getHumansCount() == getHumansCount();
            case 1: return house.getSquere() == getSquere();
        }
        throw new IllegalArgumentException("Argument must be from 0 to 3");
    }

    private int getSquere() {
        int square = 0;
        for(Floor floor: floors) {
            square += floor.getFloorSquare();
        }
        return square;
    }

    private int getHumansCount() {
        int humans = 0;
        for(Floor floor: floors){
            humans += floor.getHumans();
        }
        return humans;
    }

    private int getFlatsCount() {
        int flats = 0;
        for(Floor floor: floors){
            flats += floor.countFlats();
        }
        return flats;
    }

    public void print(){
        System.out.println("Floors: " + getFloors().size());
        System.out.println("Flats: \n\tCount: " + getFlatsCount() +
                "\n\tSquare: " + getSquere() +
                "\n\tHumans: " + getHumansCount());
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }
}
