package com.prokopchyk.builder;

import com.prokopchyk.building.Ground;
import com.prokopchyk.building.House;

import java.util.List;
import java.util.Scanner;

public class HouseBuilder {
    private House newHouse;

    public HouseBuilder() {
        newHouse = new House();
    }

    public HouseBuilder setNumOfGrounds(int height) {
        int numberOfGround = height / newHouse.getHeight();
        newHouse.setNumberOfGrounds(numberOfGround);
        return this;
    }

    public HouseBuilder setHouseName(String name) {
        newHouse.setHouseName(name);
        return this;
    }

    public HouseBuilder FillingHouse() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number of flats in ground:");
        int k = in.nextInt();
        while (k < 1) {
            System.out.print("Incorrect value.");
            System.out.print("Enter number of flats in ground:");
            k = in.nextInt();
        }
        Ground first = new GroundBuilder()
                .setNumOfFlats(k)
                .fillingGround()
                .Builder();
        newHouse.addGround(first);
        for (int i = 1; i < newHouse.getNumberOfGroundsInHouse(); i++) {
            Ground other = new GroundBuilder()
                    .setNumOfFlats(k)
                    .fillingGround(first)
                    .Builder();
            newHouse.addGround(other);
        }
        first.setFlatChecker(0);
        return this;
    }

    public HouseBuilder setGrounds(List<Ground> grounds){
        newHouse.setGrounds(grounds);
        return this;
    }

    public House builder(){
        return newHouse;
    }
}