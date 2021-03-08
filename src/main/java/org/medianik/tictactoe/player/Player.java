package org.medianik.tictactoe.player;

import org.medianik.tictactoe.gameobject.Cross;
import org.medianik.tictactoe.gameobject.Mark;
import org.medianik.tictactoe.gameobject.Nought;
import org.medianik.tictactoe.player.Statistic;

public class Player{

    private final Creator markCreator;
    private boolean turn;
    private final Statistic stats;
    private final Mark.Type side;

    public Player(String name, Mark.Type side){
        this.side = side;
        if(side == Mark.Type.CROSS)
            markCreator = Cross::new;
        else markCreator = Nought::new;
        stats = Statistic.loadStats(name, side);
        newGame();
    }

    public void newGame(){
        this.turn = getSide() == Mark.Type.CROSS;
    }

    public Mark.Type getSide(){
        return side;
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

    /**
     * @return true if wins got increased false otherwise.
     */
    public boolean updateStats(){
        if(isTurn())
            getStats().increaseWins();
        else
            getStats().increaseLoses();
        return isTurn();
    }

    public interface Creator<T extends Mark>{
        T create(int x, int y, int startingTick);
    }
}
