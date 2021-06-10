package oop.hw3.resources;

public class Experience extends Resource{

    public Experience(int poolbar, int amount) {
        super(poolbar, amount);
    }


    public void onLevelUp(int level, int inc) {
        amount = amount-poolbar;
        poolbar = level * inc;
    }

    public void onKill(int exp){
        amount+= exp;
    }
}
