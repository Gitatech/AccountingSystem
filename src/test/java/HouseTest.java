import entities.Apartment;
import entities.House;
import org.junit.Assert;
import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;

public class HouseTest {

    @Test
    public void addApartment() {
        House house = new House(1);
        Apartment apartment1 = new Apartment(1, 1, 1, 1, 1);
        Apartment apartment2 = new Apartment(2, 1, 1, 1, 1);
        Apartment apartment3 = new Apartment(3, 1, 1, 1, 1);
        house.addApartment(apartment1);
        house.addApartment(apartment2);
        house.addApartment(apartment3);
        SortedSet<Apartment> apartments = new TreeSet<>();
        apartments.add(apartment1);
        apartments.add(apartment2);
        apartments.add(apartment3);
        Assert.assertEquals(house.getApartments(), apartments);
    }

    @Test
    public void removeApartment() {
        House house = new House(1);
        Apartment apartment1 = new Apartment(1, 1, 1, 1, 1);
        Apartment apartment2 = new Apartment(2, 1, 1, 1, 1);
        Apartment apartment3 = new Apartment(3, 1, 1, 1, 1);
        house.addApartment(apartment1);
        house.addApartment(apartment2);
        house.addApartment(apartment3);
        house.removeApartment(apartment2);
        SortedSet<Apartment> apartments = new TreeSet<>();
        apartments.add(apartment1);
        apartments.add(apartment3);
        Assert.assertEquals(house.getApartments(), apartments);
    }

    @Test
    public void containsApartment() {
        House house = new House(1);
        Apartment apartment1 = new Apartment(1, 1, 1, 1, 1);
        Apartment apartment2 = new Apartment(2, 1, 1, 1, 1);
        Apartment apartment3 = new Apartment(3, 1, 1, 1, 1);
        house.addApartment(apartment1);
        house.addApartment(apartment2);
        house.addApartment(apartment3);
        Assert.assertTrue(house.containsApartment(2));
    }

    @Test
    public void getApartmentByNumber() {
        House house = new House(1);
        Apartment apartment1 = new Apartment(1, 1, 1, 1, 1);
        Apartment apartment2 = new Apartment(2, 1, 1, 1, 1);
        Apartment apartment3 = new Apartment(3, 1, 1, 1, 1);
        house.addApartment(apartment1);
        house.addApartment(apartment2);
        house.addApartment(apartment3);
        Assert.assertEquals(house.getApartmentByNumber(2), apartment2);
    }


}
