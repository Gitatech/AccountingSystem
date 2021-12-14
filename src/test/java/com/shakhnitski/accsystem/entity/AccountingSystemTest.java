package com.shakhnitski.accsystem.entity;

import com.shakhnitski.accsystem.entity.AccountingSystem;
import com.shakhnitski.accsystem.entity.Apartment;
import com.shakhnitski.accsystem.entity.House;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountingSystemTest {

    static House house1 = new House(1);
    static House house2 = new House(2);

    @BeforeClass
    public static void addApartmentsInHouses() {
        house1.addApartment(new Apartment(1, 1, 1, 1, 1));
        house1.addApartment(new Apartment(2, 1, 1, 1, 1));
    }

    @Test
    public void addHouses() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(house1);
        accountingSystem.addHouse(house2);

        House[] expected = {house1, house2};
        House[] actual = accountingSystem.getHouses().toArray(House[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void getHouseByNumber() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(house1);
        accountingSystem.addHouse(house2);

        House expected = house1;
        House actual = accountingSystem.getHouseByNumber(house1.getNumber());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeHouse() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(house1);
        accountingSystem.addHouse(house2);
        accountingSystem.removeHouse(house1);

        House[] expected = { house2};
        House[] actual = accountingSystem.getHouses().toArray(House[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void containsHouse() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(house1);
        accountingSystem.addHouse(house2);

        Assert.assertTrue(accountingSystem.containsHouse(house1.getNumber()));
    }


}
