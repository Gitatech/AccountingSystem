package com.university.lab.model;

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
    private double totalFloorLength;
    private double totalFloorWidth;

//    public static int apartmentNumber = 1;
//
//    public Apartment(int numberOfResidents, double floorLength, double floorWidth) {
//        numberOfResidentsValueCheck(numberOfResidents);
//        lengthValueCheck(floorLength);
//        widthValueCheck(floorWidth);
//        this.id = apartmentNumber++;
//    }

    public Apartment(int id, int numberOfResidents, double totalFloorLength, double totalFloorWidth) {
        numberOfResidentsValueCheck(numberOfResidents);
        lengthValueCheck(totalFloorLength);
        widthValueCheck(totalFloorWidth);
        if (id <= 0) {
            throw new IllegalArgumentException(ILLEGAL_ID);
        }
        this.id = id;
        this.numberOfResidents = numberOfResidents;
        this.totalFloorLength = totalFloorLength;
        this.totalFloorWidth = totalFloorWidth;
    }

    public void setNumberOfResidents(int numberOfResidents) {
        this.numberOfResidents = numberOfResidents;
    }

    public int getNumberOfResidents() {
        return numberOfResidents;
    }

    public double getTotalFloorLength() {
        return totalFloorLength;
    }

    public double getTotalFloorWidth() {
        return totalFloorWidth;
    }

    public int getId() {
        return id;
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
            this.totalFloorLength = floorLength;
        }
    }

    private void widthValueCheck(double floorWidth) {
        if (floorWidth < 0) {
            throw new IllegalArgumentException(CHECKING_THE_VALID_VALUE_FOR_THE_FLOOR_WIDTH);
        } else {
            this.totalFloorWidth = floorWidth;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return id == apartment.id && numberOfResidents == apartment.numberOfResidents
                && Double.compare(apartment.totalFloorLength, totalFloorLength) == 0
                && Double.compare(apartment.totalFloorWidth, totalFloorWidth) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfResidents, totalFloorLength, totalFloorWidth);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", numberOfResidents=" + numberOfResidents +
                ", totalFloorLength=" + totalFloorLength +
                ", totalFloorWidth=" + totalFloorWidth +
                '}';
    }
}
