package com.aas.dto;

import lombok.Data;

/*
* Данный класс преназначен для передачи информации о дома
* DTO - Data Transfer Object
* Данный класс несет в себе информацию о:
*   Кол-ве этажей в доме;
*   Кол-во однакомнатных квартир в доме;
*   Кол-во двухкомнатный квартир в доме;
*   Кол-во трехкомнатный квартир в доме;
*   Кол-во четырехкомнатный квартир в доме.
*/

@Data
public class HouseDto {
    public Integer numOfFloors;
    public Integer numOfOneRoomApart;
    public Integer numOfTwoRoomApart;
    public Integer numOfThreeRoomApart;
    public Integer numOfFourRoomApart;
}
