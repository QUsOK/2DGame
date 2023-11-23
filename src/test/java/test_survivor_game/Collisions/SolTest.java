package test_survivor_game.Collisions;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import survivor_game.classes.Collisions.Sol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SolTest {

    public void testSolTestConstruction() {

        // Créer une image pour le sol (remplacez le chemin par votre image réelle)
        Image solImage = new Image("file:src/main/java/Sprite/Zombie.png");

        // Créer un SolTest avec l'image
        Sol solTest = new Sol(solImage);

        // Vérifier que l'objet SolTest a été créé correctement
        assertNotNull(solTest);
        assertNotNull(solTest.getPerso());

        // Vérifier que l'objet ImageView a été créé avec l'image spécifiée
        ImageView imageView = solTest.getPerso();
        assertNotNull(imageView);
        assertEquals(solImage, imageView.getImage());
    }

    public void testSolTestPosition() {
        // Créer une image pour le sol (remplacez le chemin par votre image réelle)
        Image solImage = new Image("file:src/main/java/Sprite/Zombie.png");

        // Créer un SolTest avec l'image et des positions spécifiques
        double expectedX = 50.0;
        double expectedY = 75.0;
        Sol solTest = new Sol(solImage, expectedX, expectedY);

        // Vérifier que l'objet SolTest a été créé correctement
        assertNotNull(solTest);
        assertNotNull(solTest.getPerso());

        // Vérifier que l'objet ImageView a été créé avec l'image spécifiée
        ImageView imageView = solTest.getPerso();
        assertNotNull(imageView);
        assertEquals(solImage, imageView.getImage());

        // Vérifier que les positions ont été correctement définies
        assertEquals(expectedX, imageView.getTranslateX());
        assertEquals(expectedY, imageView.getTranslateY());
    }
}
