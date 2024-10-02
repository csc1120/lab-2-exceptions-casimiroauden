/*
 * Course: CSC1020
 * Lab 2 - Exceptions
 * Main Driver class
 * Name: Auden Casimiro
 * Last Updated: September 25
 */
package casimiroa;

import java.util.Scanner;

/**
 * Main driver class for rolled dice output
 */
public class Driver {
    public static void main(String[] args) {
        String alaska = getInput();

        String[] dice = alaska.split(" ");

        int numDice = Integer.parseInt(dice[0]);
        int numSides = Integer.parseInt(dice[1]);
        int rolls = Integer.parseInt(dice[2]);


        int[] dieIndex = createDice(numDice, numSides);

        rollDice(numDice, numSides, rolls, dieIndex);

        int max = findMax(dieIndex);


        report(max, numDice, dieIndex);
    }

    /**
     * Get the input of user of numDice numSides and rolls
     * @return String of inputs of dice
     */
    public static String getInput() {
        String numsOut = "";
        String[] output = {"", "101"};
        Scanner scan = new Scanner(System.in);
        boolean numFormat = true;
        final int maxSides = 100;

        int sides = Integer.parseInt(output[1]);

        while (output.length != 3 || sides > maxSides || sides < 2 || numFormat) {
            System.out.println("""
                    Please enter the number of dice to roll, how many sides the dice have,
                    and how many rolls to complete, separating the values by a space.
                    Example: "2 6 1000\"""");
            System.out.print("\nEnter configuration: ");

            numsOut = scan.nextLine();
            output = numsOut.split(" ");

            try {
                sides = Integer.parseInt(output[1]);
            } catch (NumberFormatException e){
                sides = maxSides + 1;
            }

            if (output.length != 3) {
                System.out.println("Invalid input: Expected 3 values but only received 2");
            } else if (sides > maxSides || sides < 2){
                System.out.println("Bad die creation: Illegal number of sides: "+output[1]);
            } else{
                numFormat = false;
                for (int x = 0; x <= 2; x++) {
                    try {
                        Integer.parseInt(output[x]);
                    } catch (NumberFormatException e) {
                        numFormat = true;
                        System.out.println("Invalid input: All values must be whole numbers.");
                    }
                }
            }

        }
        return numsOut;
    }

    /**
     * Creates an array to store dice rolls
     * @param numDice Number of dice to roll
     * @param numSides Number of sides on each dice
     * @return length of dataIndex array
     */
    public static int[] createDice(int numDice, int numSides) {
        int length = numDice * numSides - numDice + 1;
        return new int[length];
    }

    /**
     * Rolls the dice and records data to die index
     * @param numDice Number of dice to roll
     * @param numSides Number of sides on each dice
     * @param rolls Number of times to roll dice
     * @param dieIndex data set of dice and number of rolls
     */
    public static void rollDice(int numDice, int numSides, int rolls, int[] dieIndex){
        Die die = new Die(numSides);

        for (int x = 0; x < rolls; x++){
            int value = 0;
            for (int y = 0; y < numDice; y++){
                die.roll();
                value += die.getCurrentValue();
            }
            dieIndex[value - numDice]++;
        }
    }

    /**
     * Finds the max in the dieIndex
     * @param dieIndex data set of dice and number of rolls
     * @return the max number of rolls for any number
     */
    public static int findMax(int[] dieIndex){
        int max = dieIndex[0];

        for (int x = 1; x < dieIndex.length; x++) {
            if (dieIndex[x] > max) {
                max = dieIndex[x];
            }
        }

        return max;
    }

    /**
     * outputs the data set in a neat format and asterisk graph
     * @param max the max roll for any number
     * @param numDice number of dice
     * @param dieIndex data set of all the die values and number of rolls
     */
    public static void report(int max, int numDice, int[] dieIndex){
        for(int x = 0; x < dieIndex.length; x++){
            int value = numDice + x;
            String leftAlign = String.format("%-3d:%-8d", value, dieIndex[x]);

            System.out.print(leftAlign);

            final int ten = 10;
            int scale = max / ten;
            int numStars = dieIndex[x] / scale;
            while (numStars > 0){
                System.out.print("*");
                numStars--;
            }
            System.out.println(" ");
        }
    }
}

