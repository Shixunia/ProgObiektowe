package Swiat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Organizm implements Serializable {
    private int Strength;
    private int Initiative;
    protected boolean Alive;
    private int[] Position;
    private String Name;
    Swiat ActualWorld;

    public Organizm(int s, int i, String name) {
        SetStrength(s);
        SetInitiative(i);
        this.Name = name;
        this.Alive = true;
    }

    final public String getName() {
        return Name;
    }

    final public boolean IsAlive() {
        return Alive;
    }

    public void Dead() {
        this.Alive = false;
    }

    final public int GetStrength() {
        return Strength;
    }

    final public void SetStrength(int sila) {
        this.Strength = sila;
    }

    final public int GetInitiative() {
        return Initiative;
    }

    final public void SetInitiative(int inicjatywa) {
        this.Initiative = inicjatywa;
    }

    final public int[] GetPosition() {
        return Position;
    }

    final public void SetPosition(int[] polozenie) {
        this.Position = polozenie;
    }

    public void Action(List<Organizm> s) {

    };

    public boolean IsEscape() {
        return false;
    }

    public boolean IsColison(int s) {
        return true;
    }

    public List<String> PlantAction() {
        List<String> t = new ArrayList<>();
        return t;
    }

    public boolean IsAnimal() {
        return true;
    }

    public boolean ExtraBreeding() {
        return false;
    }

    public boolean GetStatusOfSuperAbility() {
        return false;
    }

    public void EnableSuperAbility() {
    }

    public boolean IsSuperAbilityIsAvailable() {
        return false;
    }

    public void AddRoundToEndSuperAbility() {

    }

    public void ResetCooldownOfSuperAbility() {

    }

    public int RoundsToEndSuperAbility() {
        return -1;
    }

    public void DisableSuperAbility() {

    }
}
