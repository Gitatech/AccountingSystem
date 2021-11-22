package entities;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class AccountingSystem {
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

    public void removeHouse(House house) {
        houses.remove(house);
    }

    public boolean containsHouse(int number) {
        return houses.parallelStream().anyMatch(e -> e.getNumber() == number);
    }

    public SortedSet<House> getHouses() {
        return Collections.unmodifiableSortedSet(houses);
    }

    public int getNumberOfHouses() {
        return houses.size();
    }

    public int getNumberOfApartments() {
        return houses.parallelStream().mapToInt(e -> e.getApartments().size()).sum();
    }

}
