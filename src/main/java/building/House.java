package building;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import builder.GroundBuilder;
import service.HouseService;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class House implements Externalizable {
    private List<Ground> grounds;
    private final int height = 3;
    private int numberOfGrounds = 0;
    private int houseNumber = 0;
    private String houseName;

    public House() {
       grounds = new ArrayList<Ground>(0);
       houseNumber = 0;
       numberOfGrounds = 0;
    }
    public void setNumberOfGrounds(int numberOfGrounds) {
        this.numberOfGrounds = numberOfGrounds;
    }

    public Ground getGround(int i){
        return grounds.get(i);
    }


    public int getHeight() {
        return height;
    }

    public int getNumberOfHuman(){  // возвращает общее число жильцов
        return HouseService.getNumberOfHuman(this);
    }

    public double getHouseArea(){
        return HouseService.getHouseArea(this);
    }

    public double getFlatArea(int i) { // возвращает площадь квартиры по её номеру
        int gr = i / (grounds.get(0).getFlatsOnGround());
        return grounds.get(gr).getFlatArea(i);
    }

    public int getManInFlat(int i){ //возвращает кол. жильцов в квартире через номер квартиры
        int gr = i / (grounds.get(0).getFlatsOnGround());
        return grounds.get(gr).getManInFlat(i);
    }

    public void addGround(Ground newGround){
        grounds.add(newGround);
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

    public  String compareHouses(@NotNull House house2)
    {
        String answer = new String();
        if(this.getHouseArea() > house2.getHouseArea()){
            System.out.println("House 1 bigger with area "+ this.getHouseArea());
        }
        else  if(this.getHouseArea() < house2.getHouseArea()){
            System.out.println("House 2 bigger with area "+ house2.getHouseArea());
        }
        else{
            System.out.println("Areas are the same: " +house2.getHouseArea());
        }


        if(this.getNumberOfHuman() > house2.getNumberOfHuman()){
            System.out.println("There are more people in the first house: "+ this.getNumberOfHuman() +" vs "+house2.getNumberOfHuman());
        }
        else  if(this.getNumberOfHuman() < house2.getNumberOfHuman()){
            System.out.println("There are more people in the first house: "+ house2.getNumberOfHuman() +" vs "+this.getNumberOfHuman());
        }
        else{
            System.out.println("Equal number of residents: " +house2.getNumberOfHuman());
        }
        return answer;
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

    public String getHouseName() {
        return houseName;
    }


    //class CompareArea implements Comparator<House>{
      //  public int compare(@NotNull House house1, House house2)
       // {
        //    return house1.getHouseArea()- house1.getHouseArea();
       // }
   // }
    public void setHouseName(String name) {
        houseName = name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(houseName);
        out.writeInt(numberOfGrounds);
        out.writeObject(grounds);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        houseName = (String)in.readObject();
        numberOfGrounds = (int) in.readObject();
        grounds =(List<Ground>) in.readObject();

    }
}
