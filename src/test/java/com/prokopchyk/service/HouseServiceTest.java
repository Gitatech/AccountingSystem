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
import static org.junit.jupiter.api.Assertions.assertNull;

public class HouseServiceTest  {

    private House house;


    @BeforeEach
     void setUp() throws Exception {
         house = new HouseBuilder().setHouseName("Alex").setNumOfGrounds(2).builder();

        house.addGround(GroundService.getGroundService().createGround(2));
        int firstFloor = 0;
        for (int i = 1; i < 2; ++i) {
            house.addGround(GroundService.getGroundService().cloneGround(house.getGround(firstFloor)));
        }
    }
    @Test
    void getAllTest(){
        HouseService.getHouseService().save(house);
        List<House> expList = HouseService.getHouseService().getAll();
        Assertions.assertFalse(expList.isEmpty());
    }

    @Test
    void saveTest(){
        HouseService.getHouseService().save(house);
        House expHouse = HouseService.getHouseService().getByInd(0);
        Assertions.assertEquals(expHouse,house);
    }

    @Test
    void deleteTest(){
        HouseService.getHouseService().save(house);
        HouseService.getHouseService().delete(house);
        assertEquals(0,HouseService.getHouseService().getAll().size());
    }

    @Test
    void updateTest(){
        House expHouse = HouseService.getHouseService().createHouse(2,2,"Max");
        HouseService.getHouseService().save(house);
        String teor = "Max";
        HouseService.getHouseService().update(house,expHouse);
        House exp = HouseService.getHouseService().getByInd(0);
        Assertions.assertEquals(exp.getHouseName(),teor);
    }

    @Test
     void testGetHouseArea() {
        double teorHouseArea = 40;
        double expHouseArea = HouseService.getHouseService().getHouseArea(house);
        assertEquals(teorHouseArea,expHouseArea,0);
    }

    @Test
     void testGetNumberOfHuman() {
        int teorNumber = 12;
        int expNumber = HouseService.getHouseService().getNumberOfHuman(house);
        assertEquals(teorNumber, expNumber);
    }
    @Test
     void testCompareHouses(){
        List<Integer> teor = new ArrayList<Integer>();
        teor.add(0);
        teor.add(0);
        List<Integer> exp = new ArrayList<Integer>(HouseService.getHouseService().compareHouses(house,house));
        assertEquals(teor,exp);
    }
    @Test
    void cloneHouseTest(){
        House expHouse =  HouseService.getHouseService().cloneHouse(house);
        Assertions.assertEquals(expHouse,house);
    }
    @Test
    void createHouseTest(){
        House expHouse = HouseService.getHouseService().createHouse(2,2,"Alex");
        Assertions.assertEquals(expHouse,house);
    }

    @Test
    void getByIndTest(){
        HouseService.getHouseService().save(house);
        House expHouse = HouseService.getHouseService().getByInd(0);
        Assertions.assertEquals(expHouse,house);

    }
    @Test
    void getNumberOfFlatsTest(){
        int teorNum = 4;
        int expNum =  HouseService.getHouseService().getNumberOfFlats(house);
        assertEquals(expNum,teorNum);
    }

    @Test
    void getFlatByNumberTest(){
        Flat expFlat =  HouseService.getHouseService().getFlatByNumber(house,0);
        int teorFlatNum = 0;
        int expFlatNum = expFlat.getNumber();
        assertEquals(teorFlatNum,expFlatNum);
    }
    @Test
    void readHouseListTest(){
        HouseService.getHouseService().save(house);
        HouseService.getHouseService().writeHouseList("src\\test\\houseTest.txt");
        HouseService.getHouseService().delete(house);
        HouseService.getHouseService().readHouseList("src\\test\\houseTest.txt");
        List<House> expList = HouseService.getHouseService().getAll();
        Assertions.assertFalse(expList.isEmpty());
    }
}

