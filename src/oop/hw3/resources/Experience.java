package oop.hw3.resources;

public class Experience extends Resource{

    public Experience(int poolbar, int amount) {
        super(poolbar, amount);
    }

    public void onLevelUp(int level) {
        poolbar = level * 50;
        amount = 0;
    }

    public void onKill(int exp){
        amount+= exp;
    }
}
