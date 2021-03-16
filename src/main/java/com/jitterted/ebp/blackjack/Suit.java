package com.jitterted.ebp.blackjack;

public enum Suit {
    HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣"), SPADES("♠");

    private final String symbol;

    Suit(String symbol) {
        this.symbol = symbol;
    }

    public static Suit fromString(String string) {
        for (Suit suit : Suit.values()) {
            if (suit.symbol.equals(string)) {
                return suit;
            }
        }
        return null; //Temp I don't like it.
    }

    boolean isRed() {
        return "♥♦".contains(symbol);
    }
}
