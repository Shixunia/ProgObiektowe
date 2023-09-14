package Swiat.Zwierzeta;

import java.util.List;
import java.util.Random;

import Swiat.Organizm;
import Swiat.Zwierze;

public class Zolw extends Zwierze {
    public Zolw() {
        super(2, 1, "Z");
    }

    @Override
    public void Action(List<Organizm> s) {
        if (IsAction()) {
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
                        tmpPos[1] = actualPos[0] + 1;
                    }
                    break;
            }
            SetPosition(tmpPos);
        }
    }

    final private boolean IsAction() {
        Random r = new Random();
        int p = r.nextInt(3);
        if (p == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean IsColison(int sEnemy) {
        if (sEnemy < 5) {
            return false;
        } else {
            return true;
        }
    }
}
