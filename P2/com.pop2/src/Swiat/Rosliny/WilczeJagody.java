package Swiat.Rosliny;

import java.util.ArrayList;
import java.util.List;

import Swiat.Roslina;

public class WilczeJagody extends Roslina {
    public WilczeJagody() {
        super(0, 0, "J");
    }

    @Override
    public List<String> PlantAction() {
        List<String> t = new ArrayList<>();
        t.add("2");
        t.add("[LOG] Wilcza jagoda zostala zjedzona przez zwierze %z i w konsekwencji umarlo");
        return t;
    }
}
