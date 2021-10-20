import java.util.Scanner;

public class Flat {
    private int n_human;
    private double sqrt;
    private static int NUM = 0;
    private int num;

    public Flat(){
        this.n_human = (int) (Math.random()*5);
        this.sqrt = Math.random()*60;
    }

    public Flat(boolean how){
        if(how){
            this.num = NUM;
            Scanner in = new Scanner(System.in);
            System.out.println("Number of human on the flat #" +NUM);
            int num = in.nextInt();
            System.out.println("Area of flat #" + NUM);
            int are = in.nextInt();
            this.n_human = num;
            this.sqrt = are;
            NUM++;
        }
        else{
            this.n_human = (int) (Math.random()*5);
            this.sqrt = Math.random()*60;
            this.num = NUM;
            NUM++;
        }
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
