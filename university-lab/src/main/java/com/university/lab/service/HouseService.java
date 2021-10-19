package com.university.lab.service;

import com.university.lab.model.House;

public class HouseService {

    public void viewHouse(House house) {
        System.out.println(house);
        apartmentsInTheHouse(house);
    }

    private void apartmentsInTheHouse(House house) {
        System.out.printf("Apartments in the \"%s\":%n", house.getName());
        for (int i = 0; i < house.apartments.size(); i++) {
            System.out.println(house.apartments.get(i));
        }
    }
}
