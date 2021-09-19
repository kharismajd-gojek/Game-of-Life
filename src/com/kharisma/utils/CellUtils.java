package com.kharisma.utils;

import com.kharisma.models.Cell;

import java.util.List;

public class CellUtils {

    public static Integer countAliveCells(List<Cell> cells) {
        int count = 0;
        for (Cell cell:cells) {
            if (cell.isAlive()) {
                count += 1;
            }
        }
        return count;
    }
}
