package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    public void testNewPlayerHasZeroBalance() {
        Player player = new Player();
        int balance = player.getPlayerBalance();
        assertThat(balance).isZero();
    }

    @Test
    void testThatAfterPlayerDepositsBalanceIsGreaterThanZero() {
        Player player = new Player();
        player.playerDeposits(10);
        int balance = player.getPlayerBalance();
        assertThat(balance).isGreaterThan(0);
    }

    @Test
    void testThatAfterPlayerDeposits100BalanceIs100() {
        Player player = new Player();
        player.playerDeposits(100);
        int balance = player.getPlayerBalance();
        assertThat(balance).isEqualTo(100);
    }

    @Test
    void testCorrectBalanceAfterPlayerDepositsTwice() {
        Player player = new Player();
        player.playerDeposits(100);
        player.playerDeposits(50);
        int balance = player.getPlayerBalance();
        assertThat(balance).isEqualTo(100+50);
    }

    @Test
    void testPlayerCantBetIfBalanceIsLowerThanBetAmount() {
        Player player = new Player();
        assertThatThrownBy(() -> player.playerBets(1))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void testCorrectBalanceAfterPlayerDepositsThenBet() {
        Player player = new Player();
        player.playerDeposits(100);
        player.playerBets(50);
        int balance = player.getPlayerBalance();
        assertThat(balance).isEqualTo(100-50);
    }

    @Test
    void testCorrectBalanceAfterPlayerDepositsThenBetTwice() {
        Player player = new Player();
        player.playerDeposits(80);
        player.playerBets(20);
        player.playerBets(8);
        int balance = player.getPlayerBalance();
        assertThat(balance).isEqualTo(80-20-8);
    }

    @Test
    void testPlayerWith100Bets100AndWinsBlackJackThenBalanceIs250() {
        Player player = new Player();
        player.playerDeposits(100);
        player.playerBets(100);
        player.playerWinsBlackjack();
        int balance = player.getPlayerBalance();
        assertThat(balance).isEqualTo(100 - 100 + ((int)(100 * 2.5)));
    }

    @Test
    void testPlayerWith100Bets100AndPushesThenBalanceIsStill100() {
        Player player = new Player();
        player.playerDeposits(100);
        player.playerBets(100);
        player.playerPushes();
        int balance = player.getPlayerBalance();
        assertThat(balance).isEqualTo(100 - 100 + (100 * 1));
    }

    @Test
    void testPlayerWith100Bets100AndLossThenBalanceIs0() {
        Player player = new Player();
        player.playerDeposits(100);
        player.playerBets(100);
        player.playerLoses();
        int balance = player.getPlayerBalance();
        assertThat(balance).isEqualTo(100 - 100 + (100 * 0));
    }

    @Test
    void testGetTotalBetAmountIsZeroWhenPlayerHasNoBetsYet() {
        Player player = new Player();
        int totalBetAmount = player.totalAmountBet();
        assertThat(totalBetAmount).isZero();
    }

    @Test
    void testGetTotalBetAmountIsSameWithAmountThatPlayerBet() {
        Player player = new Player();
        player.playerDeposits(10);
        player.playerBets(5);
        int totalBetAmount = player.totalAmountBet();
        assertThat(totalBetAmount).isEqualTo(5);
    }

    @Test
    void testGetTotalBetAmountIsCorrectForMultipleBets() {
        Player player = new Player();
        player.playerDeposits(200);
        player.playerBets(5);
        player.playerBets(10);
        player.playerBets(20);
        int totalBetAmount = player.totalAmountBet();
        assertThat(totalBetAmount).isEqualTo(5 + 10 + 20);
    }

    @Test
    void testGetTotalBetAmountIsUnchangedWhenPlayerWins() {
        Player player = new Player();
        player.playerDeposits(50);
        player.playerBets(10);
        player.playerWins();
        int totalBetAmount = player.totalAmountBet();
        assertThat(totalBetAmount).isEqualTo(10);
    }

    @Test
    void testGetTotalBetAmountIsUnchangedWhenPlayerLosses() {
        Player player = new Player();
        player.playerDeposits(50);
        player.playerBets(10);
        player.playerLoses();
        int totalBetAmount = player.totalAmountBet();
        assertThat(totalBetAmount).isEqualTo(10);
    }

    @Test
    void testGetTotalBetAmountSubtractFromPlayerBalance() {
        Player player = new Player();
        player.playerDeposits(100);
        player.playerBets(50);
        player.playerBets(50);
        int totalBetAmount = player.totalAmountBet();
        int playerBalance = player.getPlayerBalance();
        assertThat(playerBalance).isEqualTo(100 - totalBetAmount);
    }

}
