package com.bsu.accounting.system.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Floor implements Serializable {

    private long floorId;
    private final double floorHeight;
    private final double floorLength;
    private final double floorWidth;
    private List<Apartment> apartments = new ArrayList<>();

    public Floor(long floorId, double floorHeight, double floorLength, double floorWidth) {
        this.floorId = floorId;
        this.floorHeight = floorHeight;
        this.floorLength = floorLength;
        this.floorWidth = floorWidth;
    }

    public Floor(double floorHeight, double floorLength, double floorWidth) {
        this(0, floorHeight, floorLength, floorWidth);
    }

    public Floor() {
        this(0,0, 0, 0);
    }

    public long getFloorId() {
        return floorId;
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

    public Floor withId (long id){
        final Floor floorWithId = new Floor(id, this.getFloorHeight(), this.getFloorLength(), this.getFloorWidth());
        floorWithId.apartments = this.apartments;
        return floorWithId;
    }

    public void setApartment(Apartment apartment) {
        this.apartments.add(apartment);
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

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
