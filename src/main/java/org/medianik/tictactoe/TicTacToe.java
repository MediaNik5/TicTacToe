package org.medianik.tictactoe;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.medianik.tictactoe.gameobject.Cross;
import org.medianik.tictactoe.gameobject.GameObject;
import org.medianik.tictactoe.gameobject.Nought;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static org.medianik.tictactoe.Constants.*;

/**
 * JavaFX App
 */
public class TicTacToe extends Application {

    private static TicTacToe instance;
    private final int width;
    private final int height;
    private final StackPane pane;
    private final Set<GameObject> gameObjects;
    private final Timeline executor;
    private int tick;


    public TicTacToe() {
        width = calculateInitialWidth();
        height = calculateInitialHeight();
        pane = new StackPane();
        executor = new Timeline(new KeyFrame(Duration.millis(TIME_PER_TICK * 1000), TicTacToe::execute));
        gameObjects = new HashSet<>();
        instance = this;
        tick = 0;
    }

    public static void execute(ActionEvent actionEvent) {
        var instance = getInstance();
//        System.out.printf("Time %d%n", instance.tick);
        instance.tick++;
        try {
            for (GameObject go : instance.gameObjects)
                go.tick(instance.pane, instance.tick);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static TicTacToe getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch();
    }

    private int calculateInitialWidth() {
        var bounds = Screen.getPrimary().getBounds();
        return Math.min((int) bounds.getWidth() - GLOBAL_OFFSET, MAX_WIDTH);
    }

    private int calculateInitialHeight() {
        var bounds = Screen.getPrimary().getBounds();
        return Math.min((int) bounds.getHeight() - GLOBAL_OFFSET, MAX_HEIGHT);
    }

    @Override
    public void start(Stage stage) {
        var scene = new Scene(pane, width, height);
        stage.setScene(scene);
        stage.show();
        setupIcon(stage);

        gameObjects.add(new Nought(0, 0, tick));
        gameObjects.add(new Cross(0, 0, tick));
        pane.getChildren().add(new Circle(0, 0, 1));
        executor.setCycleCount(Animation.INDEFINITE);
        executor.play();
    }

    private void setupIcon(Stage stage) {
        InputStream inputIcon = getClass().getResourceAsStream("/icon.png");
        Image icon = new Image(inputIcon);
        stage.getIcons().add(icon);
    }

}