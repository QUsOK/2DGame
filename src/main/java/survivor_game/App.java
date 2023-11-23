package survivor_game;

import java.util.*;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import survivor_game.classes.scratch.Bouton;
import survivor_game.classes.scratch.Son;
import survivor_game.classes.scratch.Texte;
import survivor_game.classes.Projectile;
import survivor_game.classes.Characters.*;
import survivor_game.classes.Collisions.*;
import survivor_game.classes.Weapon.Pistolet;

/*JavaFX App*/

public class App extends Application {

    private ImageView map;
    public static int score;
    public static List<Collisions> colisions = new ArrayList<>();
    public static Personnage perso;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private List<Monstres> monstres = new ArrayList<>();
    private List<Projectile> projectiles = new ArrayList<>();
    private long spawntime = 3000;
    public static Timer spawnerTimer;
    private Son musique_jeu;

    @Override
    public void start(Stage stage) {
        menu(stage);
    }

    public void menu(Stage primaryStage) {
        Texte titre = new Texte("2D Game", 170, 70, 60);

        Texte soustitre = new Texte("Select your weapon :", 180, 110, 25);

        Image imagemap = new Image("file:src/main/java/Sprite/map.png");
        map = new ImageView(imagemap);
        try {
            musique_jeu = new Son("src/main/resources/musique_jeu.mp3");
            musique_jeu.getMediaPlayer().setVolume(0.1);
            musique_jeu.getMediaPlayer().setCycleCount(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        perso = new Personnage(new Pistolet());
        perso.getPerso().setFitWidth(50);
        perso.getPerso().setPreserveRatio(true);
        perso.getPerso().setTranslateX(270);
        perso.getPerso().setTranslateY(150);
        perso.getPerso().setRotate(180);

        Bouton boutonMenu1 = new Bouton("Pistolet", 100, 180);
        boutonMenu1.setStyle("-fx-background-color: #Fe3e3e; -fx-text-fill: white; -fx-font-size: 18px;");
        Bouton boutonMenu2 = new Bouton("Bombe", 415, 180);
        boutonMenu2.setStyle("-fx-background-color: #6bc02f; -fx-text-fill: white; -fx-font-size: 18px;");
        Bouton boutonMenu3 = new Bouton("Mitraillette", 240, 300);
        boutonMenu3.setStyle("-fx-background-color: #3fccff; -fx-text-fill: white; -fx-font-size: 18px;");

        boutonMenu1.setOnAction(event -> {
            boutonMenu1.actionMenu(this, primaryStage);
        });
        boutonMenu2.setOnAction(event -> {
            boutonMenu2.actionMenu(this, primaryStage);
        });
        boutonMenu3.setOnAction(event -> {
            boutonMenu3.actionMenu(this, primaryStage);
        });

        Pane root = new Pane(map, titre, soustitre, boutonMenu1, boutonMenu2, boutonMenu3, perso.getPerso());
        root.setMinSize(600, 400);
        root.setMaxSize(600, 400);
        Scene scene = new Scene(root);

        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Menu");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnMouseMoved(event -> {
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            double playerX = perso.getPerso().getTranslateX();
            double playerY = perso.getPerso().getTranslateY();

            perso.vision(playerX, playerY, mouseX, mouseY);
        });

    }

