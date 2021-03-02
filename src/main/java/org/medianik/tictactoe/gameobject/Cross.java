package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;

import static java.lang.Math.PI;

public class Cross extends Mark{


    protected Cross(int x, int y, int startingTick) {
        super(x, y, startingTick);
    }

    /**
     * @param time is value from 0 to 1 stating that time*100% of animation is done
     */
    @Override
    public double animationSpeed(double time) {
        return Math.pow(Math.sin(PI * time), 3) * normalizingCoefficient;
    }

    @Override
    public void animate(int tick) {

    }

    @Override
    public void display(Pane pane) {

    }
}
