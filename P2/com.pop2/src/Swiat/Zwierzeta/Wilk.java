package Swiat.Zwierzeta;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import Swiat.Organizm;
import Swiat.Zwierze;

public class Wilk extends Zwierze {
    public Wilk() {
        super(9, 5, "W");
    }

    public boolean Special__IsSafe(List<Organizm> s, int[] tmp) {
        for (ListIterator<Organizm> w = s.listIterator(); w.hasNext();) {
            Organizm k = w.next();
            if (k.getClass().getName() != this.getClass().getName() && k.GetPosition() == tmp)
                return true;
            if (k.GetPosition() == tmp && k.GetStrength() > this.GetStrength() && k != this) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void Action(List<Organizm> s) {
        Random rand = new Random();
        int actualPos[] = GetPosition();
        int tmpPos[] = { 0, 0 };
        tmpPos[0] = actualPos[0];
        tmpPos[1] = actualPos[1];
        do {
            int p = rand.nextInt(4) + 1;
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
        } while (this.Special__IsSafe(s, tmpPos));
        SetPosition(tmpPos);

    }
}
