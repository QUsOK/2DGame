package survivor_game.classes.Characters;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fantome extends Monstres {
    public Fantome(boolean armure) {
        super(3, 30, 2, 5);
        if (armure) {
            this.degat = 5;
            this.point = 50;
            this.vitesse = 1.5;
            this.life = 8;
            Image imageBouclier = new Image("file:src/main/java/Sprite/Bouclier.png");
            this.bouclier = new ImageView(imageBouclier);
            this.bouclier.setOpacity(0.5f);
            this.bouclier.setFitWidth(70);
            this.bouclier.setPreserveRatio(true);
        }

        Image image = new Image("file:src/main/java/Sprite/Ghost.png");
        this.perso = new ImageView(image);
        this.perso.setFitWidth(50);
        this.perso.setPreserveRatio(true);
    }

}
