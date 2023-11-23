package survivor_game.classes.scratch;

public class Texte extends javafx.scene.text.Text {
    public Texte(String text, double x, double y, double size) {
        setText(text);
        setLayoutY(y);
        setLayoutX(x);
        setStyle("-fx-fill: white;");
        setFont(javafx.scene.text.Font.font("Verdana", size));
    }

    public Texte(double x, double y, double size) {
        setLayoutY(y);
        setLayoutX(x);
        setStyle("-fx-fill: white;");
        setFont(javafx.scene.text.Font.font("Verdana", size));

    }
}
