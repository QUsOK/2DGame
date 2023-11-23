package test_survivor_game.scratch;

import javafx.application.Platform;
import javafx.stage.Stage;
import survivor_game.App;
import survivor_game.classes.Weapon.Bombe;
import survivor_game.classes.Weapon.Mitraillette;
import survivor_game.classes.Weapon.Pistolet;
import survivor_game.classes.scratch.Bouton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BoutonTest {

    public void testActionMenu() {
        Platform.runLater(() -> {
            Bouton bouton = new Bouton("Pistolet", 0, 0);

            Stage lejeu = new Stage();
            App instance = new App();

            // Supposons que startGame() initialise App.perso et App.spawnerTimer
            bouton.actionMenu(instance, lejeu);

            // Vérifier que App.perso a été initialisé avec une arme non nulle
            assertNotNull(App.perso);
            assertNotNull(App.perso.getArme());
        });
    }

    public void testActionVictoire() {
        Platform.runLater(() -> {
            Bouton bouton = new Bouton("Pistolet", 0, 0);

            Stage lejeu = new Stage();
            App instance = new App();

            // Supposons que menu() réinitialise App.score
            bouton.actionVictoire(instance, lejeu);

            // Vérifier que App.score a été réinitialisé à 0
            assertEquals(0, App.score);
        });
    }

    public void testChoixArme() {
        Platform.runLater(() -> {
            Bouton boutonPistolet = new Bouton("Pistolet", 0, 0);
            Bouton boutonMitraillette = new Bouton("Mitraillette", 0, 0);
            Bouton boutonBombe = new Bouton("Bombe", 0, 0);
            Bouton boutonInconnu = new Bouton("Inconnu", 0, 0);

            assertEquals(Pistolet.class, boutonPistolet.choixArme().getClass());
            assertEquals(Mitraillette.class, boutonMitraillette.choixArme().getClass());
            assertEquals(Bombe.class, boutonBombe.choixArme().getClass());
            assertEquals(null, boutonInconnu.choixArme());
        });
    }
}
