package com.university.lab.service;

import com.university.lab.model.House;

public class HouseBuilder implements HBuilder {

    private String name;
    private double length;
    private double width;
    private double height;

    @Override
    public void setHouseName(String name) {
        this.name = name;
    }

    @Override
    public void setHouseLength(double length) {
        this.length = length;
    }

    @Override
    public void setHouseWidth(double width) {
        this.width = width;
    }

    @Override
    public void setHouseHeight(double height) {
        this.height = height;
    }

    public House getResult() {
        return new House(name, length, width, height);
    }
}
