package Bilding.flatbilder;

import Bilding.Flat;

public class FlatBilderImpl implements FlatBilder {
    private int NH;
    private double sq;
    // private static int NUM = 0;
    private int n;

    @Override
    public FlatBilderImpl SetNHuman(int N_human) {
        this.NH = N_human;
        return this;
    }

    @Override
    public FlatBilderImpl Set_Sqrt(double sqrt) {
        this.sq = sqrt;
        return this;
    }

    @Override
    public FlatBilderImpl Set_num(int num) {
        this.n = num;
        return this;
    }

    public Flat bilder(){
        Flat flat = new Flat();
        flat.setNHuman(NH);
        flat.setSqrt(sq);
        flat.setNum();
        return flat;
    }
}
