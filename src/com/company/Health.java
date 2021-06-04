package com.company;

public class Health extends Resource {

    public Health(int poolbar, int amount) {
        super(poolbar, amount);
    }

    public void onLevelUp(int level) {
        poolbar = poolbar + (level*10);
        amount = poolbar;
    }
}
