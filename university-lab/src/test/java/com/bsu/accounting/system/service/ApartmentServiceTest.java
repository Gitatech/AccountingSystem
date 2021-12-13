package com.bsu.accounting.system.service;

import com.bsu.accounting.system.model.Apartment;
import com.bsu.accounting.system.model.ApartmentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApartmentServiceTest {

    private static final Logger LOGGER = LogManager.getLogger(ApartmentServiceTest.class);

    private static final Apartment APARTMENT_TEST = new Apartment(1, 5, 23.4, 18.0, ApartmentType.ONE_ROOM_APARTMENT);
    private final ApartmentService apartmentService = ApartmentService.getInstance();

    @AfterEach
    public void tearDown() {
        LOGGER.info('\n');
    }

    @Test
    public void getTotalApartmentArea_shouldReturnPositiveValue_whenTheApartmentExists() {
        LOGGER.info("getTotalApartmentArea");

        final double area = apartmentService.getTotalApartmentArea(APARTMENT_TEST);

        assertTrue(area > 0);
    }

    @Test
    public void compare_shouldReturnComparisonValues_whenTheApartmentsExists() {
        Apartment currentApartment = new Apartment(2, 4, 43.1, 18.0, ApartmentType.TWO_ROOM_APARTMENT);

        int compare = apartmentService.compare(currentApartment, APARTMENT_TEST);

        assertEquals(0, compare);
        assertNotSame(currentApartment, APARTMENT_TEST);
    }

    @Test
    public void compare_shouldReturnComparisonValues_whenTheApartmentsAreEquals() {
        Apartment currentApartment = new Apartment(1, 5, 23.4, 18.0, ApartmentType.ONE_ROOM_APARTMENT);

        int compare = apartmentService.compare(currentApartment, APARTMENT_TEST);

        assertEquals(currentApartment.getId(), APARTMENT_TEST.getId());
        assertNotSame(currentApartment, APARTMENT_TEST);
    }

    @Test
    public void compare_shouldThrowAnException_whenTheApartmentIsNotExist() {
        try {
            int compare = apartmentService.compare(null, APARTMENT_TEST);
            assertEquals(0, compare);
        } catch (NullPointerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
