package com.company;

public class Warrior extends Player {

    public Warrior(Position position, String name, int attackPoints, int defensePoints, Health health) {
        super(position,name, attackPoints, defensePoints, health);
    }

    @Override
    protected int defend() {

        RandomGenerator rnd = RandomGenerator.getInstance();
        return rnd.combat(defensePoints);
    }

    @Override
    protected int attack() {
        RandomGenerator rnd = RandomGenerator.getInstance();
        return rnd.combat(attackPoints);
    }

    @Override
    public void visit(Player p) {

    }


    @Override
    public void accept(Unit unit) {
   //     unit.visit(this);

    }
}
