package oop.hw3.tiles;

import oop.hw3.Position;
import oop.hw3.resources.Health;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {

    int vis, inVis, circle;
    Position pos1;
    Position pos2;
    int h1;
    int h2;
    Health health1;
    Health health2;
    Trap enemy1;
    Enemy enemy2;


    @BeforeEach
    void setup(){
        vis = 3;
        inVis = 7;
        circle = vis+inVis;
        h1 = 100;
        h2 = 100;
        pos1 = new Position(0,0);
        pos2 = new Position(0,1);
        health1 = new Health(h1);
        health2 = new Health(h2);
        enemy1 = new Trap('$', "KHALISI", 2000,10,health1,2,vis,inVis);
        enemy1.setPosition(pos1);
    }

    @Test
    void visibilityCircle(){
        Player warrior = new Warrior(new Position(100,100), "W", 20,10,new Health(100),2);
        for(int i=0; i<circle*5; i++){
            if((i%circle)<enemy1.visibilityTime)
                assertTrue(enemy1.toString().equals("$"));
            else assertTrue(enemy1.toString().equals("."));
            enemy1.onTick(warrior);
        }
    }

    @Test
    void trapAttack(){
        enemy2 = new Trap('H', "KHALISI2", 30,0,health2,2,vis*2,inVis);
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
        assertTrue(enemy1.position.compareTo(pos1)==0);
        assertTrue(empty.position.compareTo(pos2)==0);
    }

    @Test
    void PlayerAttack(){
        Player warrior = new Warrior(pos2, "W", 20,10,new Health(100),2);
        warrior.setMessageCallBack(a -> assertTrue(true));
        enemy1.setMessageCallBack(a -> assertTrue(true));
        enemy1.onTick(warrior);
        assertTrue(enemy1.position.compareTo(pos1)==0);
        assertTrue(warrior.position.compareTo(pos2)==0);
        assertTrue(enemy1.health.getAmount() == 100);
        assertTrue(warrior.health.getAmount() < 100);
    }

}