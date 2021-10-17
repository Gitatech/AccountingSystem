import java.util.ArrayList;

public class House extends Command{
    private final int number;
    private final int tenants;
    private final int floor;
    private final int apartments;
    private final double square;
    private static int counter=0;
    protected final int apartPerFloor;
    protected ArrayList<Floor> floors=new ArrayList<>();

    protected House(double budget, double perSqrM, int apartPerFloor) {
        this.apartPerFloor=apartPerFloor;
        double planHouseSqr = Math.ceil(budget / perSqrM);
        while (planHouseSqr - this.countSqr() > 0) {
            Floor floor = new Floor(apartPerFloor);
            floors.add(floor);
        }
        this.tenants =this.countTenants();
        this.square=this.countSqr();
        this.number=++counter;
        this.floor=floors.size();
        this.apartments=this.floor*apartPerFloor;
    }

    protected double getSqr(){
        return square;
    }

    protected int getFloors() {
        return this.floor;
    }

    protected int getNumber() {
        return number;
    }

    protected int getTenants() {
        return tenants;
    }

    protected int getApartments() {
        return apartments;
    }

    private int countTenants(){
        int pop = 0;
        for (Floor floor : floors) {
            for (int j = 0; j < apartPerFloor; j++) {
                pop += floor.getApart(j).getTenants();
            }
        }
        return pop;
    }

    protected double countSqr() {
        double sqr = 0;
        for (Floor floor : floors) {
            for (int j = 0; j < apartPerFloor; j++) {
                sqr += floor.getApart(j).getSquare();
            }
        }
        return sqr;
    }

    public Apartment getApart(int number) {
        if(number<this.apartments) {
            for (Floor floor : floors) {
                for (int i = 0; i < this.apartPerFloor; i++) {
                    if (floor.getApart(i).getNumb() == number)
                        return floor.getApart(i);
                }
            }
        }
            return null;
    }

    protected int compHouseSqr(House h){
        return Double.compare(this.getSqr(), h.getSqr());
    }

    protected int compHousePop(House h){
        return Integer.compare(this.tenants, h.getTenants());
    }

    protected int compHouseFlor(House h) {
        return Integer.compare(this.floor, h.getFloors());
    }
}