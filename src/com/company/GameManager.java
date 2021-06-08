package com.company;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class GameManager {
    FileParser fileParser;
    Board board;
    Player player;
    private TilesFactory tf;



    public GameManager() {
        fileParser = new FileParser();

    }

    public void onTick(char c) {
        //board.onTick(c);
        //board.printBoard();
    }

    public void play() {
        initializePlayer();
        char [][] currLevel= fileParser.readLevel();
        levelInitiallizer(currLevel);
        board.printBoard();
        while(true){
            board.onTick();
            board.printBoard();
        }

    }

    private void initializePlayer() {
        tf = new TilesFactory();
        player=tf.createPlayer();
    }

    public void levelInitiallizer(char[][]file )
    {
        board = new Board(file);
        for (int i = 0; i < file.length; i++) {
            for (int j = 0; j < file[i].length; j++) {
                Position pos = new Position(i,j);
                if(file[i][j]=='.')
                    board.add(new Empty(pos));
                else if(file[i][j]=='#')
                    board.add(new Wall(pos));
                else if(file[i][j]=='@'){
                    player.setPosition(pos);
                    board.addPlayer(player);
                }
                else{
                    Enemy enemy = tf.createEnemy(file[i][j]);
                    enemy.setPosition(pos);
                    enemy.setOnDeathCallBack(()-> board.removeEnemy(enemy));
                    board.addEnemy(enemy);
                }
            }

        }

    }
}
