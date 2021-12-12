package com.prokopchyk.service;

import com.prokopchyk.building.Ground;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

public class GroundServiceTest {

    private Ground ground;

    private final int flats = 4;
    private final int peopleOnFlat = 3;

    @BeforeEach
    void setUp() {
        ground = new Ground();
        for (int i = 0; i < flats; ++i) {
            ground.addFlat(FlatService.getFlatService().createFlat());
        }
    }
    @Test
    public void createGround() {
        Ground expGround = GroundService.getGroundService().createGround(flats);
        Assertions.assertEquals(expGround, ground);
    }

    @Test
    public void cloneGround() {
        Ground expGround = GroundService.getGroundService().cloneGround(ground);
        Assertions.assertEquals(expGround, ground);
    }
}