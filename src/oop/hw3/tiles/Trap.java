package oop.hw3.tiles;

import oop.hw3.resources.Health;

public class Trap extends Enemy {
    int visibilityTime;
    int inVisibilityTime;
    int ticksCount;
    boolean isVisible;
    char originalTile;

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
        if (position.range(player.position)<2)
            battle(player);

//        if (ticksCount==visibilityTime && isVisible )
//        {
//            setCharacter('.');
//            ticksCount=0;
//            isVisible=false;
//        }
//       else if (ticksCount==inVisibilityTime && !isVisible)
//        {
//            setCharacter(originalTile);
//            ticksCount=0;
//            isVisible=true;
//        }
//       else
//           ticksCount++;

        return 'q';
    }

    private void changeVisbility() {
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
