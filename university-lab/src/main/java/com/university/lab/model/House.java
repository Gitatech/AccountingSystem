package com.university.lab.model;

import java.util.ArrayList;
import java.util.Objects;

public class House {

    private final boolean roof;
    private final double length;
    private final double width;
    private final double height;

    ArrayList<Apartment> apartments = new ArrayList<>();

    public House(double length, double width, double height) {
        this.length = length;
        this.width = width;
        this.height = height;
        roof = false;
    }

    public House(boolean roof, double length, double width, double height) {
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

    public boolean isRoof() {
        return roof;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return roof == house.roof
                && Double.compare(house.length, length) == 0
                && Double.compare(house.width, width) == 0
                && Double.compare(house.height, height) == 0
                && Objects.equals(apartments, house.apartments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roof, length, width, height, apartments);
    }

    @Override
    public String toString() {
        return "House{" +
                "roof=" + roof +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", apartments=" + apartments +
                '}';
    }
}
