package builders.apartmentBuilder;

import entities.Apartment;

public class ApartmentBuilder {
    private int number;
    private int floor;
    private int roomsNumber;
    private int residentsNumber;
    private float square;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setRoomsNumber(int rooms) {
        this.roomsNumber = rooms;
    }

    public void setResidentsNumber(int residents) {
        this.residentsNumber = residents;
    }

    public void setSquare(float square) {
        this.square = square;
    }

    public Apartment getResult() {
        return new Apartment(number, floor, roomsNumber, residentsNumber, square);
    }
}
