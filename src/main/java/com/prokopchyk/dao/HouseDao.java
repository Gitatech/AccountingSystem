package com.prokopchyk.dao;

import com.prokopchyk.building.House;

import java.util.ArrayList;
import java.util.List;

public class HouseDao implements Dao<House>{
    private static List<House> houses = new ArrayList<>();

    @Override
    public List getAll() {
        return houses;
    }

    @Override
    public void save(House object) {
        houses.add(object);

    }

    @Override
    public void update(House object, House objectNew) {
        houses.set(houses.indexOf(object),objectNew);

    }

    @Override
    public void delete(House object) {
        houses.remove(object);

    }
    public void printAllNames(){
        int i = 1;
        for(House house:houses){
            System.out.println(  i +": " + house.getHouseName());
        }
    }
    public House getByNumber(int k){
        return houses.get(k);
    }
}
