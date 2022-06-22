package com.tlglearning.playingcards.util;

import com.tlglearning.playingcards.model.Card;

import java.util.Comparator;

public class DisplayComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        int comparison = card1.getSuit().getColor().compareTo (card2.getSuit().getColor());
        comparison = (comparison != 0) ? comparison : card1.getSuit().compareTo(card2.getSuit());
        comparison = (comparison != 0) ? comparison : card1.getRank().compareTo(card2.getRank());
        return comparison;
    }
}
