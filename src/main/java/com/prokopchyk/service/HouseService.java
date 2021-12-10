package com.prokopchyk.service;

import com.prokopchyk.building.Flat;
import com.prokopchyk.building.Ground;
import com.prokopchyk.building.House;
import com.prokopchyk.building.houseComparator.AreaCompare;
import com.prokopchyk.building.houseComparator.PersonsCompare;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class HouseService  {
    private static  HouseService houseService;
    private HouseService(){};
    public synchronized static HouseService getHouseService(){
        if(houseService == null)
            houseService = new HouseService();
        return houseService;
    }
    public  double getHouseArea(House house) {
        int sqrt = 0;
        for(int i = 0; i < house.getNumberOfGrounds(); i++){
            for(int j = 0; j < house.getGrounds().get(0).getFlatsOnGround(); j++) {
            sqrt += house.getGround(i).getFlat(j).getSqrt();
            }
        }
        return sqrt;
    }

    public int getNumberOfHuman(House house){  // возвращает общее число жильцов
        int kol = 0;
        for(int i = 0; i < house.getNumberOfGrounds(); i++){
            for(int j = 0; j < house.getGrounds().get(0).getFlatsOnGround(); j++) {
                kol += house.getGround(i).getFlat(j).getNumberOfHuman();
            }
        }
        return kol;
    }
    public  List<Integer> compareHouses(House house1,House house2){
        List<Integer> answer = new ArrayList<Integer>();
        answer.add(new AreaCompare().compare(house1,house2));
        answer.add(new PersonsCompare().compare(house2,house2));
        return  answer;
    }
    public Flat getFlatByNumber(House house, int i){
        int gr = i / (house.getGrounds().get(0).getFlatsOnGround());
        int fl = i % (house.getGrounds().size());
        return house.getGrounds().get(gr).getFlat(fl);
    }

    public int getNumberOfFlats(House house){
        return house.getNumberOfGrounds() * house.getGrounds().get(0).getFlatsOnGround();
    }

    public void initPersons(House house){
        for(int i = 0; i < house.getNumberOfGrounds(); i++){
            for(int j = 0; j < house.getGrounds().get(0).getFlatsOnGround(); j++){
                System.out.println("Enter number of persons in "+house.getGrounds().get(i).getFlat(j).getNumber()+" flat");
                Scanner in = new Scanner(System.in);
                int persons =  in.nextInt();
                house.getGrounds().get(i).getFlat(j).setNumberOfHuman(persons);
            }
        }
    }

    public void initPersonsRandom(House house){
        for(int i = 0; i < house.getNumberOfGrounds(); i++){
            for(int j = 0; j < house.getGrounds().get(0).getFlatsOnGround(); j++){
                int randPersons = (int) (Math.random()*5);
                house.getGrounds().get(i).getFlat(j).setNumberOfHuman(randPersons);
            }
        }
    }
}