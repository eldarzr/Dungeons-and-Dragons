package oop.hw3;

import oop.hw3.ioSystem.UserInput;
import oop.hw3.ioSystem.UserOutput;
import oop.hw3.resources.Health;
import oop.hw3.tiles.*;

import java.util.Arrays;
import java.util.HashMap;

public class TilesFactory {
    private UserInput userInput ;
    private UserOutput userOutput;
    private Player[] allPlayers;
    private HashMap<Character, Enemy> allEnemeis;


    public TilesFactory() {
        userInput = new UserInput();
        userOutput = new UserOutput();
        allEnemeis=new HashMap<Character, Enemy>();
        initializePlayers();
        initializeEnemeys();
    }

    private void initializePlayers() {
        Position pos = new Position(0, 0);
        allPlayers = new Player[3];
        allPlayers[1] = new Warrior(pos, "Jhon Snow", 30, 4, new Health(300), 3);
        allPlayers[2]= new Warrior(pos, "The Hound", 20, 6, new Health(400), 5);
    }
    private void initializeEnemeys()
    {
        allEnemeis.put('K',new Monster('K',"Night King",300,150,new Health(5),5000,8));
    }
    public Enemy createEnemy(char c)
    {
        if(c=='K')
            return new Monster('K',"Night King",30,5,new Health(50),500,8);
        if(c=='B')
            return new Trap ( 'B',"Bonus Trap",1,1 , new Health(1),250,1,5);
    return  allEnemeis.get(c);

    }

    public Player createPlayer()
    {
       boolean isPlayerCreated = false;
        Player p = null;
       while(!isPlayerCreated) {
           //userOutput.writeOutput(Arrays.toString(allPlayers));
           printPlayers();
           try {
               int num = Integer.parseInt(userInput.readLine());
               p = createPlayer(num);
               if (p != null) {
                   isPlayerCreated=true;
                   return p;
               }
           } catch (Exception e) {
                userOutput.writeOutput("You can enter one of the numbers presented only");

           }

       }
       return  p;
    }

    private void printPlayers() {
        String playersS = "";
        for (int i=1; i<allPlayers.length; i++){
            playersS+= i + ". " + allPlayers[i].describe() + "\n";
        }
        userOutput.writeOutput(playersS);
    }

    public Player createPlayer(int num)
    {
        Player player = null;
        Position pos = new Position(0,0);

        if(num>=allPlayers.length || num<=0)
            return null;

        player = allPlayers[num];

        return player;
    }

}
