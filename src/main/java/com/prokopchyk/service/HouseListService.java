package com.prokopchyk.service;

import com.prokopchyk.building.House;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class HouseListService {
    private static HouseListService houseListService;
    private HouseListService(){};
    public static HouseListService getHouseListService(){
        if(houseListService == null)
            houseListService = new HouseListService();
        return houseListService;
    }

    public void readHouseList(String fileName) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            for (House house : (List<House>) objectInputStream.readObject()) {
                HouseService.getHouseService().save(house);
            }
        } catch (Exception e) {
            System.out.println( "Error"+ e.getMessage());
        }
    }

    public void writeHouseList(String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(HouseService.getHouseService().getAll());
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}

