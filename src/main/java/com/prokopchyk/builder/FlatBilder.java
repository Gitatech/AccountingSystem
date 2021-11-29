package com.prokopchyk.builder;

import com.prokopchyk.building.Flat;

public class FlatBilder {
    private Flat flat;

     public FlatBilder()
     {
        flat = new Flat();
     }

    public FlatBilder setNumberOfHuman(int nHuman) {
        this.flat.setNHuman(nHuman);
        return this;
    }

    public FlatBilder setSqrt(double sqrt) {
        this.flat.setSqrt(sqrt);
        return this;
    }


    public FlatBilder setNumOfFlat() {
        this.flat.setNumOfFlat();
        return this;
    }

    public Flat bilder(){
        return flat;
    }
}
