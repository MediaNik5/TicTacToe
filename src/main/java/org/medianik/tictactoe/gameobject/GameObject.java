package org.medianik.tictactoe.gameobject;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class GameObject{

    private boolean alive;
    protected boolean destroyReady;

    protected final int x;
    protected final int y;

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
        this.alive = true;
        this.destroyReady = false;
    }

    public void tick(int tick, Pane pane){
        if(isAlive())
            display(pane);
    }

    public boolean isKeyToHandle(KeyCode key){
        return false;
    }

    public void handleKey(KeyCode key){
        if(!isKeyToHandle(key))
            throw new IllegalArgumentException(String.format("Cannot handle the %s event.", key));
    }

    abstract void display(Pane pane);

    public boolean isAlive(){
        return alive;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void destroy(int tick, Pane pane){
//        System.out.println("Called destroy on " + this.getClass());
        if(!isAlive())
            throw new IllegalStateException("State of GameObject is dead, why call destroy?");
        alive = false;
    }

    public boolean destroyReady(){
        return destroyReady && alive;
    }
}
