package oop.hw3.tiles;

import oop.hw3.MessageCallBack;
import oop.hw3.Position;
import oop.hw3.RandomGenerator;
import oop.hw3.resources.Health;

import java.util.List;

public abstract class Unit extends Tile{
    protected MessageCallBack messageCallBack;
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
    protected Health health;
    //protected boolean alive;

   public Unit(char tile,String name, int attackPoints, int defensePoints ,Health health) {
        super(tile);
        this.name = name;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.health=health;
        //this.alive=true;
    }

    public Health getHealth() {
        return health;
    }

    public boolean isAlive(){
       return health.getAmount() != 0;
    }

    public void setMessageCallBack(MessageCallBack messageCallBack){
       this.messageCallBack = messageCallBack;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    protected void battle(Unit u){
       messageCallBack.send(String.format("%s engaged in combat with %s.\n%s\n%s" ,getName(),u.getName(),describe(),u.describe()));
       int attack=attack();
       int defence = u.defend();
        messageCallBack.send(String.format("%s rolled %d attack points" ,getName(),attack));
        messageCallBack.send(String.format("%s rolled %d defense points" ,u.getName(),defence));
        int damageDone=Math.max(attack-defence,0);
       u.health.reduceAmount(damageDone);
       messageCallBack.send(String.format("%s dealt %d damage to %s." ,getName(),damageDone,u.getName()));

    }

    //protected abstract int defend();

    public int defend(){
        return RandomGenerator.getInstance().combat(defensePoints);
    }
    public int attack(){
        return RandomGenerator.getInstance().combat(attackPoints);
    }

    //protected abstract int attack();

    public void interact(Tile tile)
    {
        tile.accept(this);
    }
    public void visit(Empty empty){
       swapPosition(empty);
    }
    public void visit(Wall wall){
    }
    public abstract void visit(Player p);
    public abstract void visit(Enemy e);

    protected void swapPosition(Empty empty) {
        Position pos = empty.getPosition();
        empty.position = position;
        position=pos;
    }

    public abstract void Combat();
    protected abstract void onKill(Enemy e);
    public abstract void processStep();
    public abstract void onDeath();

    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttackPoints(), getDefensePoints());

    }

    public abstract void castSpecialAbility(List<Enemy> enemy);

    public abstract void onTick();
}
