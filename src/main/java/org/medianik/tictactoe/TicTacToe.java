package org.medianik.tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.InputStream;

import static org.medianik.tictactoe.util.Constants.*;

/**
 * JavaFX App
 */
public class TicTacToe extends Application{

    static {
        // https://stackoverflow.com/questions/66488204/javafx-when-i-create-new-instance-of-textfield-whole-window-becomes-white-ish
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    private static TicTacToe instance;
    public static TicTacToe getInstance(){
        return instance;
    }

    private final int width;
    private final int height;
    final Pane pane;
    private final GameManager manager;

    public TicTacToe(){
        width = calculateWidth();
        height = calculateHeight();
        pane = new StackPane();
        manager = GameManager.getInstance();
        instance = this;
    }

    private int calculateWidth(){
        var bounds = Screen.getPrimary().getBounds();
        return Math.min((int) bounds.getWidth() - GLOBAL_OFFSET, MAX_WIDTH);
    }

    private int calculateHeight(){
        var bounds = Screen.getPrimary().getBounds();
        return Math.min((int) bounds.getHeight() - 2*GLOBAL_OFFSET, MAX_HEIGHT);
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

    private void setupIcon(Stage stage){
        InputStream inputIcon = getClass().getResourceAsStream("/icon.png");
        Image icon = new Image(inputIcon);
        stage.getIcons().add(icon);
    }

    @Override
    public void stop() throws Exception{
        System.out.println("close");
        manager.onClose();
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}