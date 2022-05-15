/*
 * Player.java
 * 
 * a blueprint for objects that represent a 
 * single CardMatch player.
 * 
 * completed by: Yukun Shan(yshan@bu.edu)
 */
import java.util.*;

public class Player{
    /* fields for the Player */
    private String name;
    private Card[] hand;
    private int numCards;

    /*
     * Player Constructor
     */
    public Player(String name){
        this.name = name;
        hand = new Card[CardMatch.MAX_CARDS];
        numCards = 0;
    }

    /* an accessor method that returns the Player's name */
    public String getName(){
        return this.name;
    }

    /* 
     * an accessor method that retrns the current number of cards
     * in the player's hand
     */
    public int getNumCards(){
        return this.numCards;
    }

    /* A toString method that returns the Player's name */
    public String toString(){
        return this.name;
    }

    /* 
     * A mutator method that takes a Card Object as a parameter and 
     * adds the specified card to the Player's hand, filling the array 
     * from left to right. It should throw an IllegalArgumentException 
     * if the parameter is null, or if the player already has the Maximum 
     * number of Cards
     */
    public void addCard(Card card){
        if (card == null || numCards == CardMatch.MAX_CARDS){
            throw new IllegalArgumentException();
        }
        this.hand[numCards] = card;
        numCards++;
    }

    /* 
     * An accessor method that takes an integer index as a parameter and 
     * returns the Card at the specified position in the player’s hand,
     * without actually removing the card from the hand. 
     */
    public Card getCard(int index){
        if (index >= numCards){
            throw new IllegalArgumentException();
        }
        return hand[index];
    }
    
    /* 
     * An accessor method that computes and returns the total value of the
     * player's current hand - i.e., the sum of the values of the individual
     * cards, plus an additional 20-point penalty if the hand has the maximum
     * number of cards.
     */
    public int getHandValue(){
        int result = 0;
        for (int i = 0; i < numCards; i++){
            result += hand[i].getValue();
        }
        if (numCards == CardMatch.MAX_CARDS){
            result -= 20;
        }
        return result;
    }

    /* 
     * an accessor method names displayHand that prints the current contents of
     * the player’s hand, preceded by a heading that includes the player’s name.
     * Each card should be printed on a separate line, preceded by the index of
     * its position in the hand.
     */
    public void displayHand(){
        System.out.println(this.name + "'s hand:");

        for (int i = 0; i < numCards; i++){
            System.out.println("  " + i + ": " + hand[i].getColor() + " " + hand[i].getValue());
        }
    }
    
    /* 
     * A mutator method that takes an integer index as a parameter and both removes
     * and returns the Card at that position of the player's hand.
     */
    public Card removeCard(int index){
        if (index >= numCards || index < 0){
            throw new IndexOutOfBoundsException();
        }
        if (index != numCards - 1){
            Card temp = hand[index];
            hand[index] = hand[numCards - 1];
            hand[numCards - 1] = null;
            numCards --;
            return temp;
        }else{
            Card temp = hand[index];
            hand[index] = null;
            numCards --;
            return temp;
        }
        
    }


    /* An accessor method that determines and returns the number corresponding to the
     * player's next play: either -1 if the player wants to draw a card, or the number/index
     * of the card that the player wants to discard from his/her hand.
     */
    public int getPlay(Scanner console, Card card){
        int userEnter;
        while (true){
            System.out.print(this.name + ": number of card to play (-1 to draw)? ");
        
            userEnter = console.nextInt(); 
            if (userEnter == -1 || (userEnter < numCards && userEnter >= 0)){
                break;
            }
        }
        return userEnter;
    }
}
