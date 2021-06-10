package oop.hw3.tiles;

import oop.hw3.resources.Health;

public class Trap extends Enemy {
    int visibilityTime;
    int inVisibilityTime;
    int ticksCount;
    boolean isVisible;
    char originalTile;
    private final int TRAP_ATTACK_RANGE=2;

    public Trap(char tile, String name, int attackPoints, int defensePoints, Health health, int experienceValue, int visibilityTime, int inVisibilityTime) {
        super(tile, name, attackPoints, defensePoints, health, experienceValue);
        this.visibilityTime = visibilityTime;
        this.inVisibilityTime = inVisibilityTime;
        this.ticksCount = 0;
        this.isVisible = true;
        originalTile=tile;
    }

    @Override
    public char onTick(Player player) {
        isVisible = ticksCount < visibilityTime;
        if (ticksCount == (visibilityTime + inVisibilityTime - 1)){
            ticksCount =0;
        }
        else {
            ticksCount++;

        }
        setCharacter(isVisible ? originalTile : '.');
        if (position.range(player.position)<TRAP_ATTACK_RANGE)
            battle(player);
        return 'q';
    }




}
