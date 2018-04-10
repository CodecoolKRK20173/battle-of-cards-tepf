package com.codecool;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Game extends Pane {
    private List<Card> deck = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private List<Pile> tableauPiles = FXCollections.observableArrayList();
    private List<Pile> playersPiles = FXCollections.observableArrayList();
    private static double TABLEAU_GAP = 30;
    private static double PLAYER_GAP = 1;

    public Game(List<Player> players) {
        this.players = players;
        this.deck = Card.createNewDeck();
        initPiles();
        dealCards();
    }

    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public boolean isGameOver() {
        
        if(getLostPlayersNumber() == players.size() - 1){
            return true;
        }

        return false;
    }

    private int getLostPlayersNumber(){
        int lostPlayers = 0;

        for(Player player : players){
            if (player.getStatus().equals(Player.Status.LOST)){
                lostPlayers++;
            }
        }

        return lostPlayers;
    }

}
