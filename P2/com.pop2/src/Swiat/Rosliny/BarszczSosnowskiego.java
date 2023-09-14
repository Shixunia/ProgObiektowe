package Swiat.Rosliny;

import java.util.ArrayList;
import java.util.List;

import Swiat.Roslina;

public class BarszczSosnowskiego extends Roslina {
    public BarszczSosnowskiego() {
        super(0, 0, "B");
    }

    @Override
    public List<String> PlantAction() {
        List<String> t = new ArrayList<>();
        t.add("2");
        t.add("[LOG] Barszcz Sosnowskiego zostal zjedzony przez zwierze %z i w konsekwencji umarlo");
        return t;
    }
}
