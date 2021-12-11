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
    static List<Flat> flats =  new ArrayList<Flat>();

    @BeforeAll
    static void setUp(){
        Flat flat = new FlatBilder()
                .setSqrt(1)
                .setNumberOfHuman(1)
                .setNumOfFlat()
                .bilder();
        flats.add(flat);
    }
    @AfterAll
    static void tearDown(){
        flats.clear();
    }
    @Test
    public void compareFlats() {
        List<Integer> teor = new ArrayList<Integer>();
        teor.add(0);
        teor.add(0);
        List<Integer> exp = new ArrayList<Integer> (FlatService.getFlatService().compareFlats(flats.get(0),flats.get(0)));
        assertEquals(teor,exp);
    }
}