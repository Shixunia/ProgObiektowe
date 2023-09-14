package Swiat;

import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JFrame;

import java.awt.Button;

public class Swiat extends JFrame {
    private static List<Organizm> Organizmy = new ArrayList<>();
    private Button[][] Buttons = new Button[10][10];
    private static String[][] Board = new String[10][10];
    private List<Organizm> ToBreeding = new ArrayList<>();
    private List<Organizm> PlantsToMultiple = new ArrayList<>();
    private boolean init = true;
    private boolean PlayerRegisteredMove = false;
    private Random rand = new Random();

    public String[][] GetBoard() {
        return Swiat.Board;
    }

    public void SetBoard(String[][] b) {
        Swiat.Board = b;
    }

    //

    public List<Organizm> GetToBreeding() {
        return ToBreeding;
    }

    public void SetToBreeding(List<Organizm> t) {
        ToBreeding = t;
    }

    //

    public List<Organizm> GetPlantsToMultiple() {
        return PlantsToMultiple;
    }

    public void SetPlantsToMultiple(List<Organizm> t) {
        PlantsToMultiple = t;
    }

    //

    public List<Organizm> GetOrganizmy() {
        return Swiat.Organizmy;
    }

    public void SetOrganizmy(List<Organizm> t) {
        Swiat.Organizmy = t;
    }

    //

    final public Button[][] GetButtons() {
        return Buttons;
    }

    public void SetButtons(Button[][] b) {
        init = false;
        Buttons = b;
    }

    public Swiat() {
        for (int o = 0; o < 10; o++) {
            for (int k = 0; k < 10; k++) {
                Board[o][k] = " ";
                Button tmp = new Button(Board[o][k]);
                Buttons[o][k] = tmp;
            }
        }
    }

    private void RestoreButton(int[] pos) {
        Board[pos[0]][pos[1]] = " ";
        Buttons[pos[0]][pos[1]] = new Button(" ");
    }

    private void RestoreButton(int[] pos, String s) {
        Board[pos[0]][pos[1]] = s;
        Buttons[pos[0]][pos[1]] = new Button(s);
    }

    private void AssignButton(int[] pos, String name) {
        Board[pos[0]][pos[1]] = name;
        Buttons[pos[0]][pos[1]] = new Button(name);
    }

    private void PlantsMultipleQueue() {
        if (PlantsToMultiple.size() > 0) {
            for (ListIterator<Organizm> Child = PlantsToMultiple.listIterator(); Child.hasNext();) {
                Organizm Child2 = Child.next();
                int[] tmpPos = Child2.GetPosition();
                Board[tmpPos[0]][tmpPos[1]] = Child2.getName();
                Organizmy.add(Child2);
                Child.remove();
            }
        }
    }

    private void BreedingQueue() {
        if (ToBreeding.size() > 0)
            for (ListIterator<Organizm> Child = ToBreeding.listIterator(); Child.hasNext();) {
                Organizm Child2 = Child.next();
                int[] tmpPos = Child2.GetPosition();
                Board[tmpPos[0]][tmpPos[1]] = Child2.getName();
                Organizmy.add(Child2);
                Child.remove();
            }
    }

    public Organizm GetHumanObject() {
        for (ListIterator<Organizm> w = Organizmy.listIterator(); w.hasNext();) {
            Organizm e = w.next();
            if (e.getClass().getName() == "Swiat.Zwierzeta.Czlowiek") {
                return e;
            }
        }
        return null;
    }

    public Button[][] GenerateWorld() {
        this.BreedingQueue();
        this.PlantsMultipleQueue();
        for (int o = 0; o < 10; o++) {
            for (int k = 0; k < 10; k++) {
                Board[o][k] = " ";
                Button tmp = new Button(Board[o][k]);
                Buttons[o][k] = tmp;
            }
        }
        for (ListIterator<Organizm> w = Organizmy.listIterator(); w.hasNext();) {
            Organizm e = w.next();
            if (!init) {
                if (e.getName() != "+" && e.getClass().getName() != "Swiat.Zwierzeta.Czlowiek") {
                    if (e.IsAlive() && e.IsAnimal())
                        e.Action(Organizmy);
                    else if (!e.IsAnimal()) {
                        for (int ep = 0; ep < (e.ExtraBreeding() ? 3 : 1); ep++) {
                            int p = rand.nextInt(25) + 1;
                            if (p >= 14 && p <= 15) {
                                try {
                                    if (this.CheckAround(e.GetPosition())) {
                                        Class<?> clss = Class.forName(e.getClass().getName());
                                        Constructor<?> childt = clss.getConstructor();
                                        Organizm childw = (Organizm) childt.newInstance();
                                        childw.SetPosition(this.FreePosNear(e.GetPosition()));
                                        PlantsToMultiple.add(childw);
                                    }
                                } catch (Exception r) {
                                    System.out.println("[Exception][Swiat::GenerateWorld()] " + r.getMessage());
                                }
                            }
                        }
                    }
                }
            }
            try {
                this.Collision(e);
            } catch (Exception r) {
                System.out.println("[Exception][Swiat::GenerateWorld()] " + r.getMessage());
            }
            if (e.IsAlive())
                this.AssignButton(e.GetPosition(), e.getName());
        }
        if (init)
            this.init = false;
        PlayerRegisteredMove = false;
        /*
         * Organizm Human = GetHumanObject();
         * if (Human.GetStatusOfSuperAbility()) {
         * if (Human.RoundsToEndSuperAbility() == 5) {
         * Human.DisableSuperAbility();
         * System.out.println("[LOG] Super umiejetnosc sie skonczyla!");
         * } else {
         * Human.AddRoundToEndSuperAbility();
         * }
         * } else {
         * if (!Human.IsSuperAbilityIsAvailable()) {
         * Human.ResetCooldownOfSuperAbility();
         * }
         * }
         */
        System.out.println("[GRA] Nowa tura!");
        return Buttons;
    }

