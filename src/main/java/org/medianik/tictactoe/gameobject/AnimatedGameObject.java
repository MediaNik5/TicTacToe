package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;

import static java.lang.Math.PI;
import static org.medianik.tictactoe.util.Constants.TICKS_PER_ANIMATION;
import static org.medianik.tictactoe.util.Constants.TIME_PER_TICK_IN_ANIMATION;

public abstract class AnimatedGameObject extends GameObject{

    /**
     * @param time is value from 0 to 1 stating that time*100% of animation is done
     */

    protected double animationProgress;
    protected double normalizingCoefficient;
    protected final int startingTick;

    protected AnimatedGameObject(int startingTick, int x, int y){
        super(x, y);
        this.startingTick = startingTick;
        calculateNormalizingCoefficient();
    }

    protected void calculateNormalizingCoefficient(){
        normalizingCoefficient = 1.;
        double time = 0;
        double sum = 0;
        for(int i = 0; i < TICKS_PER_ANIMATION; i++){
            sum += animationSpeed(time);
            time = i*TIME_PER_TICK_IN_ANIMATION;
        }
        normalizingCoefficient /= sum;
    }

    /**
     * @param time is value from 0 to 1 stating that time*100% of animation is done
     */
    public double animationSpeed(double time){
        return Math.pow(Math.sin(PI*time), 3)*normalizingCoefficient;
    }

    public abstract void animate(int tick);

    @Override
    public void tick(int tick, Pane pane){
        if(isAlive()){
            animate(tick);
            display(pane);
        }
    }

    @Override
    public void destroy(int tick, Pane pane){
        super.destroy(tick, pane);
    }
}
