package com.company;

import static java.lang.System.out;
public class Main {

    public static void main(String[] args) {
        house h = new house();
        h.lodgersSquareFloors(); //calculating the square, height of the house and the total number of lodgers

        house h_2 = new house();
        h_2.lodgersSquareFloors();

        h.compare_house(h_2);
        h.f[0].compare_flats(h_2.f[0]);
    }
}
