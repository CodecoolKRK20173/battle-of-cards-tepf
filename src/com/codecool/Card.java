package com.codecool;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.*;

public class Card extends ImageView {

    private String name;
    private int mp;
    private int dmg;
    private int dex;
    private int spd;
    
    private Status state;

    private Image backFace;
    private Image frontFace;
    
    private Pile containingPile;
    private DropShadow dropShadow;
    
    static Image cardBackImage;
    private static final Map<String, Image> cardFaceImages = new HashMap<>();
    public static final int WIDTH = 150;
    public static final int HEIGHT = 215;
    
    public Card(String name, Status state, int mp, int dmg, int dex, int spd) {
        this.name = name;
        this.mp = mp;
        this.dmg = dmg;
        this.dex = dex;
        this.spd = spd;        

        this.faceDown = faceDown;
        this.dropShadow = new DropShadow(2, Color.gray(0, 0.75));
        backFace = cardBackImage;
        frontFace = cardFaceImages.get(getShortName());
        setImage(faceDown ? backFace : frontFace);
        setEffect(dropShadow);
    }
    
    public Integer[] getStatsArray() {
        int[] statsArray = {this.mp, this.dmg, this.dex, this.spd};

        return statsArray;
    } 
    // Card(Card c) {
    //     this(c.getSuit(), c.getRank(), c.isFaceDown());
    // }

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

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
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
        if(this.state = Status.FACEDOWN) {
            this.state = Status.FACEUP;
        }else {
            this.state = Status.FACEDOWN;
        }
        setImage(faceDown ? backFace : frontFace);
    }

    @Override
    public String toString() {
        return "Card name: " + this.name + "mp: " + this.mp + "dmg: " + this.dmg + "dex: " 
                             + this.dex + "spd: " + this.spd;
    }

    // public static boolean isOppositeColor(Card card1, Card card2) {
    //     return !card1.getSuit().getColor().equals(card2.getSuit().getColor()); 
    // }

    // public static boolean isSameSuit(Card card1, Card card2) {
    //     return card1.getSuit() == card2.getSuit();
    // }

    public List<Card> createNewDeck() {
        List<Card> result = new ArrayList<>();
  
        for (int i = 0; i < 30; i++) {
            String cardName = "Card" + i;
            result.add(new Card(cardName, Status.FACEDOWN, true));
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
        
        private int cardSide;
    
        private Rank(int cardSide) {
            this.cardSide = cardStrength;
        }
    
        public int getCardSide() {
            return this.cardSide;
        }
    }
}
