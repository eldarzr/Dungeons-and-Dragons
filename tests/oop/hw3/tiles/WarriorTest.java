package oop.hw3.tiles;

import oop.hw3.MessageCallBack;
import oop.hw3.Position;
import oop.hw3.resources.Health;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WarriorTest {
    Warrior warrior;
    Position pos;
    Position pos2;
    List<Enemy> enemies;
    @BeforeEach
   void setUp() {
        enemies = new ArrayList<>();
        pos =  new Position(0,0);
        pos2 =  new Position(0,1);
        warrior = new Warrior(pos, "Regular Warrior", 10, 5, new Health(200), 2);
        enemies.add(new Monster('s',"Warrior's Killer",15,4,new Health(100),25,3));
        enemies.get(0).setPosition(new Position(0,1));

    }

    @Test
    void castSpecialAbility() {
        warrior.setMessageCallBack(s -> assertTrue(true));
        Enemy enemy=enemies.get(0);

        int enemeyHealth = enemy.getHealth().getAmount();
        warrior.castSpecialAbility(enemies);
        assertTrue(enemeyHealth > enemy.getHealth().getAmount());
        enemeyHealth=enemy.getHealth().getAmount();
        warrior.castSpecialAbility(enemies);
        assertTrue(enemeyHealth == enemy.getHealth().getAmount());
    }

    @Test
    void onLevelUp() {
         String [] ExceptedLevels = {"Regular Warrior\t\tHealth: 230/230\t\tAttack: 22\t\tDefense: 9\t\tLevel: 2\t\tExperience: -50/100\t\t Cool down: 0/2","Regular Warrior\t\tHealth: 275/275\t\tAttack: 40\t\tDefense: 15\t\tLevel: 3\t\tExperience: -150/150\t\t Cool down: 0/2",
                 "Regular Warrior\t\tHealth: 335/335\t\tAttack: 64\t\tDefense: 23\t\tLevel: 4\t\tExperience: -300/200\t\t Cool down: 0/2","Regular Warrior\t\tHealth: 410/410\t\tAttack: 94\t\tDefense: 33\t\tLevel: 5\t\tExperience: -500/250\t\t Cool down: 0/2"};
        warrior.setMessageCallBack(s -> System.out.println(s));
        for (int i=0;i<ExceptedLevels.length;i++)
        {
            warrior.onLevelUp();
            assertEquals(ExceptedLevels[i],warrior.describe());
        }


    }

    @Test
    void levelUpStats()
    {
        warrior.setMessageCallBack(s -> assertTrue(s.equals("Regular Warrior reached level 2 : +30 Health, +12 Attack, +4 Defence")));
        warrior.onLevelUp();
    }

    @Test
    void Battle(){
        Enemy e=enemies.get(0);
        warrior.setMessageCallBack(a -> assertTrue(true));
        warrior.interact(e);
        assertTrue(warrior.position.compareTo(pos)==0);
        assertTrue(e.position.compareTo(pos2)==0);
        assertTrue(e.health.getAmount() < 100);
        assertTrue(warrior.getHealth().getAmount() == 200);
    }

    @Test
    void describe() {
        assertEquals(warrior.describe(),"Regular Warrior\t\tHealth: 200/200\t\tAttack: 10\t\tDefense: 5\t\tLevel: 1\t\tExperience: 0/50\t\t Cool down: 0/2");
    }
}