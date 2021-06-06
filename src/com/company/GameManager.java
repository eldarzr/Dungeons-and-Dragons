package com.company;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class GameManager {
    Board board;
    Player player;

    public GameManager() {

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
                else if(file[i][j]=='@') {
                    board.addPlayer(new Warrior(pos,"Eldar", 99999, 10, new Health(1000, 1000)));
                    player = new Warrior(pos,"Eldar", 99999, 10, new Health(1000, 1000));

                }
            }

        }

    }


    public void onTick(char c) {
        if (c=='d')
        {

            board.onTick(c);
        }


    }
}
