package oop.hw3.tiles;

import oop.hw3.Position;
import oop.hw3.RandomGenerator;
import oop.hw3.resources.Cooldown;
import oop.hw3.resources.Health;

import java.util.List;
import java.util.stream.Collectors;

public class Warrior extends Player {

    private Cooldown cooldown;

    public Warrior(Position position, String name, int attackPoints, int defensePoints, Health health, int cooldown) {
        super(position,name, attackPoints, defensePoints, health);
        this.cooldown = new Cooldown(cooldown);
    }

//    @Override
//    protected int defend() {
//
//        RandomGenerator rnd = RandomGenerator.getInstance();
//        return rnd.combat(defensePoints);
//    }
//
//    @Override
//    protected int attack() {
//        RandomGenerator rnd = RandomGenerator.getInstance();
//        return rnd.combat(attackPoints);
//    }

    @Override
    public void visit(Player p) {

    }

    @Override
    public void castSpecialAbility(List<Enemy> enemies) {
        enemies = enemies.stream().filter(e -> e.getPosition().range(position) < 3).collect(Collectors.toList());
        if(enemies.size()==0) {
            messageCallBack.send(String.format("no enemy is in %s area", getName()));
            return;
        }
        Enemy e = enemies.get(RandomGenerator.getInstance().combat(enemies.size()));
        if(!cooldown.onAbilityCast())
            messageCallBack.send(String.format("%s doesnt have enought cooldown\n", getName()));
        else {
            health.setAmount(10 * defensePoints);
            messageCallBack.send(String.format("%s casted special ability on %s.\n%s\n%s", getName(), e.getName(), describe(), e.describe()));
            int damageDone = Math.max((int)(health.getPoolbar()*0.1) - e.defend(), 0);
            e.health.reduceAmount(damageDone);
            messageCallBack.send(String.format("%s dealt %d damage to %s.", getName(), damageDone, e.getName()));
            if(!e.isAlive())
            {
                onKill(e);
            }
        }
    }

    @Override
    protected void onLevelUp() {
        level++;
        cooldown.onLevelUp();
        health.onLevelUp(level, 5);
        setAttackPoints(attackPoints+(level*2));
        setDefensePoints(defensePoints+level);
    }

    @Override
    public void onTick() {
        cooldown.onTick();
    }

    //    @Override
//    public void accept(Unit unit) {
//   //     unit.visit(this);
//
//    }
}
