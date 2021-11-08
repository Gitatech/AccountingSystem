package com.university.lab.model.room;

import com.university.lab.model.Apartment;

public class FourRoomApartment extends Apartment {

    public FourRoomApartment(int id, int numberOfResidents, double totalFloorLength, double totalFloorWidth) {
        super(id, numberOfResidents, totalFloorLength, totalFloorWidth);
    }

    public FourRoomApartment(Apartment apartment){
        super(apartment.getId(), apartment.getNumberOfResidents(),
                apartment.getTotalApartmentLength(), apartment.getTotalApartmentWidth());
    }

    @Override
    public String toString() {
        return "FourRoomApartment{" +
                "id=" + getId() +
                ", numberOfResidents=" + getNumberOfResidents() +
                ", totalFloorLength=" + getTotalApartmentLength() +
                ", totalFloorWidth=" + getTotalApartmentWidth() +
                "}";
    }
}
