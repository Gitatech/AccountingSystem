package com.shakhnitski.accsystem.service;

import com.shakhnitski.accsystem.entity.AccountingSystem;
import com.shakhnitski.accsystem.entity.Apartment;
import com.shakhnitski.accsystem.entity.House;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountingSystemServiceTest {

    static AccountingSystemService service = AccountingSystemService.getInstance();
    static AccountingSystem accountingSystem = new AccountingSystem();

    @BeforeClass
    public static void fillAccountingSystem() {
        House house1 = new House(1);
        House house2 = new House(2);
        Apartment apartment1 = new Apartment(1, 1, 1, 1, 1);
        Apartment apartment2 = new Apartment(2, 1, 1, 1, 1);
        Apartment apartment3 = new Apartment(3, 1, 1, 1, 1);
        Apartment apartment4 = new Apartment(4, 1, 1, 1, 1);
        Apartment apartment5 = new Apartment(5, 1, 1, 1, 1);
        house1.addApartment(apartment1);
        house1.addApartment(apartment2);
        house1.addApartment(apartment3);
        house2.addApartment(apartment4);
        house2.addApartment(apartment5);
        accountingSystem.addHouse(house1);
        accountingSystem.addHouse(house2);
    }

    @Test
    public void calculateHouses() {
        int expected = 2;
        int actual = service.calculateNumberOfHouses(accountingSystem);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculateApartments() {
        int expected = 5;
        int actual = service.calculateNumberOfApartments(accountingSystem);
        Assert.assertEquals(expected, actual);
    }


}
