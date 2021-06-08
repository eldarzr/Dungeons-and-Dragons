package com.company;

 public abstract class Enemy extends Unit {

    private int experienceValue;

    public Enemy(char tile, String name, int attackPoints, int defensePoints, Health health, int experienceValue) {
        super(tile, name, attackPoints, defensePoints, health);
        this.experienceValue = experienceValue;
 }


}
