package com.shakhnitski.accsystem.serializer;

import com.shakhnitski.accsystem.entity.AccountingSystem;
import com.shakhnitski.accsystem.entity.Apartment;
import com.shakhnitski.accsystem.entity.House;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;

public class AccountingSystemSerializerTest {

    static AccountingSystem accountingSystem = new AccountingSystem();
    AccountingSystemSerializer serializer = new AccountingSystemSerializer();
    String path = "src/test/resources/accounting_system.txt";

    @BeforeClass
    public static void fillAccountingSystem() {
        House house1 = new House(1);
        House house2 = new House(2);
        Apartment apartment1 = new Apartment(1, 1, 1, 1, 1);
        Apartment apartment2 = new Apartment(2, 1, 1, 1, 1);
        Apartment apartment3 = new Apartment(3, 1, 1, 1, 1);
        house1.addApartment(apartment1);
        house1.addApartment(apartment2);
        house1.addApartment(apartment3);
        house2.addApartment(apartment1);
        house2.addApartment(apartment3);
        accountingSystem.addHouse(house1);
        accountingSystem.addHouse(house2);
    }

    @Test
    public void throwsFileNotFoundException() {
        Assert.assertThrows(FileNotFoundException.class,
                () -> serializer.read(path + "41"));
    }

    @Test
    public void write() {
        try {
            serializer.write(accountingSystem, path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AccountingSystem readed = new AccountingSystem();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            readed = (AccountingSystem) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(accountingSystem, readed);
    }

    @Test
    public void read() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(accountingSystem);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AccountingSystem readed = new AccountingSystem();
        try {
            readed = serializer.read(path);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(accountingSystem, readed);
    }

}
