package test_survivor_game.Collisions;

import javafx.scene.image.Image;
import survivor_game.classes.Collisions.Etage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EtageTest {

    public void testEtageTestWithImage() {

        Image etageImage = new Image("file:src/main/java/Sprite/poutre.png");
        Etage etage = new Etage(etageImage);

        assertNotNull(etage.getPerso());
        assertEquals(0.0, etage.getPerso().getTranslateX());
        assertEquals(0.0, etage.getPerso().getTranslateY());
    }

    public void testEtageTestWithImageAndPosition() {

        Image etageImage = new Image("file:src/main/java/Sprite/poutre.png");
        double expectedX = 10.0;
        double expectedY = 20.0;

        Etage etage = new Etage(etageImage, expectedX, expectedY);

        assertNotNull(etage.getPerso());
        assertEquals(expectedX, etage.getPerso().getTranslateX());
        assertEquals(expectedY, etage.getPerso().getTranslateY());

    }
}
