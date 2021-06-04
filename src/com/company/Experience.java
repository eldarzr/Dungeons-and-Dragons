package com.company;

public class Experience extends Resource{

    public Experience(int poolbar, int amount) {
        super(poolbar, amount);
    }

    public void onLevelUp(int level) {
        poolbar = level * 50;
        amount = 0;
    }
}
