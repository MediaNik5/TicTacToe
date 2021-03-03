package org.medianik.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.InputStream;

import static org.medianik.tictactoe.Constants.*;

/**
 * JavaFX App
 */
public class TicTacToe extends Application{

    private static TicTacToe instance;

    private final int width;
    private final int height;
    final StackPane pane;
    private final GameManager manager;

    public TicTacToe(){
        width = calculateInitialWidth();
        height = calculateInitialHeight();
        pane = new StackPane();
        manager = new GameManager();
        instance = this;
    }

    public static TicTacToe getInstance(){
        return instance;
    }

    public static void main(String[] args){
        launch();
    }


    /**
     * The entry point
     */
    @Override
    public void start(Stage stage){
        var scene = new Scene(pane, width, height, BACKGROUND_COLOR);
        stage.setScene(scene);
        stage.show();
        setupIcon(stage);

        manager.start();
    }


    private int calculateInitialWidth(){
        var bounds = Screen.getPrimary().getBounds();
        return Math.min((int) bounds.getWidth() - GLOBAL_OFFSET, MAX_WIDTH);
    }

    private int calculateInitialHeight(){
        var bounds = Screen.getPrimary().getBounds();
        return Math.min((int) bounds.getHeight() - GLOBAL_OFFSET, MAX_HEIGHT);
    }

    private void setupIcon(Stage stage){
        InputStream inputIcon = getClass().getResourceAsStream("/icon.png");
        Image icon = new Image(inputIcon);
        stage.getIcons().add(icon);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}