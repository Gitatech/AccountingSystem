package com.bsu.accounting.system.dao;

import com.bsu.accounting.system.exception.ApartmentNotFoundException;
import com.bsu.accounting.system.model.Apartment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ApartmentDaoImpl implements ApartmentDao {

    private static final Logger LOGGER = LogManager.getLogger(ApartmentDaoImpl.class);
    private static final String APARTMENT_NOT_FOUND_MSG = "Apartment with %s id not found";

    private final List<Apartment> apartmentHolder;
    private static int maxId = 0;

    public ApartmentDaoImpl(List<Apartment> apartments) {
        apartmentHolder = apartments;
    }

    public ApartmentDaoImpl() {
        apartmentHolder = new ArrayList<>();
    }


    @Override
    public Apartment create(int id, Apartment apartment) {
        Apartment apartmentWithId = apartment.withId(++maxId);
        if (apartmentHolder != null) {
            apartmentHolder.add(apartmentWithId);
        }
        return apartmentWithId;
    }

    @Override
    public Apartment read(int id) throws ApartmentNotFoundException {
        final Apartment apartment = findApartmentById(id);
        if (apartment == null) {
            throw new ApartmentNotFoundException(String.format(APARTMENT_NOT_FOUND_MSG, id));
        }
        return apartment;
    }

    @Override
    public Apartment update(Apartment apartment) throws ApartmentNotFoundException {
        final Apartment savedApartment = this.read(apartment.getId());
        final int ApartmentIndex;
        if (apartmentHolder != null) {
            ApartmentIndex = apartmentHolder.indexOf(savedApartment);
            apartmentHolder.add(ApartmentIndex, savedApartment);

        }
        return apartment;
    }

    @Override
    public void delete(int id) {
        try {
            final Apartment apartment = this.read(id);
            if (apartmentHolder != null) {
                apartmentHolder.remove(apartment);
            }
        } catch (ApartmentNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private Apartment findApartmentById(int id) {
        if (apartmentHolder != null) {
            for (Apartment apartment : apartmentHolder) {
                if (apartment.getId().equals(id)) {
                    return apartment;
                }
            }
        }
        return null;
    }
}
