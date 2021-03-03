package org.medianik.tictactoe.gameobject;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import static java.lang.Math.PI;
import static org.medianik.tictactoe.Constants.*;

public class Cross extends Mark{

    private Rectangle leftStick;
    private Rectangle rightStick;

    public Cross(int x, int y, int startingTick){
        super(x, y, startingTick);
        animationProgress = 0;
    }

    /**
     * @param time is value from 0 to 1 stating that time*100% of animation is done
     */
    @Override
    public double animationSpeed(double time){
        return Math.pow(Math.sin(PI*time), 3)*normalizingCoefficient;
    }

    @Override
    public void animate(int tick){
        if(animationProgress < 1.){
            animationProgress += animationSpeed((tick - startingTick)*TIME_PER_TICK_IN_ANIMATION);
            if(leftStick == null || rightStick == null)
                createCross();

            leftStick.setRotate(LEFT_STICK_ROTATION_ANIMATION*animationProgress + 45);
            leftStick.setWidth(WIDTH_OF_STROKE*animationProgress);

            rightStick.setRotate(RIGHT_STICK_ROTATION_ANIMATION*animationProgress - 45);
            rightStick.setWidth(WIDTH_OF_STROKE*animationProgress);
        }
    }

    private void createCross(){
        leftStick = new Rectangle(0, SIZE_OF_MARK);
        leftStick.setTranslateX(getX());
        leftStick.setTranslateY(getY());
        leftStick.setFill(COLOR_OF_CROSS);
        rightStick = new Rectangle(0, SIZE_OF_MARK);
        rightStick.setTranslateX(getX());
        rightStick.setTranslateY(getY());
        rightStick.setFill(COLOR_OF_CROSS);
    }

    @Override
    public void display(Pane pane){
        if(isAlive() && !pane.getChildren().contains(leftStick))
            pane.getChildren().addAll(leftStick, rightStick);
    }
}
