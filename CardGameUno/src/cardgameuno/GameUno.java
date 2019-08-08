package cardgameuno;

import java.util.Random;
import java.util.Scanner;

/** *************************************************************
 * CARD GAME UNO - Code program
 * Developed by 
 * @author Luis_Castillo
 * 
 * Sheridan College - August 2019
 * Group 6 - Fundamentals of Software Design and Development
 * **************************************************************
 * Objective:   This class control all the game, players 
 *              and turn between the players
 * ______________________________________________________________
 */
public class GameUno {

    // Definitions and constructors
    final private static int varNumCardsGame = 7;
    private Deck varDeck;
    private Deck varDiscard;
    private Deck varGamer1;
    private Deck varGamer2;

    public GameUno() {
        //Random r = new Random();

        // --- Initialize shuffle the deck.
        varDeck = new Deck();
        varDeck.createDeck();
        varDeck.shuffleDeck();

        // --- Discard a pile of cards
        varDiscard = new Deck();

        // --- Create two players
        varGamer1 = new Deck();
        varGamer2 = new Deck();

        // --- Control how the gamers play alternately
        for (int i = 0; i < varNumCardsGame; i++) {
            varGamer1.addCard(varDeck.takeCardFromTop());
            varGamer2.addCard(varDeck.takeCardFromTop());
        }
    }

    // Method wich start the game
    public void start() {

        // --- Input from the players
        Scanner varInput = new Scanner(System.in);

        // Initialize with the first player
        System.out.println(" **** Wellcome to Card Game - UNO ****\n");
        System.out.println("PLAYER 1 - This is your game!\n" + varGamer1);
        System.out.println("Select the card would you want to discard (Type number): ");

        int varCardGame = varInput.nextInt();
        varDiscard.addCard(varGamer1.removeCard(varCardGame));

        int varTurn = 2;

        // --- The game end when players or either deck is empty
        while (varDeck.getVarNumCards() > 0 && varGamer1.getVarNumCards() > 0 && varGamer2.getVarNumCards() > 0) {

            gameTurn(varTurn);

            // Flip the player's varTurn.
            if (varTurn == 1) {
                varTurn = 2;
            } else {
                varTurn = 1;
            }
        }
        printScore();
    }

    public void gameTurn(int player) {
        // --- Method to control the turn in each player

        Scanner varInput = new Scanner(System.in);

        System.out.println("Card at the top in Game is: " + varDiscard.getTopCard());

        if (player == 1) {

            // --- This is a card to play with player 1
            if (varGamer1.canPlay(varDiscard.getTopCard())) {
                System.out.println("\nPLAYER 1 - This is your game!\n" + varGamer1);
                System.out.println("Select the card would you want to discard (Type number): ");
                int varCardGame = varInput.nextInt();

                // --- If the card is valid, the gamer can play it
                if (varGamer1.getCard(varCardGame).keepPlaying(varDiscard.getTopCard())) {
                    varDiscard.addCard(varGamer1.removeCard(varCardGame));
                } else {
                    System.out.println("UPS! that is not a valid card. You lost your opportunity to drop a card :-(");
                }

                // --- The player 1 with one card say "UNO!"
                if (varGamer1.getVarNumCards() == 1) {
                    System.out.println("PLAYER 1 - says UNO!");
                }
            } // --- Add a card and show the result.
            else {
                System.out.println("UPS!, you can't play this card. The game has been chosen one card for you.");
                varGamer1.addCard(varDeck.takeCardFromTop());
                System.out.println("PLAYER 1, here is your hand:\n" + varGamer1);
            }
        } else {

            // --- This is a card to play with player 2
            if (varGamer2.canPlay(varDiscard.getTopCard())) {
                System.out.println("\nPLAYER 2 - This is your game!\n" + varGamer2);
                System.out.println("Choose a card to discard, type the number: ");

                int varCardGame = varInput.nextInt();

                // --- If the card is valid, the gamer can play it
                if (varGamer2.getCard(varCardGame).keepPlaying(varDiscard.getTopCard())) {
                    varDiscard.addCard(varGamer2.removeCard(varCardGame));
                } else {
                    System.out.println("UPS! that is not a valid card. You lost your opportunity to drop a card :-(");
                }

                // --- The player 2 with one card say "UNO!"
                if (varGamer2.getVarNumCards() == 1) {
                    System.out.println("PLAYER 2 - says UNO!");
                }
            } // --- Add a card and show the result.
            else {
                System.out.println("UPS!, you can't play this card. The game has been chosen one card for you.");
                varGamer2.addCard(varDeck.takeCardFromTop());
                System.out.println("PLAYER 1, here is your hand:\n" + varGamer2);
            }

        }
    }

    // --- Method: It is called only at the end of the game to print the results
    public void printScore() {
        if (varDeck.getVarNumCards() == 0) {
            System.out.println("Wow, the game ended in tie!");
        } else if (varGamer1.getVarNumCards() == 0) {
            System.out.println("The winner is: PLAYER 1");
        } else {
            System.out.println("The winner is: PLAYER 2");
        }
    }

}
