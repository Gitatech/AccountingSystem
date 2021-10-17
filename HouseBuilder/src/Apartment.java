public class Apartment extends Command{
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

