package cardgameuno;

import java.util.Random;

/** *************************************************************
 * CARD GAME UNO - Code program
 * Developed by 
 * @author Luis_Castillo
 * 
 * Sheridan College - August 2019
 * Group 6 - Fundamentals of Software Design and Development
 * **************************************************************
 * Objective:   This class set up the deck UNO's game
 * ______________________________________________________________
 */

public class Deck {

    // --- Definition Deck (4 colors * 9 numbers * 2 cards each one)
    final private static int varMaxDeck = 72;

    // --- Calling Card class to fill the deck
    private Card[] cards;
    private int varNumCards;

    public Deck() {
        cards = new Card [varMaxDeck];
        varNumCards = 0;
    }

    public void createDeck() {
        // --- Method to create a deck
        varNumCards = 0;

        // Create a deck with (4 colors * 9 numbers * 2 cards each one)
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k <= 9; k++) {
                    cards[varNumCards] = new Card(j, k);
                    varNumCards++;
                }
            }
        }
    }

    public void shuffleDeck() {
        Random r = new Random();

        // Repeat 150 times and choose two random cards
        for (int i = 0; i < 150; i++) {

            // Choose two random cards.
            int c1 = Math.abs(r.nextInt() % varNumCards);
            int c2 = Math.abs(r.nextInt() % varNumCards);

            Card temp = cards[c1];
            cards[c1] = cards[c2];
            cards[c2] = temp;
        }
    }

    public int getVarNumCards() {
        // --- Getters and setters
        return varNumCards;
    }

    public Card getTopCard() {
        // --- Returns the card at the top
        if (varNumCards == 0) {
            return null;
        }
        return cards[varNumCards - 1];
    }

    public Card getCard(int index) {
        // --- Returns the in position index.
        if (index >= varNumCards || index < 0) {
            return null;
        }
        return cards[index];
    }

    public boolean canPlay(Card c) {
        // --- Method to try to find a card that can be played.
        // --- Try to find a card in our collection that can be played on c
        for (int i = 0; i < varNumCards; i++) {
            if (cards[i].keepPlaying(c)) {
                return true;
            }
        }

        // --- No card was found
        return false;
    }

    public boolean addCard(Card c) {
        // --- Method to add a new card. It control the maximun size of the deck
        if (varNumCards == varMaxDeck) {
            return false;
        }
        cards[varNumCards] = c;
        varNumCards++;
        return true;
    }

    public Card takeCardFromTop() {
        // --- Method to control the card on the top
        if (varNumCards == 0) {
            return null;
        }
        Card varCardReturn = cards[varNumCards - 1];
        varNumCards--;

        return varCardReturn;
    }

    public Card removeCard(int index) {
        // --- Method to remove a card

        if (index < 0 || index >= varNumCards) {
            return null;
        }

        //--- Card to remove and resize the deck
        Card varCardReturn = cards[index];
        cards[index] = cards[varNumCards - 1];
        varNumCards--;

        return varCardReturn;
    }

    public String toString() {
        // --- Method which print the cards
        String answer = "";

        for (int i = 0; i < varNumCards; i++) {
            answer = answer + "[ " + i + ". " + cards[i] + " ]\n";
        }
        return answer;
    }

}