    private int[] FreePosNear(int[] pos) {
        Random rand = new Random();
        int actualPos[] = pos;
        int tmpPos[] = { -1, -1 };
        tmpPos[0] = actualPos[0];
        tmpPos[1] = actualPos[1];
        int p = rand.nextInt(4) + 1;
        switch (p) {
            case 1:
                if (actualPos[1] + 1 < 10) {
                    tmpPos[1] = actualPos[1] + 1;
                    break;
                }
            case 2:
                if (actualPos[1] - 1 >= 0) {
                    tmpPos[1] = actualPos[1] - 1;
                    break;
                }
            case 3:
                if (actualPos[0] - 1 >= 0) {
                    tmpPos[0] = actualPos[0] - 1;
                    break;
                }
            case 4:
                if (actualPos[0] + 1 < 10) {
                    tmpPos[0] = actualPos[0] + 1;
                    break;
                }
            case 5:
                if (actualPos[0] + 1 < 10 && actualPos[1] + 1 < 10) {
                    tmpPos[0] = actualPos[0] + 1;
                    tmpPos[1] = actualPos[1] + 1;
                    break;
                }
            case 6:
                if (actualPos[0] - 1 > 0 && actualPos[1] - 1 > 0) {
                    tmpPos[0] = actualPos[0] - 1;
                    tmpPos[1] = actualPos[1] - 1;
                    break;
                }
            case 7:
                if (actualPos[0] + 1 < 10 && actualPos[1] - 1 > 0) {
                    tmpPos[0] = actualPos[0] + 1;
                    tmpPos[1] = actualPos[1] - 1;
                    break;
                }
            case 8:
                if (actualPos[0] - 1 > 0 && actualPos[1] + 1 < 10) {
                    tmpPos[0] = actualPos[0] - 1;
                    tmpPos[1] = actualPos[1] + 1;
                    break;
                }
        }

        return tmpPos;
    }

    private void Collision(Organizm o2) {
        for (ListIterator<Organizm> o3 = Organizmy.listIterator(); o3.hasNext();) {
            Organizm o1 = o3.next();
            if (((o1.GetPosition()[0] == o2.GetPosition()[0])
                    && (o1.GetPosition()[1] == o2.GetPosition()[1]) && (o1 != o2) && (o2.IsAlive() && o1.IsAlive()))) {
                if (o2.IsAnimal() && o1.IsAnimal()) {
                    if (o2.getClass().getName() == o1.getClass().getName()) {
                        try {
                            if (this.CheckAround(o2.GetPosition())) {
                                Class<?> clss = Class.forName(o2.getClass().getName());
                                Constructor<?> childt = clss.getConstructor();
                                Organizm childw = (Organizm) childt.newInstance();
                                childw.SetPosition(this.FreePosNear(o2.GetPosition()));
                                ToBreeding.add(childw);
                            }
                        } catch (Exception r) {
                            System.out.println("[Exception][Swiat::Collision()] " + r.getMessage());
                        }
                    } else if (o1.GetStrength() > o2.GetStrength() && o2.IsColison(o1.GetStrength())
                            && !o2.IsEscape()) {
                        if (o1.getName() == "+" && o1.GetStatusOfSuperAbility()) {
                            System.out.println("[LOG] Czlowiek ma aktywna super umiejetnosc i nie moze zginac");
                        } else {
                            o2.Dead();
                            RestoreButton(o2.GetPosition(), o1.getName());
                            System.out.println(
                                    "[LOG] Zwierze " + o2.getName() + " zostalo zabite przez zwierze " + o1.getName());
                        }
                    } else if (o1.GetStrength() < o2.GetStrength() && o1.IsColison(o2.GetStrength())
                            && !o1.IsEscape()) {
                        if (o1.getName() == "+" && o1.GetStatusOfSuperAbility()) {
                            System.out.println("[LOG] Czlowiek ma aktywna super umiejetnosc i nie moze zginac");
                        } else {
                            o1.Dead();
                            RestoreButton(o1.GetPosition(), o2.getName());
                            System.out.println(
                                    "[LOG] Zwierze " + o1.getName() + " zostalo zabite przez zwierze " + o2.getName());
                        }
                    }
                } else if (o2.IsAnimal() && !o1.IsAnimal()) {
                    List<String> s = o1.PlantAction();
                    // 1 - trawa / mlecz
                    // 2 - wilcze jagody / Barszcz Sosnowskiego
                    // 3 - Guarana
                    switch (Integer.parseInt(s.get(0))) {
                        case 1:
                            o1.Dead();
                            RestoreButton(o1.GetPosition(), o2.getName());
                            System.out.println(s.get(1).replace("%z", o2.getName()));
                            break;
                        case 2:
                            o2.Dead();
                            o1.Dead();
                            RestoreButton(o1.GetPosition(), "");
                            System.out.println(s.get(1).replace("%z", o2.getName()));
                            break;
                        case 3:
                            o1.Dead();
                            o2.SetStrength(o2.GetStrength() + 3);
                            RestoreButton(o1.GetPosition(), o2.getName());
                            System.out
                                    .println(s.get(1).replace("%z", o2.getName()).replace("%s", "" + o2.GetStrength()));
                            break;
                    }
                }
            }
        }
    }

