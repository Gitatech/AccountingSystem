package com.aas;

import com.aas.dto.HouseDto;
import com.aas.factory.house.HouseFactory;
import com.aas.model.house.House;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApartmentAccountingSystemApplication {

    public static void main(String[] args) {
        HouseFactory houseFactory = new HouseFactory();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите кол-во домов: ");
        int numberOfHouses = in.nextInt();
        System.out.println();
        List<House> houses = new ArrayList<>();
        HouseDto houseDto = new HouseDto();
        /*Инициализация HouseDto*/
        for (int i = 0; i < numberOfHouses; i++) {
            System.out.println("Введите характеристики "+(i+1)+"-ого дома");
            System.out.print("Кол-во этажей: ");
            houseDto.setNumOfFloors(in.nextInt());
            System.out.println("Введите кол-во квартир на этаже");
            System.out.print("Однакомнатных: ");
            houseDto.setNumOfOneRoomApart(in.nextInt());
            System.out.print("Двухкомнатных: ");
            houseDto.setNumOfTwoRoomApart(in.nextInt());
            System.out.print("Трехкомнатных: ");
            houseDto.setNumOfThreeRoomApart(in.nextInt());
            System.out.print("Четырехкомнатных: ");
            houseDto.setNumOfFourRoomApart(in.nextInt());
            System.out.println();
            houses.add(houseFactory.createHouse(houseDto));             //Создание дома с помошью фабрики
            System.out.println();
        }
        houses.forEach(House::printProperties);                         //Вывод характеристик домов
    }
}
