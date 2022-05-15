/*
 * Deck.java
 *
 * Computer Science 112, Boston University
 * 
 * A blueprint class for objects that represent a deck of Huno cards.
 *
 * YOU SHOULD NOT MODIFY THIS FILE.
 */

import java.util.*;

public class Deck {
    public static final int NUM_CARDS = 
      Card.COLORS.length * (Card.MAX_VALUE - Card.MIN_VALUE + 1);
    
    /* fields for the deck */
    private Card[] cards;
    private int numCardsLeft;   // number of cards not yet drawn
    private Random rand;        // random-number generator used when shuffling     
    
    /*
     * Deck constructor - takes a numeric seed for the random-number
     * generator. If the seed is -1, it is not used, which means
     * you will get a different ordering each time the program is run.
     */
    public Deck(int seed) {
        this.cards = new Card[NUM_CARDS];
        int count = 0;
        
        for (int colorIndex = 0; colorIndex < Card.COLORS.length; colorIndex++) {
            for (int val = Card.MIN_VALUE; val <= Card.MAX_VALUE; val++) {
                this.cards[count] = new Card(Card.COLORS[colorIndex], val);
                count++;
            }
        }

        this.numCardsLeft = NUM_CARDS;
        
        if (seed == -1) {
            this.rand = new Random();
        } else {
            this.rand = new Random(seed);
        }
    }

    /*
     * Deck constructor that takes no parameters. This is useful when
     * you don't want to specify a seed for the random-number generator.
     * It calls the other constructor, passing it the special seed of -1.
     */
    public Deck() {
        this(-1);
    }
    
    /*
     * reset - restores the deck to a full set of cards. 
     * This method should typically be followed by a call to shuffle(), 
     * or else the cards will be re-dealt in the same order as they were 
     * the first time this deck was used.
     */
    public void reset() {
        this.numCardsLeft = NUM_CARDS;
    }

    /*
     * shuffle - rearranges the cards in the deck
     */
    public void shuffle() {
        // Swap each card in the deck with a randomly chosen other card.
        for (int i = 0; i < NUM_CARDS; i++) {
            int swapWith = Math.abs(this.rand.nextInt()) % NUM_CARDS;
            Card temp = this.cards[i];
            this.cards[i] = this.cards[swapWith];
            this.cards[swapWith] = temp;
        }
    }

    /*
     * drawCard - returns a single card from the deck. 
     * Note that the cards aren't actually removed from the cards array. 
     * Rather, we use the numCardsLeft field to determine which 
     * element of the array should be returned. This means that we
     * end up going from right to left through the array -- returning
     * the last card, then the second-to-last, etc.
     */
    public Card drawCard() {
        if (this.numCardsLeft == 0) {
            this.reset();
            this.shuffle();
        }
        
        this.numCardsLeft--;
        return this.cards[this.numCardsLeft];
    }
}