package Bilding;

public class Flat {
    private int NHuman = 0 ;
    private double sqrt = 0;
    private static int NUM = 0;
    private int num = 0;

    public void setNHuman(int NHuman) {
        this.NHuman = NHuman;
    }

    public void setSqrt(double sqrt) {
        this.sqrt = sqrt;
    }

    public void setNum() {
        this.num = num;
        NUM++;
    }
    // public Flat(int k){  //создаёт квартиру с площадью из консоли
     //   this.num = NUM;
       // this.sqrt = k;
       // this.n_human = (int)(Math.random()*5);
       // NUM++;
    //}

  //  public Flat(@NotNull Flat flat1){
   //     this.num = NUM;
    //    this.sqrt = flat1.sqrt;
     //   this.n_human = (int)(Math.random()*5);
      //  NUM++;

    //}

    public int getN_human() {
        return NHuman;
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

