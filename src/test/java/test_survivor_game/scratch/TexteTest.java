package test_survivor_game.scratch;

import javafx.scene.text.Font;
import survivor_game.classes.scratch.Texte;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TexteTest {

    public void testTexteAvecTexte() {

        String texteAttendu = "Bonjour";
        double xAttendu = 10.0;
        double yAttendu = 20.0;
        double sizeAttendu = 14.0;

        Texte texte = new Texte(texteAttendu, xAttendu, yAttendu, sizeAttendu);

        assertEquals(texteAttendu, texte.getText());
        assertEquals(xAttendu, texte.getLayoutX());
        assertEquals(yAttendu, texte.getLayoutY());
        assertEquals("-fx-fill: white;", texte.getStyle());
        assertEquals(Font.font("Verdana", sizeAttendu), texte.getFont());
    }

    public void testTexteSansTexte() {

        double xAttendu = 30.0;
        double yAttendu = 40.0;
        double sizeAttendu = 18.0;

        Texte texte = new Texte(xAttendu, yAttendu, sizeAttendu);

        assertEquals(true, texte.getText().isEmpty());
        assertEquals(xAttendu, texte.getLayoutX());
        assertEquals(yAttendu, texte.getLayoutY());
        assertEquals("-fx-fill: white;", texte.getStyle());
        assertEquals(Font.font("Verdana", sizeAttendu), texte.getFont());
    }
}
