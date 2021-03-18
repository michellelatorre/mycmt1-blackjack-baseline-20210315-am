package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HandTest {

    public static final Suit DUMMY_SUIT = Suit.HEARTS;

    @Test
    public void handWithOneAceTwoCardsIsValuedAt11() throws Exception {
        List<Card> cards = List.of(new Card(DUMMY_SUIT, "A"),
            new Card(DUMMY_SUIT, "5"));
        Hand hand = new Hand(cards);

        assertThat(hand.getHandValue())
            .isEqualTo(11 + 5);
    }

    @Test
    public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() throws Exception {
        List<Card> cards = List.of(new Card(DUMMY_SUIT, "A"),
            new Card(DUMMY_SUIT, "8"),
            new Card(DUMMY_SUIT, "3"));
        Hand hand = new Hand(cards);
        assertThat(hand.getHandValue())
            .isEqualTo(1 + 8 + 3);
    }


}
