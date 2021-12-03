package com.bsu.accounting.system.model;

import java.util.Objects;

public class Floor {

    private final double floorHeight;

    public Floor(double floorHeight) {
        this.floorHeight = floorHeight;
    }

    public double getFloorHeight() {
        return floorHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return Double.compare(floor.floorHeight, floorHeight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(floorHeight);
    }

    @Override
    public String toString() {
        return "Floor{" +
                "floorHeight=" + floorHeight +
                '}';
    }
}
