package com.company;

public class Player extends Unit {
    public static final char playerTile = '@';
    protected int level;
    protected Experience exp;

    public Player(String name, int attackPoints, int defensePoints, Health health) {
        super(playerTile, name, attackPoints, defensePoints, health);
        this.level=1;
        this.exp=new Experience(50,0);
    }

    protected void onLevelUp(){
     exp.onLevelUp(level);
     level++;
     health.onLevelUp(level);
     setAttackPoints(attackPoints+level*4);
     setDefensePoints(defensePoints+level);
    }

    // Deals damage to the enemy with ability
    protected void abilityDamage(Enemy e, int abilityDamage) {

    }

    // When the player kills an enemy
    protected void onKill(Enemy e){

    }

    @Override
    public void Combat() {

    }

    @Override
    public void onKill() {

    }

    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String describe() {
        return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), getLevel(), exp.amount, exp.getPoolbar());
    }

}
