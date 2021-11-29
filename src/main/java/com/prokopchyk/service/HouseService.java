package com.prokopchyk.service;

import com.prokopchyk.building.House;
import com.prokopchyk.building.flatComparator.FlatAreaCompare;
import com.prokopchyk.building.flatComparator.FlatPersonsCompare;
import com.prokopchyk.building.houseComparator.AreaCompare;
import com.prokopchyk.building.houseComparator.PersonsCompare;

import java.util.ArrayList;
import java.util.List;

public class HouseService  {

    public static double getHouseArea(House house) {
        int sqrt = 0;
        for(int i = 0; i< house.getNumberOfGroundsInHouse(); i++)
        {
            sqrt += house.getGround(i).getGroundArea();
        }
        return sqrt;
    }

    public static int getNumberOfHuman(House house){  // возвращает общее число жильцов
        int kol = 0;
        for(int i = 0; i< house.getNumberOfGroundsInHouse(); i++)
        {
            kol += house.getGround(i).getManInGround();
        }
        return kol;
    }

    public static List<Integer> compareFlats(House house,int num1,int num2){
        List<Integer> answer = new ArrayList<Integer>();
        answer.add(new FlatAreaCompare().compare(house.getGroundByFlatNumber(num1).getFlatByNumber(num1)
                ,house.getGroundByFlatNumber(num2).getFlatByNumber(num2)));
        answer.add(new FlatPersonsCompare().compare(house.getGroundByFlatNumber(num1).getFlatByNumber(num1)
                ,house.getGroundByFlatNumber(num2).getFlatByNumber(num2)));
        return answer;
    }

    public static List<Integer> compareHouses(House house1,House house2){
        List<Integer> answer = new ArrayList<Integer>();
        answer.add(new AreaCompare().compare(house1,house2));
        answer.add(new PersonsCompare().compare(house2,house2));
        return  answer;
    }
}