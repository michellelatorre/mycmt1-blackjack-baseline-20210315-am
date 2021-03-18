package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

    private final Deck deck;

    private final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();
    private int playerBalance;
    private int betAmount;

    public static void main(String[] args) {
        showIntroductoryScreen();
        playGame();
        screenReset();
    }

    private static void playGame() {
        Game game = new Game();
        game.initialDeal();
        game.play();
    }

    private static void screenReset() {
        System.out.println(ansi().reset());
    }

    private static void showIntroductoryScreen() {
        System.out.println(ansi()
            .bgBright(Ansi.Color.WHITE)
            .eraseScreen()
            .cursor(1, 1)
            .fgGreen().a("Welcome to")
            .fgRed().a(" Jitterted's")
            .fgBlack().a(" BlackJack"));
    }

    public Game() {
        deck = new Deck();
    }

    public void initialDeal() {
        dealRound();
        dealRound();
    }

    private void dealRound() {
        dealCardTo(playerHand);
        dealCardTo(dealerHand);
    }

    private void dealCardTo(Hand playerHand) {
        playerHand.dealCardFrom(deck);
    }

    public void play() {
        // get Player's decision: hit until they stand, then they're done (or
        // they go bust)
        boolean playerBusted = false;
        while (!playerBusted) {
            displayGameState();
            String playerChoice = inputFromPlayer().toLowerCase();
            if (playerStands(playerChoice)) {
                break;
            }
            if (playerHolds(playerChoice)) {
                dealCardTo(playerHand);
                if (playerHand.isBusted()) {
                    playerBusted = true;
                }
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }

        // Dealer makes its choice automatically based on a simple heuristic
        // (<=16, hit, 17>=stand)
        if (!playerBusted) {
            while (dealerHand.underEqualSixteen()) {
                dealCardTo(dealerHand);
            }
        }

        displayFinalGameState();
        displayOutcome(playerBusted);
    }

    private void displayOutcome(boolean playerBusted) {
        if (playerBusted) {
            System.out.println("You Busted, so you lose.  💸");
            playerLoses();
        } else if (dealerHand.isBusted()) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! " +
                "💵");
            playerWins();
        } else if (playerHand.beats(dealerHand)) {
            System.out.println("You beat the Dealer! 💵");
            playerWins();
        } else if (dealerHand.isPushedWith(playerHand)) {
            System.out.println("Push: The house wins, you Lose. 💸");
            playerPushes();
        } else {
            System.out.println("You lost to the Dealer. 💸");
            playerLoses();
        }
    }

    private boolean playerHolds(String playerChoice) {
        return playerChoice.startsWith("h");
    }

    private boolean playerStands(String playerChoice) {
        return playerChoice.startsWith("s");
    }

    private String inputFromPlayer() {
        System.out.println("[H]it or [S]tand?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void displayGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(dealerHand.showFirstCard()); //
        // first card is
        // Face Up

        // second card is the hole card, which is hidden
        displayBackOfCard();

        System.out.println();
        System.out.println("Player has: ");
        playerHand.displayHand();
        playerHand.printHandValue();
    }

    private void displayBackOfCard() {
        System.out.print(
            ansi()
                .cursorUp(7)
                .cursorRight(12)
                .a("┌─────────┐").cursorDown(1).cursorLeft(11)
                .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
                .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
                .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
                .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                .a("└─────────┘"));
    }

    //Calculation

    private void displayFinalGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));

        System.out.println("Dealer has: ");
        playerHand.displayHand();
        playerHand.printHandValue();

        System.out.println();
        System.out.println("Player has: ");
        playerHand.displayHand();
        playerHand.printHandValue();
    }

    public int playerBalance() {
        return playerBalance;
    }

    public void playerDeposits(int i) {
        this.playerBalance = i;
    }

    public void playerBets(int i) {
        assertSufficientFunds(i);
        this.playerBalance -= i;
        this.betAmount += i;
    }

    private void assertSufficientFunds(int i) {
        if (i > playerBalance) throw new IllegalStateException();
    }

    public void playerWins() {
        this.playerBalance += betAmount * 2;
    }

    public void playerLoses() {
        this.playerBalance += betAmount * 0;
    }

    public void playerPushes() {
        this.playerBalance += betAmount * 1;
    }

    public void playerWinsBlackjack() {
        this.playerBalance += betAmount * 2.5;
    }
}
