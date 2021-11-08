import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class House implements Comparable<House>, Serializable {
    private final int number;
    static final long SerialVersionUID = 14256;

    private final SortedSet<Apartment> apartments;

    public House(int number) {
        this.number = number;
        apartments = new TreeSet<>();
    }

    public int getNumber() {
        return number;
    }

    public boolean containsApartment(int number) {
        return apartments.stream().anyMatch(e -> e.getNumber() == number);
    }

    public void addApartmentByConsole() {
        Apartment apartment = Apartment.createByConsole();
        if (containsApartment(apartment.getNumber())) {
            System.out.println(ColorScheme.ANSI_RED + "Квартира с таким номером уже есть в доме!\n" + ColorScheme.ANSI_RESET);
        } else {
            apartments.add(apartment);
            System.out.println(ColorScheme.ANSI_GREEN + "Квартира была добавлена в дом\n" + ColorScheme.ANSI_RESET);
        }
    }

    public Apartment[] getApartments() {
        return apartments.toArray(Apartment[]::new);
    }

    public void removeApartment(Apartment apartment) {
        apartments.remove(apartment);
    }

    public void printInformationAboutHouse() {
        System.out.println(ColorScheme.ANSI_CYAN + "Общая информация о доме " + number + ":" + ColorScheme.ANSI_RESET);
        System.out.printf(Locale.US, "Этажность: %d, кол-во квартир: %d, общая площадь: %.1f м^2, кол-во жителей: %d\n\n",
                calculateFloors(), getApartments().length, calculateFullSquare(), calculatePopulation());
    }

    public void printApartments() {
        System.out.println(ColorScheme.ANSI_CYAN + "Квартиры дома " + number + ":" + ColorScheme.ANSI_RESET);
        apartments.forEach(System.out::println);
    }

    public Apartment getApartmentByNumber(int number) {
        return apartments.stream().filter(e -> e.getNumber() == number).findFirst().orElse(null);
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
