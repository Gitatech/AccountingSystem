package com.shakhnitski.accsystem.entity;

import com.shakhnitski.accsystem.entity.Apartment;
import com.shakhnitski.accsystem.entity.House;
import org.junit.Assert;
import org.junit.Test;

public class HouseTest {

    Apartment apartment1 = new Apartment(1, 1, 1, 1, 1);
    Apartment apartment2 = new Apartment(2, 1, 1, 1, 1);

    @Test
    public void addApartments() {
        House house = new House(2);
        house.addApartment(apartment1);
        house.addApartment(apartment2);

        Apartment[] expected = {apartment1, apartment2};
        Apartment[] actual = house.getApartments().toArray(Apartment[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void clearHouse() {
        House house = new House(2);
        house.addApartment(apartment1);
        house.addApartment(apartment2);
        house.clear();
        Assert.assertTrue(house.getApartments().isEmpty());
    }

    @Test
    public void containsApartment() {
        House house = new House(2);
        house.addApartment(apartment1);
        house.addApartment(apartment2);
        Assert.assertTrue(house.containsApartment(apartment1.getNumber()));
    }

    @Test
    public void getApartmentByNumber() {
        House house = new House(2);
        house.addApartment(apartment1);
        house.addApartment(apartment2);

        Apartment expected = apartment1;
        Apartment actual = house.getApartmentByNumber(apartment1.getNumber());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeApartment() {
        House house = new House(2);
        house.addApartment(apartment1);
        house.addApartment(apartment2);
        house.removeApartment(apartment1);

        Apartment[] expected = {apartment2};
        Apartment[] actual = house.getApartments().toArray(Apartment[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeApartmentByNumber() {
        House house = new House(2);
        house.addApartment(apartment1);
        house.addApartment(apartment2);
        house.removeApartmentByNumber(apartment1.getNumber());

        Apartment[] expected = {apartment2};
        Apartment[] actual = house.getApartments().toArray(Apartment[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void compareHouses() {
        House house1 = new House(1);
        House house2 = new House(2);
        int expected = -1;
        int actual = house1.compareTo(house2);
        Assert.assertEquals(expected, actual);
    }
}
