package oop.hw3.resources;

public class Health extends Resource {

    public Health(int poolbar) {
        super(poolbar, poolbar);
    }

    public void onLevelUp(int level) {
        poolbar = poolbar + (level*10);
        amount = poolbar;
    }

    public void reduceAmount(int damageDone) {
        amount = amount-damageDone > 0 ? amount-damageDone : 0;
    }

}
