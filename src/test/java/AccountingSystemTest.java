import entities.AccountingSystem;
import entities.Apartment;
import entities.House;
import org.junit.Assert;
import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;

public class AccountingSystemTest {

    @Test
    public void addHouse() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(new House(1));
        accountingSystem.addHouse(new House(2));
        accountingSystem.addHouse(new House(3));
        SortedSet<House> houses = new TreeSet<>();
        houses.add(new House(1));
        houses.add(new House(2));
        houses.add(new House(3));
        Assert.assertEquals(accountingSystem.getHouses(), houses);
    }

    @Test
    public void removeHouse() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(new House(1));
        accountingSystem.addHouse(new House(2));
        accountingSystem.addHouse(new House(3));
        accountingSystem.removeHouse(new House(2));
        SortedSet<House> houses = new TreeSet<>();
        houses.add(new House(1));
        houses.add(new House(3));
        Assert.assertEquals(accountingSystem.getHouses(), houses);
    }

    @Test
    public void getHouseByNumber() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(new House(1));
        accountingSystem.addHouse(new House(2));
        accountingSystem.addHouse(new House(3));
        Assert.assertEquals(accountingSystem.getHouseByNumber(2), new House(2));
    }

    @Test
    public void getContainsHouse() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(new House(1));
        accountingSystem.addHouse(new House(2));
        accountingSystem.addHouse(new House(3));
        Assert.assertTrue(accountingSystem.containsHouse(2));
    }

    @Test
    public void getNumberOfHouses() {
        AccountingSystem accountingSystem = new AccountingSystem();
        House house1 = new House(1);
        House house2 = new House(2);
        House house3 = new House(3);
        accountingSystem.addHouse(house1);
        accountingSystem.addHouse(house2);
        accountingSystem.addHouse(house3);
        Assert.assertEquals(accountingSystem.getNumberOfHouses(), 3);
    }

    @Test
    public void getNumberOfApartments() {
        AccountingSystem accountingSystem = new AccountingSystem();
        House house1 = new House(1);
        House house2 = new House(2);
        House house3 = new House(3);
        house1.addApartment(new Apartment(1, 1, 1, 1, 1));
        house1.addApartment(new Apartment(2, 1, 1, 1, 1));
        house1.addApartment(new Apartment(3, 1, 1, 1, 1));
        house2.addApartment(new Apartment(1, 1, 1, 1, 1));
        house2.addApartment(new Apartment(2, 1, 1, 1, 1));
        house3.addApartment(new Apartment(1, 1, 1, 1, 1));
        accountingSystem.addHouse(house1);
        accountingSystem.addHouse(house2);
        accountingSystem.addHouse(house3);
        Assert.assertEquals(accountingSystem.getNumberOfApartments(), 6);
    }

}
