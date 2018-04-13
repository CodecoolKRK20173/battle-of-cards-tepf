package com.codecool;

public class ComputerPlayer extends Player{
    public int aiMove() {
        return greatestStat();
    }

    private int greatestStat() {
        Card card = this.getHand().getTopCard();
        
        if (card == null) {
            return -1;
        }

        int firstStat = 0;
        int numOfStat = 4;
        int greatest = card.getStatistic(firstStat);
        int toReturn = firstStat;
        int toCompare;

        for (int i = firstStat + 1; i < numOfStat; i++){
            toCompare = card.getStatistic(i);    
            if (toCompare > greatest) {
                toReturn = i;
            }
        }
        return toReturn;
    }
}