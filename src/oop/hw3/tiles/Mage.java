package oop.hw3.tiles;

import oop.hw3.Position;
import oop.hw3.RandomGenerator;
import oop.hw3.resources.Health;
import oop.hw3.resources.Mana;

import java.util.List;
import java.util.stream.Collectors;

public class Mage extends Player {
    private Mana mana;
    private int hitsCount;
    private int abilityRange;
    private int spellPower;

    public Mage(Position position, String name, int attackPoints, int defensePoints, Health health, Mana mana, int hitsCount, int abilityRange, int spellPower) {
        super(position, name, attackPoints, defensePoints, health);
        this.mana = mana;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        this.spellPower = spellPower;
    }

    @Override
    public void visit(Player p) {

    }

    protected void onLevelUp() {
        super.onLevelUp();
        mana.onLevelUp(level,25);
        spellPower=spellPower+(10*level);
    }

    @Override
    public void castSpecialAbility(List<Enemy> enemies) {
        int hits=0;
        enemies = enemies.stream().filter(e -> e.getPosition().range(position) < abilityRange).collect(Collectors.toList());
        while(hits<hitsCount && enemies.size()>0) {
            Enemy enemy = enemies.get(RandomGenerator.getInstance().range(enemies.size()));
            if (!mana.onAbilityCast()) {
                messageCallBack.send(String.format("%s doesnt have enough mana\n", getName()));
                  return;
            }
            else {
                messageCallBack.send(String.format("%s cast Blizzard", getName()));
                int defence = enemy.defend();
                messageCallBack.send(String.format("%s rolled %d defense points", enemy.getName(), defence));
                int damageDone = Math.max((int) spellPower - defence, 0);
                enemy.health.reduceAmount(damageDone);
                messageCallBack.send(String.format("%s dealt %d damage to %s.", getName(), damageDone, enemy.getName()));
                if (!enemy.isAlive()) {
                    onKill(enemy);
                    enemies.remove(enemy);
                }
                hits++;
            }
        }

        }


    @Override
    public void onTick() {
        mana.onTick(level);
    }
}
