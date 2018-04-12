package com.codecool;

import java.util.Map;
import java.util.HashMap;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageHandler extends ImageView{

    private int width = 150;
    private int height = 215;
    private Map<String, Image> cardFaceImages = new HashMap<>();
    Image cardBackImage;
    
    public void loadFaceCardImages(){
        
        cardBackImage = new Image("/card_images/card_back.png");
        int numberOfCards = 32;
        
        for (int i = 1; i <= numberOfCards; i++) {
            String imageFileName = "/card_images/tanks/" + i + ".jpeg";
            cardFaceImages.put(String.valueOf(i), new Image(imageFileName, width, height, false, false));
        }
    }

    public Image getFaceCardImage(String cardName){
        return cardFaceImages.get(cardName);
    }

    public Image getBackCardImage(){
        return cardBackImage;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }
}