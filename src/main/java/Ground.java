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

    public int getFlat(int i) {
        return flat[i].getN_human();
    }

    public double Ground_area(){
        int sq = 0;
        for(int i = 0;i< flat.length;i++){
            sq += flat[i].get_sqrt();
        }
        return sq;
    }
}
