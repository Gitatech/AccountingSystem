import org.junit.Assert;
import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;

public class ASTesting {

    @Test
    public void addHouse() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(1);
        accountingSystem.addHouse(2);
        accountingSystem.addHouse(3);

        SortedSet<House> houses = new TreeSet<>();
        houses.add(new House(1));
        houses.add(new House(2));
        houses.add(new House(3));

        Assert.assertArrayEquals(accountingSystem.getHouses(), houses.toArray(House[]::new));
    }

    @Test
    public void removeHouse() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(1);
        accountingSystem.addHouse(2);
        accountingSystem.addHouse(3);
        accountingSystem.removeHouse(2);

        SortedSet<House> houses = new TreeSet<>();
        houses.add(new House(1));
        houses.add(new House(3));

        Assert.assertArrayEquals(accountingSystem.getHouses(), houses.toArray(House[]::new));
    }

    @Test
    public void containsHouse() {
        AccountingSystem accountingSystem = new AccountingSystem();
        accountingSystem.addHouse(1);
        accountingSystem.addHouse(2);
        accountingSystem.addHouse(3);
        Assert.assertTrue(accountingSystem.containsHouse(2) && !accountingSystem.containsHouse(5));
    }

}
