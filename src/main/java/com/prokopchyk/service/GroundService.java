package com.prokopchyk.service;

import com.prokopchyk.builder.GroundBuilder;
import com.prokopchyk.building.Flat;
import com.prokopchyk.building.Ground;

public class GroundService {
    private static GroundService groundService;

    private GroundService() {
    }

    public static synchronized GroundService getGroundService() {
        if (groundService == null) {
            groundService= new GroundService();
        }
        return groundService;
    }

    public Ground createGround(int numberOfFlatsInGround) {
        Ground ground = new GroundBuilder().setNumOfFlats(numberOfFlatsInGround).Builder();
        for (int i = 0; i < numberOfFlatsInGround; ++i) {
            ground.addFlat(FlatService.getFlatService().createFlat());
        }

        return ground;
    }

    public Ground cloneGround(Ground ground) {
        Ground groundNew = new Ground();
        for (Flat flat : ground.getFlat()) {
            groundNew.addFlat(FlatService.getFlatService().cloneFlat(flat));
        }

        return groundNew;
    }
}
