package oop.hw3.tiles;

import oop.hw3.Position;
import oop.hw3.resources.Arrows;
import oop.hw3.resources.Energy;
import oop.hw3.resources.Health;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Hunter extends Player{
    private Arrows arrows;
    private final int ATTACK_LEVEL_UP = 3;
    private final String SPECIAL_ABILITY_NAME = "Shoot";
    private int SPECIAL_ABILITY_RANGE ;
    private int ticksCount;
    private final int TICK_COUNTER = 10;
    private final int ATTACK_LEVELUP = 2;
    private final int DEFENCE_LEVELUP = 1;


    public Hunter(Position position, String name, int attackPoints, int defensePoints, Health health, int range) {
        super(position,name, attackPoints, defensePoints, health);
        SPECIAL_ABILITY_RANGE = range;
        arrows = new Arrows(10*level);
        ticksCount = 0;
    }

    @Override
    public void visit(Player p) {

    }

    @Override
    protected void onLevelUp() {
        super.onLevelUp();
        arrows.onLevelUp(level);
        setAttackPoints(attackPoints+(level*ATTACK_LEVEL_UP));
        setDefensePoints(defensePoints+(level*DEFENCE_LEVELUP));
    }

    @Override
    public void castSpecialAbility(List<Enemy> enemies) {

        enemies = enemies.stream().filter(e -> e.getPosition().range(position) < SPECIAL_ABILITY_RANGE).collect(Collectors.toList());

        if(enemies.size()==0) {
            messageCallBack.send(String.format("no enemy is in %s area", getName()));
            return;
        }
        Enemy e = enemies.get(0);
        double range = e.position.range(position);
        for (Enemy enemy: enemies) {
            if(enemy.getPosition().range(position) <= range){
                e = enemy;
                range = enemy.getPosition().range(position);
            }
        }

        if(!arrows.onAbilityCast()) {
            messageCallBack.send(String.format("%s doesnt have enough arrows for %s\n", getName(), SPECIAL_ABILITY_NAME));
            return;
        }
        messageCallBack.send(String.format("%s casted %s on %s.\n%s\n%s", getName(), SPECIAL_ABILITY_NAME, e.getName(), describe(), e.describe()));
        int enemyDefense = e.defend();
        int damageDone = Math.max(attackPoints - enemyDefense, 0);
        e.health.reduceAmount(damageDone);
        messageCallBack.send(String.format("%s rolled %d defense points.", e.getName(), enemyDefense));
        messageCallBack.send(String.format("%s dealt %d damage to %s.", getName(), damageDone, e.getName()));
        if (!e.isAlive())
            onKill(e);
    }

    @Override
    public void onTick() {
        if (ticksCount == TICK_COUNTER) {
            arrows.onTick(level);
            arrows.onTick(level);
        }
        else    ticksCount=0;
    }
}
