package oop.hw3.resources;

public class Mana extends Resource{
    private int manaCost;

    public Mana(int poolbar, int manaCost) {
        super(poolbar, poolbar/4);
        this.manaCost = manaCost;
    }

    @Override
    public void onLevelUp(int level, int inc) {
        poolbar=poolbar+(inc*level);
        amount=Math.min((int)amount+poolbar/4,poolbar);

    }

    public void onLevelUp(int level) {
        poolbar=poolbar+(25*level);
        amount=Math.min(amount+poolbar/4,poolbar);
    }
    public void onTick(int level) {
        amount=Math.min(poolbar, amount + level);
    }

    public boolean onAbilityCast() {
        if(amount<manaCost)
            return false;
        else{
            amount=amount-manaCost;
            return true;
        }
    }
}
