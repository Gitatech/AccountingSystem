package houseComparator;
import com.prokopchyk.building.House;

import java.util.Comparator;


public class PersonsCompare implements Comparator<House> {
    @Override
    public int compare(House o1, House o2) {
        if(o1.getNumberOfHuman() > o2.getNumberOfHuman())
            return 1;
        if(o1.getNumberOfHuman() == o2.getNumberOfHuman())
            return 0;
        else
            return -1;
    }
}
