package org.medianik.tictactoe.gameobject;

public abstract class Mark extends AnimatedGameObject{

    protected final Type type;

    protected Mark(int x, int y, int startingTick){
        super(startingTick, x, y);
        if(this instanceof Cross)
            type = Type.CROSS;
        else
            type = Type.NOUGHT;
    }

    public Type getType(){
        return type;
    }

    public enum Type{
        CROSS,
        NOUGHT
    }
}
