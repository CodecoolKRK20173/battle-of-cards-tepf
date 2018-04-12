package com.codecool.Animation;

import com.codecool.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import javafx.event.EventHandler;

import java.util.List;

public class Animation {

    public void slideToDest(Card cardToSlide, Pile destPile) {
        if (cardToSlide == null){
            return;
        }
            
        double targetX = destPile.getLayoutX();
        double targetY = destPile.getLayoutY() - destPile.numOfCards();

        System.out.println("Moved card " + cardToSlide + " X: " + targetX + " Y: " + targetY);

        double sourceX = cardToSlide.getLayoutX() + cardToSlide.getTranslateX();
        double sourceY = cardToSlide.getLayoutY() + cardToSlide.getTranslateY();


        if(destPile.getPileType() == Pile.PileType.HAND){
            cardToSlide.toBack();
            destPile.toBack();
            cardToSlide.flip();
        }

        animateCardMovement(cardToSlide, sourceX, sourceY,
                targetX, targetY, Duration.millis(1000),
                e -> {
                    cardToSlide.setLast();
                    cardToSlide.moveToPile(destPile);

                    
                    if(destPile.getPileType() != Pile.PileType.HAND){
                        cardToSlide.toFront();
                        if(cardToSlide.isFaceDown()){
                            cardToSlide.flip();
                        }
                    }
                });
                }

    private void animateCardMovement(
            Card card, double sourceX, double sourceY,
            double targetX, double targetY, Duration duration,
            EventHandler<ActionEvent> doAfter) {

        Path path = new Path();
        path.getElements().add(new MoveToAbs(card, sourceX, sourceY));
        path.getElements().add(new LineToAbs(card, targetX, targetY));

        PathTransition pathTransition = new PathTransition(duration, path, card);
        pathTransition.setInterpolator(Interpolator.EASE_IN);
        pathTransition.setOnFinished(doAfter);

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
