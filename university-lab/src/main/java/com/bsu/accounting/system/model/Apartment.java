package com.bsu.accounting.system.model;

import com.bsu.accounting.system.validation.ApartmentValidator;

import java.io.*;
import java.util.Objects;

public class Apartment implements Serializable {

    private final Integer id;
    private final int numberOfResidents;
    private final double totalApartmentLength;
    private final double totalApartmentWidth;

    public ApartmentType type;

    public Apartment(Integer id, int numberOfResidents, double totalApartmentLength, double totalApartmentWidth, ApartmentType type) {
        ApartmentValidator validator = new ApartmentValidator();
        validator.checkWholeApartment(id, numberOfResidents, totalApartmentLength, totalApartmentWidth);
        this.id = id;
        this.numberOfResidents = numberOfResidents;
        this.totalApartmentLength = totalApartmentLength;
        this.totalApartmentWidth = totalApartmentWidth;
        this.type = type;
    }

    public Apartment(Integer id, int numberOfResidents, double totalApartmentLength, double totalApartmentWidth) {
        this(id, numberOfResidents, totalApartmentLength, totalApartmentWidth, null);
    }

    public Apartment(int numberOfResidents, double totalApartmentLength, double totalApartmentWidth, ApartmentType type) {
        this(null, numberOfResidents, totalApartmentLength, totalApartmentWidth, type);
    }

    public Apartment() {
        this(null, 0, 0, 0, null);
    }

    public int getNumberOfResidents() {
        return numberOfResidents;
    }

    public double getTotalApartmentLength() {
        return totalApartmentLength;
    }

    public double getTotalApartmentWidth() {
        return totalApartmentWidth;
    }

    public Integer getId() {
        return id;
    }

    public Apartment withId(Integer id) {
        return new Apartment(id, this.numberOfResidents, this.totalApartmentLength, this.totalApartmentWidth, this.type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return numberOfResidents == apartment.numberOfResidents
                && Double.compare(apartment.totalApartmentLength, totalApartmentLength) == 0
                && Double.compare(apartment.totalApartmentWidth, totalApartmentWidth) == 0
                && Objects.equals(id, apartment.id) && type == apartment.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfResidents, totalApartmentLength, totalApartmentWidth, type);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", numberOfResidents=" + numberOfResidents +
                ", totalApartmentLength=" + totalApartmentLength +
                ", totalApartmentWidth=" + totalApartmentWidth +
                ", type=" + type +
                '}';
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}