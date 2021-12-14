package com.shakhnitski.accsystem.service;

import com.shakhnitski.accsystem.entity.Apartment;
import com.shakhnitski.accsystem.entity.House;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HouseServiceTest {

    static HouseService service = HouseService.getInstance();
    static House house1 = new House(1);
    static House house2 = new House(2);

    @BeforeClass
    public static void fillHouse() {
        Apartment apartment1 = new Apartment(1, 1, 1, 1, 1);
        Apartment apartment2 = new Apartment(2, 2, 1, 1, 1);
        Apartment apartment3 = new Apartment(3, 6, 1, 1, 1);
        house1.addApartment(apartment1);
        house1.addApartment(apartment2);
        house1.addApartment(apartment3);
        house2.addApartment(apartment1);
        house2.addApartment(apartment3);
    }

    @Test
    public void calculateApartments() {
        int expected = 3;
        int actual = service.calculateNumberOfApartments(house1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculateFullSquare() {
        float expected = 3;
        float actual = service.calculateFullSquare(house1);
        Assert.assertEquals(expected, actual, 1e-6);
    }

    @Test
    public void calculatePopulation() {
        int expected = 3;
        int actual = service.calculatePopulation(house1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculateNumberOfFloors() {
        int expected = 6;
        int actual = service.calculateNumberOfFloors(house1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareByPopulation() {
        int expected = 1;
        int actual = service.compareByPopulation(house1, house2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareByFloors() {
        int expected = 0;
        int actual = service.compareByFloors(house1, house2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareByFullSquare() {
        int expected = 1;
        int actual = service.compareByFullSquare(house1, house2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareByApartmentsNumber() {
        int expected = 1;
        int actual = service.compareByApartmentsNumber(house1, house2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareByNumber() {
        int expected = -1;
        int actual = service.compareByNumber(house1, house2);
        Assert.assertEquals(expected, actual);
    }


}
