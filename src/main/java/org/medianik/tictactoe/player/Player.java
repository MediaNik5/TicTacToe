package org.medianik.tictactoe.player;

import org.medianik.tictactoe.gameobject.Cross;
import org.medianik.tictactoe.gameobject.Mark;
import org.medianik.tictactoe.gameobject.Nought;
import org.medianik.tictactoe.player.Statistic;

public class Player{

    private final Creator markCreator;

    private boolean turn;

    private final Statistic stats;

    public Player(String name, Mark.Type side){
        this.turn = side == Mark.Type.CROSS;
        if(side == Mark.Type.CROSS)
            markCreator = Cross::new;
        else markCreator = Nought::new;
        stats = Statistic.loadStats(name);
    }

    public Creator getMarkCreator(){
        return markCreator;
    }

    public Statistic getStats(){
        return stats;
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
