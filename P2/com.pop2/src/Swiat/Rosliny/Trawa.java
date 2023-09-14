package Swiat.Rosliny;

import java.util.ArrayList;
import java.util.List;

import Swiat.Roslina;

public class Trawa extends Roslina {
    public Trawa() {
        super(0, 0, "T");
    }

    @Override
    public List<String> PlantAction() {
        List<String> t = new ArrayList<>();
        t.add("1");
        t.add("[LOG] Trawa zostala zjedzona przez zwierze %z");
        return t;
    }
}
