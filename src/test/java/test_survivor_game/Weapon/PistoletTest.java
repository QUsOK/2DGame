package test_survivor_game.Weapon;

import static org.junit.Assert.assertEquals;

import survivor_game.classes.Weapon.Pistolet;

public class PistoletTest {

    public void testConstructeurPistolet() {
        Pistolet pistolet = new Pistolet();

        assertEquals(3, pistolet.getDegat());
        assertEquals(500, pistolet.getDistance());
        assertEquals(2.0, pistolet.getRecharge(), 0.1);
        assertEquals("Pistolet", pistolet.getNom());
    }
}
