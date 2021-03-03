package org.medianik.tictactoe;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.medianik.tictactoe.gameobject.GameObject;
import org.medianik.tictactoe.gameobject.Grid;
import org.medianik.tictactoe.gameobject.Mark;

import java.util.HashSet;
import java.util.Set;

import static org.medianik.tictactoe.Constants.TIME_PER_TICK;

public class GameManager{

    private final Set<GameObject> gameObjects;
    private final Timeline executor;
    private static GameManager instance;
    private Grid grid;
    private Pane pane;
    private final Player[] players;
    private int tick;

    public GameManager(){
        executor = new Timeline(new KeyFrame(Duration.millis(TIME_PER_TICK * 1000), GameManager::execute));
        gameObjects = new HashSet<>();
        players = new Player[]{new Player(Mark.Type.CROSS), new Player(Mark.Type.NOUGHT)};
        instance = this;
    }

    public void start(){
        tick = 0;

        pane = TicTacToe.getInstance().pane;
        pane.setOnMouseClicked(GameManager::click);

        grid = new Grid(pane);
        gameObjects.add(grid);

        executor.setCycleCount(Animation.INDEFINITE);
        executor.play();
    }

    public static GameManager getInstance(){
        return instance;
    }

    public static void execute(ActionEvent actionEvent) {
        var instance = getInstance();
//        var application = TicTacToe.getInstance();
        instance.tick++;
        try {
            for (GameObject go : instance.gameObjects)
                go.tick(instance.pane, instance.tick);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the Mouse clicking event. Supposed to be called by authorized javafx handler.
     */
    private static void click(MouseEvent event){
        var instance = getInstance();

        var currentPlayer = getCurrentPlayer(instance);

        Mark placedMark = instance.grid.click(
                (int)event.getX(),
                (int)event.getY(),
                instance.getTick(),
                currentPlayer
        );

        if(placedMark != null){
            instance.getPlayers()[0].changeTurn();
            instance.getPlayers()[1].changeTurn();
            instance.gameObjects.add(placedMark);
            if(instance.grid.checkWin(placedMark))
                System.out.println("WON");
        }
    }

    private static Player getCurrentPlayer(GameManager instance){
        return instance.getPlayers()[0].isTurn() ? instance.getPlayers()[0] : instance.getPlayers()[1];
    }

    private Player[] getPlayers(){
        return players;
    }

    public int getTick(){
        return tick;
    }
}
