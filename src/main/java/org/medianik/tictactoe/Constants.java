package org.medianik.tictactoe;

import javafx.scene.paint.Color;

public final class Constants{
    public static final int ROW_SIZE = 3;

    public static final int GLOBAL_OFFSET = 30;
    public static final int MAX_WIDTH = 1000;
    public static final int MAX_HEIGHT = 800;

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
    public static final Color BACKGROUND_COLOR = Color.BLACK;


    public static final int LEFT_STICK_ROTATION_ANIMATION = 270;
    public static final int RIGHT_STICK_ROTATION_ANIMATION = 450;
}
