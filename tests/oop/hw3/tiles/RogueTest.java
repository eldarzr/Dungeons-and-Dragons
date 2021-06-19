package oop.hw3.tiles;

import oop.hw3.Position;
import oop.hw3.resources.Health;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RogueTest {
    Rogue rogue;
    Position pos;
    Position pos2;
    List<Enemy> enemies;
    @BeforeEach
    void setUp() {
        enemies = new ArrayList<>();
        pos =  new Position(0,0);
        pos2 =  new Position(0,1);
        rogue = new Rogue(pos, "Regular rogue", 35, 5, new Health(250), 25);
        enemies.add(new Monster('s',"Rogue's Killer",15,4,new Health(100),25,3));
        enemies.get(0).setPosition(new Position(0,1));
    }


    @Test
    void onLevelUp() {
        String [] ExceptedLevels = {"Regular rogue		Health: 270/270		Attack: 49		Defense: 7		Level: 2		Experience: -50/100		Energy: 100/100","Regular rogue\t\tHealth: 300/300\t\tAttack: 70\t\tDefense: 10\t\tLevel: 3\t\tExperience: -150/150\t\tEnergy: 100/100",
        "Regular rogue\t\tHealth: 340/340\t\tAttack: 98\t\tDefense: 14\t\tLevel: 4\t\tExperience: -300/200\t\tEnergy: 100/100","Regular rogue\t\tHealth: 390/390\t\tAttack: 133\t\tDefense: 19\t\tLevel: 5\t\tExperience: -500/250\t\tEnergy: 100/100"};
        rogue.setMessageCallBack(s ->assertTrue(true));

       for (int i=0;i<ExceptedLevels.length;i++)
        {
            rogue.onLevelUp();
            assertEquals(ExceptedLevels[i],rogue.describe());
        }
    }

    @Test
    void levelUpStats()
    {
        rogue.setMessageCallBack(s -> assertTrue(true));
        rogue.onLevelUp();
    }

    @Test
    void castSpecialAbility() {
        rogue.setMessageCallBack(s -> assertTrue(true));
        Enemy enemy=enemies.get(0);

        int enemeyHealth = enemy.getHealth().getAmount();
        rogue.castSpecialAbility(enemies);
        assertTrue(enemeyHealth > enemy.getHealth().getAmount());
        enemeyHealth=enemy.getHealth().getAmount();
        rogue.castSpecialAbility(enemies);
        assertTrue(enemeyHealth > enemy.getHealth().getAmount());
        }




    @Test
    void Battle(){
        int rogueHealth = rogue.getHealth().getAmount();
        Enemy e=enemies.get(0);
        rogue.setMessageCallBack(a -> assertTrue(true));
        int enemyHealth = e.getHealth().getAmount();
        rogue.interact(e);

        assertTrue(rogue.position.compareTo(pos)==0);
        assertTrue(e.position.compareTo(pos2)==0);
        assertTrue(e.health.getAmount() < enemyHealth);
        assertTrue(rogue.getHealth().getAmount() == rogueHealth);
    }

    @Test
    void describe() {

        System.out.println(rogue.describe());
        assertEquals("Regular rogue\t\tHealth: 250/250\t\tAttack: 35\t\tDefense: 5\t\tLevel: 1\t\tExperience: 0/50\t\tEnergy: 100/100",rogue.describe());

    }
}