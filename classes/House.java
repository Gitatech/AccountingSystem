package AccountingSystem.classes;

import java.io.Serializable;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class House implements Comparable<House>, Serializable {
    private final int number;

    private final SortedSet<Apartment> apartments;

    public House(int number) {
        this.number = number;
        apartments = new TreeSet<>();
    }

    public int getNumber() {
        return number;
    }

    public static House templateHouse() {
        return new House(Integer.MIN_VALUE);
    }

    public boolean isApartmentThere(int number) {
        return apartments.stream().anyMatch(e -> e.getNumber() == number);
    }

    public void addApartmentByConsole() {
        Apartment apartment = Apartment.createByConsole();
        if (isApartmentThere(apartment.getNumber())) {
            System.out.println(ColorScheme.ANSI_RED + "Квартира уже есть в доме!" + ColorScheme.ANSI_RESET);
        } else {
            apartments.add(apartment);
            System.out.println(ColorScheme.ANSI_GREEN + "Квартира была добавлена в дом" + ColorScheme.ANSI_RESET);
        }
    }

    public Apartment[] getApartments() {
        return apartments.toArray(Apartment[]::new);
    }

    public void removeApartment(Apartment apartment) {
        apartments.remove(apartment);
    }

    public int calculatePopulation() {
        int population = 0;
        for (Apartment apartment : apartments) {
            population += apartment.getResidentsNumber();
        }
        return population;
    }

    public int calculateFloors() {
        if (apartments.isEmpty()) {
            return 0;
        } else {
            return apartments.last().getFloor();
        }
    }

    public float calculateFullSquare() {
        float square = 0.0f;
        for (Apartment apartment : apartments) {
            square += apartment.getSquare();
        }
        return square;
    }


    public static int compareByPopulation(House house1, House house2) {
        return Integer.compare(house1.calculatePopulation(), house2.calculatePopulation());
    }

    public static int compareByFloors(House house1, House house2) {
        return Integer.compare(house1.calculateFloors(), house2.calculateFloors());
    }

    public static int compareByFullSquare(House house1, House house2) {
        return Float.compare(house1.calculateFullSquare(), house2.calculateFullSquare());
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Дом номер ").append(number).append(":\n");
        for (Apartment apartment : apartments) {
            sb.append(apartment).append("\n");
        }
        sb.append('\n');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return number == house.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, apartments);
    }

    @Override
    public int compareTo(House o) {
        return Integer.compare(number, o.number);
    }
}
