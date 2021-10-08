package accounting;

import java.util.*;

public class House
{
    private int floorsCount = 0;
    private  int flatsPerFloor = 0;
    private static int currentHouseNumber;
    private int houseNumber;

   private List<Floor> floors = new ArrayList<>();


    public class Floor
    {
        private  int floorNumber = 0;
        private List<Flat> flats = new ArrayList<>();
        private int currentFlatsCount = 0;

         public class Flat
        {
            Scanner scannerFlat = new Scanner(System.in);
            private double flatSquare;
            private int residentsCount;
            private int flatNumber;
            private int roomsCount;
            private int currentRoomsCount = 0;
            private List<Room> rooms = new ArrayList<>();

            public class Room
            {
                private int roomNumber;
                private double roomSquare;
                Room()
                {
                    this.roomNumber = currentRoomsCount+1;
                    Scanner roomScanner = new Scanner(System.in);
                    System.out.print("Введите площадь " + Integer.toString(roomNumber) + " комнаты: ");
                   roomSquare =  roomScanner.nextDouble();
                    currentRoomsCount++;
                }

            }
            Flat()
            {
                this.flatNumber = currentFlatsCount+1;
                this.residentsCount = 2;
                if(floorNumber == 1)
                {
                    System.out.print("Введите количество комнат "+Integer.toString(flatNumber)+" квартиры на этаже: ");
                    this.roomsCount =scannerFlat.nextInt();
                    for(int i=0;i<roomsCount;i++)
                    {
                        this.rooms.add(new Room());
                    }
                }
                this.findFlatSquare();
                currentFlatsCount++;
            }
            private void findFlatSquare()
            {
                for(int i =0; i <roomsCount;i++)
                {
                    this.flatSquare+=rooms.get(i).roomSquare;
                }
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
            public String toString()
            {
                String result = "";
                result+= "Номер квартиры: " + Integer.toString(this.flatNumber) +"\nКоличество комнат: "+Integer.toString(this.roomsCount);
                for(int i = 0; i <this.roomsCount;i++)
                result+="\nПлощадь " +Integer.toString(i+1) +" квартиры: " + this.rooms.get(i).roomSquare;
                result+="\nОбщая площадь квартиры: " + Double.toString(this.flatSquare) +  "\nКоличество жильцов: " + Integer.toString(this.residentsCount);
                return result;
            }
        }
         Floor(int floorNumber)
        {
            this.floorNumber = floorNumber;
            for(int i=0;i<flatsPerFloor;i++)
            {
                this.flats.add(new Flat());
            }
        }
        public Flat getFlat(int flatNumber)
        {
            flatNumber -= (this.floorNumber)*flatsPerFloor;
            flatNumber--;
           return this.flats.get(flatNumber);
        }
    }
    public House()
    {
        this.houseNumber = currentHouseNumber+1;
        Scanner scannerHouse = new Scanner(System.in);
        System.out.print("Введите количество этажей в доме: ");
        this.floorsCount =  scannerHouse.nextInt();
        System.out.print("Введите количество квартир на одном этаже: ");
        this.flatsPerFloor = scannerHouse.nextInt();
        for(int i =0; i <this.floorsCount;i++)
        {
            this.floors.add(new Floor(i));
        }
        System.out.println("Дом номер "+ Integer.toString(currentHouseNumber+1)+ " успешно добавлен!");
        currentHouseNumber++;
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
    public int getFloorsCount()
    {
        return this.floorsCount;
    }
    public int getFlatsPerFloor()
    {
        return this.flatsPerFloor;
    }

    public String toString()
    {
        return "--------------------------------------------------------------------------------"+"\nНомер дома: "+Integer.toString(this.houseNumber)+"\nКоличество этажей: "+Integer.toString(this.floorsCount)+"\nКоличество квартир на одном этаже: "+Integer.toString(this.floors.get(0).currentFlatsCount)+"\nОбщая площадь дома: "+Double.toString(this.totalHouseSquare())+"\nОбщее количество жильцов: "+Integer.toString(this.totalHouseResidentsCount()) + "\n--------------------------------------------------------------------------------";
    }
}
