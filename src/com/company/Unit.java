package com.company;

public abstract class Unit extends Tile{
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
    protected Health health;

   public Unit(char tile,String name, int attackPoints, int defensePoints ,Health health) {
        super(tile);
        this.name = name;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.health=health;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }
    public abstract void Combat();
    public abstract void onKill();
    public abstract void processStep();
    public abstract void onDeath();

    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttackPoints(), getDefensePoints());
    }
}
