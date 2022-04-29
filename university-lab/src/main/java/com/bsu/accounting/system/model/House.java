package com.bsu.accounting.system.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class House {

    private String name;
    private double length;
    private double width;
    private double height;
    private final List<Floor> floors = new ArrayList<>();

    public House(String name, double length, double width, double height) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public House() {
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public Floor getFloor(int index) {
        return floors.get(index);
    }

    public void setFloors(Floor floor) {
        this.floors.add(floor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Double.compare(house.length, length) == 0
                && Double.compare(house.width, width) == 0
                && Double.compare(house.height, height) == 0
                && Objects.equals(name, house.name)
                && Objects.equals(floors, house.floors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length, width, height, floors);
    }

    @Override
    public String toString() {
        return getName() +
                "{length=" + length +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
