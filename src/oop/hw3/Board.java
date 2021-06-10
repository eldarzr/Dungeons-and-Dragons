package oop.hw3;

import oop.hw3.ioSystem.UserInput;
import oop.hw3.ioSystem.UserOutput;
import oop.hw3.tiles.*;

import java.util.*;
import java.util.stream.Collectors;

public class Board {

    private List<Tile> tiles;
    private List<Enemy> enemies;
    private Player player;
    private UserOutput userOutput;
    private UserInput userInput;
    private int maxX, maxY;

    public Board(char[][] chars) {
        enemies = new ArrayList<>();
        tiles = new ArrayList<>();
        this.userOutput = new UserOutput();
        this.userInput = new UserInput();
        maxX = chars.length-1;
        maxY = chars[0].length-1;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void add(Tile tile){
        tiles.add(tile);
        Position pos = tile.getPosition();
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
        String boardPrint = tiles.stream().sorted()
                .map(t -> t.getPosition().y == maxY ? t.toString() + "\n" : t.toString())
                .collect(Collectors.joining(""));
        boardPrint+= "\n" + player.describe() + "\n";
        userOutput.writeOutput(boardPrint);

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
            unitTick(enemy, enemy.onTick(player));
        }

    }

    private void unitTick(Unit unit, char c){
        Position pos = unit.getPosition();
        int x = pos.x;
        int y = pos.y;

        if(c == 'd')
            y++;
        else if (c== 'a')
            y--;
        else if (c== 'w')
            x--;
        else if (c== 's')
            x++;
        else if(c == 'e') {
            unit.castSpecialAbility(enemies);
        }
        Position position = new Position(x,y);
        interact(position, unit);
        unit.onTick();
    }

    public void interact(Position position, Unit unit){
        Tile tile = null;
        tile = tiles.stream().filter(t -> t.getPosition().compareTo(position) == 0)
                .collect(Collectors.toList()).get(0);
        unit.interact(tile);
    }

    public void removeEnemy(Enemy enemy) {
        Position pos = enemy.getPosition();
        enemies.remove(enemy);
        tiles.remove(enemy);
        Empty empty = new Empty(pos);
        tiles.add(empty);
        player.interact(empty);

    }
}
