package com.bsu.lab.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Floor {
    private int floorNumber;
    private static int floorNumberCounter;
    private final List<Flat> flats = new ArrayList<>();
    private int flatsCount = 0;

    public Floor() {
        this.floorNumber = floorNumberCounter;
        floorNumberCounter++;

    }

    // copy constructor
    public Floor(@NotNull Floor floor) {
        this.floorNumber = floorNumberCounter;
        this.flatsCount = floor.flatsCount;
        floorNumberCounter++;
        for (int i = 0; i < floor.flatsCount; i++) {
            this.flats.add(new Flat(floor.flats.get(i)));
        }
    }

    public static void NullifyFloorNumberCounter() {
        floorNumberCounter = 0;
    }

}
