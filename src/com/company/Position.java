package com.company;

import jdk.jshell.spi.ExecutionControl;

public class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double range(Position p)
    {
        return range(p.x,p.y);
    }

    public double range(int x, int y)
    {
        double disX = Math.exp(this.x-x);
        double disY = Math.exp(this.y-y);
        return Math.sqrt(disX+disY);
    }

    public void translate (int x, int y){
        this.x = x;
        this.y = y;
    }

    public int compareTo(Position p){
        if(x<p.x)
            return 1;
        else if(x == p.x){
            if(y<p.y)
                return 1;
            else if(y == p.y)
                return 0;
        }
        return -1;
    }
}
