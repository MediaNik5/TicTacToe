package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public interface GameObject{

    default void tick(StackPane pane, int tick){
        if(isAlive())
            display(pane);
    }

    void display(Pane pane);

    boolean isAlive();

    void destroy(int tick, Pane pane);
}
