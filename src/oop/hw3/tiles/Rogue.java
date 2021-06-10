package oop.hw3.tiles;

import oop.hw3.Position;
import oop.hw3.RandomGenerator;
import oop.hw3.resources.Energy;
import oop.hw3.resources.Health;

import java.util.List;
import java.util.stream.Collectors;

public class Rogue extends Player {

    private Energy energy;
    private final int ATTACK_LEVEL_UP = 3;
    private final String SPECIAL_ABILITY_NAME = "Fan of Knives";
    private final int SPECIAL_ABILITY_RANGE = 2;


    public Rogue(Position position, String name, int attackPoints, int defensePoints, Health health, int cost) {
        super(position,name, attackPoints, defensePoints, health);
        energy = new Energy(cost);
    }

    @Override
    public void visit(Player p) {

    }

    @Override
    protected void onLevelUp() {
        super.onLevelUp();
        energy.onLevelUp();
        setAttackPoints(attackPoints+(level*ATTACK_LEVEL_UP));
    }

    @Override
    public void castSpecialAbility(List<Enemy> enemies) {
        enemies = enemies.stream().filter(e -> e.getPosition().range(position) < SPECIAL_ABILITY_RANGE).collect(Collectors.toList());
        if(enemies.size()==0) {
            messageCallBack.send(String.format("no enemy is in %s area", getName()));
            return;
        }
        if(!energy.onAbilityCast()) {
            messageCallBack.send(String.format("%s doesnt have enough energy for %s\n", getName(), SPECIAL_ABILITY_NAME));
            return;
        }
        for (Enemy e: enemies) {
            messageCallBack.send(String.format("%s casted %s on %s.\n%s\n%s", getName(), SPECIAL_ABILITY_NAME, e.getName(), describe(), e.describe()));
            int enemyDefense = e.defend();
            int damageDone = Math.max(attackPoints - enemyDefense, 0);
            e.health.reduceAmount(damageDone);
            messageCallBack.send(String.format("%s rolled %d defense points.", e.getName(), enemyDefense));
            messageCallBack.send(String.format("%s dealt %d damage to %s.", getName(), damageDone, e.getName()));
            if (!e.isAlive())
                onKill(e);
        }
    }

    @Override
    public void onTick() {
        energy.onTick();
    }
}
