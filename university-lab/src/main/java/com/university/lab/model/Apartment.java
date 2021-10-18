package com.university.lab.model;

import java.util.Objects;

public class Apartment {

//    private static final double HEIGHT_FROM_FLOOR_TO_CEILING = 3.0;

    private int numberOfResidents;
    private double totalFloorLength;
    private double totalFloorWidth;

    public Apartment(int numberOfResidents, double floorLength, double floorWidth) {
        this.numberOfResidents = numberOfResidents;
        lengthCheckValue(floorLength);
        widthCheckValue(floorWidth);
    }

    public void setNumberOfResidents(int numberOfResidents) {
        this.numberOfResidents = numberOfResidents;
    }

    public int getNumberOfResidents() {
        return numberOfResidents;
    }

    public double getTotalFloorLength() {
        return totalFloorLength;
    }

    public double getTotalFloorWidth() {
        return totalFloorWidth;
    }

    private void widthCheckValue(double floorWidth) {
        if (floorWidth > 0) {
            throw new IllegalArgumentException("The total floor width of the apartment must be > 0");
        } else {
            this.totalFloorWidth = floorWidth;
        }
    }

    private void lengthCheckValue(double floorLength) {
        if (floorLength <= 0) {
            throw new IllegalArgumentException("The total floor length of the apartment must be > 0");
        } else {
            this.totalFloorLength = floorLength;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return numberOfResidents == apartment.numberOfResidents
                && Double.compare(apartment.totalFloorLength, totalFloorLength) == 0
                && Double.compare(apartment.totalFloorWidth, totalFloorWidth) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfResidents, totalFloorLength, totalFloorWidth);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "numberOfResidents=" + numberOfResidents +
                ", totalFloorLength=" + totalFloorLength +
                ", totalFloorWidth=" + totalFloorWidth +
                '}';
    }
}
