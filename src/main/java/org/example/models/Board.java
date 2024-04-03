package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<List<Cell>> board;

    public Board(int dimensions) {
        this.board = new ArrayList<>();
        for(int i=0;i<dimensions;i++) {
            this.board.add(new ArrayList<>());
            for(int j=0;j<dimensions;j++) {
                this.board.get(i).add(new Cell(i, j));
            }
        }
    }
    public void display() {
        final int size = board.size();
        for (List<Cell> cells : board) {
            for (int j = 0; j < size; j++) {
                if (cells.get(j).getCellState() == CellState.EMPTY) {
                    System.out.print("|   |");
                } else {
                    System.out.print("| " + cells.get(j).getPlayer().getSymbol() + " |");
                }
            }
            System.out.println();
        }
    }

    public List<List<Cell>> getBoard() { return board; }

    public void setBoard(List<List<Cell>> board) {
        this.board = board;
    }
}
