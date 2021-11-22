import entities.Apartment;
import entities.House;
import org.junit.Assert;
import org.junit.Test;
import services.HouseService;

public class HouseServiceTest {
    @Test
    public void compareByPopulation() {
        House house1 = new House(1);
        House house2 = new House(2);
        house1.addApartment(new Apartment(1, 1, 1, 5, 1));
        house1.addApartment(new Apartment(2, 1, 1, 3, 1));
        house2.addApartment(new Apartment(1, 1, 1, 10, 1));
        Assert.assertEquals(HouseService.compareByPopulation(house1, house2), -1);
    }

    @Test
    public void compareByFullSquare() {
        House house1 = new House(1);
        House house2 = new House(2);
        house1.addApartment(new Apartment(1, 1, 1, 1, 1.5f));
        house1.addApartment(new Apartment(2, 1, 1, 1, 1.5f));
        house2.addApartment(new Apartment(1, 1, 1, 1, 7.1f));
        Assert.assertEquals(HouseService.compareByFullSquare(house1 , house2), -1);
    }

    @Test
    public void compareByApartmentsNumber() {
        House house1 = new House(1);
        House house2 = new House(2);
        house1.addApartment(new Apartment(1, 1, 1, 1, 1.5f));
        house1.addApartment(new Apartment(2, 1, 1, 1, 1.5f));
        house2.addApartment(new Apartment(1, 1, 1, 1, 7.1f));
        Assert.assertEquals(HouseService.compareByApartmentsNumber(house1 , house2), 1);
    }

    @Test
    public void compareByFloors() {
        House house1 = new House(1);
        House house2 = new House(2);
        house1.addApartment(new Apartment(1, 1, 1, 1, 1.5f));
        house1.addApartment(new Apartment(2, 7, 1, 1, 1.5f));
        house2.addApartment(new Apartment(1, 8, 1, 1, 7.1f));
        Assert.assertEquals(HouseService.compareByFloors(house1 , house2), -1);
    }

    @Test
    public void calculatePopulation() {
        House house = new House(1);
        house.addApartment(new Apartment(1, 1, 1, 5, 1.5f));
        house.addApartment(new Apartment(2, 7, 1, 3, 1.5f));
        Assert.assertEquals(HouseService.calculatePopulation(house), 8);
    }

    @Test
    public void calculateFullSquare() {
        House house = new House(1);
        house.addApartment(new Apartment(1, 1, 1, 5, 1.5f));
        house.addApartment(new Apartment(2, 7, 1, 3, 1.7f));
        Assert.assertEquals(HouseService.calculateFullSquare(house), 3.2f, 1e-6);
    }

    @Test
    public void calculateNumberOfFloors() {
        House house = new House(1);
        house.addApartment(new Apartment(1, 1, 1, 5, 1.5f));
        house.addApartment(new Apartment(2, 7, 1, 3, 1.7f));
        house.addApartment(new Apartment(2, 6, 1, 3, 1.7f));
        Assert.assertEquals(HouseService.calculateNumberOfFloors(house), 7);
    }
}
