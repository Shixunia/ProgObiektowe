package Swiat.Zwierzeta;

import java.util.List;
import java.util.Random;

import Swiat.Organizm;
import Swiat.Zwierze;

public class Antylopa extends Zwierze {
    public Antylopa() {
        super(4, 4, "A");
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
                if (actualPos[1] + 2 < 10) {
                    tmpPos[1] = actualPos[1] + 2;
                }
                break;
            case 2:
                if (actualPos[1] - 2 >= 0) {
                    tmpPos[1] = actualPos[1] - 2;
                }
                break;
            case 3:
                if (actualPos[0] - 2 >= 0) {
                    tmpPos[0] = actualPos[0] - 2;
                }
                break;
            case 4:
                if (actualPos[0] + 2 < 10) {
                    tmpPos[0] = actualPos[0] + 2;
                }
                break;
        }
        SetPosition(tmpPos);
    }

    @Override
    public boolean IsEscape() {
        Random rand = new Random();
        int e = rand.nextInt(2) + 1;
        if (e == 1) {
            return true;
        } else
            return false;
    }
}
