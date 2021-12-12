package com.prokopchyk.dao;

import com.prokopchyk.building.House;

import java.util.ArrayList;
import java.util.List;

public class HouseDaoImp implements HouseDao{
    private static List<House> houses = new ArrayList<>();

    @Override
    public List getAll() {
        return houses;
    }

    @Override
    public void save(House object) {
        houses.add(object);

    }
    public House getHouse(House house){
         return null;
    }

    @Override
    public void update(House object, House objectNew) {
        houses.set(houses.indexOf(object),objectNew);

    }

    @Override
    public void delete(House object) {
        houses.remove(object);

    }

    public House getByNumber(int k){
        return houses.get(k);
    }
}
