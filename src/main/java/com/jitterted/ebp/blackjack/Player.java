package com.jitterted.ebp.blackjack;

public class Player {
    private int playerBalance;
    private int betAmount;

    public Player() {
    }

    public int getPlayerBalance() {
        return playerBalance;
    }

    void assertSufficientFunds(int i) {
        if (i > playerBalance) throw new IllegalStateException();
    }

    public void playerDeposits(int i) {
        this.playerBalance += i;
    }

    public void playerWins() {
        this.playerBalance = playerBalance + betAmount * 2;
    }

    public void playerLoses() {
        this.playerBalance = playerBalance + betAmount * 0;
    }

    public void playerBets(int i) {
        assertSufficientFunds(i);
        this.playerBalance = playerBalance - i;
        this.betAmount += i;
    }

    public void playerPushes() {
        this.playerBalance = playerBalance + betAmount * 1;
    }

    public void playerWinsBlackjack() {
        this.playerBalance = (int) (playerBalance + betAmount * 2.5);
    }
}