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
    private final String SPECIAL_ABILITY_NAME = "Blizzard";
    private final int MANA_ADDITIONAL =25;
    private final int SPELLPOWER_ADDITIONAL=10;


    public Mage(Position position, String name, int attackPoints, int defensePoints, Health health, Mana mana, int hitsCount, int abilityRange, int spellPower) {
        super(position, name, attackPoints, defensePoints, health);
        this.mana = mana;
        this.hitsCount = hitsCount;
        this.abilityRange = abilityRange;
        this.spellPower = spellPower;
    }


    protected void onLevelUp() {
        int healthIncreased=health.getPoolbar();
        int manaIncreased=mana.getPoolbar();
        int attackIncreased=attackPoints;
        int defenceIncreased=defensePoints;
        super.onLevelUp();
        mana.onLevelUp(level,MANA_ADDITIONAL);
        spellPower=spellPower+(SPELLPOWER_ADDITIONAL*level);
        healthIncreased=health.getPoolbar()-healthIncreased;
        attackIncreased=attackPoints-attackIncreased;
        defenceIncreased=defensePoints-defenceIncreased;
        manaIncreased=mana.getPoolbar()-manaIncreased;
        messageCallBack.send(String.format("%s reached level %d : +%d Health, +%d Mana, +%d Attack, +%d Defence", getName(), level,healthIncreased,manaIncreased,attackIncreased,defenceIncreased));
    }

    @Override
    public void castSpecialAbility(List<Enemy> enemies) {
        int hits=0;
        enemies = enemies.stream().filter(e -> e.getPosition().range(position) < abilityRange).collect(Collectors.toList());
        if ((!mana.onAbilityCast()) && enemies.size()>0) {
            messageCallBack.send(String.format("%s doesnt have enough mana\n", getName()));
            return;
        }
        while(hits<hitsCount && enemies.size()>0) {
            Enemy enemy = enemies.get(RandomGenerator.getInstance().range(enemies.size()));

                messageCallBack.send(String.format("%s cast %s", getName(),SPECIAL_ABILITY_NAME));
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



    public String describe() {
        return String.format("%s\t\tMana: %d/%d , Spell Power:%d", super.describe(), mana.getAmount(), mana.getPoolbar(),spellPower);
    }

    @Override
    public void onTick() {
        mana.onTick(level);
    }
}
