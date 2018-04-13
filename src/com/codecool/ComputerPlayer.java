package com.codecool;

public class ComputerPlayer extends Player{
    public int getMaxCardStat() {
        Card card = this.getHand().getTopCard();
        int[] statsArray = {card.getSpd(), card.getDmg(), card.getArm(), card.getHp()};
        int maxStatIndex = 0;

        for(int i = 1; i < statsArray.length; i++) {
            if(statsArray[i] > statsArray[maxStatIndex]) maxStatIndex = i;
        }
        return maxStatIndex;
    }
}