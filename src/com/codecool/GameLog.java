package com.codecool;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class GameLog{

    private Game game;
    private StringBuilder sb = new StringBuilder();
    private TextArea textArea = new TextArea();
    private Button turnLog = new Button("Game Log");

    GameLog(Game game){
        this.game = game;
        initLog();
        initButton();
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
        clearLog();
        textArea.setText(sb.toString());
    }

    private void initButton(){
        turnLog.setLayoutX(40);
        turnLog.setLayoutY(590);
        turnLog.setOnMousePressed(handleClick);
        game.getChildren().add(turnLog);
    }

    private EventHandler<MouseEvent> handleClick = e ->{
        if (game.getChildren().contains(textArea)){
            game.getChildren().remove(textArea);
        } else{
            game.getChildren().add(textArea);
        }
    };

    public void moveCardLog(Card card, Pile destpile){
        addToLog("Card " + card.getShortName() + " moved to " + destpile.getPileType());
    }

    public void numCardsPerPlayerLog(List<Player> players){
        for (int i = 0; i < players.size(); i++ ) {
            addToLog("Player " + (i + 1) + " has " + players.get(i).getHand().numOfCards() + " cards");    
        }
    }

    public void battleLog(List<Player> players, Player winner){
        int winnerIndex = 0;
        for (int i = 0; i < players.size(); i++){
            if (players.get(i).equals(winner)){
                winnerIndex = i + 1;
                break;
            }
        }
        addToLog("Player " + winnerIndex + " wins this round");
    }

    public void battleDrawLog(List<Player> players){
        String remainingPlayers="";
        for (int i = 0; i < players.size(); i++){
            if(players.get(i).getStatus().isPlaying()){
                remainingPlayers += " Player" + (i + 1);
            }
        }
        addToLog("It's a draw!\nRemaining players are:" + remainingPlayers);
    }

    public void newRoundLog(List<Player> players){
        addToLog("----- New Round -----");
        numCardsPerPlayerLog(players);
        for (int i = 0; i < players.size(); i++){
            if(players.get(i).isActivePlayer()){
            addToLog("Player " + (i+1) + " is active player");
            break;
            }
        }
    }

    public void whichStatLog(int stat, int maxStat){
        String statName = "";

        switch(stat){
            case 0:
                statName = "(1) Speed ";
                break;
            case 1:
                statName = "(2) Damage ";
                break;
            case 2:
                statName = "(3) Armor ";
                break;
            case 3:
                statName = "(4) HP ";
                break;
        }
        addToLog("Current best stat is: " + maxStat +"\nPlayer has chosen " + statName);
    }

    public void clearLog(){
        if(sb.length() > 500){
            sb.delete(500, sb.length() - 1);
        }
    }

    public void bidLog(int sliderValue){
        addToLog("Someone feels lucky today!\nBidding for " + sliderValue);
    }
}