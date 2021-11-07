import java.util.Scanner;

public class Flat {
    private int n_human;
    private double sqrt;
    private static int NUM = 0;
    private int num;

    public Flat(){  //создаёт квартиру с площадью из консоли
        this.num = NUM;
        Scanner in = new Scanner(System.in);
        System.out.println("Area of flat #" + NUM);
        this.sqrt = in.nextInt();
        this.n_human = (int)(Math.random()*4)+1;
        NUM++;
    }

    public Flat(Flat flat1){
            this.num = NUM;
            this.sqrt = flat1.get_sqrt();
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
