package builder;

import building.Flat;

public class FlatBilder {
    private Flat flat;

     public FlatBilder()
     {
        flat = new Flat();
     }

    public FlatBilder setNumberOfHuman(int N_human) {
        this.flat.setNHuman(N_human);
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
