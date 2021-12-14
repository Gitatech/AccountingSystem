package com.shakhnitski.accsystem.entity;

import com.shakhnitski.accsystem.entity.Apartment;
import org.junit.Assert;
import org.junit.Test;

public class ApartmentTest {

    @Test
    public void compareApartments() {
        Apartment apartment1 = new Apartment(1, 2, 3, 4, 5);
        Apartment apartment2 = new Apartment(1, 2, 3, 4, 9);

        int expected = -1;
        int actual = apartment1.compareTo(apartment2);
        Assert.assertEquals(expected, actual);
    }

}
