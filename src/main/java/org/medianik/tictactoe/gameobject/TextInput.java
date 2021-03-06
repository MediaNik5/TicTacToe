package org.medianik.tictactoe.gameobject;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static org.medianik.tictactoe.util.Constants.FONT_OF_MESSAGES;
import static org.medianik.tictactoe.util.Constants.SIZE_OF_FIELD;

public class TextInput extends GameObject{

    private TextField textField;
    public TextInput(String textToAsk, int x, int y, int size){
        super(x, y);
        textField = new TextField();
        textField.setTranslateX(x);
        textField.setTranslateY(y);
        textField.setMaxHeight(size);
        textField.setMaxWidth(SIZE_OF_FIELD);
        textField.setFont(Font.font(FONT_OF_MESSAGES, FontWeight.NORMAL, size));
        textField.setPromptText(textToAsk);
    }

    @Override
    public void display(Pane pane){
        if(!pane.getChildren().contains(textField))
            pane.getChildren().add(textField);
    }

    @Override
    public void destroy(int tick, Pane pane){
        super.destroy(tick, pane);
        pane.getChildren().remove(textField);
        textField = null;
    }
}
