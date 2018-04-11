package com.codecool;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class ButtonHandler{

    private Button spdButton = new Button();
    private Button dmgButton = new Button();
    private Button armButton = new Button();
    private Button hpButton = new Button();

    private ArrayList<Button> buttonList = new ArrayList<Button>(4);
    private Game game;

    ButtonHandler(Game game){
        this.game = game;
        initButtons();
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
        System.out.println("Add spd comparator");
    };
    
    private EventHandler<MouseEvent> dmgButtonHandler = e -> {
        System.out.println("Add dmg comparator");
    };

    private EventHandler<MouseEvent> armButtonHandler = e -> {
        System.out.println("Add arm comparator");
    };

    private EventHandler<MouseEvent> hpButtonHandler = e -> {
        System.out.println("Add hp comparator");
    };

}