package com.prokopchyk.service;

import com.prokopchyk.builder.FlatBilder;
import com.prokopchyk.builder.GroundBuilder;
import com.prokopchyk.builder.HouseBuilder;
import com.prokopchyk.building.Flat;
import com.prokopchyk.building.Ground;
import com.prokopchyk.building.House;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HouseServiceTest extends TestCase {

    private List<Flat> flats;
    private List<Ground> grounds;
    private List<House> houses;

    @Before
    public void setUp() throws Exception {
        flats = new ArrayList<Flat>();
        grounds = new ArrayList<Ground>();
        houses = new ArrayList<House>();

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

    @After
    public void tearDown() throws Exception {
        flats.clear();
        grounds.clear();
        houses.clear();
    }

    @Test
    public void testGetHouseArea() {
        double teorHouseArea = 1;
        double expHouseArea = HouseService.getHouseArea(houses.get(0));
        assertEquals(teorHouseArea,expHouseArea,0);
    }

    @Test
    public void testGetNumberOfHuman() {
        int teorNumber = 1;
        int expNumber = HouseService.getNumberOfHuman(houses.get(0));
        assertEquals(teorNumber, expNumber);
    }
    @Test
    public void testCompareHouses(){
        List<Integer> teor = new ArrayList<Integer>();
        teor.add(0);
        teor.add(0);
        List<Integer> exp = new ArrayList<Integer>(HouseService.compareHouses(houses.get(0),houses.get(0)));
        Assert.assertEquals(teor,exp);
    }
    @Test
    public void testCompareFlats(){
        List<Integer> teor = new ArrayList<Integer>();
        teor.add(0);
        teor.add(0);
        List<Integer> exp = new ArrayList<Integer>(HouseService.compareFlats(houses.get(0),0,0));
        Assert.assertEquals(teor,exp);
    }
}