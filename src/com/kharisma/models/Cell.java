package com.kharisma.models;

import com.kharisma.constants.CellConstants;

public class Cell {

    private final Integer rowPos;
    private final Integer colPos;
    private Boolean isAlive;

    public Cell(Integer rowPos, Integer colPos, Boolean isAlive) {
        this.rowPos = rowPos;
        this.colPos = colPos;
        this.isAlive = isAlive;
    }

    public Integer getRowPos() {
        return rowPos;
    }

    public Integer getColPos() {
        return colPos;
    }

    public void setAlive() {
        this.isAlive = CellConstants.ALIVE;
    }

    public void setDead() {
        this.isAlive = CellConstants.DEAD;
    }

    public Boolean isAlive() {
        return isAlive;
    }

    public void print() {
        if (isAlive()) {
            System.out.print("██");
        } else {
            System.out.print("░░");
        }
    }
}