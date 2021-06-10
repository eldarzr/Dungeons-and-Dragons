package oop.hw3;

public class RandomGenerator implements NumericGenerator{
    static RandomGenerator rnd;
    public RandomGenerator() {
    }

    public static RandomGenerator getInstance(){
        if(rnd==null)
            rnd=new RandomGenerator();
        return rnd;


    }
    @Override
    public char movment() {
        int m = (int)(Math.random()*5);
        String move = "qwasd";
        return move.charAt(m);
    }

    public int combat(int num) {
        return (int)(Math.random()*(num+1));
    }

    public int range(int num) {
        return (int)(Math.random()*num);
    }
}
