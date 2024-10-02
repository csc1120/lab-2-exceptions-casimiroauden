/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Auden Casimiro
 * Last Updated: September 25
 */
package casimiroa;

/**
 * Die class
 */
public class Die {

    private int currentValue;
    private final int numSides;

    /**
     * Constructor
     * @param numSides Number of sides on die
     */
    public Die(int numSides){
        this.numSides = numSides;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    /**
     * Rolls die and returns value of rolled die
     */
    public void roll(){
        currentValue = (int) (1 + Math.random() * numSides);
    }
}