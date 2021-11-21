package Bilding;

public class Flat {
    private int numberHuman = 0 ;
    private double sqrt = 0;
    private static int checker = 0;
    private int num = 0;

    public void setNHuman(int NHuman) {
        this.numberHuman = NHuman;
    }

    public void setSqrt(double sqrt) {this.sqrt = sqrt;}

    public void setNum(){
        this.num = num;
        checker++;
    }
    public int getNumberOfHuman() {return numberHuman;}
    public double getSqrt() {
        return sqrt;
    }
    public void setChecker(int k){
        checker = k;
    }
    public int getNumber() {
        return num;
    }
}

