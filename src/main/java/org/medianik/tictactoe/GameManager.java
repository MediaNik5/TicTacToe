package org.medianik.tictactoe;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import org.medianik.tictactoe.gameobject.Cross;
import org.medianik.tictactoe.gameobject.GameObject;
import org.medianik.tictactoe.gameobject.Nought;

import java.util.HashSet;
import java.util.Set;

import static org.medianik.tictactoe.Constants.TIME_PER_TICK;

public class GameManager{

    private final Set<GameObject> gameObjects;
    private final Timeline executor;
    private static GameManager instance;
    private int tick;

    public GameManager(){
        executor = new Timeline(new KeyFrame(Duration.millis(TIME_PER_TICK * 1000), GameManager::execute));
        gameObjects = new HashSet<>();
        tick = 0;
        instance = this;
    }

    public void start(){
        gameObjects.add(new Nought(0, 0, tick));
        gameObjects.add(new Cross(0, 0, tick));
        executor.setCycleCount(Animation.INDEFINITE);
        executor.play();
    }

    private static GameManager getInstance(){
        return instance;
    }

    public static void execute(ActionEvent actionEvent) {
        var instance = getInstance();
        var application = TicTacToe.getInstance();
//        System.out.printf("Time %d%n", instance.tick);
        instance.tick++;
        try {
            for (GameObject go : instance.gameObjects)
                go.tick(application.pane, instance.tick);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
