package com.shakhnitski.accsystem.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class AccountingSystem implements Serializable {
    private final SortedSet<House> houses;

    public AccountingSystem() {
        this.houses = new TreeSet<>();
    }

    public House getHouseByNumber(int houseNumber) {
        return houses.parallelStream().filter(e -> e.getNumber() == houseNumber).findFirst().orElse(null);
    }

    public void addHouse(House house) {
        houses.add(house);
    }

    public void clear() {
        houses.clear();
    }

    public void removeHouse(House house) {
        houses.remove(house);
    }

    public boolean containsHouse(int number) {
        return houses.parallelStream().anyMatch(e -> e.getNumber() == number);
    }

    public SortedSet<House> getHouses() {
        return Collections.unmodifiableSortedSet(houses);
    }

    public int size() {
        return houses.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountingSystem that = (AccountingSystem) o;
        return Objects.equals(houses, that.houses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(houses);
    }
}
