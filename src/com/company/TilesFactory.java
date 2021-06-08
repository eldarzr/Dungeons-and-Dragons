package com.company;

import java.util.Arrays;

public class TilesFactory {
    private UserInput userInput ;
    private UserOutput userOutput;
    private Player [] allPlayers;

    public TilesFactory() {
        userInput = new UserInput();
        userOutput = new UserOutput();
        initializePlayers();
    }

    private void initializePlayers() {
        Position pos = new Position(0, 0);
        allPlayers = new Player[3];
        allPlayers[1] = new Warrior(pos, "Jhon Snow", 30, 4, new Health(300, 300));
        allPlayers[2]= new Warrior(pos, "The Hound", 20, 6, new Health(400, 400));
    }

    public Player createPlayer()
    {
       boolean isPlayerCreated = false;
        Player p = null;
       while(!isPlayerCreated) {
           userOutput.writeOutput(Arrays.toString(allPlayers));
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
