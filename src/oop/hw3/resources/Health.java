package oop.hw3.resources;

public class Health extends Resource {

    public Health(int poolbar) {
        super(poolbar, poolbar);
    }

    public void onLevelUp(int level, int inc) {
        poolbar = poolbar + (level*inc);
        amount = poolbar;
    }

    public void reduceAmount(int damageDone) {
        amount = amount-damageDone > 0 ? amount-damageDone : 0;
    }

    public void addHealth(int amount) {
        this.amount = Math.min(this.amount + amount, poolbar);
    }

}
