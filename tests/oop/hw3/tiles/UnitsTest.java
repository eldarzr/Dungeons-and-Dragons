package oop.hw3.tiles;

import oop.hw3.TilesFactory;
import oop.hw3.resources.Health;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitsTest {

    static TilesFactory tilesFactory = new TilesFactory();
    static List<Unit> players = new ArrayList<>();
    static List<Unit> enemies = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        for (int i=1; i<8; i++)
            players.add(tilesFactory.createPlayer(i));
        String emeny = "skqzbgwMCKBQD";

        for (int i=0; i<emeny.length(); i++)
            enemies.add(tilesFactory.createEnemy(emeny.charAt(i)));
    }

    @Test
    void playerAttack() {
        for (Unit u: players) {
            int att = u.attack();
            int attpts = u.attackPoints;
            assertTrue(att<=attpts & att>=0);
        }

    }

    @Test
    void enemyAttack() {
        for (Unit u: enemies) {
            int att = u.attack();
            int attpts = u.attackPoints;
            assertTrue(att<=attpts & att>=0);
        }
    }

    @Test
    void playerDefense() {
        for (Unit u: players) {
            int def = u.defend();
            int defpts = u.defensePoints;
            assertTrue(def<=defpts & def>=0);
        }

    }

    @Test
    void enemyDefense() {
        for (Unit u: enemies) {
            int def = u.defend();
            int defpts = u.defensePoints;
            assertTrue(def<=defpts & def>=0);
        }
    }


}