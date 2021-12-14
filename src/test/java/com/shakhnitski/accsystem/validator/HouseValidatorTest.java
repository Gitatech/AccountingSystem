package com.shakhnitski.accsystem.validator;

import com.shakhnitski.accsystem.entity.House;
import org.junit.Assert;
import org.junit.Test;

public class HouseValidatorTest {

    @Test
    public void invalidHouseNumber() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> new House(-2));
    }

    @Test
    public void correctParameters() {
        boolean notThrows = true;
        try {
            new House(5);
        } catch (IllegalArgumentException e) {
            notThrows = false;
        }
        Assert.assertTrue(notThrows);
    }
}
