package accounting;

import accounting.House;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[]args)
    {

        int choice1, choice2;

       List<House> arrayOfHouses = new ArrayList<>();
       /* System.out.println((house.getFloor(3)).getFlat(18).equals((house.getFloor(2)).getFlat(12)));*/

        do
        {
            Scanner scannerChoice = new Scanner(System.in);
            System.out.print("Выберите нужное действие:\n1. Создать новый дом\n2. Просмотреть информацию о уже существущем доме\n3. Удалить дом\n4. Сравнить дома\n5. Выйти из программы\nВаш выбор: ");
            choice1 =scannerChoice.nextInt();
        switch(choice1)
        {
            case 1:
                arrayOfHouses.add(new House());
                break;
            case 2:
                if(arrayOfHouses.isEmpty()) {
                    System.out.println("Домов нет");
                    break;
                }
                int houseNumber = 0;
                do
                {
                    System.out.print("Введите номер нужного дома: ");
                houseNumber = scannerChoice.nextInt();
                if(arrayOfHouses.size() < houseNumber || houseNumber <= 0)
                {
                    System.out.println("Дома с таким номером нет/номер введен неверно!");

                }
                }while(arrayOfHouses.size() < houseNumber || houseNumber <= 0);

              do
              { System.out.print("Выберите нужное действие: \n1. Высчитать общую площадь дома\n2. Узнать общее количество жильцов в доме\n3. Узнать количество этажей\n4. Просмотреть информацию об отдельной квартире\n5. Вернуться в главное меню\nВаш выбор: ");
                choice2= scannerChoice.nextInt();
              switch(choice2)
               {
                   case 1:
                      System.out.println("Общая площадь дома номер "+ Integer.toString(houseNumber) +" = "+ Double.toString(arrayOfHouses.get(houseNumber-1).totalHouseSquare()));
                       break;
                   case 2:
                       System.out.println("Общее количество жильцов в доме номер "+ Integer.toString(houseNumber) +" = "+ Integer.toString(arrayOfHouses.get(houseNumber-1).totalHouseResidentsCount()));
                       break;
                   case 3:
                       System.out.println("Количество этажей в доме номер "+ Integer.toString(houseNumber) +" = "+ Integer.toString(arrayOfHouses.get(houseNumber-1).getFloorsCount()));
                       break;
                   case 4:
                       System.out.print("Введите нужный этаж: ");
                       int floorNumber = 0;
                       floorNumber = scannerChoice.nextInt();
                       System.out.print("Выберите номер нужной вам квартиры из квартир на " + Integer.toString(floorNumber) +" этаже: ");
                       for(int i = arrayOfHouses.get(houseNumber-1).getFlatsPerFloor()*(floorNumber-1)+1; i<=arrayOfHouses.get(houseNumber-1).getFlatsPerFloor()*floorNumber;i++)
                       {
                           System.out.print(Integer.toString(i) + " ");
                       }
                       System.out.print("\nВаш выбор: ");
                       int flatNumber = 0;
                       flatNumber = scannerChoice.nextInt();
                       System.out.println(arrayOfHouses.get(houseNumber-1).getFloor(floorNumber).getFlat(flatNumber));
                       break;
                   case 5:
                       break;

               }
              }while(choice2!=5);
                break;
        }
    }while(choice1!=5);
}}
