import java.io.*;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class AccountingSystem {

    private SortedSet<House> houses;
    private String path;

    public AccountingSystem(String path) {
        this.path = path;
        this.houses = new TreeSet<>();
    }

    public AccountingSystem() {
        this.houses = new TreeSet<>();
    }

    public House getHouseByNumber(int houseNumber) {
        return houses.stream().filter(e -> e.getNumber() == houseNumber).findFirst().orElse(null);
    }

    public void addHouse(int number) {
        houses.add(new House(number));
    }

    public void removeHouse(int number) {
        houses.remove(new House(number));
    }

    public boolean containsHouse(int number) {
        return houses.contains(new House(number));
    }

    public House[] getHouses() {
        return houses.toArray(House[]::new);
    }

    public void printHouses() {
        System.out.println(ColorScheme.ANSI_CYAN + "Дома: " + ColorScheme.ANSI_RESET);
        for (House house : houses) {
            System.out.println("Дом " + house.getNumber());
        }
        System.out.println();
    }

    public void save() {
        if (path != null) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
                out.writeObject(houses.toArray(House[]::new));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load() {
        if (path != null) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
                houses = new TreeSet<>(Set.of((House[]) in.readObject()));
            } catch (ClassNotFoundException ignored) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
