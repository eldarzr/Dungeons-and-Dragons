package com.company;

public abstract class Unit extends Tile{
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
  //  Protected Health h

   public Unit(char tile,String name, int attackPoints, int defensePoints) {
       super(tile);
       this.name = name;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
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
}
