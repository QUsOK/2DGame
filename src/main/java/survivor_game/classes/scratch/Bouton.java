package survivor_game.classes.scratch;

import java.util.Timer;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import survivor_game.App;
import survivor_game.classes.Characters.Personnage;
import survivor_game.classes.Weapon.Bombe;
import survivor_game.classes.Weapon.Mitraillette;
import survivor_game.classes.Weapon.Pistolet;
import survivor_game.classes.Weapon.Weapon;

public class Bouton extends Button {
    public Bouton(String text, double x, double y) {
        setText(text);
        setLayoutY(y);
        setLayoutX(x);
    }

    public void actionMenu(App instance, Stage lejeu) {
        lejeu.close();
        App.perso = new Personnage(choixArme());
        App.spawnerTimer = new Timer();
        Platform.runLater(() -> {
            instance.startGame(lejeu);
        });
    }

    public void actionVictoire(App instance, Stage lejeu) {
        lejeu.close();
        App.score = 0;
        Platform.runLater(() -> {
            instance.menu(lejeu);
        });
    }

    public Weapon choixArme() {
        if (getText().equals("Pistolet")) {
            return new Pistolet();
        } else if (getText().equals("Mitraillette")) {
            return new Mitraillette();
        } else if (getText().equals("Bombe")) {
            return new Bombe();
        } else {
            return null;
        }
    }

}
