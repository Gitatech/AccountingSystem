package Bilding;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Scanner;
import Bilding.flatbilder.FlatBilderImpl;

public class Ground  {
    private Set<Flat> flat;
    private int N_flats;

    public Ground(int p){ //p - число квартир на этаже
        Scanner in = new Scanner(System.in);
        N_flats = p;
        flat = new HashSet<Flat>();
        for(int i = 0;i<N_flats;i++){
            System.out.print("Area of flat #" + i +": ");
            int k = in.nextInt();
            while(k<1){
                System.out.print("Area too low. Enter again.");
                System.out.print("Area of flat #" + i +": ");
                k = in.nextInt();
            }
            //int kol = (int)(Math.random()*5);
            Flat F =  new FlatBilderImpl().Set_Sqrt(k).Set_num(k).bilder();
            flat.add(F);
        }
    }
    public Ground(@NotNull Ground ground0){
        this.flat = new HashSet<Flat>();
        this.N_flats = ground0.N_flats;
        for(int i = 0;i< N_flats;i++){
            //int kol = (int)(Math.random()*5);
            Flat F =  new FlatBilderImpl().Set_Sqrt(ground0.flat_area(i)).bilder();
            this.flat.add(F);
        }
    }
    public int get_Man_Ground( )
    {
        int kol = 0;
        Iterator<Flat> it = flat.iterator();
        while (it.hasNext()){
           kol += it.next().getN_human();
        }
        return kol;
    }

    public double Ground_area(){ // возвращает площадь этажа
        double kol = 0;
        Iterator<Flat> it = flat.iterator();
        while (it.hasNext()){
            kol += it.next().get_sqrt();
        }
        return kol;
    }
    public double flat_area(int i){ // возвращает площадь квартиры по её номеру на этаже
        int k = i % (N_flats);
        Iterator<Flat> it = flat.iterator();
        int iter = 0;
        while(iter != k) {
            it.next();
            iter++;
        }
        return it.next().get_sqrt();
    }
    public int get_Man_Flat(int i)
    {
        int k = i % (N_flats);
        Iterator<Flat> it = flat.iterator();
        int iter = 0;
        while(iter != k) {
            it.next();
            iter++;
        }
        return it.next().getN_human();
    }
    public void NUM(int k)
    {
        this.flat.iterator().next().NUM(0);
    } //для зануление сатической переменной в flat
    public int flat_on_ground()
    {
        return N_flats;
    }
}
