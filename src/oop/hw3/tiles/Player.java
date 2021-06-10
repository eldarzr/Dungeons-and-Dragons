package oop.hw3.tiles;

import oop.hw3.Position;
import oop.hw3.ioSystem.UserInput;
import oop.hw3.resources.Experience;
import oop.hw3.resources.Health;

public abstract class Player extends Unit {
    public static final char playerTile = '@';
    protected int level;
    protected Experience exp;
    UserInput userInput;


    public Player(Position position, String name, int attackPoints, int defensePoints, Health health) {
        super(playerTile, name, attackPoints, defensePoints, health);
        this.level=1;
        this.exp=new Experience(50,0);
        userInput= new UserInput();
        initialize(position);
    }

     protected void onLevelUp(){
        level++;
        health.onLevelUp(level,10);
        exp.onLevelUp(level, 50);
        setAttackPoints(attackPoints + 4*level);
        setDefensePoints(defensePoints + level);

    }



    public void visit(Enemy e){
        super.battle(e);
        if(!e.isAlive())
        {
            onKill(e);
        }
    }
    public void visit(Player player){}



    protected void onKill(Enemy enemy) {
        exp.onKill(enemy.getExperienceValue());
        messageCallBack.send(String.format("%s gained %s experience.", getName(),enemy.getExperienceValue()));
        while(exp.getAmount()>= exp.getPoolbar()) {
            onLevelUp();
        }

        enemy.onDeath();
    }



    @Override
    public void onDeath() {
        character = 'X';
        messageCallBack.send("Game Over");
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String describe() {
        return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), getLevel(), exp.getAmount(), exp.getPoolbar());
    }

    @Override
    public void accept(Unit unit) {
        unit.visit(this);
    }
    public abstract void onTick();

}
