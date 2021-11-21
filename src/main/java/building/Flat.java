package building;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Flat implements Externalizable{
    private int numberHuman = 0 ;
    private double sqrt = 0;
    private static int flatNumberChecker = 0;
    private int numOfFlat = 0;

    public void setNHuman(int NHuman) {
        this.numberHuman = NHuman;
    }

    public void setSqrt(double sqrt) {this.sqrt = sqrt;}

    public void setNumOfFlat(){
        this.numOfFlat = flatNumberChecker;
        flatNumberChecker++;
    }
    public int getNumberOfHuman() {return numberHuman;}
    public double getSqrt() {
        return sqrt;
    }
    public void setChecker(int k){
        flatNumberChecker = k;
    }
    public int getNumber() {
        return numOfFlat;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(numOfFlat);
        out.writeObject(numberHuman);
        out.writeObject(sqrt);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        numOfFlat = (int) in.readObject();
        numberHuman = (int) in.readObject();
        sqrt = (double) in.readObject();

    }
}

