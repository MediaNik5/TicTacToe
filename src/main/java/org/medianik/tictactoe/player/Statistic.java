package org.medianik.tictactoe.player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Statistic{

    private final String name;
    private int wins = 0;
    private int loses = 0;

    private Statistic(String name, int wins, int loses){
        this.name = name;
        this.wins = wins;
        this.loses = loses;
    }

    public static Statistic loadStats(String name){
        if(Files.exists(Path.of(name + ".data"))){
            int[] stats = null;
            try {
                 stats = Files.readAllLines(Path.of(name + ".data")).stream().mapToInt(Integer::parseInt).toArray();
            }catch(IOException e){
                e.printStackTrace(); // this should never be called.
            }
            assert stats != null;
            return new Statistic(name, stats[0], stats[1]);
        }
        return new Statistic(name, 0, 0);
    }
    public void saveStats() throws IOException{
        Files.writeString(Path.of(name + ".data"), wins + "\n" + loses);
    }

    public void increaseWins(){
        wins++;
    }

    public void increaseLoses(){
        loses++;
    }

    public int getLoses(){
        return loses;
    }

    public int getWins(){
        return wins;
    }
}
