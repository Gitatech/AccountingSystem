package com.bsu.accounting.system.model;

import java.util.Objects;

public class Apartment {

    private static final String CHECKING_THE_VALID_VALUE_FOR_THE_FLOOR_LENGTH =
            "The total floor length of the apartment must be > 0";
    private static final String CHECKING_THE_VALID_VALUE_FOR_THE_FLOOR_WIDTH =
            "The total floor width of the apartment must be > 0";
    private static final String CHECKING_THE_VALID_VALUE_OF_RESIDENTS = "The number of residents must be >= 0";
    private static final String ILLEGAL_ID = "id must be > 0";

    private final int id;
    private int numberOfResidents;
    private double totalApartmentLength;
    private double totalApartmentWidth;

    public ApartmentType type;

    public Apartment(int id, int numberOfResidents, double totalApartmentLength, double totalApartmentWidth) {
        numberOfResidentsValueCheck(numberOfResidents);
        lengthValueCheck(totalApartmentLength);
        widthValueCheck(totalApartmentWidth);
        if (id <= 0) {
            throw new IllegalArgumentException(ILLEGAL_ID);
        }
        this.id = id;
        this.numberOfResidents = numberOfResidents;
        this.totalApartmentLength = totalApartmentLength;
        this.totalApartmentWidth = totalApartmentWidth;
    }

    public Apartment(int id, int numberOfResidents, double totalApartmentLength, double totalApartmentWidth, ApartmentType type) {
        if (id <= 0) {
            throw new IllegalArgumentException(ILLEGAL_ID);
        }
        this.id = id;
        this.numberOfResidents = numberOfResidents;
        this.totalApartmentLength = totalApartmentLength;
        this.totalApartmentWidth = totalApartmentWidth;
        this.type = type;
    }

    public int getNumberOfResidents() {
        return numberOfResidents;
    }

    public double getTotalApartmentLength() {
        return totalApartmentLength;
    }

    public double getTotalApartmentWidth() {
        return totalApartmentWidth;
    }

    public int getId() {
        return id;
    }

    public ApartmentType getType() {
        return type;
    }

    private void numberOfResidentsValueCheck(int numberOfResidents) {
        if (numberOfResidents < 0) {
            throw new IllegalArgumentException(CHECKING_THE_VALID_VALUE_OF_RESIDENTS);
        } else {
            this.numberOfResidents = numberOfResidents;
        }
    }

    private void lengthValueCheck(double floorLength) {
        if (floorLength <= 0) {
            throw new IllegalArgumentException(CHECKING_THE_VALID_VALUE_FOR_THE_FLOOR_LENGTH);
        } else {
            this.totalApartmentLength = floorLength;
        }
    }

    private void widthValueCheck(double floorWidth) {
        if (floorWidth < 0) {
            throw new IllegalArgumentException(CHECKING_THE_VALID_VALUE_FOR_THE_FLOOR_WIDTH);
        } else {
            this.totalApartmentWidth = floorWidth;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return id == apartment.id && numberOfResidents == apartment.numberOfResidents
                && Double.compare(apartment.totalApartmentLength, totalApartmentLength) == 0
                && Double.compare(apartment.totalApartmentWidth, totalApartmentWidth) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfResidents, totalApartmentLength, totalApartmentWidth);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", numberOfResidents=" + numberOfResidents +
                ", totalFloorLength=" + totalApartmentLength +
                ", totalFloorWidth=" + totalApartmentWidth +
                '}';
    }
}
