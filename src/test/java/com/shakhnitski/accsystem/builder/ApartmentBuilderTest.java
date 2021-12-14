package com.shakhnitski.accsystem.builder;

import com.shakhnitski.accsystem.entity.Apartment;
import org.junit.Assert;
import org.junit.Test;

public class ApartmentBuilderTest {

    @Test
    public void buildApartment() {
        Apartment expected = new Apartment(1, 1, 1, 1, 1);
        ApartmentBuilder builder = new ApartmentBuilder();
        builder.setNumber(1);
        builder.setSquare(1);
        builder.setResidentsNumber(1);
        builder.setRoomsNumber(1);
        builder.setFloor(1);
        Apartment actual = builder.getResult();
        Assert.assertEquals(expected, actual);
    }

}
