package test_survivor_game.Weapon;

import static org.junit.Assert.assertEquals;

import survivor_game.classes.Weapon.Mitraillette;

public class MitrailletteTest {

    public void testConstructeurMitraillette() {
        Mitraillette pistolet = new Mitraillette();

        assertEquals(1, pistolet.getDegat());
        assertEquals(500, pistolet.getDistance());
        assertEquals(1.0, pistolet.getRecharge(), 0.1);
        assertEquals("Mitraillette", pistolet.getNom());
    }
}
