package com.bsu.accounting.system.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Floor {

    private final double floorHeight;
    private final double floorLength;
    private final double floorWidth;
    private final List<Apartment> apartments = new ArrayList<>();

    public Floor(double floorHeight, double floorLength, double floorWidth) {
        this.floorHeight = floorHeight;
        this.floorLength = floorLength;
        this.floorWidth = floorWidth;
    }

    public double getFloorHeight() {
        return floorHeight;
    }

    public double getFloorLength() {
        return floorLength;
    }

    public double getFloorWidth() {
        return floorWidth;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(Apartment apartment) {
        this.apartments.add(apartment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return Double.compare(floor.floorHeight, floorHeight) == 0
                && Double.compare(floor.floorLength, floorLength) == 0
                && Double.compare(floor.floorWidth, floorWidth) == 0
                && Objects.equals(apartments, floor.apartments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(floorHeight, floorLength, floorWidth, apartments);
    }

    @Override
    public String toString() {
        return "Floor{" +
                "floorHeight=" + floorHeight +
                '}';
    }
}
