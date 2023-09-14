package Swiat;

import java.util.ArrayList;
import java.util.List;

public class Roslina extends Organizm {
    public Roslina(int s, int i, String name) {
        super(s, i, name);
    }

    @Override
    public List<String> PlantAction() {
        List<String> t = new ArrayList<>();
        t.add("0");
        t.add("null");
        return t;
    }

    @Override
    public boolean IsAnimal() {
        return false;
    }

    @Override
    public boolean ExtraBreeding() {
        return false;
    }
}
