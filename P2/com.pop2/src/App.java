import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.reflect.Constructor;

import javax.swing.*;
import Swiat.*;
import Swiat.Rosliny.*;
import Swiat.Zwierzeta.*;

public class App extends JFrame implements KeyListener, ActionListener {
    private static Organizm Human;
    private static Swiat Game;
    private static Component[][] ButtonsC = new Component[10][10];
    private static int[] tmpTo = { 0, 0 };

    @Override
    final public void keyPressed(KeyEvent e) {
    }

    @Override
    final public void keyReleased(KeyEvent e) {
        Game.PlayerMove(e.getKeyCode(), Human);
    }

    @Override
    final public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Button t = (Button) event.getSource();
        int[] tmpPos = { 0, 0 };
        for (Component[] b1 : ButtonsC) {
            for (Component b2 : b1) {
                if (tmpPos[1] > 9)
                    tmpPos[1] = 0;
                if (b2.getLocation().x == t.getLocation().x && b2.getLocation().y == t.getLocation().y) {
                    tmpTo[0] = tmpPos[0];
                    tmpTo[1] = tmpPos[1];
                }
                tmpPos[1]++;
            }
            tmpPos[0]++;
        }
        JFrame newFrame = new JFrame();
        JPanel panel = new JPanel();
        newFrame.setSize(300, 200);
        String[] Animals = { "Antylopa", "Lis", "Owca", "Wilk", "Zolw" };
        JComboBox AnimalsList = new JComboBox(Animals);
        AnimalsList.addActionListener(new ComboBoxListener());
        panel.add(AnimalsList);
        newFrame.setLocationRelativeTo(null); // center
        newFrame.getContentPane().add(panel);
        newFrame.setVisible(true);
    }

    public static void SetHumanObject(Organizm h) {
        Human = h;
    }

    public static void AddAnimal(String t) {
        try {
            Class<?> clss = Class.forName("Swiat.Zwierzeta." + t);
            Constructor<?> childt = clss.getConstructor();
            Organizm animal = (Organizm) childt.newInstance();
            Swiat.AddOrganizm(animal, tmpTo);
            // App.CreateMainButtons();
        } catch (Exception er) {
            System.out.println(er.getStackTrace());
        }
    }

    public void CreateMainButtons() {
        getContentPane().removeAll();
        int[] tmpPos = { 0, 0 };
        for (Button[] b1 : Game.GenerateWorld()) {
            for (Button b2 : b1) {
                if (tmpPos[1] > 9)
                    tmpPos[1] = 0;
                ButtonsC[tmpPos[0]][tmpPos[1]] = add(b2);
                if (b2.getLabel() == " ")
                    b2.addActionListener(this);
                tmpPos[1]++;
            }
            tmpPos[0]++;
        }
        Button k = new Button("Kolejna tura");
        add(k);
        k.addActionListener(e -> CreateMainButtons());
        k = new Button("Zapis do pliku");
        add(k);
        k.addActionListener(e -> SaveGameController.SaveToFile(Game, App.Human));
        k = new Button("Wczytaj z pliku");
        add(k);
        k.addActionListener(e -> SaveGameController.RestoreFromSave(Game, App.Human));
        k = null;
        revalidate();
        repaint();
    }

    public App() {
        this.Game = new Swiat();
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    public static void main(String[] args) throws IOException {
        App app = new App();
        Human = new Czlowiek();
        Swiat.AddOrganizm(Human);
        Swiat.AddOrganizm(new Wilk());
        Swiat.AddOrganizm(new Wilk());
        Swiat.AddOrganizm(new Zolw());
        Swiat.AddOrganizm(new Zolw());
        Swiat.AddOrganizm(new Owca());
        Swiat.AddOrganizm(new Owca());
        Swiat.AddOrganizm(new Lis());
        Swiat.AddOrganizm(new Lis());
        Swiat.AddOrganizm(new Antylopa());
        Swiat.AddOrganizm(new Antylopa());
        Swiat.AddOrganizm(new WilczeJagody());
        Swiat.AddOrganizm(new Trawa());
        Swiat.AddOrganizm(new Trawa());
        Swiat.AddOrganizm(new Trawa());
        Swiat.AddOrganizm(new Mlecz());
        Swiat.AddOrganizm(new Guarana());
        Swiat.AddOrganizm(new BarszczSosnowskiego());
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                for (Button[] b1 : app.Game.GenerateWorld()) {
                    for (Button b2 : b1) {
                        app.add(b2);
                    }
                }
                Button t = new Button("Kolejna tura");
                app.add(t);
                t.addActionListener(e -> app.CreateMainButtons());
                Button k = new Button("Zapis do pliku");
                app.add(k);
                k.addActionListener(e -> SaveGameController.SaveToFile(Game, App.Human));
                k = new Button("Wczytaj z pliku");
                app.add(k);
                k.addActionListener(e -> SaveGameController.RestoreFromSave(Game, App.Human));
                app.setLayout(new GridLayout(11, 10)); // tekst
                app.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                app.setSize(800, 800);
                app.setLocationRelativeTo(null); // center
                app.setVisible(true);
            }
        });
    }
}