package test_survivor_game.Collisions;

import javafx.scene.image.Image;

import survivor_game.classes.Collisions.Collisions;
import survivor_game.classes.Collisions.Etage;
import survivor_game.classes.Collisions.ResultatCollision;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultatCollisionTest {

    public void testResultatCollision() {
        // Créer une instance de ResultatCollisionTest
        Collisions collision = new Etage(new Image("file:src/main/java/Sprite/poutre.png"));

        ResultatCollision resultat = new ResultatCollision(true, collision);

        // Utiliser les getters pour obtenir les valeurs
        Collisions collisionResult = resultat.getCollision();
        boolean collisionBool = resultat.isCollisionDetected();
        // Vérifier que les valeurs correspondent aux attentes
        assertEquals(collision, collisionResult);
        assertEquals(true, collisionBool);
    }

}