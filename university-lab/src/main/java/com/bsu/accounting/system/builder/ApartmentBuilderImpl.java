package com.bsu.accounting.system.builder;

import com.bsu.accounting.system.model.Apartment;

public class ApartmentBuilderImpl implements ApartmentBuilder {

    private int residents;
    private double length;
    private double width;

    @Override
    public void setNumberOfResidents(int residents) {
        this.residents = residents;
    }

    @Override
    public void setTotalApartmentLength(double length) {
        this.length = length;
    }

    @Override
    public void setTotalApartmentWidth(double width) {
        this.width = width;
    }

    public Apartment getResult() {
        return new Apartment(null, residents, length, width);
    }
}
