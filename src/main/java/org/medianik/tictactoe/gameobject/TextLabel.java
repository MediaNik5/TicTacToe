package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.medianik.tictactoe.TicTacToe;

import static org.medianik.tictactoe.util.Constants.*;

public class TextLabel extends GameObject{

    private Text text;

    public TextLabel(String textToPrint, int x, int y, int size, int stroke){
        super(x, y);
        text = new Text();
        text.setTranslateX(x);
        text.setTranslateY(y);
        text.setFill(COLOR_OF_MESSAGES);
        text.setStroke(Color.BLACK);
        text.setStrokeWidth(stroke);
        text.setFont(Font.font(FONT_OF_MESSAGES, FontWeight.NORMAL, size));
        text.setText(textToPrint);
        text.setLineSpacing(4);
    }

    public void setText(String text){
        this.text.setText(text);
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
