package com.bsu.accounting.system.service;

import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.Floor;
import com.bsu.accounting.system.model.House;
import com.bsu.accounting.system.model.HouseName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FloorServiceTest {

    private static final Floor TEST_FLOOR = new Floor(3.0, 70.0, 20.0);
    private static final Apartment TEST_APARTMENT = new Apartment(1, 5, 23.2, 18.0);
    private static final House TEST_HOUSE = new House(new HouseName("test", "1"), 70.9, 18.4, 10.2);

    private final FloorService floorService = FloorService.getInstance();

    @Test
    public void createFloor_shouldReturnFloorObject() {

        final Floor floor = floorService.createFloor(TEST_HOUSE, 3);

        assertNotNull(floor);
    }

    @Test
    public void addApartment_shouldAddApartmentToApartmentList() {

        final Floor currentFloor = floorService.addApartment(TEST_FLOOR, TEST_APARTMENT);

        assertNotNull(currentFloor.getApartment(0));
        assertSame(currentFloor, TEST_FLOOR);
    }

    @Test
    public void availableFloorArea_shouldReturnValidData_always() {
        double length = 24.2;

        final double actualResult = floorService.availableFloorArea(TEST_HOUSE, length);

        assertTrue(actualResult >= 0);
    }

    @Test
    public void fillTheSecondPartOfTheHouse_shouldFillFloor_always() {
        final Floor currentFloor = floorService.addApartment(TEST_FLOOR, TEST_APARTMENT);

        final List<Apartment> apartmentList = floorService.fillTheSecondPartOfTheHouse(currentFloor);

        assertNotNull(apartmentList);
        assertTrue(apartmentList.size() > 0);
    }
}
