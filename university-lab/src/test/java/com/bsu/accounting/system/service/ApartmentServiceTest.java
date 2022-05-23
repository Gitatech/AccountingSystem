package com.bsu.accounting.system.service;

import com.bsu.accounting.system.model.Apartment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApartmentServiceTest {

    private final ApartmentService apartmentService = ApartmentService.getInstance();

    @Test
    public void getTotalApartmentArea_shouldReturnValidArea_always() {
        Apartment apartment = new Apartment(1, 5, 23.3, 8.7);

        final double totalApartmentArea = apartmentService.getTotalApartmentArea(apartment);

        assertTrue(totalApartmentArea >= 0);
    }
}
