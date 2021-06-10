package oop.hw3.tiles;

import oop.hw3.Position;
import oop.hw3.RandomGenerator;
import oop.hw3.resources.Cooldown;
import oop.hw3.resources.Health;

import java.util.List;
import java.util.stream.Collectors;

public class Warrior extends Player {

    private Cooldown cooldown;
    private final int HEALTH_ADDITION = 10;
    private final String SPECIAL_ABILITY_NAME = "Avenger’s Shield";


    public Warrior(Position position, String name, int attackPoints, int defensePoints, Health health, int cooldown) {
        super(position,name, attackPoints, defensePoints, health);
        this.cooldown = new Cooldown(cooldown);
    }


    @Override
    public void castSpecialAbility(List<Enemy> enemies) {
        enemies = enemies.stream().filter(e -> e.getPosition().range(position) < 3).collect(Collectors.toList());
        if(enemies.size()==0) {
            messageCallBack.send(String.format("no enemy is in %s area", getName()));
            return;
        }
        Enemy e = enemies.get(RandomGenerator.getInstance().range(enemies.size()));
        if(!cooldown.onAbilityCast()) {
            messageCallBack.send(String.format("%s doesnt have enough energy for %s\n", getName(), SPECIAL_ABILITY_NAME));
            return;
        }
        health.addHealth(HEALTH_ADDITION * defensePoints);
        messageCallBack.send(String.format("%s casted %s on %s.\n%s\n%s", getName(), SPECIAL_ABILITY_NAME, e.getName(), describe(), e.describe()));
        int defence = e.defend();
        int damageDone = Math.max((int) (health.getPoolbar() * 0.1) - defence, 0);
        e.health.reduceAmount(damageDone);
        messageCallBack.send(String.format("%s rolled %d defense points", e.getName(), defence));
        messageCallBack.send(String.format("%s dealt %d damage to %s.", getName(), damageDone, e.getName()));
        if (!e.isAlive())
            onKill(e);
    }

    @Override
    protected void onLevelUp() {

        int healthIncreased=health.getPoolbar();
        int attackIncreased=attackPoints;
        int defenceIncreased=defensePoints;
        super.onLevelUp();
        cooldown.onLevelUp();
        health.onLevelUp(level, 5);
        setAttackPoints(attackPoints+(level*2));
        setDefensePoints(defensePoints+level);


        healthIncreased=health.getPoolbar()-healthIncreased;
        attackIncreased=attackPoints-attackIncreased;
        defenceIncreased=defensePoints-defenceIncreased;
        messageCallBack.send(String.format("%s reached level %d : +%d Health, +%d Attack, +%d Defence", getName(), level,healthIncreased,attackIncreased,defenceIncreased));
    }

    @Override
    public void onTick() {
        cooldown.onTick();
    }

    public String describe() {
        return String.format("%s\t\t Cool down: %d/%d", super.describe(), cooldown.getRemainingCooldown(),cooldown.getAbilityCooldown());
    }





}
