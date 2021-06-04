package com.company;

public abstract class Tile implements Comparable<Tile>  {
    protected char character;
    protected Position position;

    protected Tile(char character) {
        this.character=character;
    }

    protected void initialize(Position position){
        this.position = position;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int compareTo(Tile tile) {
        return getPosition().compareTo(tile.getPosition());
    }

    public String toString() {
        return String.valueOf(character);
    }
}
