package com.university.lab.model.room;

import com.university.lab.model.Apartment;

public class ThreeRoomApartment extends Apartment {

    public ThreeRoomApartment(int id, int numberOfResidents, double totalFloorLength, double totalFloorWidth) {
        super(id, numberOfResidents, totalFloorLength, totalFloorWidth);
    }

    public ThreeRoomApartment(Apartment apartment){
        super(apartment.getId(), apartment.getNumberOfResidents(),
                apartment.getTotalApartmentLength(), apartment.getTotalApartmentWidth());
    }

    @Override
    public String toString() {
        return "ThreeRoomApartment{" +
                "id=" + getId() +
                ", numberOfResidents=" + getNumberOfResidents() +
                ", totalFloorLength=" + getTotalApartmentLength() +
                ", totalFloorWidth=" + getTotalApartmentWidth() +
                "}";
    }
}
