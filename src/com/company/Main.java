package com.company;

public class Main {

    public static void main(String[] args) {
        Board initialBoard = new Board(new int[]{0, 1, 2, 5, 6, 3, 4, 7, 8});
        State initialState = new State(0, initialBoard);
        AStarSearch aStar = new AStarSearch(initialState);
        aStar.solve();
    }
}
