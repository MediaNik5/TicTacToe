package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;

public abstract class GameObject{

    private boolean alive;

    protected final int x;
    protected final int y;

    public GameObject(int x, int y){
        this.x = x;
        this.y = y;
        this.alive = true;
    }

    public void tick(Pane pane, int tick){
        if(isAlive())
            display(pane);
    }

    abstract void display(Pane pane);

    boolean isAlive(){
        return alive;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void destroy(int tick, Pane pane){
        alive = false;
        System.out.println("Called destroy on " + this.getClass());
        if(!isAlive())
            throw new IllegalStateException("State of GameObject is dead, why call destroy?");
    }
}
