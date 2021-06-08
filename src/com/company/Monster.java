package com.company;

import java.util.Random;

public class Monster extends Enemy {

    int visionRange;
    NumericGenerator numericGenerator;

    public Monster(char tile, String name, int attackPoints, int defensePoints, Health health, int experienceValue, int visionRange) {
        super(tile, name, attackPoints, defensePoints, health, experienceValue);
        this.visionRange = visionRange;
        numericGenerator = new RandomGenerator();
    }

    @Override
    public char move(Player player){
        Position pp = player.position;
        if(position.range(pp) < visionRange){
            int dx = position.x - pp.x;
            int dy = position.y - pp.y;
            if(Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0)
                    return 'w';
                else return 's';
            }
            else if(dy > 0)
                return 'a';
            else return 'd';
        }
        return numericGenerator.movment();
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
