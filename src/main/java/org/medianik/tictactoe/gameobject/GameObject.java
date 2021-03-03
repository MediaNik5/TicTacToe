package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public interface GameObject{

    default void tick(Pane pane, int tick){
        if(isAlive())
            display(pane);
    }



    void display(Pane pane);

    boolean isAlive();

    default void destroy(int tick, Pane pane){
        if(!isAlive())
            throw new IllegalStateException("State of GameObject is dead, why call destroy?");
    }
}
