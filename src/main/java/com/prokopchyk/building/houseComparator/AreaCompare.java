package houseComparator;

import com.prokopchyk.building.House;

import java.util.Comparator;


public class AreaCompare implements Comparator<House> {
    @Override
    public int compare(House o1, House o2) {
        if(o1.getHouseArea() > o2.getHouseArea())
            return 1;
        if(o1.getHouseArea() == o2.getHouseArea())
            return 0;
        else
            return -1;
    }
}
