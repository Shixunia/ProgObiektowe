package Swiat.Zwierzeta;

import Swiat.Zwierze;

public class Lis extends Zwierze {
    public Lis() {
        super(3, 7, "L");
    }

    public boolean IsAction(int sEnemy) {
        if (sEnemy > 3) {
            return false;
        } else
            return true;
    }
}
