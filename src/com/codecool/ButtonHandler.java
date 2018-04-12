package com.codecool;

import javafx.event.ActionEvent;
import javafx.event.Event;
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
    private Button nextButton = new Button();
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
                btn.setStyle("-fx-border-color: lime; -fx-background-color: transparent; -fx-border-width: 3");
    }});

        btn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent e) {
                btn.setStyle("-fx-border-color: transparent; -fx-background-color: transparent; -fx-border-width: 3");
            }
    });
    }

    public void initButtons(){
        spdButton.setOnMouseClicked(spdButtonHandler);
        spdButton.setStyle("-fx-background-color: transparent");
        buttonList.add(spdButton);

        dmgButton.setOnMouseClicked(dmgButtonHandler);
        dmgButton.setStyle("-fx-background-color: transparent");
        buttonList.add(dmgButton);

        armButton.setOnMouseClicked(armButtonHandler);
        armButton.setStyle("-fx-background-color: transparent");
        buttonList.add(armButton);

        hpButton.setOnMouseClicked(hpButtonHandler);
        hpButton.setStyle("-fx-background-color: transparent");
        buttonList.add(hpButton);
        
        nextButton.setOnMouseClicked(nextButtonHandler);
        setButtonSize(nextButton, 100, 100);
        nextButton.setText("Next Round");
        nextButton.setLayoutX(1100);
        nextButton.setLayoutY(600);
        nextButton.toFront();
        nextButton.setVisible(false);
        game.getChildren().add(nextButton);

        for (Button btn : buttonList) {
            setButtonSize(btn, 25, 25);
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
        game.handleBattle(stat);
        nextButton.setVisible(true);
    };
    
    private EventHandler<MouseEvent> dmgButtonHandler = e -> {
        int stat = 1;
        game.handleBattle(stat);
        nextButton.setVisible(true);
    };

    private EventHandler<MouseEvent> armButtonHandler = e -> {
        int stat = 2;
        game.handleBattle(stat);
        nextButton.setVisible(true);
    };


    private EventHandler<MouseEvent> hpButtonHandler = e -> {
        pushedNext();
    };

    private void pushedNext() {
        int stat = 3;
        game.handleBattle(stat);
        nextButton.setVisible(true);
    }

    public void pushNext() {
        pushedNext();
    } 

    private EventHandler<MouseEvent> nextButtonHandler = e -> {
        game.endRound();
        if (game.winner instanceof ComputerPlayer) {
            int aiMove = ((ComputerPlayer)game.winner).aiMove();
            game.handleBattle(aiMove);
            setStatButtons(false);
        } else {
            setStatButtons(true);
            nextButton.setVisible(false);
        }
    };

    private void setStatButtons(boolean isVisible) {
        for (Button btn: buttonList) {
            btn.setVisible(isVisible);
        }
    }

    private void initSlider() {
        bidSlider.setLayoutX(300);
        bidSlider.setLayoutY(300);
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