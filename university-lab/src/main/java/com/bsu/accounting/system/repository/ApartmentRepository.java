package com.bsu.accounting.system.repository;

import com.bsu.accounting.system.exception.ApartmentNotFoundException;
import com.bsu.accounting.system.model.Apartment;

public interface ApartmentRepository extends Repository<Apartment> {

    Apartment create(int id, Apartment apartment);

    Apartment read(int id) throws ApartmentNotFoundException;

    Apartment update(Apartment apartment) throws ApartmentNotFoundException;

}
