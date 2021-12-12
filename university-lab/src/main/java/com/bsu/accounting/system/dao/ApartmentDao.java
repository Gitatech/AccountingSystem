package com.bsu.accounting.system.dao;

import com.bsu.accounting.system.exception.ApartmentNotFoundException;
import com.bsu.accounting.system.model.Apartment;

public interface ApartmentDao extends Dao<Apartment> {

    Apartment create(Apartment apartment);

    Apartment read(int id) throws ApartmentNotFoundException;

    Apartment update(Apartment apartment) throws ApartmentNotFoundException;

}
