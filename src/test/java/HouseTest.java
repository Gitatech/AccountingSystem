import Entities.Apartment;
import Entities.House;
import org.junit.Assert;
import org.junit.Test;
import java.util.Set;

public class HouseTest {

    @Test
    public void addApartment() {
        House house = new House(1);
        house.addApartment(new Apartment(1, 1, 1, 1, 0.2f));
        house.addApartment(new Apartment(2, 1, 1, 5, 0.2f));
        house.addApartment(new Apartment(8, 1, 1, 2, 0.2f));
        house.addApartment(new Apartment(9, 1, 1, 3, 0.2f));
        house.addApartment(new Apartment(2, 1, 1, 3, 0.2f));
        Set<Apartment> expected = Set.of(new Apartment(1, 1, 1, 1, 0.2f),
                new Apartment(2, 1, 1, 5, 0.2f),
                new Apartment(8, 1, 1, 2, 0.2f),
                new Apartment(9, 1, 1, 3, 0.2f));
        Assert.assertEquals(expected, house.getApartments());
    }

    @Test
    public void removeApartment() {
        House house = new House(1);
        house.addApartment(new Apartment(1, 1, 1, 1, 0.2f));
        house.addApartment(new Apartment(2, 1, 1, 5, 0.2f));
        house.addApartment(new Apartment(8, 1, 1, 2, 0.2f));
        house.addApartment(new Apartment(9, 1, 1, 3, 0.2f));
        house.addApartment(new Apartment(2, 1, 1, 3, 0.2f));
        house.removeApartment(new Apartment(8, 1, 1, 1,1.1f));
        Set<Apartment> expected = Set.of(new Apartment(1, 1, 1, 1, 0.2f),
                new Apartment(2, 1, 1, 5, 0.2f),
                new Apartment(9, 1, 1, 3, 0.2f));
        Assert.assertEquals(expected, house.getApartments());
    }

    @Test
    public void containsApartment() {
        House house = new House(1);
        house.addApartment(new Apartment(1, 1, 1, 1, 0.2f));
        house.addApartment(new Apartment(2, 1, 1, 5, 0.2f));
        house.addApartment(new Apartment(9, 1, 1, 3, 0.2f));
        Assert.assertTrue(house.containsApartment(2));
        Assert.assertFalse(house.containsApartment(15));
    }

    @Test
    public void getApartmentByNumber() {
        House house = new House(1);
        house.addApartment(new Apartment(1, 1, 1, 1, 0.2f));
        house.addApartment(new Apartment(2, 1, 1, 5, 0.2f));
        house.addApartment(new Apartment(9, 1, 1, 3, 0.2f));
        Assert.assertNotNull(house.getApartmentByNumber(2));
        Assert.assertNull(house.getApartmentByNumber(8));
    }

}
