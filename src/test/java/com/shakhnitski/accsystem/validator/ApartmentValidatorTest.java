package com.shakhnitski.accsystem.validator;

import com.shakhnitski.accsystem.entity.Apartment;
import org.junit.Assert;
import org.junit.Test;

public class ApartmentValidatorTest {

    @Test
    public void invalidApartmentNumber() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> new Apartment(-2, 5, 6, 7, 8));
    }

    @Test
    public void invalidRoomsNumber() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> new Apartment(2, 5, -2, 7, 8));
    }

    @Test
    public void invalidResidentsNumber() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> new Apartment(2, 5, 6, -2, 8));
    }

    @Test
    public void invalidApartmentSquare() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> new Apartment(2, 5, 6, 7, 0));
    }

    @Test
    public void invalidFloorNumber() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> new Apartment(2, -2, 6, 7, 8));
    }

    @Test
    public void correctParameters() {
        boolean notThrows = true;
        try {
            new Apartment(1, 1, 1, 1, 1);
        } catch (IllegalArgumentException e) {
            notThrows = false;
        }
        Assert.assertTrue(notThrows);
    }
}
