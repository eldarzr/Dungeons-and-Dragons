package oop.hw3.resources;

public class Energy {

    private int cost;
    private final int totalEnergy = 100;
    private int currEnergy;

    public Energy(int cost){
        this.cost = cost;
        currEnergy = totalEnergy;
    }

    public boolean onAbilityCast(){
        if(currEnergy<cost)
            return false;
        currEnergy-= cost;
        return true;
    }

    public void onLevelUp() {
        currEnergy = totalEnergy;
    }

    public void onTick(){
        currEnergy = Math.min(currEnergy+10, 100);
    }
}
