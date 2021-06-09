package oop.hw3.tiles;

import oop.hw3.EnemyDeathCallBack;
import oop.hw3.resources.Health;

public abstract class Enemy extends Unit {

     public int getExperienceValue() {
         return experienceValue;
     }

     private int experienceValue;
    protected EnemyDeathCallBack enemyDeathCallBack;

    public Enemy(char tile, String name, int attackPoints, int defensePoints, Health health, int experienceValue) {
        super(tile, name, attackPoints, defensePoints, health);
        this.experienceValue = experienceValue;
 }

     public void setOnDeathCallBack(EnemyDeathCallBack enemyDeathCallBack){
        this.enemyDeathCallBack = enemyDeathCallBack;
     }

    public abstract char move(Player player);

     @Override
     public void visit(Enemy e) { }

     @Override
     public void visit(Player p) {
         super.battle(p);
         if(!p.isAlive())
             p.onDeath();
     }

     @Override
     public void onDeath() {
         enemyDeathCallBack.call();
     }

     public void accept(Unit unit){
         unit.visit(this);
     }
 }
