package com.codecool;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.control.Slider;
import java.util.ArrayList;
import javafx.scene.text.Text;

public class ButtonHandler{

    private Button spdButton = new Button();
    private Button dmgButton = new Button();
    private Button armButton = new Button();
    private Button hpButton = new Button();
    private Slider bidSlider = new Slider(0.0, 3.0, 0.0);

    private ArrayList<Button> buttonList = new ArrayList<Button>(4);
    private Game game;

    ButtonHandler(Game game){
        this.game = game;
        initButtons();
        initSlider();
    }

    private void setButtonSize(Button btn, double x, double y){
        btn.setMaxSize(x, y);
        btn.setMinSize(x, y);
    }

    private void makeButtonBorderGlow(Button btn){
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent e) {
                btn.setStyle("-fx-border-color: lime; -fx-background-color: transparent;");
    }});

        btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent e) {
                btn.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
            }
    });
    }

    public void initButtons(){
        spdButton.setOnMouseClicked(spdButtonHandler);
        buttonList.add(spdButton);

        dmgButton.setOnMouseClicked(dmgButtonHandler);
        buttonList.add(dmgButton);

        armButton.setOnMouseClicked(armButtonHandler);
        buttonList.add(armButton);

        hpButton.setOnMouseClicked(hpButtonHandler);
        buttonList.add(hpButton);

        for (Button btn : buttonList) {
            setButtonSize(btn, 20, 20);
            makeButtonBorderGlow(btn);
        }

        game.getChildren().addAll(buttonList);
    }

    public void setButtonsPosition(int x, int y){
        for (Button btn : buttonList) {
            btn.setLayoutX(x);
            btn.setLayoutY(y);
            btn.toFront();
            x += 28;
        }
    }

    private EventHandler<MouseEvent> spdButtonHandler = e -> {
        int stat = 0;
        if(game.getActivePlayer().getHand().numOfCards() >= getSliderValue()){
        game.handleBattle(stat);
            System.out.println(game.getActivePlayer().getHand().numOfCards());
        }
    };
    
    private EventHandler<MouseEvent> dmgButtonHandler = e -> {
        int stat = 1;
        if(game.getActivePlayer().getHand().numOfCards() >= getSliderValue()){
        game.handleBattle(stat);
            System.out.println(game.getActivePlayer().getHand().numOfCards());
        }
    };

    private EventHandler<MouseEvent> armButtonHandler = e -> {
        int stat = 2;
        if(game.getActivePlayer().getHand().numOfCards() >= getSliderValue()){
        game.handleBattle(stat);
            System.out.println(game.getActivePlayer().getHand().numOfCards());
        }
    };

    private EventHandler<MouseEvent> hpButtonHandler = e -> {
        int stat = 3;
        if(game.getActivePlayer().getHand().numOfCards() >= getSliderValue()){
        game.handleBattle(stat);
            System.out.println(game.getActivePlayer().getHand().numOfCards());
        }
    };

    private void initSlider() {
        bidSlider.setLayoutX(1200);
        bidSlider.setLayoutY(800);
        bidSlider.setShowTickLabels(true);
        bidSlider.setShowTickMarks(true);
        bidSlider.setMajorTickUnit(1);
        bidSlider.setMinorTickCount(0);
        bidSlider.setSnapToTicks(true);
    
        Text sliderDescript = new Text("Bid");
        sliderDescript.setLayoutX(1200);
        sliderDescript.setLayoutY(780);
    
        game.getChildren().add(bidSlider);
        game.getChildren().add(sliderDescript);
    }
    
    public void resetSlider() {
        bidSlider.adjustValue(0);
    }

    public double getSliderValue(){
        return bidSlider.getValue();
    }
}