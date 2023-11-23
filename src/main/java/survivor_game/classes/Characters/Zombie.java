package survivor_game.classes.Characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Zombie extends Monstres {
    public Zombie() {
        super(1, 10, 1, 2);
        Image image = new Image("file:src/main/java/Sprite/Zombie.png");
        this.perso = new ImageView(image);
        this.perso.setFitWidth(50);
        this.perso.setPreserveRatio(true);
    }
}
