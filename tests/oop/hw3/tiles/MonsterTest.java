package oop.hw3.tiles;

import oop.hw3.Position;
import oop.hw3.resources.Health;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.monitor.MonitorSettingException;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {

    Position pos1;
    Position pos2;
    Health health1;
    Health health2;
    int h1;
    int h2;
    Monster enemy1;
    Enemy enemy2;

    @BeforeEach
    void setup(){
        h1 = 100;
        h2 = 100;
        pos1 = new Position(0,0);
        pos2 = new Position(0,1);
        health1 = new Health(h1);
        health2 = new Health(h2);
        enemy1 = new Monster('$', "KHALISI", 2000,10,health1,2,10);
        enemy1.setPosition(pos1);
    }

    @Test
    void trapAttack(){
        enemy2 = new Trap('H', "KHALISI2", 30,0,health2,2,3,7);
        enemy2.setPosition(pos2);
        enemy1.interact(enemy2);
        assertTrue(enemy1.position.compareTo(pos1)==0);
        assertTrue(enemy2.position.compareTo(pos2)==0);
        assertTrue(enemy1.getHealth().getAmount() == h1);
        assertTrue(enemy2.getHealth().getAmount() == h2);
    }

    @Test
    void monsterAttack(){
        enemy2 = new Monster('H', "KHALISI2", 30,0,health2,2,10);
        enemy2.setPosition(pos2);
        enemy1.interact(enemy2);
        assertTrue(enemy1.position.compareTo(pos1)==0);
        assertTrue(enemy2.position.compareTo(pos2)==0);
        assertTrue(enemy1.getHealth().getAmount() == h1);
        assertTrue(enemy2.getHealth().getAmount() == h2);
    }

    @Test
    void wallAttack(){
        Wall wall = new Wall(pos2);
        enemy1.interact(wall);
        assertTrue(enemy1.position.compareTo(pos1)==0);
        assertTrue(wall.position.compareTo(pos2)==0);
    }

    @Test
    void emptyAttack(){
        Empty empty = new Empty(pos2);
        enemy1.interact(empty);
        assertTrue(enemy1.position.compareTo(pos2)==0);
        assertTrue(empty.position.compareTo(pos1)==0);
    }

    @Test
    void PlayerAttack(){
        Player warrior = new Warrior(pos2, "W", 20,10,new Health(100),2);
        warrior.setMessageCallBack(a -> assertTrue(true));
        enemy1.setMessageCallBack(a -> assertTrue(true));
        enemy1.battle(warrior);
        assertTrue(enemy1.position.compareTo(pos1)==0);
        assertTrue(warrior.position.compareTo(pos2)==0);
        assertTrue(enemy1.health.getAmount() == 100);
        assertTrue(warrior.health.getAmount() < 100);
    }


}