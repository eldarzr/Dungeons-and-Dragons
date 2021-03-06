package oop.hw3;

import oop.hw3.ioSystem.FileParser;
import oop.hw3.tiles.Empty;
import oop.hw3.tiles.Enemy;
import oop.hw3.tiles.Player;
import oop.hw3.tiles.Wall;

public class GameManager {
    FileParser fileParser;
    Board board;
    Player player;
    private TilesFactory tf;



    public GameManager(String path) {
        fileParser = new FileParser(path);

    }

    public void play() {
        initializePlayer();
        while (player.isAlive()) {
            char[][] currLevel = fileParser.readLevel();
            if(currLevel == null)
                return;
            levelInitiallizer(currLevel);
            board.printBoard();
            boolean isActive = true;
            while (isActive) {
                board.onTick();
                board.printBoard();
                if (board.getEnemies().size() == 0 | !player.isAlive())
                    isActive = false;
            }
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
