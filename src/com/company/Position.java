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

    public int range(Position p)
    {
       return 0;
    }

    public void translate (int x, int y){

    }
}
