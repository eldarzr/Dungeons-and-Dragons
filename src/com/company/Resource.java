package com.company;

abstract class Resource {
    int x;
    int y;

    public Resource(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract int onLevelUp(int level);
}
