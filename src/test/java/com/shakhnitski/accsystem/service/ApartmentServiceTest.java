package com.shakhnitski.accsystem.service;

import com.shakhnitski.accsystem.entity.Apartment;
import org.junit.Assert;
import org.junit.Test;

public class ApartmentServiceTest {

    ApartmentService service = ApartmentService.getInstance();
    Apartment apartment1 = new Apartment(1, 5, 2, 8, 4);
    Apartment apartment2 = new Apartment(5, 2, 3, 2, 4);

    @Test
    public void compareByFloor() {
        int expected = 1;
        int actual = service.compareByFloor(apartment1, apartment2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareByResidentsNumber() {
        int expected = 1;
        int actual = service.compareByResidents(apartment1, apartment2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareByRoomsNumber() {
        int expected = -1;
        int actual = service.compareByNumberOfRooms(apartment1, apartment2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareBySquare() {
        int expected = 0;
        int actual = service.compareBySquare(apartment1, apartment2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareByNumber() {
        int expected = -1;
        int actual = service.compareByNumber(apartment1, apartment2);
        Assert.assertEquals(expected, actual);
    }


}
