package com.codecool;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import com.codecool.Enums.*;
import java.util.*;

public class Card extends ImageView {

    private int spd;
    private int dmg;
    private int arm;
    private int hp;
    private String name;    
    private Status state;
    private Image backFace;
    private Image frontFace;
    private Pile containingPile;
    private boolean isOnBack;
    
    public Card(String name, Image backFace, Image frontFace, Status state, ArrayList<Integer> stats) {
        this.name = name;
        this.backFace = backFace;
        this.frontFace = frontFace;
        this.state = state;
        this.spd = stats.get(0);
        this.dmg = stats.get(1);
        this.arm = stats.get(2);
        this.hp = stats.get(3);

        setImage(state.getCardSide() ? backFace : frontFace);
    }
    
    public int getStatistic(int index) {
        ArrayList<Integer> statsArray = new ArrayList<>();
        statsArray.addAll(Arrays.asList(this.spd, this.dmg,
                                        this.arm, this.hp));

        return statsArray.get(index);
    }

    public void setLast(){
        this.isOnBack = true;
    }

    public boolean isLast(){
        return this.isOnBack;
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