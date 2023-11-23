package survivor_game.classes;

import javafx.scene.image.ImageView;
import survivor_game.classes.Weapon.Bombe;
import survivor_game.classes.Weapon.Pistolet;

public class Projectile extends Sprites {

    public Projectile(Object o) {
        if (o instanceof Bombe) {
            this.perso = new ImageView("file:src/main/java/Sprite/balle_verte.png");
        } else if (o instanceof Pistolet) {
            this.perso = new ImageView("file:src/main/java/Sprite/Balle.png");
        } else {
            this.perso = new ImageView("file:src/main/java/Sprite/Bouclier.png");
        }
        this.perso.setFitWidth(20);
        this.perso.setPreserveRatio(true);
    }

}
