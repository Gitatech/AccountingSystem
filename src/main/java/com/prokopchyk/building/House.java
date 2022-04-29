package com.prokopchyk.building;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import com.prokopchyk.service.HouseService;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;
import java.util.Scanner;

public class House implements Externalizable {
    private List<Ground> grounds;
    private final int heightOfGround = 3;
    private int numberOfGrounds = 0;
   // private int houseNumber = 0;
    private String houseName;

    public House() {
       grounds = new ArrayList<Ground>(0);
       numberOfGrounds = 0;
    }
    public void setGrounds(List<Ground> grounds){
        this.grounds = new ArrayList<Ground>(grounds);
    }
    public void setNumberOfGrounds(int numberOfGrounds) {
        this.numberOfGrounds = numberOfGrounds;
    }
    public Ground getGround(int i){
        return grounds.get(i);
    }
    public List<Ground> getGrounds(){return grounds;}
    public int getHeightOfGround() {
        return heightOfGround;
    }
    public void addGround(Ground newGround){
        grounds.add(newGround);
    }
    public int getNumberOfGrounds(){
        return this.numberOfGrounds;
    }
    public String getHouseName() {
        return houseName;
    }
    public void setHouseName(String name) {
        houseName = name;
    }
     @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof House)) {
            return false;
        }
        House house = (House) o;
        return this.houseName == house.houseName && this.numberOfGrounds == house.numberOfGrounds;
    }
    @Override
    public int hashCode(){
        return Objects.hash(numberOfGrounds,grounds);
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
