package com.company;

import java.util.Scanner;
import static java.lang.System.out;

public class house {
    private double totalSquare;
    private int lodgers;
    private int Floors;
    public flat[] f;

    private int flatsPerFloor; //the number of flats per floor
    private int flatNumber; // the total number of flats in the house

    void lodgersSquareFloors(){
        f = new flat[flatNumber];
        for(int i = 0; i < flatNumber; i++){
            f[i] = new flat();
            this.lodgers += f[i].person;
            this.totalSquare += f[i].square;
        }
        this.Floors = flatNumber/flatsPerFloor;
        out.printf("The house has %d floors, %d lodgers and %f square\n", Floors, lodgers, totalSquare);
    }
    void compare_house(house h){
        out.printf("first house: apartments - %d, height - %d floors\n", this.flatNumber, this.Floors);
        out.printf("second house: apartments - %d, height - %d floors\n", h.flatNumber, h.Floors);

        if(this.flatNumber > h.flatNumber){
            out.println("The first house has more apartments than the second");
        }
        if(this.flatNumber < h.flatNumber){
            out.println("The second house has more apartments than the first");
        }
        else if(this.flatNumber == h.flatNumber){
            out.println("Both house has the same number of apartments");
        }

        if(this.Floors > h.Floors){
            out.println("The first house higher than the second");
        }
        if(this.Floors < h.Floors){
            out.println("The second house higher than the first");
        }
        else if(this.Floors == h.Floors){
            out.println("Both house has the same height");
        }

        if(this.lodgers > h.lodgers){
            out.println("The first house has more lodgers than the second");
        }
        if(this.lodgers < h.lodgers){
            out.println("The second house has more lodgers than the first");
        }
        else if(this.lodgers == h.lodgers){
            out.println("Both house has the same number of lodgers");
        }
    }
    public class flat {
        private double square;
        private int person;
        private int size;

        void print() {
            out.printf("%d people live in %d-room flat with %f square\n", person, size, square);
        }

        void compare_flats(flat f){
            out.printf("first apartment: person - %d, square - %f\n", this.person, this.square);
            out.printf("second apartment: person - %d, square - %f\n", f.person, f.square);

            if(this.person > f.person){
                out.println("More people live in the first flat than in the second");
            }
            if(this.person < f.person){
                out.println("More people live in the second flat than in the first");
            }
            else if(this.person == f.person){
                out.println("The same number of people live in both apartments");
            }

            if(this.square > f.square){
                out.println("The first flat has bigger square than the second");
            }
            if(this.square < f.square){
                out.println("The second flat has bigger square than the first");
            }
            else if(this.square == f.square){
                out.println("Both flats have the same square");
            }
        }

        flat() {
            Scanner in = new Scanner(System.in);
                while (size < 1 || size > 4) {
                    try {
                        out.println("Input the size of an apartment from 1 to 4:");
                        this.size = in.nextInt();
                        switch (size) {
                            case 1 -> {
                                //the number of people living in the apartment varies in the range of 0-3
                                this.person = (int) (Math.random() * 10) % 4;
                                //the area of the apartment varies in the range of 27-30 square meters
                                this.square = (Math.random() * 100) % 4 + 27;
                            }
                            case 2 -> {
                                this.person = (int) (Math.random() * 10) % 5;
                                this.square = (Math.random() * 100) % 6 + 45;
                            }
                            case 3 -> {
                                this.person = (int) (Math.random() * 10) % 6;
                                this.square = (Math.random() * 100) % 6 + 60;
                            }
                            case 4 -> {
                                this.person = (int) (Math.random() * 10) % 7;
                                this.square = (Math.random() * 100) % 6 + 75;
                            }
                            default -> throw new Exception("It's not the flat type.");
                        }
                    } catch (Exception ex) {
                        out.println(ex.getMessage() + " Input new flat type from 1 to 4:");
                        this.size = in.nextInt();
                    }
                }
            }
        }

    house() {
        Scanner in = new Scanner(System.in);
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