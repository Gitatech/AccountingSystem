import java.io.Serializable;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class House implements Comparable<House>, Serializable {
    private static final long SerialVersionUID = 1110L;

    private int number;

    private final SortedSet<Apartment> apartments;

    public House(int number) {
        this.number = number;
        apartments = new TreeSet<>();
    }

    public void addApartment(Apartment apartment) {
        apartments.add(apartment);
    }

    public int getNumber() {
        return number;
    }

    public boolean containsApartment(int number) {
        if (number < 1) return false;
        return apartments.contains(new Apartment(number, 1, 1, 1, 1));
    }

    public Apartment[] getApartments() {
        return apartments.toArray(Apartment[]::new);
    }

    public void removeApartment(Apartment apartment) {
        apartments.remove(apartment);
    }

    public Apartment getApartmentByNumber(int number) {
        return apartments.parallelStream().filter(e -> e.getNumber() == number).findFirst().orElse(null);
    }

    public int calculatePopulation() {
        return apartments.stream().mapToInt(Apartment::getResidentsNumber).sum();
    }

    public int calculateFloors() {
        return apartments.isEmpty() ? 0 : apartments.last().getFloor();
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
        return "Дом " + number;
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
