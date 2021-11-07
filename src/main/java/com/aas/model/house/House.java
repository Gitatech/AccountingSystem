package com.aas.model.house;

import com.aas.model.floor.Floor;
import lombok.Data;

import java.util.List;

/*
* Класс House несет информацию о доме
*/

@Data
public class House {
    public final Integer numOfFloors;   //Кол-во этажей
    public List<Floor> floors;          //Этажи

    /*Вывод характеристик дома*/
    public void printProperties() {
        System.out.println("Кол-во этажей: " + this.numOfFloors);
        System.out.println("Общая площадь дома: " + totalArea());
        System.out.println("Кол-во проживающих: " + totalPerson());
    }

    /*Подсчет общей площади дома*/
    public Double totalArea() {
        Double totalAreaOfFloor = 0.0;
        for (int i = 0; i < this.floors.get(0).apartments.size(); i++) {
            totalAreaOfFloor += this.floors.get(0).apartments.get(i).getApartmentArea();
        }
        return totalAreaOfFloor * this.numOfFloors;
    }
    /*Подсчет общего кол-ва жильцов дома*/
    public  Integer totalPerson(){
        Integer totalPerson = 0;
        for (int i = 0; i < this.numOfFloors; i++) {
            for (int j = 0; j < this.floors.get(i).getNumberOfApartments(); j++) {
                totalPerson += this.floors.get(i).apartments.get(j).numberOfPersons;
            }
        }
        return totalPerson;
    }
}
