package com.prokopchyk.service;

import com.prokopchyk.builder.FlatBilder;
import com.prokopchyk.building.Flat;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class FlatServiceTest{
    private  Flat flat;
    private final int numPeople = 3;
    private final double area = 35;

    @BeforeEach
     void setUp(){
         flat = new FlatBilder()
                .setSqrt(area)
                .setNumberOfHuman(numPeople)
                .setNumOfFlat()
                .bilder();
    }

    @Test
     void createFlat(){
        Flat expFlat = FlatService.getFlatService().createFlat();
        Assertions.assertEquals(expFlat,flat);
    }

    @Test
     void compareFlats() {
        List<Integer> teor = new ArrayList<Integer>();
        teor.add(0);
        teor.add(0);
        List<Integer> exp = new ArrayList<Integer> (FlatService.getFlatService().compareFlats(flat,flat));
        assertEquals(teor,exp);
    }
    @Test
    void cloneFlat() {
        Flat expFlat = FlatService.getFlatService().cloneFlat(flat);
        Assertions.assertEquals(expFlat,flat);
    }
}