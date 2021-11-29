package builder;

import com.prokopchyk.building.Flat;
import com.prokopchyk.building.Ground;

import java.util.List;
import java.util.Scanner;

public class GroundBuilder {
    private Ground newGround;

    public GroundBuilder(){
        newGround = new Ground();
    }

    public GroundBuilder setNumOfFlats(int numFlats){
        newGround.setNumberOfFlatsInGround(numFlats);
        return this;
    }
    public GroundBuilder fillingGround(){
        double flatArea = 0;
        for(int i = 0; i < newGround.getFlatsOnGround();i++) {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter area of the flat #" + i);
            flatArea = in.nextDouble();
            Flat newFlat = new FlatBilder().setSqrt(flatArea).setNumOfFlat().bilder();
            newGround.addFlat(newFlat);
        }
        return this;
    }
    public GroundBuilder fillingGround(Ground ground){
        double flatArea = 0;
        for(int i = 0; i < newGround.getFlatsOnGround();i++) {
            flatArea = ground.getFlatArea(i);
            Flat newFlat = new FlatBilder().setSqrt(flatArea).setNumOfFlat().bilder();
            newGround.addFlat(newFlat);
        }
        return this;
    }

    public GroundBuilder setGround(List<Flat> flats){
        newGround.setFlat(flats);
        return this;
    }
    public Ground Builder(){
        return newGround;
    }
}