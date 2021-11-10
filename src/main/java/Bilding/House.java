package Bilding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class House {
    private List<Ground> grounds;
    private final int height = 3;
    private int N_grounds;

    public House() {
        System.out.print("Enter height of house:");
        Scanner in = new Scanner(System.in);  //высчитывается кол. этажей
        N_grounds = (in.nextInt())/height;
        while (N_grounds <= 0) {
            System.out.println("Height too low.");
            System.out.print("Enter height of house again:");
            N_grounds = (in.nextInt())/height;
        }
        System.out.println("It turned out "+ N_grounds +" floors");
        grounds = new ArrayList<Ground>();
        System.out.print("Enter number of flats in ground:");
        int k = in.nextInt();
        while(k<1){
            System.out.print("Incorrect value.");
            System.out.print("Enter number of flats in ground:");
            k = in.nextInt();
        }
        Ground F = new Ground(k);
        grounds.add(F);
        for(int i = 1;i< N_grounds;i++) {
            Ground P = new Ground(grounds.get(0));
            grounds.add(P);
        }

        this.grounds.get(0).NUM(0);
    }





    public int get_N_man(){  // возвращает общее число жильцов
        int KOL = 0;
        for(int i = 0;i<N_grounds;i++)
        {
            KOL += grounds.get(i).get_Man_Ground();
        }
        return KOL;
    }

    public double House_area(){
        int SQ = 0;
        for(int i = 0;i<N_grounds;i++)
        {
            SQ += grounds.get(i).Ground_area();
        }
        return SQ;
    }

    public double Flat_area(int i) { // возвращает площадь квартиры по её номеру
        int gr = i / (grounds.get(0).flat_on_ground());
        return grounds.get(gr).flat_area(i);
    }

    public int get_Man_falt(int i){ //возвращает кол. жильцов в квартире через номер квартиры
        int gr = i / (grounds.get(0).flat_on_ground());
        return grounds.get(gr).get_Man_Flat(i);
    }

    public void Compare_flats(int Number1,int Number2) {
        if(this.Flat_area(Number1) > this.Flat_area(Number2)){
            System.out.println("The area on the first flat is larger: "+ this.Flat_area(Number1));
        }
        else if(this.Flat_area(Number1) < this.Flat_area(Number2)){
            System.out.println("The area on the second flat is larger: "+ this.Flat_area(Number2));
        }
        else{
            System.out.println("Areas are equal: " + this.Flat_area(Number1));
        }
        if(this.get_Man_falt(Number1) > this.get_Man_falt(Number2)){
            System.out.println("There are more people on the first flat: "+ this.get_Man_falt(Number1));
        }
        else if(this.get_Man_falt(Number1) < this.get_Man_falt(Number2)){
            System.out.println("There are more people on the second flat: "+ this.get_Man_falt(Number2));
        }
        else{
            System.out.println("Residents equally: " + this.get_Man_falt(Number1));
        }
    }

    public  void compare_houses(@NotNull House house2)
    {
        if(this.House_area() > house2.House_area()){
            System.out.println("House 1 bigger with the area " + this.House_area() + ". Area of the 2 house: " + house2.House_area());
        }
        else  if(this.House_area() < house2.House_area()){
            System.out.println("House 2 bigger with the area " + house2.House_area() + ". Area of the 1 house: " + this.House_area());
        }
        else{
            System.out.println("Areas are the same: " + house2.House_area());
        }


        if(this.get_N_man() > house2.get_N_man()){
            System.out.println("There are more people in the first house: "+ this.get_N_man() +" vs "+house2.get_N_man());
        }
        else  if(this.get_N_man() < house2.get_N_man()){
            System.out.println("There are more people in the first house: "+ house2.get_N_man() +" vs "+this.get_N_man());
        }
        else{
            System.out.println("Equal number of residents: " +house2.get_N_man());
        }
    }
    public int N_house_grounds(){
        return this.N_grounds;
    }
    public int N_flats_in_house()
    {
        return (this.N_grounds * grounds.get(0).flat_on_ground());
    }
    public void InitPersons(){
        Scanner in = new Scanner(System.in);
        for(int i =0;i<N_grounds;i++){
            grounds.get(i).initPersons();
        }
    }
    public void InitPersonsRandom(){
        Scanner in = new Scanner(System.in);
        for(int i =0;i<N_grounds;i++){
            grounds.get(i).initPersonsRandom();
        }
    }
}
