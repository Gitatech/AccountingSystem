package com.prokopchyk.service;

import com.prokopchyk.builder.FlatBilder;
import com.prokopchyk.builder.GroundBuilder;
import com.prokopchyk.builder.HouseBuilder;
import com.prokopchyk.building.Flat;
import com.prokopchyk.building.Ground;
import com.prokopchyk.building.House;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HouseServiceTest  {

    static List<Flat> flats =  new ArrayList<Flat>();
    static List<Ground> grounds = new ArrayList<Ground>();
    static List<House> houses = new ArrayList<House>();

    @BeforeAll
    static void setUp() throws Exception {
        Flat flat = new FlatBilder()
                .setSqrt(1)
                .setNumberOfHuman(1)
                .setNumOfFlat()
                .bilder();
        flats.add(flat);
        Ground ground = new GroundBuilder()
                .setGround(flats)
                .setNumOfFlats(1)
                .Builder();
        grounds.add(ground);
        House house = new HouseBuilder()
                .setHouseName("First")
                .setGrounds(grounds)
                .setNumOfGrounds(3)
                .builder();
        houses.add(house);
    }

    @AfterAll
    static void tearDown() throws Exception {
        flats.clear();
        grounds.clear();
        houses.clear();
    }

    @Test
     void testGetHouseArea() {
        double teorHouseArea = 1;
        double expHouseArea = HouseService.getHouseService().getHouseArea(houses.get(0));
        assertEquals(teorHouseArea,expHouseArea,0);
    }

    @Test
     void testGetNumberOfHuman() {
        int teorNumber = 1;
        int expNumber = HouseService.getHouseService().getNumberOfHuman(houses.get(0));
        assertEquals(teorNumber, expNumber);
    }
    @Test
     void testCompareHouses(){
        List<Integer> teor = new ArrayList<Integer>();
        teor.add(0);
        teor.add(0);
        List<Integer> exp = new ArrayList<Integer>(HouseService.getHouseService().compareHouses(houses.get(0),houses.get(0)));
        assertEquals(teor,exp);
    }
}

