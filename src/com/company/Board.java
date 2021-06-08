package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Board {

    private Tile[][] tilesArr;
    private List<Tile> tiles;
    private List<Enemy> enemies;
    private Player player;
    private UserOutput userOutput;
    private UserInput userInput;

    public Board(char[][] chars) {
        //tiles= Arrays.stream(board).flatMap(Arrays::stream).collect(Collectors.toList());
        //enemies = Arrays.stream(board).flatMap(Arrays::stream).collect(Collectors.toList())
        tilesArr = new Tile[chars.length][chars[0].length];
        enemies = new ArrayList<>();
        tiles = new ArrayList<>();
        this.userOutput = new UserOutput();
        this.userInput = new UserInput();
        //tiles.stream().sorted().map(t -> t.position.x == 5 ? t.toString() + "/n" : t.toString());
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void add(Tile tile){
        tiles.add(tile);
        Position pos = tile.position;
        tilesArr[pos.x][pos.y] = tile;
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
        add(enemy);
        enemy.setMessageCallBack(s-> userOutput.writeOutput(s));
    }

    public void addPlayer(Player player){
        this.player = player;
        add(player);
        player.setMessageCallBack(s-> userOutput.writeOutput(s));
    }

    public void gameOver(String s){
        userOutput.writeOutput(s);
    }

    public void printBoard(){
//        for(int i=0; i<tilesArr.length; i++)
//            //for(int j=0; j<tilesArr[i].length; j++)
//                userOutput.writeOutput(Arrays.toString(tilesArr[i]));
        var v = tiles.stream().sorted()
                .map(t -> t.position.y == tilesArr[0].length-1 ? t.toString() + "\n" : t.toString())
                .collect(Collectors.joining(""));
        userOutput.writeOutput(v);

    }


    public Player getPlayer() {
        return player;
    }

    public void onTick() {


        String s = userInput.readLine();
        while (!(s.length()==1 && "adwseq".contains(s))) {
             s = userInput.readLine();
        }

        char c = s.charAt(0);
        unitTick(player, c);
        for (Enemy enemy: enemies) {
            unitTick(enemy, enemy.move(player));
        }

    }

    private void unitTick(Unit unit, char c){
        Position pos = unit.getPosition();
        int x = pos.x;
        int y = pos.y;

        //Tile t =null;
        if(c == 'd')
            //t = tilesArr[pos.x][pos.y+1];
            y++;
        else if (c== 'a')
            //t = tilesArr[pos.x][pos.y-1];
            y--;
        else if (c== 'w')
            //t = tilesArr[pos.x-1][pos.y];
            x--;
        else if (c== 's')
            //t = tilesArr[pos.x+1][pos.y];
            x++;
        //player.interact(t);

        //tilesArr[player.position.x ][player.position.y] = player;


        Position position = new Position(x,y);
        interact(position, unit);

    }

    public void interact(Position position, Unit unit){
        Tile tile = null;
        for (Tile t: tiles) {
            if(t.position.compareTo(position) == 0)
                tile = t;
        }
        unit.interact(tile);
    }

    public void removeEnemy(Enemy enemy) {
        Position pos = enemy.position;
        enemies.remove(enemy);
        tiles.remove(enemy);
        Empty empty = new Empty(pos);
        tiles.add(empty);
        player.interact(empty);

    }
}
