package com.bsu.accounting.system.model;

import java.util.Objects;

public class HouseName {

    private final String street;
    private final String houseNumber;

    public HouseName(String street, String houseNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HouseName houseName = (HouseName) o;
        return houseNumber == houseName.houseNumber && Objects.equals(street, houseName.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber);
    }

    @Override
    public String toString() {
        return street + " " + houseNumber;
    }
}
