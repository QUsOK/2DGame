package survivor_game.classes.scratch;

import java.io.File;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Son {
    private MediaPlayer mediaPlayer;

    public Son(String musicFile) {
        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);

        mediaPlayer.setOnReady(() -> {
            // Obtenir la durée totale du son en millisecondes
            double totalDurationMillis = sound.getDuration().toMillis();

            // Créer un KeyFrame avec la durée totale du son
            KeyFrame keyFrame = new KeyFrame(Duration.millis(totalDurationMillis));

            Timeline timeline = new Timeline(keyFrame);
            timeline.play();
            mediaPlayer.play();
        });
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

}
