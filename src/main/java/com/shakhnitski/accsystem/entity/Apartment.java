package com.shakhnitski.accsystem.entity;

import com.shakhnitski.accsystem.validator.ApartmentValidator;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

public class Apartment implements Serializable, Comparable<Apartment> {
    private final int number;
    private int floor;
    private float square;
    private int residentsNumber;
    private int roomsNumber;

    public Apartment(int number, int floor, int roomsNumber, int residentsNumber, float square) {
        this.number = number;
        this.floor = floor;
        this.roomsNumber = roomsNumber;
        this.residentsNumber = residentsNumber;
        this.square = square;
        ApartmentValidator validator = new ApartmentValidator();
        if (!validator.validate(this)) {
            throw new IllegalArgumentException("Недопустимые параметры для квартиры");
        }
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
        ApartmentValidator validator = new ApartmentValidator();
        if (!validator.validate(this)) {
            throw new IllegalArgumentException("Номер этажа не может быть меньше 1");
        }
    }

    public float getSquare() {
        return square;
    }

    public void setSquare(float square) {
        this.square = square;
        ApartmentValidator validator = new ApartmentValidator();
        if (!validator.validate(this)) {
            throw new IllegalArgumentException("Площадь не может быть меньше либо равно нулю");
        }
    }

    public int getResidentsNumber() {
        return residentsNumber;
    }

    public void setResidentsNumber(int residents) {
        this.residentsNumber = residents;
        ApartmentValidator validator = new ApartmentValidator();
        if (!validator.validate(this)) {
            throw new IllegalArgumentException("Кол-во жителей не может быть отрицательным");
        }
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
        ApartmentValidator validator = new ApartmentValidator();
        if (!validator.validate(this)) {
            throw new IllegalArgumentException("Кол-во комнат не может быть меньше 1");
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return number == apartment.number
                && floor == apartment.floor
                && Float.compare(apartment.square, square) == 0
                && residentsNumber == apartment.residentsNumber
                && roomsNumber == apartment.roomsNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, floor, square, residentsNumber, roomsNumber);
    }

    @Override
    public String toString() {
        return String.format(Locale.US,
                "Этаж: %d, номер: %d, кол-во комнат: %d, кол-во жителей: %d, площадь: %.1f м^2",
                floor, number, roomsNumber, residentsNumber, square);
    }

    @Override
    public int compareTo(Apartment o) {
        int result = Integer.compare(number, o.number);
        if (result == 0) {
            result = Integer.compare(floor, o.floor);
        }
        if (result == 0) {
            result = Float.compare(square, o.square);
        }
        if (result == 0) {
            result = Integer.compare(roomsNumber, o.roomsNumber);
        }
        if (result == 0) {
            result = Integer.compare(residentsNumber, o.residentsNumber);
        }
        return result;
    }
}
