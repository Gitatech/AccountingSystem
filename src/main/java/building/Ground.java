package Bilding;

import org.jetbrains.annotations.NotNull;

import java.util.*;

import Bilding.flatbilder.FlatBilderImpl;

public class Ground  {
    private List<Flat> flat;
    private int NumberOfFlatsInGround = 0;

    public Ground(int p){ //p - число квартир на этаже
        Scanner in = new Scanner(System.in);
        NumberOfFlatsInGround = p;
        flat = new ArrayList<Flat>();
        for(int i = 0;i<NumberOfFlatsInGround;i++){
            System.out.print("Area of flat #" + i +": ");
            int k = in.nextInt();
            while(k<1){
                System.out.print("Area too low. Enter again.");
                System.out.print("Area of flat #" + i +": ");
                k = in.nextInt();
            }
            //int kol = (int)(Math.random()*5);
            Flat F =  new FlatBilderImpl().setSqrt(k).setNum(k).bilder();
            flat.add(F);
        }
    }
    public Ground(@NotNull Ground ground0){
        this.flat = new ArrayList<Flat>();
        this.NumberOfFlatsInGround = ground0.NumberOfFlatsInGround;
        for(int i = 0;i< NumberOfFlatsInGround;i++){
            Flat F =  new FlatBilderImpl().setSqrt(ground0.flat_area(i)).bilder();
            this.flat.add(F);
        }
    }
    public void initPersons(){
        Scanner in = new Scanner(System.in);
        for(int i =0;i<NumberOfFlatsInGround;i++){
            System.out.println("Enter number of Person in the " + flat.get(i).getNumber() + "flat");
            int k = in.nextInt();
            flat.get(i).setNHuman(k);
        }
    }
    public void initPersonsRandom(){
        Scanner in = new Scanner(System.in);
        for(int i =0;i<NumberOfFlatsInGround;i++){
            int k = (int)(Math.random()*5);
            flat.get(i).setNHuman(k);
        }
    }
    public int get_Man_Ground( )
    {
        int kol = 0;
        for(int i = 0;i<NumberOfFlatsInGround;i++){
           kol += flat.get(i).getNumberOfHuman();
        }
        return kol;
    }

    public double Ground_area(){ // возвращает площадь этажа
        double kol = 0;
        for(int i = 0;i<NumberOfFlatsInGround;i++){
            kol += flat.get(i).getSqrt();
        }
        return kol;
    }
    public double flat_area(int i){ // возвращает площадь квартиры по её номеру на этаже
        int ground = i % (flat.size());
        return flat.get(ground).getSqrt();
    }
    public int get_Man_Flat(int i)
    {
        int gr = i % (flat.size());
        return flat.get(gr).getNumberOfHuman();
    }
    public void NUM(int k)
    {
        this.flat.iterator().next().setChecker(0);
    } //для зануление сатической переменной в flat
    public int getFlatsOnGround()
    {
        return NumberOfFlatsInGround;
    }
}
