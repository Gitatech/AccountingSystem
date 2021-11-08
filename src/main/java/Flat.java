public class Flat {
    private int roomsCount;
    private int humansCount;
    private int square;

    public Flat(int roomsCount, int humansCount, int square){
        this.humansCount = humansCount;
        this.roomsCount = roomsCount;
        this.square = square;
    }

    public boolean compare(Flat flat, int param){
        switch (param){
            case 0: return flat.getHumansCount() == humansCount;
            case 1: return flat.getSquare() == square;
        }
        throw new IllegalArgumentException("Argument must be from 0 to 2");
    }

    public int getHumansCount() {
        return humansCount;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public int getSquare() {
        return square;
    }

    public void setHumansCount(int humansCount) {
        this.humansCount = humansCount;
    }

    public void setRoomsCount(int roomsCount) {
        this.roomsCount = roomsCount;
    }

    public void setSquare(int square) {
        this.square = square;
    }
}
