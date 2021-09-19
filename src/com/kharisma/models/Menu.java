package com.kharisma.models;

import com.kharisma.constants.BoardConstants;
import com.kharisma.utils.FileUtils;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Scanner inputScanner = new Scanner(System.in);

    public static void selectBoardType() throws InterruptedException {
        System.out.println("Select how you want to play!");
        System.out.println("1. Select board from file in pattern folder");
        System.out.println("2. Make your own board");
        System.out.println("Type your number choice!");

        try {
            if (Integer.parseInt(inputScanner.nextLine()) == 1) {
                selectBoardFromFile();
            } else {
                makeMyBoard();
            }
        } catch (Exception e) {
            System.out.println("Must be a number!");
            selectBoardType();
        }
    }

    public static void selectBoardFromFile() throws InterruptedException {
        System.out.println("Available files: ");

        List<String> fileNames = FileUtils.getDirectoryFileNames("pattern/");
        for (int i = 0; i < fileNames.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + fileNames.get(i));
        }

        int inputInt = -1;
        while (inputInt < 1 || inputInt > fileNames.size()) {
            try {
                System.out.println("Type your number choice!");
                String input = inputScanner.nextLine();
                inputInt = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Must be a number!");
                selectBoardFromFile();
            }
        }

        Board board = new Board(fileNames.get(inputInt - 1));
        Game game = new Game(board);
        game.play();
    }

    public static void makeMyBoard() throws InterruptedException {
        Board board = new Board(BoardConstants.BOARD_DEFAULT_ROW, BoardConstants.BOARD_DEFAULT_COLUMN);

        String input;
        while (true) {
            board.print();

            System.out.println("Type a coordinate to add a cell. Format = '<row> <col>'");
            System.out.println("Type 'play' to play!");

            input = inputScanner.nextLine();
            if (input.equals("play")) {
                break;
            }

            try {
                String[] rowcol = input.split(" ");
                int row = Integer.parseInt(rowcol[0]);
                int col = Integer.parseInt(rowcol[1]);
                board.getBoard()[row][col].setAlive();
            } catch (Exception e) {
                System.out.println("Must be of format '<row> <col>' and number!");
            }
        }

        Game game = new Game(board);
        game.play();
    }
}
