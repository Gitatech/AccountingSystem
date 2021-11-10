import org.junit.Assert;
import org.junit.Test;

public class HouseTesting {

    @Test
    public void addApartment() {
        House house = new House(1);
        house.addApartment(new Apartment(1, 1, 1, 1, 0.2f));
        house.addApartment(new Apartment(2, 1, 1, 5, 0.2f));
        house.addApartment(new Apartment(8, 1, 1, 2, 0.2f));
        house.addApartment(new Apartment(9, 1, 1, 3, 0.2f));
        house.addApartment(new Apartment(2, 1, 1, 3, 0.2f));
        Apartment[] expected = {new Apartment(1, 1, 1, 1, 0.2f),
                new Apartment(2, 1, 1, 5, 0.2f),
                new Apartment(8, 1, 1, 2, 0.2f),
                new Apartment(9, 1, 1, 3, 0.2f)};
        Assert.assertArrayEquals(expected, house.getApartments());
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
        Apartment[] expected = {new Apartment(1, 1, 1, 1, 0.2f),
                new Apartment(2, 1, 1, 5, 0.2f),
                new Apartment(9, 1, 1, 3, 0.2f)};
        Assert.assertArrayEquals(expected, house.getApartments());
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



    @Test
    public void calculatePopulation() {
        House house = new House(1);
        house.addApartment(new Apartment(1, 1, 1, 1, 0.2f));
        house.addApartment(new Apartment(2, 1, 1, 5, 0.2f));
        house.addApartment(new Apartment(3, 1, 1, 2, 0.2f));
        house.addApartment(new Apartment(4, 1, 1, 3, 0.2f));
        Assert.assertEquals(11, house.calculatePopulation());
    }

    @Test
    public void calculateNumberOfFloors() {
        House house = new House(1);
        house.addApartment(new Apartment(1, 2, 1, 1, 0.2f));
        house.addApartment(new Apartment(2, 5, 1, 5, 0.2f));
        house.addApartment(new Apartment(3, 6, 1, 2, 0.2f));
        house.addApartment(new Apartment(4, 7, 1, 3, 0.2f));
        Assert.assertEquals(7, house.calculateNumberOfFloors());
    }

    @Test
    public void calculateFullSquare() {
        House house = new House(1);
        house.addApartment(new Apartment(1, 2, 1, 1, 15.2f));
        house.addApartment(new Apartment(2, 5, 1, 5, 16.7f));
        house.addApartment(new Apartment(3, 6, 1, 2, 45.1f));
        house.addApartment(new Apartment(4, 7, 1, 3, 14.2f));
        Assert.assertEquals(91.2f, house.calculateFullSquare(), 1e-6);
    }

}
