package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.medianik.tictactoe.Player;
import org.medianik.tictactoe.TicTacToe;

import static org.medianik.tictactoe.Constants.*;

public class Grid implements GameObject{

    private final Rectangle[] lines;
    Mark[][] marks = new Mark[3][3];
    private boolean alive = true;

    public Grid(Pane pane){
        lines = initializeGrid();
        pane.getChildren().addAll(lines);
    }

    private Rectangle[] initializeGrid(){
        final Rectangle[] lines = new Rectangle[(ROW_SIZE - 1)*2];

        for(int i = 0; i < lines.length; i++)
            lines[i] = new Rectangle(WIDTH_OF_STROKE, 3*SIZE_OF_CELL, COLOR_OF_GRID);

        lines[0].setTranslateX( SIZE_OF_CELL/2);
        lines[1].setTranslateX(-SIZE_OF_CELL/2);
        lines[2].setTranslateY( SIZE_OF_CELL/2);
        lines[3].setTranslateY(-SIZE_OF_CELL/2);

        lines[2].setRotate(90);
        lines[3].setRotate(90);

        return lines;
    }

    @Override
    public void display(Pane pane){
        //pass
    }

    @Override
    public boolean isAlive(){
        return alive;
    }

    @Override
    public void destroy(int tick, Pane pane){
        GameObject.super.destroy(tick, pane);
        pane.getChildren().removeAll(lines);
        alive = false;
    }

    /**
     * Checks whether this click is supposed to place mark and places if needed.
     *
     * @return Placed mark or null if no changes have been made.
     */
    public Mark click(int x, int y, int tick, Player player){
        x = normalizeX(x);
        y = normalizeY(y);

        if(Math.max(x, y) > 3*SIZE_OF_CELL)
            return null;
        if(Math.min(x, y) < 0)
            return null;

        int column = (x - 1)/(int) SIZE_OF_CELL;
        int row = (y - 1)/(int) SIZE_OF_CELL;

        if(marks[column][row] == null){
            int newX = getCoordinate(column);
            int newY = getCoordinate(row);
            return marks[column][row] = player.getMarkCreator().create(newX, newY, tick);
        }
        return null;
    }

    /**
     * @param number number of row or column
     * @return coordinate of center where this mark supposed to be
     */
    private int getCoordinate(int number){
        return (int) (number*SIZE_OF_CELL - SIZE_OF_CELL);
    }

    /**
     * @param x coordinate related to left upper corner
     * @return x coordinate related to center
     */
    private static int normalizeX(int x){
        return (int) (x - TicTacToe.getInstance().getWidth()/2 + 1.5*SIZE_OF_CELL);
    }

    /**
     * @param y coordinate related to left upper corner
     * @return y coordinate related to center
     */
    private static int normalizeY(int y){
        return (int) (y - TicTacToe.getInstance().getHeight()/2 + 1.5*SIZE_OF_CELL);
    }

    /**
     * Checks whether player(supposed to be the last one that made turn) won the game.
     */
    public boolean checkWin(Mark m){
        int column = getNumber(m.getX());
        int row = getNumber(m.getY());

        return (marks[1][1] != null && checkWinDiagonal())
                || checkWinStraight(column, row);
    }

    /**
     * @return number of row or column for this coordinate
     */
    private int getNumber(int coordinate){
        return (int) Math.round((coordinate + SIZE_OF_CELL)/SIZE_OF_CELL);
    }

    /**
     * Checks whether three in row occurred in row or column containing this (x, y)
     */
    private boolean checkWinStraight(int x, int y){
        if(typeOf(marks[x][y]) == typeOf(marks[(x + 1)%3][y]))
            if(typeOf(marks[x][y]) == typeOf(marks[(x + 2)%3][y]))
                return true;

        if(typeOf(marks[x][y]) == typeOf(marks[x][(y + 1)%3]))
            return typeOf(marks[x][y]) == typeOf(marks[x][(y + 2)%3]);

        return false;
    }

    /**
     * Checks whether three in row occurred or not in one of diagonals
     */
    private boolean checkWinDiagonal(){
        if(typeOf(marks[1][1]) == typeOf(marks[2][2]))
            if(typeOf(marks[1][1]) == typeOf(marks[0][0]))
                return true;

        if(typeOf(marks[1][1]) == typeOf(marks[0][2]))
            return typeOf(marks[1][1]) == typeOf(marks[2][0]);

        return false;
    }

    private static Mark.Type typeOf(Mark m){
        if(m == null)
            return null;
        return m.getType();
    }
}
