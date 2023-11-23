package survivor_game.classes.Collisions;

import javafx.geometry.Bounds;
import survivor_game.App;
import survivor_game.classes.Projectile;
import survivor_game.classes.Sprites;
import survivor_game.classes.Characters.Zombie;

public abstract class Collisions extends Sprites {

    public static ResultatCollision checkCollision(Sprites sprite) {
        for (Collisions collision : App.colisions) {
            Bounds boundsSprite = sprite.getPerso().localToScene(sprite.getPerso().getBoundsInLocal());
            Bounds boundsCollision = collision.getPerso().localToScene(collision.getPerso().getBoundsInLocal());

            if (sprite instanceof Zombie) {
                if (boundsSprite.intersects(boundsCollision)) {
                    return new ResultatCollision(true, collision);
                }
            } else if (sprite == App.perso) {
                if (boundsSprite.intersects(boundsCollision)) {
                    return new ResultatCollision(true, collision);
                }
            } else if (sprite instanceof Projectile && collision instanceof Etage) {
                if (boundsSprite.intersects(boundsCollision)) {
                    return new ResultatCollision(true, collision);
                }
            }
        }
        return new ResultatCollision(false, null);
    }

}
