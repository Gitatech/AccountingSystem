package builders.apartmentBuilder;

import java.util.Random;

public class Director {
    //возвращает сгенерированную квартиру с известным номером и этажом
    public static void generateApartmentWithNumberAndFloor(ApartmentBuilder builder, int number, int floor) {
        builder.setNumber(number);
        builder.setFloor(floor);
        Random r = new Random(System.nanoTime());
        builder.setRoomsNumber(r.nextInt(5) + 1);
        builder.setResidentsNumber(r.nextInt(6));
        builder.setSquare((r.nextInt(500) + 250) / 10.0f);
    }

}
