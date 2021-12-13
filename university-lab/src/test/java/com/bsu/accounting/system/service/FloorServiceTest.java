package com.bsu.accounting.system.service;

import com.bsu.accounting.system.dao.ApartmentDao;
import com.bsu.accounting.system.dao.ApartmentDaoImpl;
import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.ApartmentType;
import com.bsu.accounting.system.model.Floor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class FloorServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(FloorServiceTest.class);
    private static final String PATH_TO_FILE = "AccountingSystem/university-lab/src/main/resources/results.ob";

    @InjectMocks
    ApartmentDao apartmentDao = new ApartmentDaoImpl();

    @InjectMocks
    private Apartment apartment = apartmentDao.create(new Apartment(6, 43, 18, ApartmentType.ONE_ROOM_APARTMENT));

    @Test
    public void createFloor_shouldReturnFloorObject() {
        LOGGER.info("createFloor");

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_TO_FILE))) {
            final Floor savingFloor = (Floor) inputStream.readObject();

            assertNotNull(savingFloor);

            LOGGER.info(savingFloor);
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Test
    public void addApartment_shouldAddApartmentToApartmentList() {
        Floor floor = new Floor(3.0, 70.0, 20.0);

        floor.setApartment(0, apartment);

        assertNotNull(floor.getApartment(0));
        LOGGER.info(floor.getApartments());
    }
}
