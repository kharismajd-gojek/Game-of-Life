package com.kharisma.models;

import com.kharisma.constants.CellConstants;
import com.kharisma.utils.CellUtils;
import com.kharisma.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final Integer row;
    private final Integer column;
    private final Cell[][] boardCells;

    public Board(Integer row, Integer column) {
        this.row = row;
        this.column = column;
        this.boardCells = new Cell[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.boardCells[i][j] = new Cell(i, j, CellConstants.DEAD);
            }
        }
    }

    public Board(String fileName) {
        List<String> content = FileUtils.scanFile("pattern/" + fileName);

        this.row = content.size();
        this.column = content.get(0).length();

        Cell[][] boardCells = new Cell[this.row][this.column];

        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (content.get(i).charAt(j) == '.') {
                    boardCells[i][j] = new Cell(i, j, CellConstants.DEAD);
                } else {
                    boardCells[i][j] = new Cell(i, j, CellConstants.ALIVE);
                }
            }
        }

        this.boardCells = boardCells;
    }

    public Cell[][] getBoard() {
        return boardCells;
    }

    public void print() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.boardCells[i][j].print();
            }
            System.out.println();
        }
    }

    public Boolean isValid(Integer rowPos, Integer colPos) {
        return (0 <= rowPos && rowPos < this.row) && (0 <= colPos && colPos < this.column);
    }

    public List<Cell> getSurroundingCell(Cell cell) {
        List<Cell> cells = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Integer checkRow = cell.getRowPos() + i;
                Integer checkCol = cell.getColPos() + j;
                if ((isValid(checkRow, checkCol))
                        && !(checkRow.equals(cell.getRowPos()) && checkCol.equals(cell.getColPos()))) {
                    cells.add(this.boardCells[checkRow][checkCol]);
                }
            }
        }
        return cells;
    }

    public Integer countAliveCells() {
        int count = 0;
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (this.boardCells[i][j].isAlive()) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public Boolean isCellShouldAliveInNextGen(Cell cell) {
        List<Cell> surroundingCells = this.getSurroundingCell(cell);
        Integer aliveSurroundingCells = CellUtils.countAliveCells(surroundingCells);
        if (cell.isAlive()) {
            if (aliveSurroundingCells >= CellConstants.ALIVE_TO_DEAD_MIN_BOUNDARY
                    && aliveSurroundingCells <= CellConstants.ALIVE_TO_DEAD_MAX_BOUNDARY) {
                return true;
            } else {
                return false;
            }
        } else {
            if (aliveSurroundingCells.equals(CellConstants.DEAD_TO_ALIVE_BOUNDARY)) {
                return true;
            }
        }
        return false;
    }

    public void toNextGen() {
        List<Cell> aliveToDeadCells = new ArrayList<>();
        List<Cell> deadToAliveCells = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                Cell cell = this.boardCells[i][j];
                if (isCellShouldAliveInNextGen(cell) && !cell.isAlive()) {
                    deadToAliveCells.add(cell);
                } else if (!isCellShouldAliveInNextGen(cell) && cell.isAlive()){
                    aliveToDeadCells.add(cell);
                }
            }
        }

        for (Cell cell:deadToAliveCells) {
            cell.setAlive();
        }

        for (Cell cell:aliveToDeadCells) {
            cell.setDead();
        }
    }
}