package accounting;

import java.util.*;

public class House
{
    private int floorsCount = 0;
    private static int flatsPerFloor = 0;
    private static int currentFloorsNumber = 0;


   private List<Floor> floors = new ArrayList<>();


    public class Floor
    {
        private  int floorNumber = 0;

        private List<Flat> flats = new ArrayList<>();

         public class Flat
        {
            Scanner scannerFlat = new Scanner(System.in);
            private double flatSquare;
            private int residentsCount;
            private int flatNumber;
            private static int currentFlatsCount = 0;

            Flat()
            {

                this.flatNumber = currentFlatsCount+1;
                this.residentsCount = 2;
                if(floorNumber == 1)
                {
                    System.out.print("Введите площадь "+Integer.toString(flatNumber)+" квартиры на этаже: ");
                     this.flatSquare = Double.valueOf(scannerFlat.nextLine());
                }
                currentFlatsCount++;
            }

            @Override
            public boolean equals(Object o)
            {
                if (o == null || getClass() != o.getClass()) return false;
                Flat flat = (Flat) o;
                if(this.flatSquare == flat.flatSquare) return true;
                return false;
            }

            @Override
            public String toString() {
                return "Номер квартиры: " + Integer.toString(this.flatNumber) + " " + "Площадь квартиры: " + Double.toString(this.flatSquare) + " " + "Количество жильцов: " + Integer.toString(this.residentsCount);
            }
        }
         Floor()
        {
            this.floorNumber = currentFloorsNumber+1;
            currentFloorsNumber++;
            for(int i=0;i<flatsPerFloor;i++)
            {
                this.flats.add(new Flat());
            }
        }
        public Flat getFlat(int flatNumber)
        {
            flatNumber -= (this.floorNumber-1)*flatsPerFloor;
            flatNumber--;
           return this.flats.get(flatNumber);
        }
    }
    public House()
    {
        Scanner scannerHouse = new Scanner(System.in);
        System.out.print("Введите количество этажей в доме: ");
        this.floorsCount =  scannerHouse.nextInt();
        System.out.print("Введите количество квартир на одном этаже: ");
        this.flatsPerFloor = scannerHouse.nextInt();
        for(int i =0; i <this.floorsCount;i++)
        {
            this.floors.add(new Floor());
        }

    }

    public int getFloorsCount()
    {
        return this.floorsCount;
    }
   public  void setFloorsCount(int floorsCount)
    {
        this.floorsCount = floorsCount;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || this.getClass() != o.getClass()) return false;
        House house = (House)o;
        if(this.floorsCount == house.floorsCount && this.flatsPerFloor == house.flatsPerFloor)
            return true;
        else return false;

    }
    public double totalHouseSquare()
    {
        double result =0;
        for(int i =0; i < floorsCount;i++)
        {
            for(int j =0;j<flatsPerFloor;j++)
            {
               result+= floors.get(i).flats.get(j).flatSquare;
            }
        }
        return result;
    }
    public int totalHouseResidentsCount()
    {
        int result =0;
        for(int i =0; i < floorsCount;i++)
        {
            for(int j =0;j<flatsPerFloor;j++)
            {
                result+= floors.get(i).flats.get(j).residentsCount;
            }
        }
        return result;
    }
    public Floor getFloor(int floorNumber)
    {
        return this.floors.get(floorNumber-1);
    }

    public static int getFlatsPerFloor() {
        return flatsPerFloor;
    }
}
