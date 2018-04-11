package com.codecool.Enums;

public enum Statistics {
    SPEED(0),
    DAMAGE(1),
    ARMOR(2),
    HEALTH_POINTS(3);
    
    private int statistic;

    private Statistics(int statistic) {
        this.statistic = statistic;
    }

    public int getStatistic() {
        return this.statistic;
    }
}