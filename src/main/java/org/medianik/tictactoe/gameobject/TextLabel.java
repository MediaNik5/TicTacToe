package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static org.medianik.tictactoe.util.Constants.*;

public class TextLabel extends GameObject{

    private Text text;

    public TextLabel(String textToPrint, int x, int y, int size){
        super(x, y);
        text = new Text();
        text.setTranslateX(x);
        text.setTranslateY(y);
        text.setFill(COLOR_OF_MESSAGES);
        text.setStroke(STROKE_OF_MESSAGES);
        text.setStrokeWidth(WIDTH_OF_STROKE/2);
        text.setFont(Font.font(FONT_OF_MESSAGES, FontWeight.NORMAL, size));
        text.setText(textToPrint);
        text.setFill(new Color(1, 1, 1, 1));
    }

    @Override
    public void display(Pane pane){
        if(!pane.getChildren().contains(text))
            pane.getChildren().add(text);
    }

    @Override
    public void destroy(int tick, Pane pane){
        super.destroy(tick, pane);
        pane.getChildren().remove(text);
        text = null;
    }

}
