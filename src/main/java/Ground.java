import java.util.Scanner;

public class Ground {
    private Flat[] flat;
    private int N_flats;

    public Ground(){
        System.out.print("Enter number of flats on ground:");
        Scanner in = new Scanner(System.in);
        N_flats = in.nextInt();
        while(N_flats<=0) {
            System.out.println("Nice try.");
            System.out.println("Enter number of flats in ground:");
            N_flats = in.nextInt();
        }
        flat = new Flat[N_flats];
        for(int i = 0;i< flat.length;i++){
            flat[i] = new Flat();
        }
    }
    public Ground(int k,boolean l) {
        flat = new Flat[k];
        for (int i = 0; i < k; i++) {
            flat[i] = new Flat(l);
        }
    }
    public int get_Man_Ground( )
    {
        int kol = 0;
        for(int i = 0;i< flat.length;i++){
            kol += flat[i].getN_human();
        }
        return kol;
    }

    public double Ground_area(){
        int sq = 0;
        for(int i = 0;i< flat.length;i++){
            sq += flat[i].get_sqrt();
        }
        return sq;
    }
    public double flat_area(int i){
        int k = i % ((flat.length));
        return flat[k].get_sqrt();
    }
    public int get_Man_Flat(int i)
    {
        i = i % (flat.length);
        return flat[i].getN_human();
    }
    public void NUM(int k)
    {
        this.flat[1].NUM(1);
    }
    public int flat_on_ground()
    {
        return flat.length;

    }
}
