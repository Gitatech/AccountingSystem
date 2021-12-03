package com.bsu.accounting.system.builder;

public interface ApartmentBuilder {

    void setApartmentId(int id);

    void setNumberOfResidents(int residents);

    void setTotalApartmentLength(double length);

    void setTotalApartmentWidth(double width);
}
