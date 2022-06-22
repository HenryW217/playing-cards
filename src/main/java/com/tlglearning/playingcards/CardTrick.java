package com.tlglearning.playingcards;

import com.tlglearning.playingcards.model.Card;
import com.tlglearning.playingcards.model.Deck;
import com.tlglearning.playingcards.model.Suit;

import java.util.*;

public class CardTrick {
    private Deque<Card> blackPile;
    private Deque<Card> redPile;

    public CardTrick() {
        blackPile = new LinkedList<>();
        redPile = new LinkedList<>();
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        CardTrick trick = new CardTrick();
        trick.splitDeck(deck);
        // TODO (Optional: Shuffle the red deque and black deque.)

        trick.swapCards();


        // TODO Sort each deque by color, suit, and rank, and print them out, along with the counts from
        //  previous step.

        trick.tally();
    }

    public void splitDeck(Deck deck) {
        while (deck.getRemaining() > 0) {
            Card indicator = deck.draw();
            Card next = deck.draw();
            if (indicator.getSuit().getColor() == Suit.Color.BLACK) {
                blackPile.add(next);
            } else {
                redPile.add(next);
            }
        }
    }

    public void swapCards() {
        Random rng = new Random();
        int swapSize = rng.nextInt(1 + Math.min(blackPile.size(), redPile.size()));
        for (int i = 0; i < swapSize; i++) {
            redPile.add(blackPile.remove());
            blackPile.add(redPile.remove());
        }
    }

    public void tally() {
        int redCount = 0;
        int blackCount = 0;
        for (Card card : blackPile) {
            if (card.getSuit().getColor() == Suit.Color.BLACK) {
                blackCount++;
            }
        }
        for (Card card : redPile) {
            if (card.getSuit().getColor() == Suit.Color.RED) {
                redCount++;
            }
        }
        DisplayComparator comparator = new DisplayComparator();
        Collections.sort((LinkedList<Card>) blackPile, comparator);
        Collections.sort((LinkedList<Card>) redPile, comparator);


        System.out.printf("Black: count=%d, cards=%s%n", blackCount, blackPile);
        System.out.printf("Red: count=%d, cards=%s%n", redCount, redPile);
    }

    private static class DisplayComparator implements Comparator<Card> {

        @Override
        public int compare(Card card1, Card card2) {
            int comparison = card1.getSuit().getColor().compareTo(card2.getSuit().getColor());
            comparison = (comparison != 0) ? comparison : card1.getSuit().compareTo(card2.getSuit());
            comparison = (comparison != 0) ? comparison : card1.getRank().compareTo(card2.getRank());
            return comparison;
        }
    }

}

