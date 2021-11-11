package builders.apartmentBuilder;

import entities.Apartment;

public class SimpleApartmentBuilder implements ApartmentBuilder {
    private int number;
    private int floor;
    private int roomsNumber;
    private int residentsNumber;
    private float square;

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public void setRoomsNumber(int rooms) {
        this.roomsNumber = rooms;
    }

    @Override
    public void setResidentsNumber(int residents) {
        this.residentsNumber = residents;
    }

    @Override
    public void setSquare(float square) {
        this.square = square;
    }

    public Apartment getResult() {
        return new Apartment(number, floor, roomsNumber, residentsNumber, square);
    }
}
