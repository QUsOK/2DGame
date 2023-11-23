package test_survivor_game;

import javafx.application.Platform;
import test_survivor_game.Characters.*;
import test_survivor_game.Collisions.*;
import test_survivor_game.Weapon.*;
import test_survivor_game.scratch.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AppTest {

    @BeforeAll
    public static void initJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {
            });
        }
    }

    @Test
    public void test() throws InterruptedException {
        ProjectileTest projectileTest = new ProjectileTest();
        projectileTest.testConstructeurProjectileAvecBombe();
        projectileTest.testConstructeurProjectileAvecPistolet();
        projectileTest.testConstructeurProjectileAvecObjetInconnu();

        BombeTest bombeTest = new BombeTest();
        bombeTest.testConstructeurBombe();

        MitrailletteTest mitrailletteTest = new MitrailletteTest();
        mitrailletteTest.testConstructeurMitraillette();

        PistoletTest pistoletTest = new PistoletTest();
        pistoletTest.testConstructeurPistolet();

        PersonnageTest personnageTest = new PersonnageTest();
        personnageTest.testConstructeurPersonnage();
        personnageTest.testVision();
        personnageTest.testDeplacement();
        personnageTest.testTir();
        personnageTest.testRechargement();

        ZombieTest zombieTest = new ZombieTest();
        zombieTest.testZombieConstructeur();

        FantomeTest fantomeTest = new FantomeTest();
        fantomeTest.testFantomeConstructeur();
        fantomeTest.testFantomeBouclierConstructeur();

        EtageTest etageTest = new EtageTest();
        etageTest.testEtageTestWithImage();
        etageTest.testEtageTestWithImageAndPosition();

        ResultatCollisionTest resultatCollisionTest = new ResultatCollisionTest();
        resultatCollisionTest.testResultatCollision();

        SolTest solTest = new SolTest();
        solTest.testSolTestConstruction();
        solTest.testSolTestPosition();

        BoutonTest boutonTest = new BoutonTest();
        boutonTest.testActionMenu();
        boutonTest.testActionVictoire();
        boutonTest.testChoixArme();

        SonTest sonTest = new SonTest();
        sonTest.testMediaPlayerNotNull();
        sonTest.testMediaPlayerDuration();
        sonTest.testMediaPlayerIsPlaying();

        TexteTest texteTest = new TexteTest();
        texteTest.testTexteAvecTexte();
        texteTest.testTexteSansTexte();
    }
}
