package oop.hw3.tiles;

import oop.hw3.Position;
import oop.hw3.resources.Health;
import oop.hw3.resources.Mana;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MageTest {
    Mage mage;
    Position pos;
    Position pos2;
    List<Enemy> enemies;

    @BeforeEach
    void setUp() {
        enemies = new ArrayList<>();
        pos =  new Position(0,0);
        pos2 =  new Position(0,1);
        mage = new Mage(pos, "Regular Mage", 5, 1, new Health(100), new Mana(300, 30), 5, 6, 15);
        enemies.add(new Monster('s',"Mage's Killer",15,4,new Health(100),25,3));
        enemies.get(0).setPosition(new Position(0,1));
    }

    @Test
    void onLevelUp() {
        String [] ExceptedLevels = {"Regular Mage\t\tHealth: 120/120\t\tAttack: 13\t\tDefense: 3\t\tLevel: 2\t\tExperience: -50/100\t\tMana: 162/350 , Spell Power:35","Regular Mage\t\tHealth: 150/150\t\tAttack: 25\t\tDefense: 6\t\tLevel: 3\t\tExperience: -150/150\t\tMana: 268/425 , Spell Power:65",
        "Regular Mage\t\tHealth: 190/190\t\tAttack: 41\t\tDefense: 10\t\tLevel: 4\t\tExperience: -300/200\t\tMana: 399/525 , Spell Power:105","Regular Mage\t\tHealth: 240/240\t\tAttack: 61\t\tDefense: 15\t\tLevel: 5\t\tExperience: -500/250\t\tMana: 561/650 , Spell Power:155"};
        mage.setMessageCallBack(s -> System.out.println(s));
       for (int i=0;i<ExceptedLevels.length;i++)
        {
            mage.onLevelUp();
            assertEquals(ExceptedLevels[i],mage.describe());
        }
    }

    @Test
    void levelUpStats()
    {
        mage.setMessageCallBack(s -> assertTrue(s.equals("Regular Mage reached level 2 : +20 Health, +50 Mana, +8 Attack, +2 Defence")));
        mage.onLevelUp();
    }

    @Test
    void castSpecialAbility() {
        mage.setMessageCallBack(s -> assertTrue(true));
        Enemy enemy=enemies.get(0);

        int enemeyHealth = enemy.getHealth().getAmount();
        mage.castSpecialAbility(enemies);
        assertTrue(enemeyHealth > enemy.getHealth().getAmount());
        System.out.println(enemy.describe());
    }

    @Test
    void Battle(){
        int mageHealth = mage.getHealth().getAmount();
        Enemy e=enemies.get(0);
        mage.setMessageCallBack(a -> assertTrue(true));
        int enemyHealth = e.getHealth().getAmount();
        mage.interact(e);

        assertTrue(mage.position.compareTo(pos)==0);
        assertTrue(e.position.compareTo(pos2)==0);
        assertTrue(e.health.getAmount() < enemyHealth);
        assertTrue(mage.getHealth().getAmount() == mageHealth);
    }


    @Test
    void describe() {
        assertEquals(mage.describe(),"Regular Mage\t\tHealth: 100/100\t\tAttack: 5\t\tDefense: 1\t\tLevel: 1\t\tExperience: 0/50\t\tMana: 75/300 , Spell Power:15");
    }

}