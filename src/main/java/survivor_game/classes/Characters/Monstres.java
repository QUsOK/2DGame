package survivor_game.classes.Characters;

import javafx.scene.image.ImageView;
import survivor_game.App;
import survivor_game.classes.Collisions.Collisions;
import survivor_game.classes.Collisions.ResultatCollision;

public abstract class Monstres extends Character {
    protected int degat;
    protected int point;
    protected ImageView bouclier;
    protected double vitesse;

    public Monstres(int degat, int point, int vitesse, int life) {
        this.degat = degat;
        this.point = point;
        this.vitesse = vitesse;
        this.life = life;
    }

    public int getDegat() {
        return degat;
    }

    public int getPoint() {
        return point;
    }

    public ImageView getBouclier() {
        return bouclier;
    }

    public void setBouclier(ImageView bouclier) {
        this.bouclier = bouclier;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setPositionBouclier(double x, double y) {
        this.bouclier.setTranslateX(x);
        this.bouclier.setTranslateY(y);
    }

    public void deplacement() {
        double targetX = App.perso.getPerso().getTranslateX();
        double targetY = App.perso.getPerso().getTranslateY();

        double ancienX = this.perso.getTranslateX();
        double ancienY = this.perso.getTranslateY();

        double deltaX = targetX - ancienX;
        double deltaY = targetY - ancienY;

        double norme = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        double deplacementX = deltaX / norme * this.vitesse;
        double deplacementY = deltaY / norme * this.vitesse;

        double newTranslateX = this.perso.getTranslateX() + deplacementX;
        double newTranslateY = this.perso.getTranslateY() + deplacementY;

        this.perso.setTranslateX(newTranslateX);
        this.perso.setTranslateY(newTranslateY);

        ResultatCollision resultat = Collisions.checkCollision(this);
        if (resultat.isCollisionDetected()) {
            double coliX = resultat.getCollision().getPerso().getTranslateX();
            double coliY = resultat.getCollision().getPerso().getTranslateY();

            // La collision est détectée, déterminez l'axe de la collision
            double deltaXAfterCollision = coliX - this.perso.getTranslateX();
            double deltaYAfterCollision = coliY - this.perso.getTranslateY();

            if (Math.abs(deltaXAfterCollision) > Math.abs(deltaYAfterCollision)) {
                // La collision s'est produite principalement sur l'axe X
                newTranslateX += 1;
                this.perso.setTranslateX(newTranslateX);
            } else {
                // La collision s'est produite principalement sur l'axe Y
                newTranslateY += 1;
                this.perso.setTranslateY(newTranslateY);
            }
        }

        if (bouclier != null) {
            setPositionBouclier(newTranslateX - 3, newTranslateY - 3);
        }
    }

}
