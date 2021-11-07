package com.aas.factory.house;

import com.aas.dto.HouseDto;
import com.aas.factory.apartment.ApartmentFactory;
import com.aas.factory.apartment.ApartmentType;
import com.aas.model.apartment.Apartment;
import com.aas.model.floor.Floor;
import com.aas.model.house.House;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* Фабрика дома
* Возвращает дом, с заданными типами квартир, кол-ом квартир, кол-ом этажей
*/

public class HouseFactory {
    public House createHouse(HouseDto houseDto) {
        List<Apartment> apartments = new ArrayList<>();
        ApartmentFactory apartmentFactory = new ApartmentFactory();
        Scanner in = new Scanner(System.in);
        System.out.println("Введите площадь квартир");
        Double squareArea1 = 0.0;
        Double squareArea2 = 0.0;
        Double squareArea3 = 0.0;
        Double squareArea4 = 0.0;
        if (houseDto.numOfOneRoomApart != 0) {
            System.out.print("Однакомнатная: ");
            squareArea1 = Double.parseDouble(in.nextLine());
        }
        if (houseDto.numOfTwoRoomApart != 0) {
            System.out.print("Двухкомнатная: ");
            squareArea2 = Double.parseDouble(in.nextLine());
        }
        if (houseDto.numOfThreeRoomApart != 0) {
            System.out.print("Трехкомнатная: ");
            squareArea3 = Double.parseDouble(in.nextLine());
        }
        if (houseDto.numOfFourRoomApart != 0) {
            System.out.print("Четырехкомнатная: ");
            squareArea4 = Double.parseDouble(in.nextLine());
        }
        /*Создание этажа с помощью фабрики квартир*/
        while (houseDto.numOfOneRoomApart + houseDto.numOfTwoRoomApart + houseDto.numOfThreeRoomApart + houseDto.numOfFourRoomApart > 0) {
            if (houseDto.numOfOneRoomApart > 0) {
                apartments.add(apartmentFactory.createApartment(ApartmentType.ONEROOM, squareArea1));
                houseDto.numOfOneRoomApart--;
            } else if (houseDto.numOfTwoRoomApart > 0) {
                apartments.add(apartmentFactory.createApartment(ApartmentType.TWOROOM, squareArea2));
                houseDto.numOfTwoRoomApart--;
            } else if (houseDto.numOfThreeRoomApart > 0) {
                apartments.add(apartmentFactory.createApartment(ApartmentType.THREEROOM, squareArea3));
                houseDto.numOfThreeRoomApart--;
            } else if (houseDto.numOfFourRoomApart > 0) {
                apartments.add(apartmentFactory.createApartment(ApartmentType.FOURROOM, squareArea4));
                houseDto.numOfFourRoomApart--;
            } else break;
        }
        /*Создание этажа с квартирами*/
        Floor floor = new Floor(apartments.size());
        floor.setApartments(apartments);
        /*Создание этажей*/
        List<Floor> floorList = new ArrayList<>();
        for (int i = 0; i < houseDto.numOfFloors; i++) {
            floorList.add(floor);
        }
        /*Создание дома*/
        House house = new House(houseDto.numOfFloors);
        house.setFloors(floorList);
        return house;
    }
}
