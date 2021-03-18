package com.jitterted.ebp.blackjack;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WalletTest {

    //Zero, Simplest
    @Test
    void testWalletIsEmptyOnceCreated() {
        Wallet wallet = new Wallet();
        assertThat(wallet.isEmpty()).isTrue();
    }

    @Test
    void testThatAfterAddingFundsTheWalletIsntEmpty() {
        //Given
        Wallet wallet = new Wallet();

        //When
        wallet.addMoney(10);

        //Then
        assertThat(wallet.isEmpty()).isFalse();
    }

    @Test
    void testBalanceIsZeroAfterCreatingAWallet() {
        Wallet wallet = new Wallet();
        int balance = wallet.getBalance();
        assertThat(balance).isEqualTo(0);
    }

    @Test
    void testNewWalletAdd20HasBalanceOf20() {
        Wallet wallet = new Wallet();
        wallet.addMoney(20);
        assertThat(wallet.getBalance()).isEqualTo(20);
    }

    @Test
    void testAddingMoneyTwice() {
        Wallet wallet = new Wallet();
        wallet.addMoney(20);
        wallet.addMoney(30);
        assertThat(wallet.getBalance()).isEqualTo(20 + 30);
    }

    @Test
    void testShouldntAcceptNegativeValue() {
        Wallet wallet = new Wallet();
        assertThatThrownBy(() -> wallet.addMoney(-50))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
