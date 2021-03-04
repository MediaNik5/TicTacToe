package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;

import static java.lang.Math.PI;
import static org.medianik.tictactoe.Constants.TICKS_PER_ANIMATION;
import static org.medianik.tictactoe.Constants.TIME_PER_TICK_IN_ANIMATION;

public abstract class AnimatedGameObject implements GameObject{

    /**
     * @param time is value from 0 to 1 stating that time*100% of animation is done
     */

    protected double animationProgress;
    protected double normalizingCoefficient;
    protected final int startingTick;
    protected final int x;
    protected final int y;

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    private boolean alive = true;

    protected AnimatedGameObject(int startingTick, int x, int y){
        this.startingTick = startingTick;
        this.x = x;
        this.y = y;
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
    public void tick(Pane pane, int tick){
        if(isAlive()){
            animate(tick);
            display(pane);
        }
    }

    @Override
    public boolean isAlive(){
        return alive;
    }

    @Override
    public void destroy(int tick, Pane pane){
        GameObject.super.destroy(tick, pane);
        alive = false;
    }
}
