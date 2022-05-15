/*
 * ComputerPlayer.java
 * 
 * a blueprint for objects that represent a 
 * single computer player.
 * 
 * completed by: Yukun Shan(yshan@bu.edu)
 */
import java.util.*;

public class ComputerPlayer extends Player{

    /*
     * ComputerPlayer Constructor
     */
    public ComputerPlayer(String name){
        super(name);
    }

    /* 
     * a displayHand method that overrides the inherited version 
     * of that method. This version of the method should simply print
     * the number of cards in the ComputerPlayer‘s hand.
     */
    public void displayHand(){
        System.out.println(this.getName() + "'s hand:");
        if (this.getNumCards() == 1){
            System.out.println("  " + this.getNumCards() + " card");
        }else{
            System.out.println("  " + this.getNumCards() + " cards");
        }
    }

    /* a getPlay method that overrides the inherited version of that method.
     * This version of the method should figure out if the computer has a card
     * that matches the card at the top of the discard pile (this card is 
     * passed in as the second parameter of the method). 
     */
    public int getPlay(Scanner console, Card card){
        int matchIndex = -1;
        int maxValue = 0;       
        for (int i = 0; i < this.getNumCards(); i++){
            if (this.getCard(i).matches(card) && this.getCard(i).getValue() > maxValue){
                matchIndex = i;
                maxValue = this.getCard(i).getValue();
            }
        }
        return matchIndex;

    }
}