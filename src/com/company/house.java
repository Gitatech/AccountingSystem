package com.company;

import java.util.Scanner;
import static java.lang.System.*;

public class house {
    private double totalSquare;
    private int residents;

    private int flatsPerFloor; //the number of flats per floor
    private int flatNumber; //the total number of flats in the house
    
    public class flatFactory {
        private int room;
        private double square;
        private int person;

        flatFactory() {
            int size; //flat size
            do {
                try {
                    out.println("Input the size of an apartment from 1 to 4:");
                    Scanner in = new Scanner(System.in);
                    size = in.nextInt();
                    in.close();
                    switch (size) {
                        case 1:
                            this.room = 1;
                            //the number of people living in the apartment varies in the range of 0-3
                            this.person = (int) (Math.random() * 10) % 4;
                            //the area of the apartment varies in the range of 27-30 square meters
                            this.square = (Math.random() * 100) % 4 + 27;
                            break;
                        case 2:
                            this.room = 2;
                            this.person = (int) (Math.random() * 10) % 5;
                            this.square = (Math.random() * 100) % 6 + 45;
                            break;
                        case 3:
                            this.room = 3;
                            this.person = (int) (Math.random() * 10) % 6;
                            this.square = (Math.random() * 100) % 6 + 60;
                            break;
                        case 4:
                            this.room = 4;
                            this.person = (int) (Math.random() * 10) % 7;
                            this.square = (Math.random() * 100) % 6 + 75;
                            break;
                        default:
                            throw new Exception("It's not the flat type.");
                    }
                } catch (Exception ex) {
                    out.println(ex.getMessage() + " Input new flat type from 1 to 4:");
                    Scanner in = new Scanner(System.in);
                    size = in.nextInt();
                    in.close();
                }
            } while (size < 1 || size > 4);
        }
    }
    void NumberOfFloors(){
        out.printf("The house consists of %d floors\n", flatNumber/flatsPerFloor);
    }
    void TotalSquare(){
        flatFactory[] Flat = new flatFactory[flatsPerFloor]; //one-floor apartments

    }
    house() {
        Scanner in = new Scanner(System.in);
        do {
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
        }while(flatsPerFloor <= 0);

        do {
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
        }while(flatNumber % flatsPerFloor != 0 || flatNumber < 1 || flatsPerFloor > flatNumber);
        in.close();
    }
}
