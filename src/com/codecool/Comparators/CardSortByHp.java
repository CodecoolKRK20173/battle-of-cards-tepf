package com.codecool.Comparators;

import com.codecool.Card;
import java.util.Comparator;

public class CardSortByHp implements Comparator<Card> {

    @Override
    public int compare(Card c1, Card c2) {
        return c2.getHp() - c1.getHp();
    }
}