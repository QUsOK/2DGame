package test_survivor_game.scratch;

import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import survivor_game.classes.scratch.Son;

import static org.junit.jupiter.api.Assertions.*;

public class SonTest {

    public void testMediaPlayerNotNull() {

        Son son = new Son("src/main/resources/musique_jeu.mp3");
        MediaPlayer mediaPlayer = son.getMediaPlayer();

        assertNotNull(mediaPlayer);
    }

    public void testMediaPlayerIsPlaying() throws InterruptedException {

        Son son = new Son("src/main/resources/musique_jeu.mp3");
        MediaPlayer mediaPlayer = son.getMediaPlayer();

        assertNotNull(mediaPlayer);

        // Jouer le son et attendre la fin de la lecture (ajustez si nécessaire)
        mediaPlayer.play();

        // Attendez un certain temps pour la lecture (ajustez si nécessaire)
        Thread.sleep(5000);

        // Vérifier si le lecteur est toujours en train de jouer
        assertTrue(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING);

        // Arrêter le son (si nécessaire)
        mediaPlayer.stop();
    }

    public void testMediaPlayerDuration() throws InterruptedException {

        Son son = new Son("src/main/resources/musique_jeu.mp3");
        MediaPlayer mediaPlayer = son.getMediaPlayer();

        assertNotNull(mediaPlayer);

        // Jouer le son
        mediaPlayer.play();

        // Attendez un certain temps pour la lecture (ajustez si nécessaire)
        Thread.sleep(3000);

        // Vérifier que la durée du lecteur est correcte (ajustez si nécessaire)
        assertEquals(Duration.millis(156456.0), mediaPlayer.getTotalDuration());

        // Arrêter le son (si nécessaire)
        mediaPlayer.stop();
    }
}