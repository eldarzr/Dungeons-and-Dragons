package com.company;

public abstract class Unit extends Tile{
    protected MessageCallBack messageCallBack;
    protected String name;
    protected int attackPoints;
    protected int defensePoints;
    protected Health health;

   public Unit(char tile,String name, int attackPoints, int defensePoints ,Health health) {
        super(tile);
        this.name = name;
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        this.health=health;
    }

    public Health getHealth() {
        return health;
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
       int damageDone=Math.max(attack() - u.defend(),0);
       u.health.reduceAmount(damageDone);
        messageCallBack.send(String.format("%s dealt %d damage to %s." ,getName(),damageDone,u.getName()));

    }

    protected abstract int defend();

    protected abstract int attack();

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

    private void swapPosition(Empty empty) {
    }

    public abstract void Combat();
    protected abstract void onKill(Enemy e);
    public abstract void processStep();
    public abstract void onDeath();

    public String describe() {
        return String.format("%s\t\tHealth: %s\t\tAttack: %d\t\tDefense: %d", getName(), getHealth(), getAttackPoints(), getDefensePoints());
    }
}
