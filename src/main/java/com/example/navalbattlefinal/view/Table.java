package com.example.navalbattlefinal.view;

/**
 * Represents the game board.
 */
public class Table {
    /** Two-dimensional array to represent the table. */
    private String[][] table;

    /**
     * Constructs a new Table object.
     */
    public Table() {
        initializeTable();
    }

    /**
     * Initializes the game board with default values.
     */
    private void initializeTable() {
        this.table = new String[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                table[0][j] = String.valueOf(j);
                table[i][0] = String.valueOf(i);
                this.table[i][j] = ".";
            }
        }
    }

    /**
     * Retrieves the table.
     * @return The two-dimensional array representing the table.
     */
    public String[][] getTable() {
        return table;
    }

    /**
     * Sets the table to its initial state.
     */
    public void setTable() {
        initializeTable();
    }

    /**
     * Prints the player's game board.
     */
    public void printPlayerBoard() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
}

