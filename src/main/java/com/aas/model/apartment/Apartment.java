package com.aas.model.apartment;

import lombok.Data;

/*
* Класс Apartment несет в себе информацию о квартире определенного тип
*/

@Data
public class Apartment {
    public final Double apartmentArea;
    public final Integer maximumPersons;
    public final Integer numberOfPersons;
}
