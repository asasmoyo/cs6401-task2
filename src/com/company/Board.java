package com.company;

/**
 * Created by asasmoyo on 22/09/2016.
 */
public class Board {

    private static final int SIZE = 3;
    private static final int[][] GOAL_VALUES = new int[][]{
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };

    private int[][] values;
    private int hZero;
    private int vZero;

    public Board(int[][] values) {
        this.values = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (values[i][j] == 0) {
                    this.hZero = j;
                    this.vZero = i;
                }
                this.values[i][j] = values[i][j];
            }
        }
    }

    public Board(int[] values) {
        this.values = new int[SIZE][SIZE];
        int counter = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (values[counter] == 0) {
                    this.hZero = j;
                    this.vZero = i;
                }
                this.values[i][j] = values[counter++];
            }
        }
    }

    public void display() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(this.values[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(String.format("Zero at: [%d,%d]", this.vZero, this.hZero));
        System.out.println("Is Goal: " + this.isGoal());
        System.out.println("Manhattan Distance: " + this.getManhattanDistance());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Board)) {
            return false;
        }

        return this.hashCode() == o.hashCode();
    }

    @Override
    public int hashCode() {
        String temp = "";
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                temp += this.values[i][j];
            }
        }
        return temp.hashCode();
    }

    public boolean isGoal() {
        boolean isGoal = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.values[i][j] != GOAL_VALUES[i][j]) {
                    isGoal = false;
                }
            }
        }
        return isGoal;
    }

    public int getManhattanDistance() {
        int distance = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int horizontal, vertical;
                if (this.values[i][j] == 0) {
                    horizontal = 2;
                    vertical = 2;
                } else {
                    horizontal = (this.values[i][j] - 1) % 3;
                    vertical = (this.values[i][j] - 1) / 3;
                }
                distance += Math.abs(horizontal - j) + Math.abs(vertical - i);
            }
        }
        return distance;
    }

    private void swap(int[][] values, int v, int h, int newV, int newH) {
        int temp = values[v][h];
        values[v][h] = values[newV][newH];
        values[newV][newH] = temp;
    }

    public Board moveUp() {
        if (this.vZero - 1 < 0) {
            return null;
        }
        int oldV = this.vZero;
        int newV = this.vZero - 1;

        int[][] newValues = this.getClonedValues();
        this.swap(newValues, oldV, this.hZero, newV, this.hZero);
        return new Board(newValues);
    }

    public Board moveDown() {
        if (this.vZero + 1 >= SIZE) {
            return null;
        }
        int oldV = this.vZero;
        int newV = this.vZero + 1;

        int[][] newValues = this.getClonedValues();
        this.swap(newValues, oldV, this.hZero, newV, this.hZero);
        return new Board(newValues);
    }

    public Board moveLeft() {
        if (this.hZero - 1 < 0) {
            return null;
        }
        int oldH = this.hZero;
        int newH = this.hZero - 1;

        int[][] newValues = this.getClonedValues();
        this.swap(newValues, this.vZero, oldH, this.vZero, newH);
        return new Board(newValues);
    }

    public Board moveRight() {
        if (this.hZero + 1 >= SIZE) {
            return null;
        }
        int oldH = this.hZero;
        int newH = this.hZero + 1;

        int[][] newValues = this.getClonedValues();
        this.swap(newValues, this.vZero, oldH, this.vZero, newH);
        return new Board(newValues);
    }

    public int[][] getClonedValues() {
        int[][] clone = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                clone[i][j] = this.values[i][j];
            }
        }
        return clone;
    }

}
