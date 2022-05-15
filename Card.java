/*
 * Card.java
 * 
 * A blueprint class for objects that represent a single playing card 
 * for a game in which cards have both colors and numeric values.
 * 
 * starter code: CS 112 Staff (cs112-staff@cs.bu.edu)
 * completed by: Yukun Shan(yshan@bu.edu)
 */

public class Card {
    /* The smallest possible value that a Card can have. */
    public static final int MIN_VALUE = 0;
    
    /* The possible colors that a Card can have. */
    public static final String[] COLORS = {"blue", "green", "red", "yellow"};

    /* Define the third class constant here. */
    public static final int MAX_VALUE = 9;
    
    /* Define the instance variables of the class */
    private String color;
    private int value;

    // Constructor for class object;
    public Card(String color, int value){
        this.setColor(color);
        this.setValue(value);
    }
    
    /* Put the rest of your class definition below. */
    public static boolean isValidColor(String name){
        for (int i = 0; i < COLORS.length; i++){
            if (COLORS[i].equals(name)){
                return true;
            }
        }
        return false;
    }

    /* Mutator methods for the fields */
    public void setColor(String initcolor){
        if (!isValidColor(initcolor)){
            throw new IllegalArgumentException();
        }
        this.color = initcolor;
    }

    public void setValue(int initvalue){
        if (initvalue < MIN_VALUE || initvalue > MAX_VALUE){
            throw new IllegalArgumentException();
        }
        this.value = initvalue;
    }

    /* Accessor methods */
    public int getValue(){
        return this.value;
    }

    public String getColor(){
        return this.color;
    }


    /* ToString method for Card objects */
    public String toString(){
        String str = "" + this.color;
        str += " " + this.value;
        return str;
    }
    
    /* Methods for comparing Card Objects */
    public boolean matches(Card other){
        return (this != null && other != null) && 
                (this.color == other.color || other.value == this.value);
    }

    public boolean equals(Card other){
        return (this != null && other != null) &&
                this.color == other.color && this.value == other.value;

    }

    public static void main(String[] args){
    
    }
}
    