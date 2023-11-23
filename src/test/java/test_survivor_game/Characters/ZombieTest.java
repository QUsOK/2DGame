package test_survivor_game.Characters;

import javafx.scene.image.Image;

import survivor_game.classes.Characters.Zombie;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ZombieTest {

    public void testZombieConstructeur() {

        Zombie zombie = new Zombie();
        Image zombieImage = new Image("file:src/main/java/Sprite/Zombie.png");

        assertNotNull(zombie.getPerso());
        assertEquals(0.0, zombie.getPerso().getTranslateX());
        assertEquals(0.0, zombie.getPerso().getTranslateY());

        assertEquals(zombieImage.getUrl(), zombie.getPerso().getImage().getUrl());
        assertEquals(50, zombie.getPerso().getFitWidth());
        assertTrue(zombie.getPerso().isPreserveRatio());

        assertEquals(1, zombie.getDegat());
        assertEquals(10, zombie.getPoint());
        assertEquals(1, zombie.getVitesse(), 0.1);
        assertEquals(2, zombie.getLife());
    }

}