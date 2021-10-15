package com.company;

public class house {
    private double totalSquare;
    private int residents;
    private int numberOfFloors;

    public class entrance {
        private int numberOfEntrances;
        private double entranceSquare;
        {
            entranceSquare = 240;
        }
        public class flat {
            private int room;
            private double square;
            private int person;
            private int maxPerson; //максимальное кол-во человек, проживающих в квартире

            {
                maxPerson = 3;
                square = 30;
            }
            public double getSquare() {
                return square;
            }
            public int getPerson() {
                return person;
            }
            public int getRoom() {
                return room;
            }
            public int getMaxPerson(){
                return maxPerson;
            }
            public void setPerson(int person) {
                this.person = person;
            }

            flat(int room){
                this.room = room;
                if(room > 1){
                    this.maxPerson += room - 1;
                    this.square += (room - 1) * 15;
                }
            }

        }
    }
}
