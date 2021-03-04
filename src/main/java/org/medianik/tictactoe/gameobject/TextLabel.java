package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Arrays;

import static org.medianik.tictactoe.Constants.*;

public class TextLabel extends AnimatedGameObject{

    private final Text text;

    public TextLabel(String textToPrint, int startingTick, int x, int y){
        super(startingTick, x, y);
        text = new Text();
        text.setFill(COLOR_OF_MESSAGES);
        text.setStroke(STROKE_OF_MESSAGES);
        text.setStrokeWidth(WIDTH_OF_STROKE/2);
        text.setFont(Font.font(FONT_OF_MESSAGES, FontWeight.NORMAL, SIZE_OF_MESSAGES));
        text.setText(textToPrint);
        text.setFill(new Color(1, 1, 1, 0));
    }

    @Override
    public void animate(int tick){
        if(animationProgress <= 1.){
            animationProgress += animationSpeed((tick - startingTick)*TIME_PER_TICK_IN_ANIMATION);
            if(animationProgress <= 1.)
                text.setFill(new Color(1, 1, 1, animationProgress));

//            text.setText(textToPrint.substring(0, (int) (textToPrint.length()*animationProgress)));
        }
    }

    @Override
    public void display(Pane pane){
        if(!pane.getChildren().contains(text))
            pane.getChildren().add(text);
    }
}
