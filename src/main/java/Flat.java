import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Flat {
    private int n_human;
    private double sqrt;
    private static int NUM = 0;
    private int num;

    public Flat(int k){  //создаёт квартиру с площадью из консоли
        this.num = NUM;
        this.sqrt = k;
        this.n_human = (int)(Math.random()*5);
        NUM++;
    }

    public Flat(@NotNull Flat flat1){
            this.num = NUM;
            this.sqrt = flat1.sqrt;
            this.n_human = (int)(Math.random()*5);
            NUM++;

    }

    public int getN_human() {
        return n_human;
    }
    public double get_sqrt() {
        return sqrt;
    }
    public void NUM(int k){
        NUM = k;
    }
    public int getNumber() {
        return num;
    }
}
