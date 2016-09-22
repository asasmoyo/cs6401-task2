package com.company;

import java.util.ArrayDeque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by asasmoyo on 22/09/2016.
 */
public class AStarSearch {

    private State initialState;
    private State finalState;

    public AStarSearch(State initialState) {
        this.initialState = initialState;
    }

    public void solve() {
        System.out.println("### INITIAL STATE ###");
        this.initialState.display();
        System.out.println();

        PriorityQueue<State> openQueue = this.createOpenQueue();
        openQueue.add(this.initialState);
        Queue<State> closedState = new ArrayDeque<>();
        while (!openQueue.isEmpty()) {
            State currentState = openQueue.poll();
            closedState.add(currentState);

            List<State> nextPossibleState = currentState.getNextPossibleState();
            openQueue.addAll(nextPossibleState);

            if (currentState.isGoal()) {
                this.finalState = currentState;
                break;
            }
        }

        System.out.println("### FINAL STATE ###");
        this.finalState.display();
        System.out.println();

        System.out.println("### STEPS (" + closedState.size() + "):###");
        while (!closedState.isEmpty()) {
            closedState.poll().display();
            System.out.println();
        }
    }

    private PriorityQueue<State> createOpenQueue() {
        PriorityQueue<State> queue;
        queue = new PriorityQueue<>(1, (o1, o2) -> o1.getF() - o2.getF());
        return queue;
    }

}
