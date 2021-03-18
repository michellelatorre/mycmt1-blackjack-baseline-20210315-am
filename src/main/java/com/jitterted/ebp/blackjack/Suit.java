package com.jitterted.ebp.blackjack;

public enum Suit {
    HEARTS("♥"), CLUBS("♣"), DIAMONDS("♦"), SPADES("♠");

    private String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    boolean isRed() {
        return "♥♦".contains(symbol);
    }
}
