package com.example.navalbattlefinal.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomShooter {
    private List<int[]> remainingPositions;
    private Random random;

    public RandomShooter(int rows, int cols) {
        remainingPositions = new ArrayList<>();
        random = new Random();

        // Llenar la lista con todas las posiciones posibles
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                remainingPositions.add(new int[]{row, col});
            }
        }

        // Barajar aleatoriamente la lista
        Collections.shuffle(remainingPositions);
    }

    public int[] shoot() {
        // Verificar si quedan posiciones disponibles
        if (remainingPositions.isEmpty()) {
            return null; // No hay más posiciones disponibles
        }

        // Obtener la primera posición de la lista barajada
        int[] position = remainingPositions.remove(0);

        return position;
    }
}
