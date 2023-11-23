module survivor_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.media;
    requires transitive javafx.graphics;

    opens survivor_game to javafx.fxml;

    exports survivor_game;
    exports survivor_game.classes.Collisions;
    exports survivor_game.classes;
    exports survivor_game.classes.Characters;
    exports survivor_game.classes.Weapon;
}
