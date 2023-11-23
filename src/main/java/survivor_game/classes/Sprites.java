package survivor_game.classes;

import javafx.scene.image.ImageView;

public abstract class Sprites {
    protected ImageView perso;

    public ImageView getPerso() {
        return perso;
    }

    public void setPositionSprite(double x, double y) {
        this.perso.setTranslateX(x);
        this.perso.setTranslateY(y);
    }

}
