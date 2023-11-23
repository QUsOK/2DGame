package survivor_game.classes.Characters;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import survivor_game.classes.Projectile;
import survivor_game.classes.Collisions.Collisions;
import survivor_game.classes.Collisions.ResultatCollision;
import survivor_game.classes.Weapon.Weapon;

public class Personnage extends Character {
    private Weapon arme;
    private double visionX;
    private double visionY;

    public Personnage(Weapon arme) {
        this.arme = arme;
        this.life = 10;
        // Charge l'image du personnage
        Image imagePersonnage = new Image("file:src/main/java/Sprite/Joueur1.png");
        this.perso = new ImageView(imagePersonnage);
        this.perso.setFitWidth(50);
        this.perso.setPreserveRatio(true);

        // Positionne le personnage au centre de l'écran
        this.perso.setTranslateX((1000 - this.perso.getFitWidth()) / 2);
        this.perso.setTranslateY((600 - this.perso.getFitHeight()) / 2);
    }

    public Weapon getArme() {
        return arme;
    }

    public Projectile Tir() {
        Projectile balle = this.rechargementArme();

        if (balle != null) {
            double duree_Animation = 50;
            double distancePerUpdate = this.getArme().getDistance() / (1 * duree_Animation);

            // Calculer la direction initiale vers le curseur de la souris
            double deltaX = visionX - balle.getPerso().getTranslateX();
            double deltaY = visionY - balle.getPerso().getTranslateY();
            double angleInitial = Math.atan2(deltaY, deltaX);

            // Créer une Timeline pour déplacer la balle vers le curseur de la souris
            Timeline timelineTir = new Timeline();
            timelineTir.setCycleCount((int) duree_Animation);

            for (int i = 1; i <= duree_Animation; i++) {
                KeyFrame keyFrame = new KeyFrame(
                        Duration.seconds(i * 1 / duree_Animation),
                        e -> {
                            ResultatCollision resultat = Collisions.checkCollision(balle);
                            if (!resultat.isCollisionDetected()) {
                                // Utiliser la direction initiale pour déplacer la balle
                                balle.getPerso().setTranslateX(
                                        balle.getPerso().getTranslateX() + distancePerUpdate * Math.cos(angleInitial));
                                balle.getPerso().setTranslateY(
                                        balle.getPerso().getTranslateY() + distancePerUpdate * Math.sin(angleInitial));
                            } else {
                                timelineTir.stop();
                                balle.getPerso().setVisible(false);

                            }
                        });
                timelineTir.getKeyFrames().add(keyFrame);
            }

            timelineTir.play();

        }
        return balle;
    }

    public void deplacement(double deltaX, double deltaY) {

        double ancienX = this.perso.getTranslateX();
        double ancienY = this.perso.getTranslateY();

        double newTranslateX = ancienX + deltaX;
        double newTranslateY = ancienY + deltaY;

        // Vérifiez si la nouvelle position se trouve toujours à l'intérieur des limites
        // de la fenêtre
        if (newTranslateX >= 0 && newTranslateX + this.perso.getBoundsInParent().getWidth() <= 1000
                && newTranslateY >= 0 && newTranslateY + this.perso.getBoundsInParent().getHeight() <= 600) {
            this.perso.setTranslateX(newTranslateX);
            this.perso.setTranslateY(newTranslateY);
            ResultatCollision resultat = Collisions.checkCollision(this);
            if (resultat.isCollisionDetected()) {
                this.perso.setTranslateX(ancienX);
                this.perso.setTranslateY(ancienY);
            }

        }

        vision(newTranslateX, newTranslateY, visionX, visionY);

    }

    public void vision(double x1, double y1, double mouseX, double mouseY) {
        visionX = mouseX;
        visionY = mouseY;
        double angle = Math.toDegrees(Math.atan2(visionX - x1, -(visionY - y1)));
        angle = (angle + 360) % 360; // Ajuster l'angle dans la plage de 0 à 360 degrés
        this.getPerso().setRotate(angle);
    }

    public Projectile rechargementArme() {
        if (this.arme.isRecharge() == true) {
            this.arme.setRecharge(false);
            this.arme.recharge();
            Projectile projectile = new Projectile(this.arme);
            projectile.setPositionSprite(this.perso.getTranslateX(), this.perso.getTranslateY());
            return projectile;
        } else {
            return null;
        }
    }
}
