package com.company;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asasmoyo on 22/09/2016.
 */
public class State {

    private int cost;
    private Board board;

    public State(int cost, Board board) {
        this.cost = cost;
        this.board = board;
    }

    public void display() {
        this.board.display();
        System.out.println("Cost: " + this.cost);
        System.out.println(String.format("Heuristic: [f=%d g=%d h=%d]", this.getF(), this.getG(), this.getH()));
    }

    public List<State> getPossibleNextStates() {
        List<State> possibleStates = new ArrayList<>();

        // move up
        Board boardUp = this.board.moveUp();
        if (boardUp != null) {
            possibleStates.add(new State(this.cost + 1, boardUp));
        }
        // move down
        Board boardDown = this.board.moveDown();
        if (boardDown != null) {
            possibleStates.add(new State(this.cost + 1, boardDown));
        }
        // move left
        Board boardLeft = this.board.moveLeft();
        if (boardLeft != null) {
            possibleStates.add(new State(this.cost + 1, boardLeft));
        }
        // move right
        Board boardRight = this.board.moveRight();
        if (boardRight != null) {
            possibleStates.add(new State(this.cost + 1, boardRight));
        }

        return possibleStates;
    }

    public boolean isGoal() {
        return this.board.isGoal();
    }

    public int getF() {
        return this.getG() + this.getH();
    }

    public int getG() {
        return this.cost;
    }

    public int getH() {
        return this.board.getManhattanDistance();
    }

}
