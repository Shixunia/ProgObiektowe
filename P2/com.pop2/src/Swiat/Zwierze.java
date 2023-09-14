package Swiat;

import java.util.List;
import java.util.Random;

public class Zwierze extends Organizm {
    public Zwierze(int s, int i, String name) {
        super(s, i, name);
    }

    @Override
    public void Action(List<Organizm> s) {
        Random rand = new Random();
        int p = rand.nextInt(4) + 1;
        int actualPos[] = GetPosition();
        int tmpPos[] = { 0, 0 };
        tmpPos[0] = actualPos[0];
        tmpPos[1] = actualPos[1];
        switch (p) {
            case 1:
                if (actualPos[1] + 1 < 10) {
                    tmpPos[1] = actualPos[1] + 1;
                }
                break;
            case 2:
                if (actualPos[1] - 1 >= 0) {
                    tmpPos[1] = actualPos[1] - 1;
                }
                break;
            case 3:
                if (actualPos[0] - 1 >= 0) {
                    tmpPos[0] = actualPos[0] - 1;
                }
                break;
            case 4:
                if (actualPos[0] + 1 < 10) {
                    tmpPos[0] = actualPos[0] + 1;
                }
                break;
        }
        SetPosition(tmpPos);
    }

}
