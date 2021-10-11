import java.util.Scanner;

public class Ground {
    Flat[] flat;

    public Ground(){
        System.out.print("Enter number of flats on ground:");
        Scanner in = new Scanner(System.in);
        flat = new Flat[in.nextInt()];
        for(int i = 0;i< flat.length;i++){
            flat[i] = new Flat();
        }
    }
    public Ground(int k){
        flat = new Flat[k];
        for(int i = 0;i< k;i++){
            flat[i] = new Flat();
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
}
