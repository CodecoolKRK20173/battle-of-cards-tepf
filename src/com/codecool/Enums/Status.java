package com.codecool.Enums;

public enum Status {
    FACEDOWN(true),
    FACEUP(false);
    
    private boolean isFrontFace;

    private Status(boolean isFrontFace) {
        this.isFrontFace = isFrontFace;
    }

    public boolean getCardSide() {
        return this.isFrontFace;
    }
}