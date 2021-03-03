package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;

import static org.medianik.tictactoe.Constants.TICKS_PER_ANIMATION;
import static org.medianik.tictactoe.Constants.TIME_PER_TICK_IN_ANIMATION;

public abstract class Mark implements AnimatedGameObject{

    protected final int x;
    protected final int y;
    protected final int startingTick;
    protected double animationProgress;
    protected double normalizingCoefficient;
    protected final Type type;
    private boolean alive = true;

    protected Mark(int x, int y, int startingTick){
        this.x = x;
        this.y = y;
        this.startingTick = startingTick;
        calculateNormalizingCoefficient();
        if(this instanceof Cross)
            type = Type.CROSS;
        else
            type = Type.NOUGHT;
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

    @Override
    public boolean isAlive(){
        return alive;
    }

    @Override
    public void destroy(int tick, Pane pane){
        AnimatedGameObject.super.destroy(tick, pane);
        alive = false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Type getType(){
        return type;
    }

    public enum Type{
        CROSS,
        NOUGHT
    }
}
