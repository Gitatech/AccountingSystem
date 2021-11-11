package entities;

import java.io.Serializable;
import java.util.*;

public class House implements Comparable<House>, Serializable {
    private final int number;
    private final SortedSet<Apartment> apartments;

    public House(int number) {
        if (number < 0) throw new IllegalArgumentException("Номер дома не может быть отрицательным");
        this.number = number;
        apartments = new TreeSet<>();
    }

    public void clear() {
        apartments.clear();
    }

    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }

    public int getNumber() {
        return number;
    }

    public boolean containsApartment(int number) {
        if (number < 1) return false;
        return apartments.contains(new Apartment(number, 1, 1, 1, 1));
    }

    public SortedSet<Apartment> getApartments() {
        return Collections.unmodifiableSortedSet(apartments);
    }

    public void removeApartment(Apartment apartment) {
        apartments.remove(apartment);
    }

    public Apartment getApartmentByNumber(int number) {
        return apartments.parallelStream().filter(e -> e.getNumber() == number).findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "Дом " + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return number == house.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, apartments);
    }

    @Override
    public int compareTo(House o) {
        return Integer.compare(number, o.number);
    }
}
