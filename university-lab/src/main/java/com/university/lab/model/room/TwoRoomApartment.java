package com.university.lab.model.room;

import com.university.lab.model.Apartment;

public class TwoRoomApartment extends Apartment {

    public TwoRoomApartment(int id, int numberOfResidents, double totalFloorLength, double totalFloorWidth) {
        super(id, numberOfResidents, totalFloorLength, totalFloorWidth);
    }

    public TwoRoomApartment(Apartment apartment){
        super(apartment.getId(), apartment.getNumberOfResidents(),
                apartment.getTotalApartmentLength(), apartment.getTotalApartmentWidth());
    }

    @Override
    public String toString() {
        return "TwoRoomApartment{" +
                "id=" + getId() +
                ", numberOfResidents=" + getNumberOfResidents() +
                ", totalFloorLength=" + getTotalApartmentLength() +
                ", totalFloorWidth=" + getTotalApartmentWidth() +
                "}";
    }
}
