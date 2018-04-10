package com.codecool.Comparators;

import com.codecool.Card;
import java.util.Comparator;

public class CardSortBySpd implements Comparator<Card> {

    @Override
    public int compare(Card c1, Card c2) {
        return c2.getSpd() - c1.getSpd();
    }
}