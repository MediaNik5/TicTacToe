package org.medianik.tictactoe.util;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.math.BigInteger;

public final class Constants{
    public static final int ROW_SIZE = 3;

    public static final int GLOBAL_OFFSET = 40;
    public static final int MAX_WIDTH = 3000;
    public static final int MAX_HEIGHT = 2000;

    public static final int TICKS_IN_SECOND = 60;
    public static final double TIME_PER_TICK = 1./TICKS_IN_SECOND;

    public static final double TIME_OF_ANIMATION = 1;
    public static final int TICKS_PER_ANIMATION = (int) (TICKS_IN_SECOND*TIME_OF_ANIMATION);
    public static final double TIME_PER_TICK_IN_ANIMATION = 1./TICKS_PER_ANIMATION;

    public static final double SIZE_OF_CELL = 200.;
    public static final double SIZE_OF_MARK = SIZE_OF_CELL - GLOBAL_OFFSET;
    public static final double WIDTH_OF_STROKE = 5;
    public static final Color COLOR_OF_NOUGHT = Color.WHITE;
    public static final Color COLOR_OF_CROSS = Color.WHITE;
    public static final Color COLOR_OF_GRID = Color.WHITE;
    public static final Paint COLOR_OF_MESSAGES = Paint.valueOf("#d15271");
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color STROKE_OF_MESSAGES = Color.gray(0.15);
    public static final String FONT_OF_MESSAGES = "Centaur";
    public static final int DISTANCE_BETWEEN_INPUTS_X = 0;
    public static final int DISTANCE_BETWEEN_INPUTS_Y = 100;
    public static final int SIZE_OF_STATS = 40;
    public static final int SIZE_OF_FIELD = 200;
    public static final int SIZE_OF_MESSAGES = 130;

    public static final String WIN_TEXT = "%s won!";
    public static final String TIE_TEXT = "Tie!";
    public static final String STAT_LABEL = "%s's wins: %d, loses: %d";

    public static final BigInteger MAX_SCORE = new BigInteger("100000000000000000000000000000000000000000000000000000000000000000");


    public static final int LEFT_STICK_ROTATION_ANIMATION = 270;
    public static final int RIGHT_STICK_ROTATION_ANIMATION = 450;
    public static final String SAVES_PATH = System.getProperty("user.dir") + "/saves/";
}
