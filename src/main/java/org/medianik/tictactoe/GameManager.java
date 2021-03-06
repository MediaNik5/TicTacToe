package org.medianik.tictactoe;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.medianik.tictactoe.gameobject.*;
import org.medianik.tictactoe.player.Player;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.medianik.tictactoe.util.Constants.*;

public class GameManager{

    private final static GameManager instance = new GameManager();
    public static GameManager getInstance(){
        return instance;
    }

    private final Set<GameObject> gameObjects;
    private final Timeline executor;
    private Grid grid;
    private Pane pane;
    private final Player[] players;
    private int tick;

    public GameManager(){
        executor = new Timeline(new KeyFrame(Duration.millis(TIME_PER_TICK*1000), GameManager::execute));
        gameObjects = new HashSet<>();
        players = new Player[2];
    }

    public void start(){
        tick = 0;

        initializePlayers();

        pane = TicTacToe.getInstance().pane;
//        pane.setOnMouseClicked(GameManager::click);

//        grid = new Grid();
//        gameObjects.add(grid);
//        gameObjects.add(new TextInput("Privet", 0, 0));

//        players[0] = new Player("MediaNik", Mark.Type.CROSS);
//        players[1] = new Player("Twelve", Mark.Type.NOUGHT);

        executor.setCycleCount(Animation.INDEFINITE);
        executor.play();
    }

    private void initializePlayers(){
        var instance = TicTacToe.getInstance();
        TextInput player1 = new TextInput("Player 1", -DISTANCE_BETWEEN_INPUTS_X/2, -DISTANCE_BETWEEN_INPUTS_Y/2, SIZE_OF_STATS);
        TextInput player2 = new TextInput("Player 2", DISTANCE_BETWEEN_INPUTS_X/2, DISTANCE_BETWEEN_INPUTS_Y/2, SIZE_OF_STATS);
        gameObjects.add(player1);
        gameObjects.add(player2);
    }


    private static void execute(ActionEvent actionEvent){
        var instance = getInstance();
//        var application = TicTacToe.getInstance();
        instance.increaseTick();
        try {
            for(GameObject go : instance.gameObjects)
                go.tick(instance.pane, instance.tick);
        }catch(Throwable e){
            e.printStackTrace();
        }
    }

    private void increaseTick(){
        instance.tick++;
    }

    /**
     * Handles the Mouse clicking event. Supposed to be called by authorized javafx handler.
     */
    private static void click(MouseEvent event){
        var instance = getInstance();
        var currentPlayer = getCurrentPlayer(instance);

        Mark placedMark = instance.grid.click(
                (int) event.getX(),
                (int) event.getY(),
                instance.getTick(),
                currentPlayer
        );

        if(isPresent(placedMark)){
            instance.gameObjects.add(placedMark);

            if(instance.grid.checkWin(placedMark))
                win();

            instance.getPlayers()[0].changeTurn();
            instance.getPlayers()[1].changeTurn();
        }
    }


    private static boolean isPresent(Mark placedMark){
        return placedMark != null;
    }

    private static Player getCurrentPlayer(GameManager instance){
        Player[] players = instance.getPlayers();
        return players[0].isTurn() ? players[0] : players[1];
    }

    private Player[] getPlayers(){
        return players;
    }

    public int getTick(){
        return tick;
    }

    private static void win(){
        getInstance().gameObjects.add(new TextLabel(WIN_TEXT, 0, 0, SIZE_OF_MESSAGES));
        Player[] players = instance.getPlayers();
        if(players[0].isTurn()){
            players[0].getStats().increaseWins();
            players[1].getStats().increaseLoses();
        }else{
            players[1].getStats().increaseWins();
            players[0].getStats().increaseLoses();
        }
    }

    public void stop() throws IOException{
        for(Player p : players)
            p.getStats().saveStats();
        for(var go : gameObjects)
            go.destroy(getTick(), pane);
    }
}
