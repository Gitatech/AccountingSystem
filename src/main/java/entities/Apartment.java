package entities;

import entities.validators.apartmentValidator.ApartmentValidator;

import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

public class Apartment implements Comparable<Apartment>, Serializable {
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
        ApartmentValidator.validate(this);
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        if (floor < 1) throw new IllegalArgumentException("Номер этажа квартиры должен быть больше 0");
        this.floor = floor;
    }

    public float getSquare() {
        return square;
    }

    public void setSquare(float square) {
        if (square < 0.0f) throw new IllegalArgumentException("Площадь не может быть меньше нуля");
        this.square = square;
    }

    public int getResidentsNumber() {
        return residentsNumber;
    }

    public void setResidentsNumber(int residents) {
        if (residents < 0) throw new IllegalArgumentException("Кол-во жителей не может быть отрицательным");
        this.residentsNumber = residents;
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(int roomsNumber) {
        if (roomsNumber < 1) throw new IllegalArgumentException("Кол-во комнат должно быть больше 0");
        this.roomsNumber = roomsNumber;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return number == apartment.number;
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
        return Integer.compare(number, o.number);
    }
}