    public void victoire(Stage primaryStage) {
        Bouton replay = new Bouton("Rejouer", 200, 150);
        replay.setStyle("-fx-background-color: #21b6de; -fx-text-fill: white; -fx-font-size: 20px;");

        replay.setOnAction(event -> {
            replay.actionVictoire(this, primaryStage);
        });

        Texte message = new Texte(25, 100, 20);
        if (score <= 100) {
            message.setText("Vous avez perdu avec un score de " + score + " points\nIl va falloir s'entrainer");
        } else if (score <= 300) {
            message.setText(
                    "Vous avez gagné avec un score de " + score + " points\nPas mal mais vous pouvez mieux faire");
        } else {
            message.setText("Oh mon gâté tié trop fort\nTon score est incroyable: " + score + " points");
        }

        Pane root = new Pane(map, replay, message);
        root.setMinSize(500, 300);
        root.setMaxSize(500, 300);
        Scene scene = new Scene(root);
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Défaite");
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.E) {
                Image image = new Image("file:src/main/java/Sprite/SCH.png");

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(50);
                imageView.setPreserveRatio(true);
                imageView.setTranslateX(225);
                imageView.setTranslateY(200);
                root.getChildren().add(imageView);
            }
        });
    }

    public void startGame(Stage primaryStage) {
        Image imagemap = new Image("file:src/main/java/Sprite/background.png");
        ImageView background = new ImageView(imagemap);

        Texte info = new Texte(
                "SCORE : " + score + "\n" + "Arme : " + perso.getArme().getNom() + "\n" + "Vie : "
                        + perso.getLife(),
                30, 40, 30);

        perso.getPerso().setFitWidth(32);
        perso.getPerso().setPreserveRatio(true);

        Pane root = new Pane(
                background,
                perso.getPerso(),
                info);

        root.setMinSize(1000, 600);
        root.setMaxSize(1000, 600);
        colisions = createcollision(root);

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            perso.getPerso().toFront();
            info.toFront();
            keys.put(event.getCode(), true);
            if (event.getCode() == KeyCode.SPACE) {
                Projectile newprojectile = perso.Tir();
                if (newprojectile != null) {
                    projectiles.add(newprojectile);
                    root.getChildren().add(newprojectile.getPerso());

                    Timeline timeline = new Timeline(
                            new KeyFrame(Duration.seconds(1), e -> {
                                projectiles.remove(newprojectile);
                                root.getChildren().remove(newprojectile.getPerso());
                            }));

                    timeline.play();
                }

            }
        });

        scene.setOnKeyReleased(event -> {
            keys.put(event.getCode(), false);
        });

        scene.setOnMouseMoved(event -> {
            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();
            double playerX = perso.getPerso().getTranslateX();
            double playerY = perso.getPerso().getTranslateY();

            perso.vision(playerX, playerY, mouseX, mouseY);
        });

        scene.setOnMouseClicked(event -> {
            Projectile newprojectile = perso.Tir();
            if (newprojectile != null) {
                projectiles.add(newprojectile);
                root.getChildren().add(newprojectile.getPerso());

                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(1), e -> {
                            projectiles.remove(newprojectile);
                            root.getChildren().remove(newprojectile.getPerso());
                        }));

                timeline.play();
            }
        });

        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });

        deplacementCharacter(root, primaryStage);
        NextSpawn(root);

        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);
        primaryStage.setTitle("2DGAME");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void deplacementCharacter(Pane root, Stage primaryStage) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!monstres.isEmpty()) {

                    for (Monstres monstre : monstres) {

                        double deltaX = perso.getPerso().getTranslateX() - monstre.getPerso().getTranslateX();
                        double deltaY = perso.getPerso().getTranslateY() - monstre.getPerso().getTranslateY();

                        double angle = Math.atan2(deltaX, -deltaY);
                        double degrees = Math.toDegrees(angle);
                        degrees = (degrees + 360) % 360;

                        monstre.getPerso().setRotate(degrees);

                        if (!projectiles.isEmpty()) {
                            for (Projectile balle : projectiles) {
                                if (monstre.getPerso().getBoundsInParent()
                                        .intersects(balle.getPerso().getBoundsInParent())) {
                                    monstre.setLife(monstre.getLife() - perso.getArme().getDegat());
                                    Platform.runLater(() -> {
                                        projectiles.remove(balle);
                                        root.getChildren().remove(balle.getPerso());
                                        if (monstre.getBouclier() != null) {
                                            if (monstre.getLife() <= 3) {
                                                monstre.setLife(3);
                                                root.getChildren().remove(monstre.getBouclier());
                                                monstre.setBouclier(null);
                                            }
                                        } else if (monstre.getLife() <= 0) {
                                            score += monstre.getPoint();
                                            Texte info = root.getChildren().stream()
                                                    .filter(node -> node instanceof Texte).map(node -> (Texte) node)
                                                    .findFirst().get();
                                            info.setText(
                                                    "Score : " + score + "\n" + "Arme : " + perso.getArme().getNom()
                                                            + "\n" + "Vie : " + perso.getLife());
                                            monstres.remove(monstre);
                                            root.getChildren().remove(monstre.getPerso());
                                        }
                                    });

                                }
                            }
                        }
                        if (monstre.getPerso().getBoundsInParent().intersects(perso.getPerso().getBoundsInParent())) {
                            perso.setLife(perso.getLife() - monstre.getDegat());
                            Son damage = new Son("src/main/resources/damageJoueur.wav");
                            damage.getMediaPlayer().setVolume(0.5);
                            Platform.runLater(() -> {
                                monstres.remove(monstre);
                                Texte info = root.getChildren().stream()
                                        .filter(node -> node instanceof Texte).map(node -> (Texte) node)
                                        .findFirst().get();
                                info.setText("Score : " + score + "\n" + "Arme : " + perso.getArme().getNom() + "\n"
                                        + "Vie : " + perso.getLife());
                                root.getChildren().remove(monstre.getPerso());
                                if (monstre.getBouclier() != null) {
                                    root.getChildren().remove(monstre.getBouclier());
                                }
                                if (perso.getLife() <= 0) {
                                    spawnerTimer.cancel();
                                    this.stop();
                                    perso = null;
                                    monstres.clear();
                                    projectiles.clear();
                                    keys.clear();
                                    primaryStage.close();
                                    Son mort = new Son("src/main/resources/mort.wav");
                                    mort.getMediaPlayer().setVolume(0.1);
                                    if (musique_jeu != null) {

                                        try {
                                            musique_jeu.getMediaPlayer().stop();
                                            musique_jeu = null;
                                        } catch (Exception e) {
                                            e.printStackTrace();

                                        }
                                    }

                                    victoire(primaryStage);
                                }
                            });
                        }
                        monstre.deplacement();
                    }
                }

                if (keys.getOrDefault(KeyCode.Z, false) || (keys.getOrDefault(KeyCode.UP, false))) {
                    perso.deplacement(0, -5);
                }

                if (keys.getOrDefault(KeyCode.S, false) || (keys.getOrDefault(KeyCode.DOWN, false))) {
                    perso.deplacement(0, 5);
                }

                if (keys.getOrDefault(KeyCode.Q, false) || (keys.getOrDefault(KeyCode.LEFT, false))) {
                    perso.deplacement(-5, 0);
                }

                if (keys.getOrDefault(KeyCode.D, false) || (keys.getOrDefault(KeyCode.RIGHT, false))) {
                    perso.deplacement(5, 0);
                }

            }
        };
        timer.start();
    }

    public void NextSpawn(Pane root) {
        spawnerTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                SpawnMonstre(root);

                // Réduire le délai entre chaque spawn (25% de la valeur actuelle)
                spawntime = (long) (spawntime * 0.9);

                // Planifier la prochaine tâche avec le nouveau délai
                if (spawntime > 500) { // Arrêter lorsque le délai est inférieur à 1000 ms
                    NextSpawn(root);
                } else {
                    spawntime = 500;
                    NextSpawn(root);
                }
            }
        }, spawntime);
    }

    public void SpawnMonstre(Pane root) {
        Random randMonstre = new Random();
        int randomNumber = randMonstre.nextInt(10) + 1;
        Random randSpawn = new Random();
        int randomNumberSpawn = randSpawn.nextInt(4) + 1;
        Son son_monstre = new Son("src/main/resources/spawn.wav");
        son_monstre.getMediaPlayer().setVolume(0.1);
        if (randomNumber <= 6) {
            Monstres zombie = new Zombie();
            monstres.add(zombie);
            switch (randomNumberSpawn) {
                case 1:
                    zombie.setPositionSprite(-50, 250);
                    break;
                case 2:
                    zombie.setPositionSprite(-50, 650);
                    break;
                case 3:
                    zombie.setPositionSprite(1000, 650);
                    break;
                case 4:
                    zombie.setPositionSprite(1000, 150);
                    break;
                default:
                    break;
            }
            zombie.getPerso().setFitWidth(32);
            zombie.getPerso().setPreserveRatio(true);
            Platform.runLater(() -> {
                root.getChildren().add(zombie.getPerso());
            });
        } else if (randomNumber <= 8) {
            Monstres fantome = new Fantome(false);
            monstres.add(fantome);
            switch (randomNumberSpawn) {
                case 1:
                    fantome.setPositionSprite(-50, 250);
                    break;
                case 2:
                    fantome.setPositionSprite(-50, 650);
                    break;
                case 3:
                    fantome.setPositionSprite(1000, 650);
                    break;
                case 4:
                    fantome.setPositionSprite(1000, 250);
                    break;
                default:
                    break;
            }
            fantome.getPerso().setFitWidth(32);
            fantome.getPerso().setPreserveRatio(true);
            Platform.runLater(() -> {
                root.getChildren().add(fantome.getPerso());
            });
        } else if (randomNumber > 9) {
            Monstres fantome = new Fantome(true);
            monstres.add(fantome);
            switch (randomNumberSpawn) {
                case 1:
                    fantome.setPositionSprite(-50, 250);
                    fantome.setPositionBouclier(-50, 250);
                    break;
                case 2:
                    fantome.setPositionSprite(-50, 650);
                    fantome.setPositionBouclier(-50, 650);
                    break;
                case 3:
                    fantome.setPositionSprite(1000, 650);
                    fantome.setPositionBouclier(1000, 650);
                    break;
                case 4:
                    fantome.setPositionSprite(1000, 250);
                    fantome.setPositionBouclier(1000, 250);
                    break;
                default:
                    break;
            }
            fantome.getPerso().setFitWidth(32);
            fantome.getPerso().setPreserveRatio(true);
            fantome.getBouclier().setFitWidth(38);
            fantome.getBouclier().setPreserveRatio(true);
            Platform.runLater(() -> {
                root.getChildren().add(fantome.getPerso());
                root.getChildren().add(fantome.getBouclier());

            });
        }
    }

    public List<Collisions> createcollision(Pane root) {

        Image haut = new Image("file:src/main/java/Sprite/haut.png");
        Collisions haut_mapCollisions = new Etage(haut);
        haut_mapCollisions.getPerso().setFitHeight(100);

        Image lave = new Image("file:src/main/java/Sprite/lave.png");
        Collisions laveCollisions = new Sol(lave, 100, 120);
        laveCollisions.getPerso().setFitWidth(150);
        laveCollisions.getPerso().setPreserveRatio(true);

        Image Lac_poison = new Image("file:src/main/java/Sprite/poison_grand.png");
        Collisions Lac_poisonCollisions = new Sol(Lac_poison, 645, 300);
        Lac_poisonCollisions.getPerso().setFitWidth(100);
        Lac_poisonCollisions.getPerso().setPreserveRatio(true);

        Image petit_lac = new Image("file:src/main/java/Sprite/petit_poison.png");
        Collisions petit_lacCollisions = new Sol(petit_lac, 600, 300);
        Collisions petit_lacCollisions2 = new Sol(petit_lac, 645, 400);
        Collisions petit_lacCollisions3 = new Sol(petit_lac, 780, 300);

        Image pilier = new Image("file:src/main/java/Sprite/poutre.png");
        Collisions pilier2 = new Etage(pilier, 180, 450);
        pilier2.getPerso().setFitWidth(18);
        pilier2.getPerso().setPreserveRatio(true);
        Collisions pilier3 = new Etage(pilier, 300, 150);
        pilier3.getPerso().setFitWidth(18);
        pilier3.getPerso().setPreserveRatio(true);
        Collisions pilier4 = new Etage(pilier, 400, 300);
        pilier4.getPerso().setFitWidth(18);
        pilier4.getPerso().setPreserveRatio(true);

        List<Collisions> returncreate = new ArrayList<>();
        returncreate.add(haut_mapCollisions);
        returncreate.add(laveCollisions);

        returncreate.add(petit_lacCollisions);
        returncreate.add(petit_lacCollisions2);
        returncreate.add(petit_lacCollisions3);
        returncreate.add(Lac_poisonCollisions);

        returncreate.add(pilier2);
        returncreate.add(pilier3);
        returncreate.add(pilier4);

        Platform.runLater(() -> {
            root.getChildren().add(haut_mapCollisions.getPerso());
            root.getChildren().add(laveCollisions.getPerso());

            root.getChildren().add(petit_lacCollisions.getPerso());
            root.getChildren().add(petit_lacCollisions2.getPerso());
            root.getChildren().add(petit_lacCollisions3.getPerso());
            root.getChildren().add(Lac_poisonCollisions.getPerso());

            root.getChildren().add(pilier2.getPerso());
            root.getChildren().add(pilier3.getPerso());
            root.getChildren().add(pilier4.getPerso());
        });
        return returncreate;

    }

    public static void main(String[] args) {
        launch(args);
    }

}
