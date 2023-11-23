package test_survivor_game;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.image.ImageView;

import survivor_game.classes.Projectile;
import survivor_game.classes.Weapon.Bombe;
import survivor_game.classes.Weapon.Pistolet;

public class ProjectileTest {

    public void testConstructeurProjectileAvecBombe() {
        Bombe bombe = new Bombe();
        Projectile projectile = new Projectile(bombe);

        assertTrue(projectile.getPerso() instanceof ImageView);
    }

    public void testConstructeurProjectileAvecPistolet() {
        Pistolet pistolet = new Pistolet();
        Projectile projectile = new Projectile(pistolet);

        assertTrue(projectile.getPerso() instanceof ImageView);
    }

    public void testConstructeurProjectileAvecObjetInconnu() {
        Object objetInconnu = new Object();
        Projectile projectile = new Projectile(objetInconnu);

        assertTrue(projectile.getPerso() instanceof ImageView);
    }
}
