package com.aas.model.floor;

import com.aas.model.apartment.Apartment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/*
* Класс Floor несет в себе информацию об однои этаже дома
*/

@Data
public class Floor {
    public final Integer numberOfApartments;  //Кол-во квартир на этаже
    public List<Apartment> apartments;        //Квартиры


}
