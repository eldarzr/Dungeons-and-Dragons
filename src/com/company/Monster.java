package com.company;

public class Monster extends Enemy {

    int visionRange;

    public Monster(char tile, String name, int attackPoints, int defensePoints, Health health, int experienceValue, int visionRange) {
        super(tile, name, attackPoints, defensePoints, health, experienceValue);
        this.visionRange = visionRange;
    }

    public void move(){

    }

    @Override
    protected int defend() {
        return 0;
    }

    @Override
    protected int attack() {
        return 0;
    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void visit(Enemy e) {

    }

    @Override
    public void Combat() {

    }

    @Override
    protected void onKill(Enemy e) {

    }


    @Override
    public void processStep() {

    }

    @Override
    public void onDeath() {

    }

    @Override
    public void accept(Unit unit) {

    }
}
