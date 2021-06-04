package com.company;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Board {
    private List<Tile> tiles;
    private List<Enemy> enemies;
    Player player;

    public Board(Tile[][]board) {
        tiles= Arrays.stream(board).flatMap(Arrays::stream).collect(Collectors.toList());
        //enemies = Arrays.stream(board).flatMap(Arrays::stream).collect(Collectors.toList())
        enemies = new LinkedList<>();

    }
}
