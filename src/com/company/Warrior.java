package com.company;

public class Warrior extends Player {

    public Warrior(Position position, String name, int attackPoints, int defensePoints, Health health) {
        super(position,name, attackPoints, defensePoints, health);
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
    public void accept(Unit unit) {
   //     unit.visit(this);

    }
}
