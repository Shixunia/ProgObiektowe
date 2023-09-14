import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.Button;

import Swiat.Organizm;
import Swiat.Swiat;

public class SaveGameController {
    static void RestoreFromSave(Swiat Game, Organizm Human) {
        try (FileInputStream fis = new FileInputStream("Organizmy.dat");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Game.SetOrganizmy((java.util.List<Organizm>) ois.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream("Board.dat");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Game.SetBoard((String[][]) ois.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream("Buttons.dat");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Game.SetButtons((Button[][]) ois.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream("ToBreeding.dat");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Game.SetToBreeding((java.util.List<Organizm>) ois.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try (FileInputStream fis = new FileInputStream("PlantsToMultiple.dat");
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            Game.SetPlantsToMultiple((java.util.List<Organizm>) ois.readObject());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        App.SetHumanObject(Game.GetHumanObject());
        Game.GenerateWorld();
        System.out.println("[LOG] Gra wczytana. Rozpocznij nowa ture w celu inicjacji wczytanej gry");
    }

    static void SaveToFile(Swiat Game, Organizm Human) {
        try (FileOutputStream fos = new FileOutputStream("Organizmy.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            java.util.List<Organizm> t = Game.GetOrganizmy();
            oos.writeObject(t);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (FileOutputStream fos = new FileOutputStream("Board.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            String[][] t = Game.GetBoard();
            oos.writeObject(t);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (FileOutputStream fos = new FileOutputStream("Buttons.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            Button[][] t = Game.GetButtons();
            oos.writeObject(t);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (FileOutputStream fos = new FileOutputStream("ToBreeding.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            java.util.List<Organizm> t = Game.GetToBreeding();
            oos.writeObject(t);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (FileOutputStream fos = new FileOutputStream("PlantsToMultiple.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            java.util.List<Organizm> t = Game.GetPlantsToMultiple();
            oos.writeObject(t);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (FileOutputStream fos = new FileOutputStream("Human.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(Human);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("[LOG] Gra zapisana!");
    }
}
