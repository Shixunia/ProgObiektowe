package Swiat.Rosliny;

import java.util.ArrayList;
import java.util.List;

import Swiat.Roslina;

public class Mlecz extends Roslina {
    public Mlecz() {
        super(0, 0, "M");
    }

    @Override
    public List<String> PlantAction() {
        List<String> t = new ArrayList<>();
        t.add("1");
        t.add("[LOG] Mlecz zostal zjedzony przez zwierze %z");
        return t;
    }

    @Override
    public boolean ExtraBreeding() {
        return true;
    }
}
