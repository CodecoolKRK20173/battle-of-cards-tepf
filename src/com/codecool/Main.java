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

    List<Player> players = new ArrayList<>();
    private static final double WINDOW_WIDTH = 1400;
    private static final double WINDOW_HEIGHT = 928;

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

        Button startButton = createNewButton("Start game", 500, 450);

        startButton.setOnAction(e -> {

            for (ChoiceBox choiceBox : choiceBoxes){                
                if(choiceBox.getValue().equals("Human player")){
                    players.add(new HumanPlayer());
                }
                else if(choiceBox.getValue().equals("Computer player")){
                    players.add(new ComputerPlayer());
                }
            }
        });

        menu.setBackground(new Image("/table/green.png"));
        menu.getChildren().add(startButton);
        menu.getChildren().addAll(choiceBoxes);
        
        stage.setTitle("Game Options");
        stage.setScene(new Scene(menu, WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.show();    
    }

    private Scene getGameScene(){

        Game game = new Game(players);
        game.setTableBackground(new Image("/table/green.png"));

        return new Scene(game, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    private ChoiceBox<String> createNewChoiceBox(double x, double y){
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Computer player", "Human player");
        choiceBox.setLayoutX(x);
        choiceBox.setLayoutY(y);

        return choiceBox;
    }

    private Button createNewButton(String label, double x, double y){
        Button btn = new Button();
        btn.setText(label);
        btn.setLayoutX(x);
        btn.setLayoutY(y);
    
        return btn;
    }
}