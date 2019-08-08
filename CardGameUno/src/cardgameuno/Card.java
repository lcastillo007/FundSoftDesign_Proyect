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
 * Objective:   This class set up the cards into the game
 * ______________________________________________________________
 */
public class Card {

    // --- Definition each type of  (Color and Number)
    final private static String[] colors = {"Yellow", "Red", "Green", "Blue"};
    private int varColor;
    private int varNumber;

    // --- Method random to choose any card
    public Card(Random r) {
        varColor = Math.abs(r.nextInt() % 4);
        varNumber = Math.abs(r.nextInt() % 9) + 1;
    }

    // --- Create a Card Uno's in this way: c=0 Blue, c=1 Green, c=2 Red and c=3 Yellow
    public Card(int c, int n) {
        varColor = c;
        varNumber = n;
    }

    public int compareTo(Card otherCard) {
        // --- Method to compare the cards in the game
        // --- Condtion by color
        if (this.varColor < otherCard.varColor) {
            return -1;
        } else if (this.varColor > otherCard.varColor) {
            return 1;
        }

        // --- Break ties by number
        if (this.varNumber < this.varNumber) {
            return -1;
        } else if (this.varNumber > this.varNumber) {
            return 1;
        }

        return 0;
    }

    // --- Method to evaluate  if the player can draw other card
    public boolean keepPlaying(Card otherCard) {
        return this.varColor == otherCard.varColor || this.varNumber == otherCard.varNumber;
    }

    // --- Method to String
    public String toString() {
        return colors[varColor] + " " + varNumber;
    }

}
