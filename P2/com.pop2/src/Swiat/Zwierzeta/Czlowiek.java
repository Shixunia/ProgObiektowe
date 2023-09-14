package Swiat.Zwierzeta;

import Swiat.Zwierze;

public class Czlowiek extends Zwierze {
    private int SuperAbility__Rounds = 0;
    private int SuperAbility__Available = 5;
    private boolean SuperAbility__Active = false;
    private static final long serialVersionUID = 6529685098267757690L;

    public Czlowiek() {
        super(5, 4, "+");
    }

    @Override
    public void Dead() {
        if (!SuperAbility__Active) {
            this.Alive = false;
        }
    }

    @Override
    public boolean GetStatusOfSuperAbility() {
        return SuperAbility__Active;
    }

    @Override
    public void EnableSuperAbility() {
        SuperAbility__Active = true;
        SuperAbility__Rounds = 0;
        SuperAbility__Available = 0;
        System.out.println("[LOG] Czlowiek aktywowal super umiejetnosc (jest niesmiertelny)");
    }

    @Override
    public boolean IsSuperAbilityIsAvailable() {
        if (SuperAbility__Available == 5) {
            return true;
        }
        return false;
    }

    @Override
    public void ResetCooldownOfSuperAbility() {
        SuperAbility__Available++;
    }

    @Override
    public void AddRoundToEndSuperAbility() {
        this.SuperAbility__Rounds++;
    }

    @Override
    public int RoundsToEndSuperAbility() {
        return SuperAbility__Rounds;
    }

    @Override
    public void DisableSuperAbility() {
        SuperAbility__Active = false;
        SuperAbility__Rounds = 0;
    }

}
