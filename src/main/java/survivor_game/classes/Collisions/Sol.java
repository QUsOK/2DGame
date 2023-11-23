package survivor_game.classes.Collisions;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sol extends Collisions {
    public Sol(Image sol) {
        this.perso = new ImageView(sol);
    }

    public Sol(Image sol, double x, double y) {
        this.perso = new ImageView(sol);
        this.setPositionSprite(x, y);
    }
}
