package com.bsu.accounting.system.service;

import com.bsu.accounting.system.model.Apartment;

public class ApartmentService {

    public double getTotalApartmentArea(Apartment apartment) {
        return apartment.getTotalApartmentLength() * apartment.getTotalApartmentWidth();
    }

}
