package test_survivor_game.Weapon;

import static org.junit.Assert.assertEquals;

import survivor_game.classes.Weapon.Bombe;

public class BombeTest {

    public void testConstructeurBombe() {
        Bombe pistolet = new Bombe();

        assertEquals(5, pistolet.getDegat());
        assertEquals(300, pistolet.getDistance());
        assertEquals(2.5, pistolet.getRecharge(), 0.1);
        assertEquals("Bombe", pistolet.getNom());
    }
}
