package com.company;

public class RandomGenerator implements NumericGenerator{

    public RandomGenerator() {
    }

    @Override
    public char movment() {
        int m = (int)(Math.random()*5);
        String move = "qwasd";
        return move.charAt(m);
    }
}
