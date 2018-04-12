package com.codecool;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import com.codecool.Enums.*;
import com.codecool.Comparators.*;
import com.codecool.Animation.*;


public class Game extends Pane {

    private List<Pile> tableauPiles = FXCollections.observableArrayList();
    private List<Pile> playersPiles = FXCollections.observableArrayList();
    private ButtonHandler buttonHandler = new ButtonHandler(this);
    private GameLog gameLog = new GameLog(this);
    private ImageHandler imageHandler = new ImageHandler();
    private Animation animationHandler = new Animation();
    private List<Player> players = new ArrayList<>();
    private List<Card> deck = new ArrayList<>();
    private Pile wastePile;
    private double GAP = 1;
    private List<Card> cardsToMoveFromBids = new ArrayList<>();

    public Game(List<Player> players) {
        this.players = players;
        prepareGame();
    }

    private void prepareGame(){
        Player firstPlayer = players.get(0);

        imageHandler.loadFaceCardImages();
        deck = createNewDeck();
        initPiles();
        dealCards();
        firstPlayer.activate();
        setButtonsOnPlayer(firstPlayer);
    }

    private List<Card> createNewDeck() {
        HandleFile readFile = new HandleFile("test.txt");
        List<ArrayList<Integer>> statsCollection = readFile.getStatsList();
        List<Card> result = new ArrayList<>();
        Image backFace = imageHandler.getBackCardImage();
        int i = 1;
  
        for (ArrayList<Integer> row: statsCollection) {
            String cardName = String.valueOf(i);
            Image frontFace = imageHandler.getFaceCardImage(cardName);
            result.add(new Card(cardName, backFace, frontFace, Status.FACEDOWN, row));
            i++;
        }
        Collections.shuffle(result);

        return result;
    }

    private void initPiles() {
        initWastePile();
        initTableauPiles();
        initPlayersPiles();       
    }

    private void initWastePile(){
        wastePile = new Pile(Pile.PileType.WASTE, "Waste", GAP);
        wastePile.setBlurredBackground();
        wastePile.setLayoutX(95);
        wastePile.setLayoutY(20);
        getChildren().add(wastePile);
    }

    private void initTableauPiles(){
        for (int i = 0; i < players.size(); i++) {
            Pile tableauPile = new Pile(Pile.PileType.TABLEAU, "Tableau " + i, GAP);
            tableauPile.setBlurredBackground();
            tableauPile.setLayoutX(300 + i * 180);
            tableauPile.setLayoutY(275);
            tableauPiles.add(tableauPile);
            getChildren().add(tableauPile);
        }
    }

    private void initPlayersPiles(){
        int i = 0;
        double[] pilesLayoutsX = {550, players.size() == 2 ? 550 : 20, 550, 1100};
        double[] pilesLayoutsY = {510, players.size() == 2 ? 20 : 275, 20, 275};

        for(Player player : players){
            Pile playersPile = new Pile(Pile.PileType.HAND, "Player " + i, GAP);
            playersPile.setBlurredBackground();
            playersPile.setLayoutX(pilesLayoutsX[i]);
            playersPile.setLayoutY(pilesLayoutsY[i]);
            playersPiles.add(playersPile);
            getChildren().add(playersPile);
            player.setHand(playersPile);
            i++;
        }
    }

    private void dealCards() {

        Iterator<Card> deckIterator = deck.iterator();

        while(deckIterator.hasNext()){
            for(Pile destPile : playersPiles){
                Card card = deckIterator.next();
                addMouseEventHandlers(card);
                destPile.addCardOnTop(card);
                getChildren().add(card);
                if(!deckIterator.hasNext()){
                    break;
                }
            }
        }
    }

    public void handleBattle(int statistic){
        List<Card> cardsToCompare = getCardsToCompare();
        List<Card> sortedCards = getSortedCardsByStatistic(statistic, cardsToCompare);
        int maxStatistic = sortedCards.get(0).getStatistic(statistic);
        addBidCards();
        animateCardsMovement(cardsToCompare);

        if(isDraw(sortedCards, statistic)){
            for(Card card : sortedCards){
                if(card.getStatistic(statistic) < maxStatistic){
                    Player player = card.getContainingPile().getOwner();
                    player.setStatus(Player.Status.OUT);
                }
                // card.moveToPile(wastePile);
            }
        }
        else{
            Player winner = sortedCards.get(0).getContainingPile().getOwner();

            // moveWinnedCards(sortedCards, winner);
            restorePlayersToGame();
        }

        if(isGameOver()){
            System.out.println("Game ended");
        }
    }

