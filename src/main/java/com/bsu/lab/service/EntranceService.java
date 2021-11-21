package com.bsu.lab.service;

import com.bsu.lab.model.Entrance;
import com.bsu.lab.model.Floor;
import org.jetbrains.annotations.NotNull;

public class EntranceService {
    public static void addFloor(@NotNull Entrance entrance, @NotNull Floor floor) {
        entrance.getFloors().add(floor);
        entrance.setFloorsCount(entrance.getFloorsCount() + 1);
    }
}
