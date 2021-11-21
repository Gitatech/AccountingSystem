package Bilding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class House {
    private List<Ground> grounds;
    private final int height = 3;
    private int numberOfGrounds;

    public House() {
        System.out.print("Enter height of house:");
        Scanner in = new Scanner(System.in);  //высчитывается кол. этажей
        numberOfGrounds = (in.nextInt())/height;
        while (numberOfGrounds <= 0) {
            System.out.println("Height too low.");
            System.out.print("Enter height of house again:");
            numberOfGrounds = (in.nextInt())/height;
        }
        System.out.println("It turned out "+ numberOfGrounds +" floors");
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
        for(int i = 1; i< numberOfGrounds; i++) {
            Ground P = new Ground(grounds.get(0));
            grounds.add(P);
        }

        this.grounds.get(0).NUM(0);
    }

    public int getNumberOfMan(){  // возвращает общее число жильцов
        int KOL = 0;
        for(int i = 0; i< numberOfGrounds; i++)
        {
            KOL += grounds.get(i).get_Man_Ground();
        }
        return KOL;
    }

    public double getHouseArea(){
        int SQ = 0;
        for(int i = 0; i< numberOfGrounds; i++)
        {
            SQ += grounds.get(i).Ground_area();
        }
        return SQ;
    }

    public double getFlatArea(int i) { // возвращает площадь квартиры по её номеру
        int gr = i / (grounds.get(0).getFlatsOnGround());
        return grounds.get(gr).flat_area(i);
    }

    public int getManInFlat(int i){ //возвращает кол. жильцов в квартире через номер квартиры
        int gr = i / (grounds.get(0).getFlatsOnGround());
        return grounds.get(gr).get_Man_Flat(i);
    }

    public void compareFlats(int Number1,int Number2) {
        if(this.getFlatArea(Number1) > this.getFlatArea(Number2)){
            System.out.println("The area on the first flat is larger: "+ this.getFlatArea(Number1));
        }
        else if(this.getFlatArea(Number1) < this.getFlatArea(Number2)){
            System.out.println("The area on the second flat is larger: "+ this.getFlatArea(Number2));
        }
        else{
            System.out.println("Areas are equal: " + this.getFlatArea(Number1));
        }
        if(this.getManInFlat(Number1) > this.getManInFlat(Number2)){
            System.out.println("There are more people on the first flat: "+ this.getManInFlat(Number1));
        }
        else if(this.getManInFlat(Number1) < this.getManInFlat(Number2)){
            System.out.println("There are more people on the second flat: "+ this.getManInFlat(Number2));
        }
        else{
            System.out.println("Residents equally: " + this.getManInFlat(Number1));
        }
    }

    public  void compareHouses(@NotNull House house2)
    {
        if(this.getHouseArea() > house2.getHouseArea()){
            System.out.println("House 1 bigger with the area " + this.getHouseArea() + ". Area of the 2 house: " + house2.getHouseArea());
        }
        else  if(this.getHouseArea() < house2.getHouseArea()){
            System.out.println("House 2 bigger with the area " + house2.getHouseArea() + ". Area of the 1 house: " + this.getHouseArea());
        }
        else{
            System.out.println("Areas are the same: " + house2.getHouseArea());
        }


        if(this.getNumberOfMan() > house2.getNumberOfMan()){
            System.out.println("There are more people in the first house: "+ this.getNumberOfMan() +" vs "+house2.getNumberOfMan());
        }
        else  if(this.getNumberOfMan() < house2.getNumberOfMan()){
            System.out.println("There are more people in the first house: "+ house2.getNumberOfMan() +" vs "+this.getNumberOfMan());
        }
        else{
            System.out.println("Equal number of residents: " +house2.getNumberOfMan());
        }
    }
    public int getNumberOfGroundsInHouse(){
        return this.numberOfGrounds;
    }
    public int getNumberOfFlatsInHouse()
    {
        return (this.numberOfGrounds * grounds.get(0).getFlatsOnGround());
    }
    public void initPersons(){
        Scanner in = new Scanner(System.in);
        for(int i = 0; i< numberOfGrounds; i++){
            grounds.get(i).initPersons();
        }
    }
    public void initPersonsRandom(){
        Scanner in = new Scanner(System.in);
        for(int i = 0; i< numberOfGrounds; i++){
            grounds.get(i).initPersonsRandom();
        }
    }
}
