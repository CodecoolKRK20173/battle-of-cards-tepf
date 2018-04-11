package com.codecool;

public abstract class Player{

    private Pile hand;
    private Status status = Status.PLAYING;
    private boolean isActive;

    public void setHand(Pile hand){
        this.hand = hand;
        hand.setOwner(this);
    }

    public Pile getHand(){
        return hand;
    }

    public Status getStatus(){
        return status;
    }

    public boolean isActivePlayer(){
        return isActive;
    }

    public void activate(){
        isActive = true;
    }

    public void deactivate(){
        isActive = false;
    }

    public enum Status{
        PLAYING (true),
        OUT (false);

        private boolean isPlaying;

        private Status(boolean isPlaying){
            this.isPlaying = isPlaying;
        }

        public boolean getStatus(){
            return this.isPlaying;
        }
    }
}