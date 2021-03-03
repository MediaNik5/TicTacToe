package org.medianik.tictactoe;

import org.medianik.tictactoe.gameobject.Cross;
import org.medianik.tictactoe.gameobject.Mark;
import org.medianik.tictactoe.gameobject.Nought;

public class Player{

    private final Creator markCreator;

    private boolean turn;


    public Player(Mark.Type side){
        this.turn = side == Mark.Type.CROSS;
        if(side == Mark.Type.CROSS)
            markCreator = Cross::new;
        else markCreator = Nought::new;
    }

    public Creator getMarkCreator(){
        return markCreator;
    }

    public boolean isTurn(){
        return turn;
    }

    public void changeTurn(){
        turn = !turn;
    }

    public interface Creator<T extends Mark>{
        T create(int x, int y, int startingTick);
    }
}
