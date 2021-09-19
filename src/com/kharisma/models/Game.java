package com.kharisma.models;

import java.util.concurrent.TimeUnit;

public class Game {
    private final Board board;

    public Game(Board board) {
        this.board = board;
    }

    public void play() throws InterruptedException {
        while (true) {
            this.board.print();
            System.out.println("Remaining cells: " + this.board.countAliveCells() + "\n");

            if (this.board.countAliveCells() == 0) {
                break;
            }

            TimeUnit.MILLISECONDS.sleep(250);

            this.board.toNextGen();
        }
        System.out.println("All cells have died");
    }
}
