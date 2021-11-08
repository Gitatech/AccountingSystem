
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Ground {
    private Flat[] flat;
    private int N_flats;

    public Ground(int p){ //p - число квартир на этаже
        Scanner in = new Scanner(System.in);
        N_flats = p;
        flat = new Flat[p];
        for(int i = 0;i< flat.length;i++){
            System.out.print("Area of flat #" + i +": ");
            int k = in.nextInt();
            while(k<1){
                System.out.print("Area too low. Enter again.");
                System.out.print("Area of flat #" + i +": ");
                k = in.nextInt();

            }            flat[i] = new Flat(k);
        }
    }
    public Ground(@NotNull Ground ground0){
        flat = new Flat[ground0.N_flats];
        for(int i = 0;i< flat.length;i++){
            flat[i] = new Flat(ground0.flat[i]);
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

    public double Ground_area(){ // возвращает площадь этажа
        int sq = 0;
        for(int i = 0;i< flat.length;i++){
            sq += flat[i].get_sqrt();
        }
        return sq;
    }
    public double flat_area(int i){ // возвращает площадь квартиры по её номеру на этаже
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
        this.flat[1].NUM(k);
    } //для зануление сатической переменной в flat
    public int flat_on_ground()
    {
        return flat.length;
    }
}
