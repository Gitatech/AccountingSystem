package com.university.lab.model;

import java.util.ArrayList;
import java.util.Objects;

public class House {

    private final String name;
    private final double length;
    private final double width;
    private final double height;
    private final boolean roof;
    public ArrayList<Apartment> apartments = new ArrayList<>();

    public House(String name, double length, double width, double height) {
        this.name = name;
        this.length = length;
        this.width = width;
        this.height = height;
        roof = false;
    }

    public House(String name, double length, double width, double height, boolean roof) {
        this.name = name;
        this.roof = roof;
        this.length = length;
        this.width = width;
        this.height = height;
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

    public ArrayList<Apartment> getApartments() {
        return apartments;
    }

    public String getName() {
        return name;
    }

    public boolean isRoof() {
        return roof;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Double.compare(house.length, length) == 0
                && Double.compare(house.width, width) == 0
                && Double.compare(house.height, height) == 0
                && roof == house.roof
                && Objects.equals(name, house.name)
                && Objects.equals(apartments, house.apartments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, length, width, height, roof, apartments);
    }

    @Override
    public String toString() {
        return getName() +
                "{length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", roof=" + roof +
                '}';
    }
}
