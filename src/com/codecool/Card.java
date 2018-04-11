package com.codecool;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.*;

public class Card extends ImageView implements Comparable {

    private String name;
    private int spd;
    private int dmg;
    private int arm;
    private int hp;

    private boolean faceDown;
    
    private Status state;

    private Image backFace;
    private Image frontFace;
    
    private Pile containingPile;
    private DropShadow dropShadow;
    
    public Card(String name, Image backFace, Image frontFace, Status state, ArrayList<Integer> stats) {
        this.name = name;
        this.backFace = backFace;
        this.frontFace = frontFace;
        this.spd = stats.get(0);
        this.dmg = stats.get(1);
        this.arm = stats.get(2);
        this.hp = stats.get(3);        
        this.state = state;
        // this.dropShadow = imageHandler.getDropShadow();
        setImage(state.getCardSide() ? backFace : frontFace);
        // setEffect(dropShadow);
    }
    
    public Integer[] getStatsArray() {
        Integer[] statsArray = {this.spd, this.dmg, this.arm, this.hp};

        return statsArray;
    }

    public void setBackFace() {
        backFace = new Image("/card_images/card_back.png");
        setImage(faceDown ? backFace : frontFace);
    }

    public void setFrontFace(Image newFrontFace) {
        frontFace = newFrontFace;
        setImage(faceDown ? backFace : frontFace);
    }

    public Image getFrontFace() {
        return frontFace;
    }

    public int getSpd() {
        return this.spd;
    }

    public int getDmg() {
        return this.dmg;
    }

    public int getArm() {
        return this.arm;
    }

    public int getHp() {
        return this.hp;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public String getShortName() {
        return this.name;
    }

    public DropShadow getDropShadow() {
        return dropShadow;
    }

    public Pile getContainingPile() {
        return containingPile;
    }

    public void setContainingPile(Pile containingPile) {
        this.containingPile = containingPile;
    }

    public void moveToPile(Pile destPile) {
        this.getContainingPile().getCards().remove(this);
        destPile.addCard(this);
    }

    public void flip() {
        if(this.state == Status.FACEDOWN) {
            this.state = Status.FACEUP;
        }else {
            this.state = Status.FACEDOWN;
        }
        setImage(faceDown ? backFace : frontFace);
    }

    @Override
    public String toString() {
        return "Card name: " + this.name + "spd: " + this.spd + "dmg: " + this.dmg + "arm: " 
                             + this.arm + "hp: " + this.hp;
    }

    // public static boolean isOppositeColor(Card card1, Card card2) {
    //     return !card1.getSuit().getColor().equals(card2.getSuit().getColor()); 
    // }

    // public static boolean isSameSuit(Card card1, Card card2) {
    //     return card1.getSuit() == card2.getSuit();
    // }

    public void loadCardImages(){
        cardBackImage = new Image("card_images/card_back.png");
        int numberOfCards = 30;
        
        for (int i = 1; i <= numberOfCards; i++) {
            String imageFileName = "/card_images/tanks" + i + ".jpeg";

            cardFaceImages.put(cardName, new Image(imageFileName));
        }
    }   
}
