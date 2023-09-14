package Swiat.Rosliny;

import java.util.ArrayList;
import java.util.List;

import Swiat.Roslina;

public class Guarana extends Roslina {
    public Guarana() {
        super(0, 0, "G");
    }

    @Override
    public List<String> PlantAction() {
        List<String> t = new ArrayList<>();
        t.add("3");
        t.add("[LOG] Guarana zostala zjedzona przez zwierze %z i w konsekwencji sila zwierzecia zostala zwiekszona o 3 (aktualnie %s)");
        return t;
    }
}
