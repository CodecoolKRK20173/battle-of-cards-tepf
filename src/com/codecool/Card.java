package com.codecool;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.*;

import com.codecool.Status;

public class Card extends ImageView {

    private String name;
    private int spd;
    private int dmg;
    private int arm;
    private int hp;
    
    private Status state;

    private ImageHandler imageHandler = new ImageHandler();
    private Image backFace;
    private Image frontFace;
    
    private Pile containingPile;
    private DropShadow dropShadow;
    
    public Card(String name, Status state, ArrayList<Integer> stats) {
        this.name = name;
        this.spd = stats.get(0);
        this.dmg = stats.get(1);
        this.arm = stats.get(2);
        this.hp = stats.get(3);        
        this.state = state;
        imageHandler.loadFaceCardImages();
        this.dropShadow = imageHandler.getDropShadow();
        backFace = imageHandler.getBackCardImage();
        frontFace = imageHandler.getFaceCardImage(getShortName());
        setImage(state.getCardSide() ? backFace : frontFace);
        setEffect(dropShadow);
    }
    
    public Integer[] getStatsArray() {
        Integer[] statsArray = {this.spd, this.dmg, this.arm, this.hp};

        return statsArray;
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
        return state.getCardSide();
    }

    public String getShortName() {
        return this.name;
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
        setImage(this.state.getCardSide() ? backFace : frontFace);
    }

    @Override
    public String toString() {
        return "Card name: " + this.name + "spd: " + this.spd + "dmg: " + this.dmg + "arm: " 
                             + this.arm + "hp: " + this.hp;
    }
}
