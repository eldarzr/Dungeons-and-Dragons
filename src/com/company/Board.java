package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Board {

    private Tile[][] tilesArr;
    private List<Tile> tiles;
    private List<Enemy> enemies;
    private Player player;
    private UserOutput userOutput;

    public Board(char[][] chars) {
        //tiles= Arrays.stream(board).flatMap(Arrays::stream).collect(Collectors.toList());
        //enemies = Arrays.stream(board).flatMap(Arrays::stream).collect(Collectors.toList())
        tilesArr = new Tile[chars.length][chars[0].length];
        enemies = new ArrayList<>();
        tiles = new ArrayList<>();
        this.userOutput = UserOutput.getInstance();
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


    public Player getPlayer() {
        return player;
    }

    public void onTick(char c) {
        Position pos = player.getPosition();
        int currentPos = pos.getX()+1;
        Tile t = tilesArr[pos.x+1][pos.y];
        player.interact(t);

        for (Tile tile: tiles) {
            tilesArr[tile.position.x][tile.position.y] = tile;
        }
    }
}
