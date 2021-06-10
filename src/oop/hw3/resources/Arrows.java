package oop.hw3.resources;

public class Arrows {
    private int arrowsCount;

    public Arrows(int arrowsCount){
        this.arrowsCount = arrowsCount;
    }

    public boolean onAbilityCast(){
        if(arrowsCount == 0)
            return false;
        arrowsCount--;
        return true;
    }

    public void onLevelUp(int level) {
        arrowsCount+= 10*level;
    }

    public void onTick(int level){
        arrowsCount+=level;
    }
}
