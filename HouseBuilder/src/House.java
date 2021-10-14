import java.util.ArrayList;

public class House {
    private int population;
    private ArrayList<Floor> floors=new ArrayList<Floor>();
    private final int number;
    private static int counter=0;

    public House(double budget, double perSqrM, int apartPerFloor) {
        double planHouseSqr = Math.ceil(budget / perSqrM);
        while (planHouseSqr - this.getSqr() > 0) {
            Floor floor = new Floor(apartPerFloor);
            floors.add(floor);
        }
        floors.remove(floors.size() - 1);
        Floor floor = new Floor(1);
        floors.add(floor);
        this.population=this.countPopulation();
        this.number=++counter;
    }

    public int getFloors() {
        return floors.size();
    }

    public int getNumber() {
        return number;
    }

    public int getPopulation() {
        return population;
    }

    public double getSqr() {
        double sqr = 0;
        for (int i = 0; i < floors.size(); i++) {
            int apartCount=floors.get(i).getApartCount();
            for (int j = 0; j < apartCount; j++) {
                sqr += floors.get(i).getApart(j).getSquare();
            }
        }
        return sqr;
    }

    private int countPopulation(){
        int pop = 0;
        for (int i = 0; i < floors.size(); i++) {
            int apartCount=floors.get(i).getApartCount();
            for (int j = 0; j < apartCount; j++) {
                pop += floors.get(i).getApart(j).getTenants();
            }
        }
        return pop;
    }
}