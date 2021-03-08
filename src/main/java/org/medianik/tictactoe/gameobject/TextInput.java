package org.medianik.tictactoe.gameobject;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.medianik.tictactoe.GameManager;
import org.medianik.tictactoe.TicTacToe;
import org.medianik.tictactoe.player.Player;

import static org.medianik.tictactoe.util.Constants.*;

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
    public boolean isKeyToHandle(KeyCode key){
        return key == KeyCode.ENTER || key == KeyCode.TAB;
    }

    @Override
    public void handleKey(KeyCode key){
        if(key == KeyCode.TAB){
            if(textField.isFocused())
                return;
            if(textField.getText().isBlank())
                return;
        }else if(!textField.isFocused())
            return;
        GameManager.getInstance().addPlayer(textField.getText());
        destroyReady = true;
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
