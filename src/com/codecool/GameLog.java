package com.codecool;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class GameLog{

    private Game game;
    private StringBuilder sb = new StringBuilder();
    private TextArea textArea = new TextArea();

    GameLog(Game game){
        this.game = game;
        initLog();
    }

    private void initLog(){
        textArea.setLayoutX(30);
        textArea.setLayoutY(620);
        textArea.setEditable(false);
        textArea.setPrefRowCount(7);
        textArea.setPrefColumnCount(15);
        textArea.setWrapText(true);
    }

    public void addToLog(String stringToAdd){
        sb.insert(0, stringToAdd + "\n");
        displayLog();
    }

    public void displayLog(){
        textArea.setText(sb.toString());
    }



}