package com.company;
import java.util.*;

public class LevelInit {
    public Board board;

    public LevelInit() {
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
                  else if(file[i][j]=='@')
                        board.add(new Player("Eldar",99999,10,10000));
            }

        }

        }
    }

