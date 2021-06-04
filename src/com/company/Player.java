package com.company;

public class Player extends Unit {

    protected int level;
    protected Experience exp;

    public Player(char tile, String name, int attackPoints, int defensePoints, Health health) {
        super(tile, name, attackPoints, defensePoints, health);
        this.level=1;
        this.exp=new Experience(50,0);
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
}
