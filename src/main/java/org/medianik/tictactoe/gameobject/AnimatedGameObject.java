package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.StackPane;

public interface AnimatedGameObject extends GameObject{

    /**
     * @param time is value from 0 to 1 stating that time*100% of animation is done
     */

    double animationSpeed(double time);

    void animate(int tick);

    @Override
    default void tick(StackPane pane, int tick){
        if(isAlive()){
            animate(tick);
            display(pane);
        }
    }
}
