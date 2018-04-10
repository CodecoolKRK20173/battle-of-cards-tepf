package com.codecool;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.*;

public class Card extends ImageView {

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
    
    static Image cardBackImage;
    private static final Map<String, Image> cardFaceImages = new HashMap<>();
    public static final int WIDTH = 150;
    public static final int HEIGHT = 215;
    
    public Card(String name, Status state, ArrayList<Integer> stats) {
        this.name = name;

        this.spd = stats.get(0);
        this.dmg = stats.get(1);
        this.arm = stats.get(2);
        this.hp = stats.get(3);        

        this.faceDown = faceDown;
        this.dropShadow = new DropShadow(2, Color.gray(0, 0.75));
        backFace = cardBackImage;
        frontFace = cardFaceImages.get(getShortName());
        setImage(faceDown ? backFace : frontFace);
        setEffect(dropShadow);
    }
    
    public Integer[] getStatsArray() {
        Integer[] statsArray = {this.spd, this.dmg, this.arm, this.hp};

        return statsArray;
    }

    public void setBackFace() {
        backFace = new Image("?");
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

    // public Pile getContainingPile() {
    //     return containingPile;
    // }

    // public void setContainingPile(Pile containingPile) {
    //     this.containingPile = containingPile;
    // }

    // public void moveToPile(Pile destPile) {
    //     this.getContainingPile().getCards().remove(this);
    //     destPile.addCard(this);
    // }

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

    public List<Card> createNewDeck(ArrayList<ArrayList<Integer>> statsCollection) {
        List<Card> result = new ArrayList<>();
        int i = 0;
  
        for (ArrayList<Integer> row: statsCollection) {
            String cardName = "Card" + i;
            result.add(new Card(cardName, Status.FACEDOWN, row));
        }
        return result;
    }

    public void loadCardImages() {
        cardBackImage = new Image("?");
        int numberOfCards = 30;
        
        for (int i = 0; i < numberOfCards; i++) {
            String cardName = "Card" + i;
            String imageFileName = "card_images/" + cardName + ".png";

            cardFaceImages.put(cardName, new Image(imageFileName));
        }
    }
    
    // public enum Suit {
    //     HEARTS("red"),
    //     DIAMONDS("red"),
    //     SPADES("black"),
    //     CLUBS("black");
    
    //     private String color;
    
    //     private Suit(String color) {
    //         this.color = color;
    //     }
    
    //     public String getColor() {
    //         return this.color;
    //     }
    // }

    public enum Status {
        FACEDOWN(false),
        FACEUP(true);
        
        private boolean isFrontFace;
    
        private Status(boolean isFrontFace) {
            this.isFrontFace = isFrontFace;
        }
    
        public boolean getCardSide() {
            return this.isFrontFace;
        }
    }
}
