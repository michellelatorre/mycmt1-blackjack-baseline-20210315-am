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
        game.player.playerDeposits(25);
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(25);
    }

    @Test
    void testPlayerWith100BalanceBets75ThenTheBalanceIs25() {
        Game game = new Game();
        game.player.playerDeposits(100);
        game.player.playerBets(75);
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(25);
    }

    @Test
    void testPlayerWith100Bets50AndWinsThenBalanceIs150() {
        Game game = new Game();
        game.player.playerDeposits(100);
        game.player.playerBets(50);
        game.player.playerWins();
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(100 - 50 + 100);
    }

    @Test
    void testPlayerWith50Bets25AndLosesThenTheBalance25() {
        Game game = new Game();
        game.player.playerDeposits(50);
        game.player.playerBets(25);
        game.player.playerLoses();
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(50 - 25);
    }

    @Test
    void testPlayerBetsTwiceAndWinsThenTheBalanceIs150() {
        Game game = new Game();
        game.player.playerDeposits(50);
        game.player.playerBets(25);
        game.player.playerBets(25);
        game.player.playerWins();
        int balance = game.playerBalance();
        assertThat(balance).isEqualTo(100);
    }

    @Test
    void testPlayerWith50Bets5TimesExceededTheAmount() {
        Game game = new Game();
        game.player.playerDeposits(50);
        assertThatThrownBy(() -> game.player.playerBets(1000))
            .isInstanceOf(IllegalStateException.class);
    }

    //OK - Green Test
    @Test
    void testPlayerDeposits50bets25bets30() {
        Game game = new Game();
        game.player.playerDeposits(50);
        game.player.playerBets(25);
        assertThatThrownBy(() -> game.player.playerBets(30))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void testPlayerWith75Bets50AndPushesThenBalanceIs75() {
        Game game = new Game();
        game.player.playerDeposits(75);
        game.player.playerBets(50);

        game.player.playerPushes();
        assertThat(game.playerBalance()).isEqualTo(75);
    }

    @Test
    void testPlayerWith200Bets100AndWinsBlackJackThenBalance350() {
        Game game = new Game();
        game.player.playerDeposits(200);
        game.player.playerBets(100);

        game.player.playerWinsBlackjack();
        assertThat(game.playerBalance()).isEqualTo(200 - 100 + ((int)(100 * 2.5)));
    }

    @Test
    void testPlayerHas50Bets25WinsCanBet75() {
        Game game = new Game();
        game.player.playerDeposits(50);
        game.player.playerBets(25);
        game.player.playerWins();
        game.player.playerBets(75);
        assertThat(game.playerBalance()).isEqualTo(50 - 25 + (25 * 2) - 75);
    }
}
