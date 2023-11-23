package test_survivor_game.Characters;

import javafx.scene.image.Image;

import survivor_game.classes.Characters.Fantome;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FantomeTest {

    public void testFantomeConstructeur() {

        Fantome fantome = new Fantome(false);
        Image fantomeImage = new Image("file:src/main/java/Sprite/Ghost.png");

        assertNotNull(fantome.getPerso());
        assertEquals(0.0, fantome.getPerso().getTranslateX());
        assertEquals(0.0, fantome.getPerso().getTranslateY());

        assertEquals(fantomeImage.getUrl(), fantome.getPerso().getImage().getUrl());
        assertEquals(50, fantome.getPerso().getFitWidth());
        assertTrue(fantome.getPerso().isPreserveRatio());

        assertEquals(3, fantome.getDegat());
        assertEquals(30, fantome.getPoint());
        assertEquals(2, fantome.getVitesse(), 0.1);
        assertEquals(5, fantome.getLife());
    }

    public void testFantomeBouclierConstructeur() {

        Fantome fantome = new Fantome(true);
        Image fantomeImage = new Image("file:src/main/java/Sprite/Ghost.png");

        assertNotNull(fantome.getPerso());
        assertEquals(0.0, fantome.getPerso().getTranslateX());
        assertEquals(0.0, fantome.getPerso().getTranslateY());

        assertEquals(fantomeImage.getUrl(), fantome.getPerso().getImage().getUrl());
        assertEquals(50, fantome.getPerso().getFitWidth());
        assertTrue(fantome.getPerso().isPreserveRatio());

        assertEquals(5, fantome.getDegat());
        assertEquals(50, fantome.getPoint());
        assertEquals(1.5, fantome.getVitesse(), 0.1);
        assertEquals(8, fantome.getLife());

        Image bouclierImage = new Image("file:src/main/java/Sprite/Bouclier.png");
        assertNotNull(fantome.getBouclier());
        assertEquals(bouclierImage.getUrl(), fantome.getBouclier().getImage().getUrl());
        assertEquals(0.5f, fantome.getBouclier().getOpacity(), 0.1);
        assertEquals(70, fantome.getBouclier().getFitWidth());
        assertTrue(fantome.getBouclier().isPreserveRatio());
    }
}