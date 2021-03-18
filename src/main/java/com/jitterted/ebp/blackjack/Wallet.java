package com.jitterted.ebp.blackjack;

public class Wallet {
    private int money = 0;

    public boolean isEmpty() {
        return money == 0;
    }

    public void addMoney(int money) {
        assertThatMoneyIsPositive(money);
        this.money += money;
    }

    private void assertThatMoneyIsPositive(int money) {
        if (money < 0)
            throw new IllegalArgumentException("Cannot accept negative money");
    }

    public int getBalance() {
        return money;
    }
    //CMD+SHIFT+T
}
