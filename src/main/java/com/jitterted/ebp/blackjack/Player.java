package com.jitterted.ebp.blackjack;

public class Player {
    private int playerBalance;
    private int betAmount;
    private int totalAmountBet;
    private int bonus;

    private static final int BONUS_AMOUNT = 10;
    private static final int AMOUNT_QUALIFIED_FOR_BONUS = 100;

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
        this.totalAmountBet += i;
        addBonusWhenBetAmountIsQualified(i);
    }

    public void playerPushes() {
        this.playerBalance = playerBalance + betAmount * 1;
    }

    public void playerWinsBlackjack() {
        this.playerBalance = (int) (playerBalance + betAmount * 2.5);
    }

    public int totalAmountBet() {
        return totalAmountBet;
    }

    public int getPlayerBonus() {
        return bonus;
    }

    private void addBonusWhenBetAmountIsQualified(int i) {
        if (i >= AMOUNT_QUALIFIED_FOR_BONUS) {
            this.bonus += BONUS_AMOUNT;
        }
    }
}