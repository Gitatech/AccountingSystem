package com.bsu.accounting.system.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Floor implements Serializable {

    private double floorHeight;
    private double floorLength;
    private double floorWidth;
    private List<Apartment> apartments = new ArrayList<>();

    public Floor(double floorHeight, double floorLength, double floorWidth) {
        this.floorHeight = floorHeight;
        this.floorLength = floorLength;
        this.floorWidth = floorWidth;
    }

    public Floor() {
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

    public Apartment getApartment(int index) {
        return apartments.get(index);
    }

    public void setApartment(Apartment apartment) {
        this.apartments.add(apartment);
    }

    public void setApartment(int index, Apartment apartment) {
        this.apartments.add(index, apartment);
    }

    public void setApartments(List<Apartment> apartmentList) {
        for (int i = 0; i < apartmentList.size(); i++) {
            apartments.set(i, apartmentList.get(i));
        }
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

    private void writeObject (ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject (ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
