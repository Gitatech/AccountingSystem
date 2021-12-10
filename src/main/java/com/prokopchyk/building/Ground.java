package com.prokopchyk.building;

import java.io.Externalizable;
import java.util.*;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Ground  implements Externalizable {
    private List<Flat> flat;
    private int NumberOfFlatsInGround = 0;

    public Ground() {
        flat = new ArrayList<>(0);
        setNumberOfFlatsInGround(0);
    }

    public void setFlat(List<Flat>flat){

        this.flat = new ArrayList<Flat>(flat);
    }

    public void addFlat(Flat flat){

        this.flat.add(flat);
    }

    public void setNumberOfFlatsInGround(int numberOfFlatsInGround) {
        NumberOfFlatsInGround = numberOfFlatsInGround;
    }

    public Flat getFlat(int i){
        return flat.get(i);
    }

    public void setFlatChecker(int k) {//для зануление сатической переменной в flat
        this.flat.iterator().next().setChecker(0);
    }

    public int getFlatsOnGround() {
        return NumberOfFlatsInGround;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(NumberOfFlatsInGround);
        out.writeObject(flat);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        NumberOfFlatsInGround = (int) in.readObject();
        flat = (List<Flat>) in.readObject();

    }
}

