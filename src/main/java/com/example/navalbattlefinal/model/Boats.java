package com.example.navalbattlefinal.model;

/**
 * Base class representing boats in the game.
 */
public class Boats {
    protected int size; // Size of the boat
    protected String name; // Name of the boat

    /**
     * Constructor to initialize a boat with a given size and name.
     * @param size The size of the boat.
     * @param name The name of the boat.
     */
    public Boats(int size, String name) {
        this.size = size;
        this.name = name;
    }

    /**
     * Retrieves the size of the boat.
     * @return The size of the boat.
     */
    public int getSize() {
        return size;
    }

    /**
     * Retrieves the name of the boat.
     * @return The name of the boat.
     */
    public String getName() {
        return name;
    }
}

