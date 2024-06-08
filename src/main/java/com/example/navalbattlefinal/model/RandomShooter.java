package com.example.navalbattlefinal.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents a random shooter for generating random shots on a grid.
 */
public class RandomShooter {
    private List<int[]> remainingPositions; // List of remaining positions to shoot
    private List<int[]> shotsTaken; // List of shots already taken
    private Random random; // Random number generator

    /**
     * Constructs a new RandomShooter object.
     * @param rows The number of rows in the grid.
     * @param cols The number of columns in the grid.
     */
    public RandomShooter(int rows, int cols) {
        remainingPositions = new ArrayList<>();
        shotsTaken = new ArrayList<>();
        random = new Random();

        // Fill the list with all possible positions
        for (int row = 0; row <= rows; row++) {
            for (int col = 0; col <= cols; col++) {
                remainingPositions.add(new int[]{row, col});
            }
        }

        // Shuffle the list randomly
        Collections.shuffle(remainingPositions);
    }

    /**
     * Generates a random shot position and removes it from the list of remaining positions.
     * @return The random shot position as an array of integers [row, col].
     */
    public int[] shoot() {
        // Check if there are any remaining positions
        if (remainingPositions.isEmpty()) {
            return null; // No more positions available
        }

        // Get the first position from the shuffled list
        int[] position = remainingPositions.remove(0);

        // Record the shot position
        shotsTaken.add(position);

        return position;
    }

    /**
     * Gets the list of shots taken.
     * @return The list of shots taken.
     */
    public List<int[]> getShotsTaken() {
        return shotsTaken;
    }
}
