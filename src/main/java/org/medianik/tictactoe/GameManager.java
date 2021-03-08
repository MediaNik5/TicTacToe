package org.medianik.tictactoe;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.jetbrains.annotations.Nullable;
import org.medianik.tictactoe.gameobject.*;
import org.medianik.tictactoe.player.Player;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.medianik.tictactoe.util.Constants.*;
import static org.medianik.tictactoe.util.Util.isPresent;

public class GameManager{

    private final static GameManager instance = new GameManager();
    public static GameManager getInstance(){
        return instance;
    }

    private final Set<GameObject> gameObjects;
    private Timeline executor;
    private Grid grid;
    private Pane pane;
    private final Player[] players;
    private int tick;

    public GameManager(){
        gameObjects = new HashSet<>();
        players = new Player[2];
    }

    private void executeTick(@Nullable ActionEvent actionEvent){
        nextTick();
        Set<GameObject> destroyed = new HashSet<>();
        try {
            for(GameObject go : gameObjects){
                go.tick(tick, pane);
                if(go.destroyReady()){
                    go.destroy(tick, pane);
                    destroyed.add(go);
                }
            }
        }catch(Throwable e){
            e.printStackTrace(); //should not happen
        }finally{
            gameObjects.removeAll(destroyed);
        }
    }

    private void nextTick(){
        instance.tick++;
    }

    public void start(){
        tick = 0;

        initializePlayers();

        pane = TicTacToe.getInstance().pane;
        pane.setOnKeyReleased(instance::pressKey);

        executor = new Timeline(new KeyFrame(Duration.millis(TIME_PER_TICK*1000), instance::executeTick));
        executor.setCycleCount(Animation.INDEFINITE);
        executor.play();
    }

    private void initializePlayers(){
        TextInput player1 = new TextInput("Player 1", -DISTANCE_BETWEEN_INPUTS_X/2, -DISTANCE_BETWEEN_INPUTS_Y/2, SIZE_OF_STATS);
        TextInput player2 = new TextInput("Player 2", DISTANCE_BETWEEN_INPUTS_X/2, DISTANCE_BETWEEN_INPUTS_Y/2, SIZE_OF_STATS);
        gameObjects.add(player1);
        gameObjects.add(player2);
    }

    private void pressKey(KeyEvent e){
        for(var go : gameObjects)
            if(go.isKeyToHandle(e.getCode()))
                go.handleKey(e.getCode());
    }

    public void addPlayer(String name){
        if(players[0] == null){
            players[0] = new Player(name, Mark.Type.CROSS);
        }else if(players[1] == null){
            players[1] = new Player(name, Mark.Type.NOUGHT);
            initializeGrid();
        }else
            throw new IllegalStateException("Cannot add more than two players.");
    }

    private void initializeGrid(){
        pane.setOnMouseClicked(instance::click);

        grid = new Grid();
        gameObjects.add(grid);

        gameObjects.add(players[0].getStats().getLabel());
        gameObjects.add(players[1].getStats().getLabel());
    }

    /**
     * Handles the Mouse clicking event. Supposed to be called by authorized javafx handler.
     */
    private void click(MouseEvent event){
        var currentPlayer = getCurrentPlayer();

        Mark placedMark = grid.click(
                (int) event.getX(),
                (int) event.getY(),
                getTick(),
                currentPlayer
        );

        if(isPresent(placedMark)){
            gameObjects.add(placedMark);

            if(grid.checkWin(placedMark))
                win();
            else if(grid.isFull())
                tie();
            else{
                getPlayers()[0].changeTurn();
                getPlayers()[1].changeTurn();
            }
        }
    }

    private Player getCurrentPlayer(){
        Player[] players = getPlayers();
        return players[0].isTurn() ? players[0] : players[1];
    }

    private Player[] getPlayers(){
        return players;
    }

    private void win(){
        Mark.Type winner = updatePlayerStats();

        var text = String.format(WIN_TEXT, winner);

        gameObjects.add(new TextLabel(text, 0, 0, SIZE_OF_MESSAGES, 2));
        delayRestart();
    }

    /**
     * @return the winner side
     */
    private Mark.Type updatePlayerStats(){
        Mark.Type winner = null;
        for(Player p : getPlayers())
            if(p.updateStats())
                winner = p.getSide();
        return winner;
    }

    private void delayRestart(){
        pane.onMouseClickedProperty().setValue((e) -> {});
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), instance::restart));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void restart(ActionEvent e){
        //stop
        for(var go : gameObjects){
            go.destroy(tick, pane);
        }
        gameObjects.clear();

        //start
        for(var p : players){
            p.getStats().initializeLabel();
            gameObjects.add(p.getStats().getLabel());
            p.newGame();
        }

        grid = new Grid();
        gameObjects.add(grid);
        pane.onMouseClickedProperty().setValue(instance::click);
    }

    private void tie(){
        instance.gameObjects.add(new TextLabel(TIE_TEXT, 0, 0, SIZE_OF_MESSAGES, 2));
        delayRestart();
    }

    public int getTick(){
        return tick;
    }

    public void onClose() throws IOException{
        for(Player p : players)
            if(isPresent(p))
                p.getStats().saveStats();
        for(var go : gameObjects)
            go.destroy(getTick(), pane);
    }
}
