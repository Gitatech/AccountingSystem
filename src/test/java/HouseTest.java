import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HouseTest {

    private House house;

    @Before
    public void initHouse() {
        house = new House();
    }
    @Test
    public void get_N_man() {
        assertEquals(house.get_N_man(),20);
    }

    @Test
    public void house_area() {
        assertEquals(house.House_area(),100,0);

    }

    @Test
    public void flat_area() {
        assertEquals(house.Flat_area(1),25,0);
    }

    @Test
    public void get_Man_falt() {
        assertEquals(house.get_Man_falt(1),5);
    }

}