package building;

import houseComparator.*;
import flatComparator.*;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;
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
    public Ground getGroundByFlatNumber(int number){return grounds.get(number/grounds.size());}


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

    public List<Integer> compareFlats(int Number1,int Number2) {
        List<Integer> answer = new ArrayList<Integer>();
        answer.add(new FlatAreaCompare().compare(this.getGroundByFlatNumber(Number1).getFlatByNumber(Number1)
                ,this.getGroundByFlatNumber(Number2).getFlatByNumber(Number2)));
        answer.add(new FlatPersonsCompare().compare(this.getGroundByFlatNumber(Number1).getFlatByNumber(Number1)
                ,this.getGroundByFlatNumber(Number2).getFlatByNumber(Number2)));
        return answer;
    }

    public  List<Integer> compareHouses(@NotNull House house2)
    {
        List<Integer> answer = new ArrayList<Integer>();
        answer.add(new AreaCompare().compare(this,house2));
        answer.add(new PersonsCompare().compare(this,house2));
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

    public void setHouseName(String name) {
        houseName = name;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(houseName);
        out.writeObject(numberOfGrounds);
        out.writeObject(grounds);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        houseName = (String)in.readObject();
        numberOfGrounds = (int) in.readObject();
        grounds =(List<Ground>) in.readObject();

    }
}
