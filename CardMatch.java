/*
 * CardMatch.java
 * 
 * Computer Science 112, Boston University
 * 
 * The main class for a program that plays a card game called CardMatch.
 * It also serves as a blueprint class for a CardMatch object, which maintains
 * the state of the game.
 *
 * modified by: <Yukun Shan>, <yshan@bu.edu>
 */

import java.util.*;

public class CardMatch {
    /* the number of players in the game */
    public static final int NUM_PLAYERS = 2;
    
    /* cards per player at start of game */
    public static final int NUM_INIT_CARDS = 5;
    
    /* the game ends if a player ends up with this many cards */
    public static final int MAX_CARDS = 10; 
    
    /* the penalty for ending up with MAX_CARDS cards */
    public static final int MAX_CARDS_PENALTY = 20;
    
    /* fields of a CardMatch object */
    private Scanner console;    // used to read from the console
    private Deck deck;          // the deck of cards used for the game
    private Player[] players;   // the players of the game
    private Card topDiscard;    // card at the top of the discard pile
    
    /*
     * constructor - takes the Scanner to be used to read from the
     * console, a randomSeed for the Deck's random-number generator,
     * and the name of the player.
     */
    public CardMatch(Scanner console, int randomSeed, String playerName) {
        this.console = console;

        // Create and shuffle the deck.
        this.deck = new Deck(randomSeed);
        this.deck.shuffle();
        
        // Create the players.
        this.players = new Player[NUM_PLAYERS];
        this.players[0] = new Player(playerName);
        this.players[1] = new ComputerPlayer("the computer");
        
        // Deal the cards.
        this.dealHands();
        this.topDiscard = this.deck.drawCard();   
    }
    
    /*
     * dealHands - deals the initial hands of the players.
     * Each player gets NUM_INIT_CARDS cards.
     */
    public void dealHands() {
        for (int i = 0; i < NUM_INIT_CARDS; i++) {
            for (int j = 0; j < NUM_PLAYERS; j++) {
                this.players[j].addCard(this.deck.drawCard());
            }
        }
    }
    
    /*
     * play - plays an entire game of CardMatch
     */
    public void play() {
        this.printGameState();
        
        while (true) {
            // Each player makes one play.
            for (int i = 0; i < this.players.length; i++) {
                executeOnePlay(this.players[i]);
            }
            
            this.printGameState();
            if (this.gameOver()) {
                return;
            }
        }
    }
    
    /*
     * printGameState - prints the players' hands and the card at the
     * top of the discard pile
     */
    public void printGameState() {
        System.out.println();
        for (int i = 0; i < this.players.length; i++) {
            this.players[i].displayHand();
        }
        System.out.println("discard pile: ");
        System.out.println("  " + this.topDiscard);
    }
    
    /*
     * executeOnePlay - carries out a single play by the specified player
     */
    public void executeOnePlay(Player player) {
        // Keep looping until a valid play is obtained.
        // We break out of the loop using a return statement.
        while (true) {
            int cardNum = player.getPlay(this.console, this.topDiscard);
            
            if (cardNum == -1) {
                System.out.println(player + " draws a card.");
                player.addCard(this.deck.drawCard());
                return;
            } else {
                Card cardToPlay = player.getCard(cardNum);
                
                if (cardToPlay.matches(this.topDiscard)) {
                    System.out.println(player + " discards " + cardToPlay + ".");
                    player.removeCard(cardNum);
    
                    // The card played is now at the top of the discard pile.
                    this.topDiscard = cardToPlay;
                    return;
                } else {
                    System.out.println("invalid play -- " + cardToPlay + 
                                       " doesn't match top of discard pile");
                }
            }
        }
    }
    
    /*
     * gameOver - returns true if the game is over -- either because
     * a player has no cards or because a player has the maximum
     * number of cards -- and false otherwise.
     */
    public boolean gameOver() {  
        for (int i = 0; i < this.players.length; i++) {
            if (this.players[i].getNumCards() == 0
              || this.players[i].getNumCards() == MAX_CARDS) {
                    return true;
            }
        }
        
        return false;
    }
    
    /*
     * reportResults - determines and prints the results of the game.
     */
    public void reportResults() {
        int totalValue = 0;
     
        int winnerIndex = 0;
        int winnerValue = this.players[0].getHandValue();
        totalValue += winnerValue;
        boolean isTie = false;
        
        for (int i = 1; i < this.players.length; i++) {
            int playerValue = this.players[i].getHandValue();
            totalValue += playerValue;
            
            if (playerValue < winnerValue) {
                winnerValue = playerValue;
                winnerIndex = i;
                isTie = false;
            } else if (playerValue == winnerValue) {
                isTie = true;
            }
        }
        
        if (isTie) {
            System.out.println("The game is a tie; no one earns any points.");
        } else {
            System.out.print("The winner is " + this.players[winnerIndex]);
            System.out.print(", who earns " + (totalValue - winnerValue));
            System.out.println(" points.");
        }
    }
    
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        
        int seed = -1;
        if (args.length > 0) {
            seed = Integer.parseInt(args[0]);
        }
        
        System.out.println("Welcome to the game of Card Match!");
        System.out.print("What's your first name? ");
        String name = console.next();
               
        CardMatch game = new CardMatch(console, seed, name);
        game.play();
        game.reportResults();

        console.close();
    }
}
