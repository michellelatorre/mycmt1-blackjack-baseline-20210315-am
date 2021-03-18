package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GameBettingTest {
    @Test
    void testPlayerStartsWithZero() {
        Game game = new Game();
        int balance = game.playerBalance();
        assertThat(balance).isZero();
    }

    @Test
    void testPlayerDeposits25ThenTheBalanceIs25() {
        Game game = new Game();
        game.playerDeposits(25);
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(25);
    }

    @Test
    void testPlayerWith100BalanceBets75ThenTheBalanceIs25() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(75);
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(25);
    }

    @Test
    void testPlayerWith100Bets50AndWinsThenBalanceIs150() {
        Game game = new Game();
        game.playerDeposits(100);
        game.playerBets(50);
        game.playerWins();
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(100 - 50 + 100);
    }

    @Test
    void testPlayerWith50Bets25AndLosesThenTheBalance25() {
        Game game = new Game();
        game.playerDeposits(50);
        game.playerBets(25);
        game.playerLoses();
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(50 - 25);
    }

    @Test
    void testPlayerBetsTwiceAndWinsThenTheBalanceIs150() {
        Game game = new Game();
        game.playerDeposits(50);
        game.playerBets(25);
        game.playerBets(25);
        game.playerWins();
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(100);
    }

    @Test
    void testPlayerWith50Bets5TimesExceededTheAmount() {
        Game game = new Game();
        game.playerDeposits(50);
        assertThatThrownBy(() -> game.playerBets(1000))
            .isInstanceOf(IllegalStateException.class);
    }

    //OK - Green Test
    @Test
    void testPlayerDeposits50bets25bets30() {
        Game game = new Game();
        game.playerDeposits(50);
        game.playerBets(25);
        assertThatThrownBy(() -> game.playerBets(30))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void testPlayerWith75Bets50AndPushesThenBalanceIs75() {
        Game game = new Game();
        game.playerDeposits(75);
        game.playerBets(50);

        game.playerPushes();
        assertThat(game.playerBalance()).isEqualTo(75);
    }

    @Test
    void testPlayerWith200Bets100AndWinsBlackJackThenBalance350() {
        Game game = new Game();
        game.playerDeposits(200);
        game.playerBets(100);

        game.playerWinsBlackjack();
        assertThat(game.playerBalance()).isEqualTo(200 - 100 + ((int)(100 * 2.5)));
    }

    @Test
    void testPlayerHas50Bets25WinsCanBet75() {
        Game game = new Game();
        game.playerDeposits(50);
        game.playerBets(25);
        game.playerWins();
        game.playerBets(75);
        assertThat(game.playerBalance()).isEqualTo(50 - 25 + (25 * 2) - 75);
    }
}
