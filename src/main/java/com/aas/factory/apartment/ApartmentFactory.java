package com.aas.factory.apartment;

import com.aas.model.apartment.*;

/*
* Фабрика квартир
* Возвращает квартиру, с заданными полями, типа, определенного в ApartmentType
*/

public class ApartmentFactory {
    public Apartment createApartment(ApartmentType apartmentType, Double apartmentArea) {
        return switch (apartmentType) {
            case ONEROOM -> new OneRoomApartment(apartmentArea, 2,(int)(Math.random()*2+1));
            case TWOROOM -> new TwoRoomApartment(apartmentArea, 3,(int)(Math.random()*3+1));
            case THREEROOM -> new ThreeRoomApartment(apartmentArea, 5,(int)(Math.random()*5+1));
            case FOURROOM -> new FourRoomApartment(apartmentArea, 6,(int)(Math.random()*6+1));
        };
    }
}
