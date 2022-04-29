package com.prokopchyk.building.houseComparator;
import com.prokopchyk.building.House;
import com.prokopchyk.service.HouseService;

import java.util.Comparator;


public class PersonsCompare implements Comparator<House> {
    @Override
    public int compare(House o1, House o2) {
        if(HouseService.getHouseService().getNumberOfHuman(o1) > HouseService.getHouseService().getNumberOfHuman(o2))
            return 1;
        if(HouseService.getHouseService().getNumberOfHuman(o1) == HouseService.getHouseService().getNumberOfHuman(o2))
            return 0;
        else
            return -1;
    }
}
