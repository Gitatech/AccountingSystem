import entities.Apartment;
import org.junit.Assert;
import org.junit.Test;
import services.ApartmentService;

public class ApartmentServiceTest {

    @Test
    public void compareByResidentsNumber() {
        Apartment apartment1 = new Apartment(1, 1, 1, 7, 1);
        Apartment apartment2 = new Apartment(1, 1, 1, 5, 1);
        Assert.assertEquals(ApartmentService.compareByResidents(apartment1, apartment2), 1);
    }

    @Test
    public void compareByFloor() {
        Apartment apartment1 = new Apartment(1, 5, 1, 7, 1);
        Apartment apartment2 = new Apartment(1, 7, 1, 5, 1);
        Assert.assertEquals(ApartmentService.compareByFloor(apartment1, apartment2), -1);
    }

    @Test
    public void compareByRooms() {
        Apartment apartment1 = new Apartment(1, 1, 3, 7, 1);
        Apartment apartment2 = new Apartment(1, 1, 2, 5, 1);
        Assert.assertEquals(ApartmentService.compareByNumberOfRooms(apartment1, apartment2), 1);
    }

    @Test
    public void compareBySquare() {
        Apartment apartment1 = new Apartment(1, 1, 1, 7, 1.5f);
        Apartment apartment2 = new Apartment(1, 1, 1, 5, 1.1f);
        Assert.assertEquals(ApartmentService.compareBySquare(apartment1, apartment2), 1);
    }
}
