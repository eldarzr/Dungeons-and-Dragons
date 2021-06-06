package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private Tile[][] tilesArr;
    private List<Tile> tiles;
    private List<Enemy> enemies;
    private Player player;
    private UserOutput userOutput;

    public Board(char[][] chars, UserOutput userOutput) {
        //tiles= Arrays.stream(board).flatMap(Arrays::stream).collect(Collectors.toList());
        //enemies = Arrays.stream(board).flatMap(Arrays::stream).collect(Collectors.toList())
        tilesArr = new Tile[chars.length][chars[0].length];
        enemies = new ArrayList<>();
        tiles = new ArrayList<>();
        this.userOutput = userOutput;
    }

    public void add(Tile tile){
        tiles.add(tile);
        Position pos = tile.position;
        tilesArr[pos.x][pos.y] = tile;
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public void addPlayer(Player player){
        this.player = player;
    }

    public void printBoard(){
        for(int i=0; i<tilesArr.length; i++)
            for(int j=0; j<tilesArr[i].length; j++)
                userOutput.writeOutput(String.valueOf(tilesArr[i][j].character));
    }
}
