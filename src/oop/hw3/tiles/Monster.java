package oop.hw3.tiles;

import oop.hw3.NumericGenerator;
import oop.hw3.Position;
import oop.hw3.RandomGenerator;
import oop.hw3.resources.Health;

public class Monster extends Enemy {

    int visionRange;
    NumericGenerator numericGenerator;

    public Monster(char tile, String name, int attackPoints, int defensePoints, Health health, int experienceValue, int visionRange) {
        super(tile, name, attackPoints, defensePoints, health, experienceValue);
        this.visionRange = visionRange;
        numericGenerator = new RandomGenerator();
    }

    @Override
    public char onTick(Player player){
        Position pp = player.position;
        if(position.range(pp) < visionRange){
            int dx = position.getX() - pp.getX();
            int dy = position.getY() - pp.getY();
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

//    @Override
//    protected int defend() {
//        return 0;
//    }
//
//    @Override
//    protected int attack() {
//        return 1000;
//    }

//    @Override
//    public void visit(Player p) {
//
//    }

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


}
