package com.codecool.Comparators;

import com.codecool.Card;
import java.util.Comparator;

public class CardSortByDmg implements Comparator<Card> {

    @Override
    public int compare(Card c1, Card c2) {
        return c2.getDmg() - c1.getDmg();
    }
}