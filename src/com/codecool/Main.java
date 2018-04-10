package com.codecool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.beans.*;
import javafx.beans.value.*;
import java.lang.Number;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main extends Application{

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage){

    }

    private void setMenuScene(Stage stage){

        Menu menu = new Menu();
        List<ChoiceBox<String>> choiceBoxes = new ArrayList<>();

        for(int i = 0; i < 4; i++){
            choiceBoxes.add(createNewChoiceBox(500, 300 + i*30));
        }

        menu.setBackground(new Image("/table/green.png"));
    }

    private ChoiceBox<String> createNewChoiceBox(double x, double y){
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Computer player", "Human player");
        choiceBox.setLayoutX(x);
        choiceBox.setLayoutY(y);

        return choiceBox;
    }
}