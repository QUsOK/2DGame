package survivor_game.classes.Collisions;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Etage extends Collisions {
    public Etage(Image etage) {
        this.perso = new ImageView(etage);
    }

    public Etage(Image etage, double x, double y) {
        this.perso = new ImageView(etage);
        this.setPositionSprite(x, y);
    }
}
