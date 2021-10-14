public class Apartment {
    private double square;
    private int tenants;
    private final int numb;
    public final int MIN_SQR=10;//minimal sqr for per person
    private static int counter=0;

    public Apartment(){
        this.square=10+Math.random()*50;
        this.tenants=(int)Math.floor(this.square/MIN_SQR);
        this.numb=++counter;
    }

    public int getTenants(){
        return tenants;
    }

    public double getSquare() {
        return square;
    }

    public int getNumb() {
        return numb;
    }
}