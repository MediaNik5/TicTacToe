package org.medianik.tictactoe.jfxhelper;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import lombok.AllArgsConstructor;

import static org.medianik.tictactoe.Constants.*;

public class CenteredArc{

    /**
     * Generates an arc of ArcType
     *
     * @param x    the center x coord
     * @param y    the center y coord
     * @param type the type of arc
     * @return Group with coordinates (x, y) with built-in arc
     */
    public static Group centeredArc(double x, double y, ArcType type){
        Arc arc = new Arc(
                type.sizeOfCell/2, type.sizeOfCell/2,
                type.sizeOfMark/2, type.sizeOfMark/2,
                0,
                0
        );

        arc.setStrokeWidth(type.widthOfStroke);
        arc.setStrokeLineCap(StrokeLineCap.ROUND);
        arc.setStroke(type.colorOfNought);
        arc.setFill(Color.TRANSPARENT);

        // Create a background fill on which the arc will be centered.
        // The paint of the background fill can be set to Color.TRANSPARENT
        // if you don't want the fill to be seen.
        Rectangle fill = new Rectangle(type.sizeOfCell, type.sizeOfCell);
        fill.setFill(Color.TRANSPARENT);

        Group group = new Group(fill, arc);
        group.setTranslateX(x);
        group.setTranslateY(y);

        // Place the fill and the arc in the group.
        // The Group will be a fixed sized matching the fill size.
        return group;
    }

    @AllArgsConstructor
    public enum ArcType{
        Mark(SIZE_OF_MARK, SIZE_OF_CELL, WIDTH_OF_STROKE, COLOR_OF_NOUGHT);

        private final double sizeOfMark;
        private final double sizeOfCell;
        private final double widthOfStroke;
        private final Color colorOfNought;
    }
}