    public static boolean CheckPos(int[] k) {
        if (Board[k[0]][k[1]] == "") {
            return true;
        }
        return false;
    }

    public boolean CheckAround(int[] k) {
        boolean tmp = false;
        try {
            if (Board[k[0] + 1][k[1]] != " " && Board[k[0]][k[1] + 1] != " " && Board[k[0] - 1][k[1]] != " "
                    && Board[k[0]][k[1] - 1] != " " && Board[k[0] + 1][k[1] + 1] != " "
                    && Board[k[0] - 1][k[1] - 1] != " "
                    && Board[k[0] + 1][k[1] - 1] != " " && Board[k[0] - 1][k[1] + 1] != " ") {
                tmp = false;
            } else {
                tmp = true;
            }
        } catch (Exception er) {
        }
        return tmp;
    }

    final public static void AddOrganizm(Organizm t) {
        int[] tempPos = { ThreadLocalRandom.current().nextInt(1, 8 + 1),
                ThreadLocalRandom.current().nextInt(1, 8 + 1) };
        do {
            tempPos[0] = ThreadLocalRandom.current().nextInt(1, 8 + 1);
            tempPos[1] = ThreadLocalRandom.current().nextInt(1, 8 + 1);
        } while (CheckPos(tempPos));
        t.SetPosition(tempPos);
        Organizmy.add(t);
    }

    final public static void AddOrganizm(Organizm t, int[] pos) {
        System.out.println(pos[0] + " " + pos[1]);
        t.SetPosition(pos);
        Organizmy.add(t);
    }

    final public void PlayerMove(int e, Organizm Human) {
        if (!PlayerRegisteredMove) {
            int[] tmpPos = Human.GetPosition();
            this.RestoreButton(tmpPos);
            switch (e) {
                case KeyEvent.VK_DOWN:
                    PlayerRegisteredMove = true;
                    if ((tmpPos[0] + 1) < 10)
                        tmpPos[0] = tmpPos[0] + 1;
                    Human.SetPosition(tmpPos);
                    this.AssignButton(tmpPos, Human.getName());
                    break;
                case KeyEvent.VK_UP:
                    PlayerRegisteredMove = true;
                    if ((tmpPos[0] - 1) >= 0)
                        tmpPos[0] = tmpPos[0] - 1;
                    Human.SetPosition(tmpPos);
                    this.AssignButton(tmpPos, Human.getName());
                    break;
                case KeyEvent.VK_RIGHT:
                    PlayerRegisteredMove = true;
                    if ((tmpPos[1] + 1) < 10)
                        tmpPos[1] = tmpPos[1] + 1;
                    Human.SetPosition(tmpPos);
                    this.AssignButton(tmpPos, Human.getName());
                    break;
                case KeyEvent.VK_LEFT:
                    PlayerRegisteredMove = true;
                    if ((tmpPos[1] - 1) >= 0)
                        tmpPos[1] = tmpPos[1] - 1;
                    Human.SetPosition(tmpPos);
                    this.AssignButton(tmpPos, Human.getName());
                    break;
                case KeyEvent.VK_R:
                    if (!Human.GetStatusOfSuperAbility()) {
                        if (Human.IsSuperAbilityIsAvailable()) {
                            Human.EnableSuperAbility();
                            PlayerRegisteredMove = true;
                        } else {
                            System.out.println("[LOG] Umiejetnosc nie jest jeszcze gotowa!");
                        }
                    } else
                        System.out.println("[LOG] Umiejetnosc jest jeszcze aktywna przez "
                                + (5 - Human.RoundsToEndSuperAbility()) + " rund.");
                    break;
            }
        }
    }

}
