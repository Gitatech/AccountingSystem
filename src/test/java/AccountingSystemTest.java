import Entities.AccountingSystem;
import Entities.Apartment;
import Entities.House;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class AccountingSystemTest {

    @Test
    public void addHouse() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(new House(1));
        accountingSystem.addHouse(new House(2));
        accountingSystem.addHouse(new House(4));
        accountingSystem.addHouse(new House(2));
        Set<House> expected = Set.of(new House(1), new House(2), new House(4));
        Assert.assertEquals(expected, accountingSystem.getHouses());
    }

    @Test
    public void removeHouse() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(new House(1));
        accountingSystem.addHouse(new House(2));
        accountingSystem.addHouse(new House(4));
        accountingSystem.addHouse(new House(6));
        accountingSystem.removeHouse(new House(4));
        Set<House> expected = Set.of(new House(1), new House(2), new House(6));
        Assert.assertEquals(expected, accountingSystem.getHouses());
    }

    @Test
    public void containsHouse() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(new House(1));
        accountingSystem.addHouse(new House(2));
        accountingSystem.addHouse(new House(4));
        accountingSystem.addHouse(new House(6));
        Assert.assertTrue(accountingSystem.containsHouse(4));
        Assert.assertFalse(accountingSystem.containsHouse(11));
    }

    @Test
    public void getHouseByNumber() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(new House(1));
        accountingSystem.addHouse(new House(2));
        accountingSystem.addHouse(new House(4));
        accountingSystem.addHouse(new House(6));
        Assert.assertNull(accountingSystem.getHouseByNumber(7));
        Assert.assertNotNull(accountingSystem.getHouseByNumber(4));
    }

    @Test
    public void getNumberOfApartments() {
        AccountingSystem accountingSystem = new AccountingSystem();
        House house1 = new House(1);
        House house2 = new House(3);
        House house3 = new House(7);
        house1.addApartment(new Apartment(1, 1, 1, 1 ,1f));
        house1.addApartment(new Apartment(2, 1, 1, 1 ,1f));
        house1.addApartment(new Apartment(3, 1, 1, 1 ,1f));
        house2.addApartment(new Apartment(1, 1, 1, 1, 1f));
        accountingSystem.addHouse(house1);
        accountingSystem.addHouse(house2);
        accountingSystem.addHouse(house3);
        Assert.assertEquals(4, accountingSystem.getNumberOfApartments());
    }

}
