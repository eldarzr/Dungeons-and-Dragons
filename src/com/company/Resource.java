package com.company;

abstract class Resource {
    protected int poolbar;
    protected int amount;

    public Resource(int poolbar, int amount) {
        this.poolbar = poolbar;
        this.amount = amount;
    }

    public int getPoolbar() {
        return poolbar;
    }

    public void setPoolbar(int poolbar) {
        this.poolbar = poolbar;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public abstract void onLevelUp(int level);
}
