package com.university.lab.service;

import com.university.lab.model.Apartment;

public class ApartmentService {

    public double getTotalApartmentArea(Apartment apartment) {
        return apartment.getTotalFloorLength() * apartment.getTotalFloorWidth();
    }
}
