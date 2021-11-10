package Bilding;

import org.jetbrains.annotations.NotNull;

import java.util.*;

import Bilding.flatbilder.FlatBilderImpl;

public class Ground  {
    private List<Flat> flat;
    private int N_flats;

    public Ground(int p){ //p - число квартир на этаже
        Scanner in = new Scanner(System.in);
        N_flats = p;
        flat = new ArrayList<Flat>();
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
        this.flat = new ArrayList<Flat>();
        this.N_flats = ground0.N_flats;
        for(int i = 0;i< N_flats;i++){
            //int kol = (int)(Math.random()*5);
            Flat F =  new FlatBilderImpl().Set_Sqrt(ground0.flat_area(i)).bilder();
            this.flat.add(F);
        }
    }
    public void initPersons(){
        Scanner in = new Scanner(System.in);
        for(int i =0;i<N_flats;i++){
            System.out.println("Enter number of Person in the " + flat.get(i).getNumber() + "flat");
            int k = in.nextInt();
            flat.get(i).setNHuman(k);
        }
    }
    public void initPersonsRandom(){
        Scanner in = new Scanner(System.in);
        for(int i =0;i<N_flats;i++){
            int k = (int)(Math.random()*5);
            flat.get(i).setNHuman(k);
        }
    }
    public int get_Man_Ground( )
    {
        int kol = 0;
        for(int i = 0;i<N_flats;i++){
           kol += flat.get(i).getN_human();
        }
        return kol;
    }

    public double Ground_area(){ // возвращает площадь этажа
        double kol = 0;
        for(int i = 0;i<N_flats;i++){
            kol += flat.get(i).get_sqrt();
        }
        return kol;
    }
    public double flat_area(int i){ // возвращает площадь квартиры по её номеру на этаже
        int gr = i % (flat.size());
        return flat.get(gr).get_sqrt();
    }
    public int get_Man_Flat(int i)
    {
        int gr = i % (flat.size());
        return flat.get(gr).getN_human();
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
