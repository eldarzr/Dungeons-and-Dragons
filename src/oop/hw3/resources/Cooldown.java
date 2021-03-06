package oop.hw3.resources;

public class Cooldown {
    private int abilityCooldown;
    private int remainingCooldown;

    public Cooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
        this.remainingCooldown = 0;
    }

    public int getAbilityCooldown() {
        return abilityCooldown;
    }

    public void setAbilityCooldown(int abilityCooldown) {
        this.abilityCooldown = abilityCooldown;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public void setRemainingCooldown(int remainingCooldown) {
        this.remainingCooldown = remainingCooldown;
    }

    public void onTick(){
        if(remainingCooldown > 0)
            remainingCooldown--;
    }

    public boolean onAbilityCast(){
        if(remainingCooldown == 0) {
            remainingCooldown = abilityCooldown;
            return true;
        }
        return false;
    }

    public void onLevelUp(){
        remainingCooldown = 0;
    }
}
