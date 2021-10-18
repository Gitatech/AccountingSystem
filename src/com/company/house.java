package com.company;

import java.util.Scanner;
import static java.lang.System.out;

public class house {
    private double totalSquare;
    private int residents;

    private int flatsPerFloor; //the number of flats per floor
    private int flatNumber; // the total number of flats in the house

    void NumberOfFloors(){
        out.printf("The house consists of %d floors\n", flatNumber/flatsPerFloor);
    }

    house() {
        try(Scanner in = new Scanner(System.in)) {
            while(flatsPerFloor <= 0){
                try {
                    out.println("Input the number of apartments per floor:");
                    this.flatsPerFloor = in.nextInt();
                    if (flatsPerFloor <= 0) {
                        throw new Exception("The number of apartments per floor must be no less than 1");
                    }
                } catch (Exception ex) {
                    out.println(ex.getMessage() + ". Input new number of flats per floor:");
                    this.flatsPerFloor = in.nextInt();
                }
            }

            while (flatNumber % flatsPerFloor != 0 || flatNumber < 1 || flatsPerFloor > flatNumber) {
                try {
                    out.println("Input the total number of apartments in the house:");
                    this.flatNumber = in.nextInt();
                    if (flatNumber % flatsPerFloor != 0 || flatNumber <= 0 || flatsPerFloor > flatNumber) {
                        throw new Exception("The total number of apartments must be multiple to the number of apartments,\n" +
                                "no less than 1, and no less the number of flats per floor");
                    }
                } catch (Exception ex) {
                    out.println(ex.getMessage() + ". Input new total number of apartments in the house:");
                    this.flatNumber = in.nextInt();
                }
            }
        }
    }

}
