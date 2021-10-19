package com.company;

import java.util.Scanner;
import static java.lang.System.out;

public class flat {
    private double square;
    private int person;
    private int size;

    void print(){
        out.printf("%d people live in %d-room flat with %f square\n", person, size, square);
    }

    flat() {
        try (Scanner in = new Scanner(System.in)) {
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
}