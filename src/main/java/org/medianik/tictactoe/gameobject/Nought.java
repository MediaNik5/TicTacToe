package org.medianik.tictactoe.gameobject;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import org.medianik.tictactoe.jfxhelper.CenteredArc;

import static java.lang.Math.PI;
import static org.medianik.tictactoe.Constants.TIME_PER_TICK_IN_ANIMATION;

public class Nought extends Mark{

    private Group group;
    private Arc arc;

    public Nought(int x, int y, int startingTick){
        super(x, y, startingTick);
        animationProgress = 0;
    }

    /**
     * @param time is value from 0 to 1 stating that time*100% of animation is done
     */
    @Override
    public double animationSpeed(double time){
        return Math.pow(Math.sin(PI*time), 7)*normalizingCoefficient; //sin(pi*x)^7
    }

    @Override
    public void animate(int tick){
        if(animationProgress < 1.){
            animationProgress += animationSpeed((tick - startingTick)*TIME_PER_TICK_IN_ANIMATION);
            if(group == null){
                group = CenteredArc.centeredArc(getX(), getY(), CenteredArc.ArcType.Mark);
                arc = (Arc) group.getChildren().get(1);
            }else
                arc.setLength(360*animationProgress);
        }else
            arc.setType(ArcType.OPEN);
    }


    @Override
    public void display(Pane pane){
        if(isAlive() && !pane.getChildren().contains(group))
            pane.getChildren().add(group);
        else if(!isAlive())
            pane.getChildren().remove(group);
    }

    @Override
    public void destroy(int tick, Pane pane){
        super.destroy(tick, pane);
        pane.getChildren().remove(group);
        group.getChildren().clear();
        arc = null;
        group = null;
    }
}
