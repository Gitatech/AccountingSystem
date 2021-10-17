import java.util.ArrayList;

public class House{
    private final int number;
    private final int tenants;
    private final int floor;
    private final int apartments;
    private final double square;
    private static int counter=0;
    protected final int apartPerFloor;
    protected ArrayList<Floor> floors=new ArrayList<>();

    class Floor{
        protected ArrayList<Apartment> apart=new ArrayList<>();

        class Apartment{
            private final double square;
            private final int tenants;
            private final int numb;
            private static int counter=0;
            public final int MIN_SQR=10;//minimal sqr for per person


            protected Apartment(){
                this.square=10+Math.random()*50;
                this.tenants=(int)Math.floor(this.square/MIN_SQR);
                this.numb=++counter;
            }

            protected int getTenants(){
                return tenants;
            }

            protected double getSquare() {
                return square;
            }

            protected int getNumb() {
                return numb;
            }

            protected int compApartSqr(Apartment apartment){
                return Double.compare(apartment.getSquare(), this.getSquare());
            }

            protected int compApartTen(Apartment apartment) {
                return Integer.compare(apartment.getTenants(), this.getTenants());
            }
        }

        public Floor(int apartPerFloor){
            for(int i=0;i<apartPerFloor;i++) {
                Apartment apartment = new Apartment();
                apart.add(apartment);
            }
        }

        protected Apartment getApart(int i){
            return apart.get(i);
        }
    }

    protected House(double budget, double perSqrM, int apartPerFloor) {
        this.apartPerFloor=apartPerFloor;
        double planHouseSqr = Math.ceil(budget / perSqrM);
        while (planHouseSqr - this.countSqr() > 0) {
            Floor floor = new Floor(apartPerFloor);
            floors.add(floor);
        }
        floors.remove(floors.size()-1);
        this.tenants =this.countTenants();
        this.square=this.countSqr();
        this.number=++counter;
        this.floor=floors.size();
        this.apartments=this.floor*apartPerFloor;
        Floor.Apartment.counter=0;
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

    public Floor.Apartment getApart(int number) {
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