import java.util.ArrayList;

public class Floor {
    private int floorNumber;
    private static int counter=0;
    private ArrayList<Apartment> apart=new ArrayList<Apartment>();

    public Floor(int apartPerFloor){
        this.floorNumber=++counter;
        for(int i=0;i<apartPerFloor;i++) {
            Apartment apartment = new Apartment();
            apart.add(apartment);
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public int getPop(){
        int count = 0;
        for(int i=0;i< apart.size();i++)
        {
            count+=apart.get(i).getTenants();
        }
        return count;
    }
    public Apartment getApart(int i){
        return apart.get(i);
    }

    public int getApartCount(){
        return apart.size();
    }
}