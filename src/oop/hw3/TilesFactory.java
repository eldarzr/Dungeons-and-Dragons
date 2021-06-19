package oop.hw3;

import oop.hw3.ioSystem.UserInput;
import oop.hw3.ioSystem.UserOutput;
import oop.hw3.resources.Health;
import oop.hw3.resources.Mana;
import oop.hw3.tiles.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    }

    private void initializePlayers() {
        Position pos = new Position(0, 0);
        allPlayers = new Player[8];
        allPlayers[1] = new Warrior(pos, "Jhon Snow", 30, 4, new Health(300), 3);
        allPlayers[2]= new Warrior(pos, "The Hound", 20, 6, new Health(400), 5);
        allPlayers[3]= new Mage(pos,"Melisandra",5,1,new Health(100),new Mana(300,30),5,6,15);
        allPlayers[4]= new Mage(pos,"Thoros of Myr",25,4,new Health(250),new Mana(150,20),3,4,20);
        allPlayers[5]= new Rogue(pos, "Arya Stark", 40, 2, new Health(150), 20);
        allPlayers[6]= new Rogue(pos, "Bronn", 35, 3, new Health(250), 50);
        allPlayers[7]= new Hunter(pos,"Ygritte",30,2,new Health(220),6);

    }

    public Enemy createEnemy(char c)
    {
        if(c=='s')
            return new Monster('s',"Lannister Solider",8,3,new Health(80),25,3);
        if(c=='k')
            return new Monster('k',"Lannister Knight",14,8,new Health(200),50,4);
        if(c=='q')
            return new Monster('q',"Queen’s Guard",20,15,new Health(400),100,5);
        if(c=='z')
            return new Monster('z',"Wright",30,15,new Health(600),100,3);
        if(c=='b')
            return new Monster('b',"Bear-Wright",75,30,new Health(1000),250,4);
        if(c=='g')
            return new Monster('g',"Giant-Wright",100,40,new Health(1500),500,5);
        if(c=='w')
            return new Monster('w',"White Walker",150,50,new Health(2000),1000,6);
        if(c=='M')
            return new Monster('M',"The Mountain",60,25,new Health(1000),500,6);
        if(c=='C')
            return new Monster('C',"Queen Cersei",10,10,new Health(100),1000,1);
        if(c=='K')
            return new Monster('K',"Night King",300,150,new Health(5000),5000,8);
        if(c=='B')
            return new Trap ( 'B',"Bonus Trap",1,1 , new Health(1),250,1,5);
        if(c=='Q')
            return new Trap ( 'Q',"Queen’s Trap",50,10 , new Health(250),100,3,7);
        if(c=='D')
            return new Trap ( 'D',"Death Trap",100,20 , new Health(500),250,1,10);

        return  null;

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
