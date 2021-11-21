package service;

import building.House;

public class HouseService {

    public static double getHouseArea(House house){
        int sqrt = 0;
        for(int i = 0; i< house.getNumberOfGroundsInHouse(); i++)
        {
            sqrt += house.getGround(i).getGroundArea();
        }
        return sqrt;
    }

    public static int getNumberOfHuman(House house){  // возвращает общее число жильцов
        int kol = 0;
        for(int i = 0; i< house.getNumberOfGroundsInHouse(); i++)
        {
            kol += house.getGround(i).getManInGround();
        }
        return kol;
    }
}
