public class Flat {
    private int n_human;
    private double sqrt;

    public Flat(){
        this.n_human = (int) (Math.random()*5);
        this.sqrt = Math.random()*60;
    }

    public int getN_human() {
        return n_human;
    }
    public double get_sqrt() {
        return sqrt;
    }
}
