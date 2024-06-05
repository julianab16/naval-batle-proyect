package com.example.navalbattlefinal.view;

public class Table {
    private String [][] table;

    public  Table()
    {
    initializableTable();
    }
    private void initializableTable(){
        this.table = new String[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                table[0][j] = String.valueOf(j);
                table[i][0] = String.valueOf(i);
                this.table[i][j] = ".";
            }
        }
    }

    public String[][] getTable() {
        return table;
    }

    public void setTable() {
        this.table = new String[11][11];
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                table[0][j] = String.valueOf(j);
                table[i][0] = String.valueOf(i);
                this.table[i][j] = ".";
            }
        }
    }

    public void printPlayerBoard() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

}
