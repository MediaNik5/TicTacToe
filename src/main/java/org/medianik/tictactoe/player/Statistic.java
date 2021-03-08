package org.medianik.tictactoe.player;

import org.medianik.tictactoe.TicTacToe;
import org.medianik.tictactoe.gameobject.*;
import org.medianik.tictactoe.util.Constants;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.medianik.tictactoe.util.Constants.*;

public class Statistic{

    private final Mark.Type type;
    private final String name;
    private BigInteger wins;
    private BigInteger loses;
    private TextLabel label;

    private Statistic(String name, Mark.Type type, BigInteger wins, BigInteger loses){
        this.name = name;
        this.wins = wins;
        this.loses = loses;
        this.type = type;
        initializeLabel();
    }

    public void initializeLabel(){
        label = new TextLabel(
                String.format(STAT_LABEL, name, wins, loses),
                0,
                -TicTacToe.getInstance().getHeight()/2 + GLOBAL_OFFSET + (type == Mark.Type.CROSS ? GLOBAL_OFFSET : 0),
                Constants.SIZE_OF_STATS,
                0);
    }

    public static Statistic loadStats(String name, Mark.Type type){
        if(Files.exists(Path.of(Constants.SAVES_PATH + name + ".data"))){
            BigInteger[] stats = null;
            try {
                stats = Files
                        .readAllLines(Path.of(Constants.SAVES_PATH + name + ".data"))
                        .stream()
                        .map(BigInteger::new)
                        .toArray(BigInteger[]::new);
            }catch(IOException e){
                e.printStackTrace(); // this should never be called.
            }
            assert stats != null;
            var out = new Statistic(name, type, stats[0], stats[1]);
            out.checkRange();
            out.initializeLabel();
            return out;
        }
        return new Statistic(name, type, BigInteger.ZERO, BigInteger.ZERO);
    }

    private void checkRange(){
        if(wins.compareTo(MAX_SCORE) > 0)
            wins = MAX_SCORE.negate().add(wins.subtract(MAX_SCORE).mod(MAX_SCORE));
        if(loses.compareTo(MAX_SCORE) > 0)
            loses = MAX_SCORE.negate().add(loses.subtract(MAX_SCORE).mod(MAX_SCORE));
    }

    public void saveStats() throws IOException{
        Files.createDirectories(Path.of(Constants.SAVES_PATH));
        Files.writeString(Path.of(Constants.SAVES_PATH + name + ".data"), wins + "\n" + loses);
    }

    public void increaseWins(){
        wins = wins.add(BigInteger.ONE);
        checkRange();
        label.setText(String.format(STAT_LABEL, name, wins, loses));
    }

    public void increaseLoses(){
        loses = loses.add(BigInteger.ONE);
        checkRange();
        label.setText(String.format(STAT_LABEL, name, wins, loses));
    }

    public BigInteger getLoses(){
        return loses;
    }

    public BigInteger getWins(){
        return wins;
    }

    public TextLabel getLabel(){
        return label;
    }
}
