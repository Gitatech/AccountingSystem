import java.util.ArrayList;

public class Floor extends Command{
    protected ArrayList<Apartment> apart=new ArrayList<>();

    public Floor(int apartPerFloor){
        for(int i=0;i<apartPerFloor;i++) {
            Apartment apartment = new Apartment();
            apart.add(apartment);
        }
    }

    public Apartment  getApart(int i){
        return apart.get(i);
    }
}