
import java.util.Scanner;

public class House {
    //private int N_ground;
    Ground[]grounds;

    public House() {
        System.out.println("Enter number of grounds:");
        Scanner in = new Scanner(System.in);
        this.grounds = new Ground[in.nextInt()];
        System.out.println("Enter number of flats in ground:");
        int k = in.nextInt();
        //Ground[] grounds = new Ground[N_ground];
        for (int i = 0; i < grounds.length; i++) {
            grounds[i] = new Ground(k);

        }
    }

    public int getN_man_in_flat(int ground,int flat) {
        return grounds[ground].getFlat(flat);
    }

    public double House_area(){
        int SQ = 0;
        for(int i = 0;i<grounds.length;i++)
        {
            SQ += grounds[i].Ground_area();
        }
        return SQ;
    }
}