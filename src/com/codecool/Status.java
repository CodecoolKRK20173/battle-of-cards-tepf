package com.codecool;

public enum Status {
    FACEDOWN(false),
    FACEUP(true);
    
    private boolean isFrontFace;

    private Status(boolean isFrontFace) {
        this.isFrontFace = isFrontFace;
    }

    public boolean getCardSide() {
        return this.isFrontFace;
    }
}