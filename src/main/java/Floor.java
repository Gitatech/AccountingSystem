import java.util.ArrayList;

public class Floor {
    private ArrayList<Flat> flats;
    Floor(ArrayList<Flat> flats){
        this.flats = flats;
    }

    public int countFlats(){
        return flats.size();
    }

    public int getFloorSquare() {
        int square = 0;
        for(Flat flat: flats){
            square += flat.getSquare();
        }
        return square;
    }

    public ArrayList<Flat> getFlats() {
        return flats;
    }

    public void setFlats(ArrayList<Flat> flats) {
        this.flats = flats;
    }

    public int getHumans() {
        int humans = 0;
        for(Flat flat: flats){
           humans += flat.getHumansCount();
        }
        return humans;
    }
}
