package Entities;

import java.io.*;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class AccountingSystem {

    private SortedSet<House> houses;

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
        return houses.contains(new House(number));
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

    public void save(String path) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(houses.toArray(House[]::new));
        }
    }

    public void load(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            houses = new TreeSet<>(Set.of((House[]) in.readObject()));
        }

    }

}
