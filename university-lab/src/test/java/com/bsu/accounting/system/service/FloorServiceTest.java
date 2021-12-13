package com.bsu.accounting.system.service;

import com.bsu.accounting.system.dao.ApartmentDaoImpl;
import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.Floor;
import com.bsu.accounting.system.model.House;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FloorServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(FloorServiceTest.class);

    private final FloorService floorService = FloorService.getInstance();

    @InjectMocks
    private House house;

    @InjectMocks
    ApartmentDaoImpl apartmentDao;

    @Mock
    private Apartment apartment;

    @Test
    public void createFloor_shouldReturnFloorObject() {
        LOGGER.info("createFloor");

        final Floor floor = floorService.createFloor(house);

        assertNotNull(floor);
    }

    @Test
    public void addApartment_shouldAddApartmentToApartmentList() {
        Floor floor = new Floor(3.0, 70.0, 20.0);

        apartmentDao.create(apartment);
        floorService.addApartment(floor, apartment);
        floor.setApartment(0, apartment);

        assertNotNull(floor.getApartment(0));
        LOGGER.info(floor.getApartments());
    }
}