    private void moveWinnedCards(List<Card> cards, Player winner){
        for(Card card : cards){
            card.moveToPile(winner.getHand());
        }
        if(!wastePile.isEmpty()){
            for(Card card : wastePile.getCards()){
                card.moveToPile(winner.getHand());
            }
        }
        for (Card card : cardsToMoveFromBids) {
            card.moveToPile(winner.getHand());
        }
        cardsToMoveFromBids.clear();
    }

    private void restorePlayersToGame(){
        for(Player player : players){
            if(!player.getHand().isEmpty()){
                player.setStatus(Player.Status.PLAYING);
            }
        }
    }

    private void animateCardsMovement(List<Card> cards){
        int index = 0;

        for(Card card : cards){
            animationHandler.slideToDest(card, tableauPiles.get(index));
            index++;
        }
    }

    private boolean isDraw(List<Card> cards, int statistic){
        int firstElement = cards.get(0).getStatistic(statistic);
        int secondElement = cards.get(1).getStatistic(statistic);

        return firstElement == secondElement;
    }

    private boolean isGameOver() {
        
        if(getLostPlayersNumber() == players.size() - 1){
            return true;
        }

        return false;
    }

    private List<Card> getCardsToCompare(){
        List<Card> cardsToCompare = new ArrayList<>();
        
        for(Player player : players){
            if (player.getStatus().isPlaying()){
                Card card = player.getHand().getTopCard();
                cardsToCompare.add(card);
                System.out.println("Card " + card + " added to cards to compare");
            }
        }

        return cardsToCompare;
    }

    private List<Card> getSortedCardsByStatistic(int stat, List<Card> cardsToCompare) {
        switch(stat) { 
            case 0:
                Collections.sort(cardsToCompare, new CardSortBySpd());
                break;
            case 1:
                Collections.sort(cardsToCompare, new CardSortByDmg());
                break;
            case 2:
                Collections.sort(cardsToCompare, new CardSortByArm());
                break;
            case 3:
                Collections.sort(cardsToCompare, new CardSortByHp());
        }
        
        return cardsToCompare;
    }

    private void setButtonsOnPlayer(Player player){
        int xShift = 15;
        int yShift = player.getHand().getCards().size() + 110;
        
        int x = (int)player.getHand().getLayoutX() + xShift;
        int y = (int)player.getHand().getLayoutY() + yShift;
        
        buttonHandler.setButtonsPosition(x, y);
    }

    private int getLostPlayersNumber(){
        int lostPlayers = 0;

        for(Player player : players){
            if (player.getStatus().equals(Player.Status.OUT)){
                lostPlayers++;
            }
        }

        return lostPlayers;
    }

    private void addMouseEventHandlers(Card card) {
        card.setOnMouseClicked(onMouseClickedHandler);
    }

    private EventHandler<MouseEvent> onMouseClickedHandler = e -> {
        Card card = (Card) e.getSource();
        Pile containingPile = card.getContainingPile();
        if (containingPile.getPileType().equals(Pile.PileType.HAND)){
            Player owner = containingPile.getOwner();
            if (owner.isActivePlayer() && card.isFaceDown()){
                card.flip();
                System.out.println(card + " revealed.");
                gameLog.addToLog("test");
            }
        }
    };

    public void setTableBackground(Image tableBackground) {
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }

    public Player getActivePlayer(){
        for (Player player : players) {
            if(player.isActivePlayer()){
                return player;
            }
        }
        return players.get(0);
    }

    private void addBidCards(){
        int numberOfBidCards = (int) buttonHandler.getSliderValue();

        for (Player player : players) {
            if(player.getStatus().isPlaying()){
                for(int i = 1; i < numberOfBidCards + 1; i++){
                    if(player.getHand().numOfCards() > i){
                        cardsToMoveFromBids.add(player.getHand().getCardAt(i));
                    }
                }
            }
        }
    }
}
