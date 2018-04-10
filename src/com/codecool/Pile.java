package com.codecool;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.ListIterator;

public class Pile extends Pane {

    private PileType pileType;
    private Player owner;
    private String name;
    private double cardGap;
    private ObservableList<Card> cards = FXCollections.observableArrayList();
    private ImageHandler imageHandler = new ImageHandler();

    public Pile(PileType pileType, String name, double cardGap) {
        this.pileType = pileType;
        this.owner = null;
        this.cardGap = cardGap;
        this.name = name;
    }

    public PileType getPileType() {
        return pileType;
    }

    public Player getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public double getCardGap() {
        return cardGap;
    }

    public ObservableList<Card> getCards() {
        return cards;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int numOfCards() {
        return this.cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void clear() {
        this.cards.clear();
    }

    public void addCard(Card card) {
        cards.add(card);
        card.setContainingPile(this);
        card.toFront();
        layoutCard(card);
    }

    private void layoutCard(Card card) {
        card.relocate(card.getLayoutX() + card.getTranslateX(), card.getLayoutY() + card.getTranslateY());
        card.setTranslateX(0);
        card.setTranslateY(0);
        card.setLayoutX(getLayoutX());
        card.setLayoutY(getLayoutY() + (cards.size() - 1) * cardGap);
    }

    public Card getTopCard() {
        if (cards.isEmpty())
            return null;
        else
            return cards.get(cards.size() - 1);
    }

    public void setBlurredBackground() {
        setPrefSize(imageHandler.getWidth(), imageHandler.getHeight());
        BackgroundFill backgroundFill = new BackgroundFill(Color.gray(0.0, 0.2), null, null);
        Background background = new Background(backgroundFill);
        GaussianBlur gaussianBlur = new GaussianBlur(10);
        setBackground(background);
        setEffect(gaussianBlur);
    }

    public enum PileType {
        HAND,
        TABLEAU
    }
}
