package test_survivor_game.Characters;

import javafx.scene.image.Image;

import survivor_game.classes.Projectile;
import survivor_game.classes.Characters.Personnage;
import survivor_game.classes.Weapon.Pistolet;
import survivor_game.classes.Weapon.Weapon;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonnageTest {

    public void testConstructeurPersonnage() {

        // Créer un Personnage avec une arme fictive
        Weapon armeFictive = new Pistolet();
        Personnage personnage = new Personnage(armeFictive);

        Image personnageImage = new Image("file:src/main/java/Sprite/Joueur1.png");
        // Vérifier que le personnage a bien été créé
        assertNotNull(personnage.getPerso());

        assertNotNull(personnage.getArme());
        assertEquals("Pistolet", personnage.getArme().getNom());

        assertEquals(10, personnage.getLife());
        assertEquals(personnageImage.getUrl(), personnage.getPerso().getImage().getUrl());
        assertEquals(50, personnage.getPerso().getFitWidth());
        assertTrue(personnage.getPerso().isPreserveRatio());
        assertEquals((1000 - personnage.getPerso().getFitWidth()) / 2, personnage.getPerso().getTranslateX());
        assertEquals((600 - personnage.getPerso().getFitHeight()) / 2, personnage.getPerso().getTranslateY());
    }

    public void testVision() {
        // Créer un Personnage avec une arme fictive
        Weapon armeFictive = new Pistolet();
        Personnage personnage = new Personnage(armeFictive);

        // Définir la position de la souris
        personnage.vision(0, 0, 100, 100);
        assertEquals(135, personnage.getPerso().getRotate(), 0.001);
    }

    public void testRechargement() {
        // Créer un Personnage avec une arme fictive
        Weapon armeFictive = new Pistolet();
        Personnage personnage = new Personnage(armeFictive);

        assertTrue(personnage.getArme().isRecharge());

        Projectile rechargement = personnage.rechargementArme();
        Projectile rechargementtest = new Projectile(personnage.getArme());
        assertFalse(personnage.getArme().isRecharge());
        assertEquals(rechargementtest.getPerso().getImage().getUrl(), rechargement.getPerso().getImage().getUrl());

        double x = personnage.getPerso().getTranslateX();
        double y = personnage.getPerso().getTranslateY();
        assertEquals(x, rechargement.getPerso().getTranslateX(), 0.001);
        assertEquals(y, rechargement.getPerso().getTranslateY(), 0.001);
    }

    public void testTir() {

        // Créer un Personnage avec une arme fictive
        Weapon armeFictive = new Pistolet();
        Personnage personnage = new Personnage(armeFictive);

        // Définir la position de la souris
        personnage.vision(0, 0, 100, 100);

        // Appeler la méthode Tir() et vérifier que la balle est créée
        Projectile projectile = personnage.Tir();
        assertNotNull(projectile);

        // Vérifier que la balle a été initialisée correctement
        assertEquals(20.0, projectile.getPerso().getFitWidth(), 0.001);
        assertEquals(0.0, projectile.getPerso().getFitHeight(), 0.001);

        // Vérifier que la balle est dans une position initiale correcte
        assertEquals(personnage.getPerso().getTranslateX(), projectile.getPerso().getTranslateX(), 0.001);
        assertEquals(personnage.getPerso().getTranslateY(), projectile.getPerso().getTranslateY(), 0.001);
    }

    public void testDeplacement() {
        // Créer un Personnage avec une arme fictive
        Weapon armeFictive = new Pistolet();
        Personnage personnage = new Personnage(armeFictive);
        double x = personnage.getPerso().getTranslateX();
        double y = personnage.getPerso().getTranslateY();
        // Définir la position de la souris
        personnage.deplacement(0, 5);
        assertEquals(y + 5, personnage.getPerso().getTranslateY(), 0.001);
        personnage.deplacement(5, 0);
        assertEquals(x + 5, personnage.getPerso().getTranslateX(), 0.001);
        personnage.deplacement(0, -5);
        assertEquals(y, personnage.getPerso().getTranslateY(), 0.001);
        personnage.deplacement(-5, 0);
        assertEquals(x, personnage.getPerso().getTranslateX(), 0.001);
    }
}