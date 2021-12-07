package com.bsu.accounting.system.model;

public enum ApartmentType {

    ONE_ROOM_APARTMENT(1),
    TWO_ROOM_APARTMENT(2),
    THREE_ROOM_APARTMENT(3),
    FOUR_ROOM_APARTMENT(4);

    public final int amountOfRooms;

    ApartmentType(int amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }
}
