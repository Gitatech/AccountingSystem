package building;

import org.jetbrains.annotations.NotNull;

import java.io.Externalizable;
import java.util.*;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import builder.FlatBilder;

public class Ground  implements Externalizable {
    private List<Flat> flat;
    private int NumberOfFlatsInGround = 0;

    public Ground() {
        flat = new ArrayList<>(0);
        setNumberOfFlatsInGround(0);
    }

    public void addFlat(Flat flat){
        this.flat.add(flat);
    }

    public void setNumberOfFlatsInGround(int numberOfFlatsInGround) {
        NumberOfFlatsInGround = numberOfFlatsInGround;
    }

    public void initPersons() {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < NumberOfFlatsInGround; i++) {
            System.out.println("Enter number of Person in the " + flat.get(i).getNumber() + "flat");
            int k = in.nextInt();
            flat.get(i).setNHuman(k);
        }
    }

    public void initPersonsRandom() {
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < NumberOfFlatsInGround; i++) {
            int k = (int) (Math.random() * 5);
            flat.get(i).setNHuman(k);
        }
    }

    public int getManInGround() {
        int kol = 0;
        for (int i = 0; i < NumberOfFlatsInGround; i++) {
            kol += flat.get(i).getNumberOfHuman();
        }
        return kol;
    }

    public double getGroundArea() { // возвращает площадь этажа
        double kol = 0;
        for (int i = 0; i < NumberOfFlatsInGround; i++) {
            kol += flat.get(i).getSqrt();
        }
        return kol;
    }

    public double getFlatArea(int i) { // возвращает площадь квартиры по её номеру на этаже
        int ground = i % (flat.size());
        return flat.get(ground).getSqrt();
    }

    public int getManInFlat(int i) {
        int gr = i % (flat.size());
        return flat.get(gr).getNumberOfHuman();
    }

    public void setFlatChecker(int k) {
        this.flat.iterator().next().setChecker(0);
    } //для зануление сатической переменной в flat

    public int getFlatsOnGround() {
        return NumberOfFlatsInGround;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(NumberOfFlatsInGround);
        out.writeObject(flat);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        NumberOfFlatsInGround = (int) in.readObject();
        flat = (List<Flat>) in.readObject();

    }
}

