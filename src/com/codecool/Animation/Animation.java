package com.codecool.Animation;

import com.codecool.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.List;

public class Animation {

    public void slideToDest(Card cardToSlide, Pile destPile) {
        if (cardToSlide == null){
            return;
        }
            
        double targetX = destPile.getLayoutX();
        double targetY = destPile.getLayoutY();

        double sourceX = cardToSlide.getLayoutX() + cardToSlide.getTranslateX();
        double sourceY = cardToSlide.getLayoutY() + cardToSlide.getTranslateY();

        animateCardMovement(cardToSlide, sourceX, sourceY,
                targetX, targetY, Duration.millis(150));
    }

    private void animateCardMovement(
            Card card, double sourceX, double sourceY,
            double targetX, double targetY, Duration duration) {

        Path path = new Path();
        path.getElements().add(new MoveToAbs(card, sourceX, sourceY));
        path.getElements().add(new LineToAbs(card, targetX, targetY));

        PathTransition pathTransition = new PathTransition(duration, path, card);
        pathTransition.setInterpolator(Interpolator.EASE_IN);
        // pathTransition.setOnFinished(doAfter);

        // Timeline blurReset = new Timeline();
        // KeyValue bx = new KeyValue(card.getDropShadow().offsetXProperty(), 0, Interpolator.EASE_IN);
        // KeyValue by = new KeyValue(card.getDropShadow().offsetYProperty(), 0, Interpolator.EASE_IN);
        // KeyValue br = new KeyValue(card.getDropShadow().radiusProperty(), 2, Interpolator.EASE_IN);
        // KeyFrame bKeyFrame = new KeyFrame(duration, bx, by, br);
        // blurReset.getKeyFrames().add(bKeyFrame);

        ParallelTransition pt = new ParallelTransition(card, pathTransition);
        pt.play();
    }

    private static class MoveToAbs extends MoveTo {
        MoveToAbs(Node node, double x, double y) {
            super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2,
                    y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
        }
    }

    private static class LineToAbs extends LineTo {
        LineToAbs(Node node, double x, double y) {
            super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2,
                    y - node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
        }
    }

}
