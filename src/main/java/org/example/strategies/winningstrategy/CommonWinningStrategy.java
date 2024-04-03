package org.example.strategies.winningstrategy;

import org.example.models.Board;
import org.example.models.Cell;
import org.example.models.CellState;
import org.example.models.Player;

public class CommonWinningStrategy implements IGameWinningStrategy {

    boolean isCellOnTopDiagonal(int row, int col) {
        return row == col;
    }

    boolean isCellOnBottomDiagonal(int row, int col, int dimension) {
        return row + col == dimension - 1;
    }
    @Override
    public boolean checkWinner(Board board, Player currentPlayer, Cell moveCell) {
        // get the row and col of the current cell
        int row = moveCell.getRow();
        int col = moveCell.getCol();
        int dimensions = board.getBoard().size();
        // check for the current row if all filled by current player
        int countSymbol = 0;
        for(int i=0;i<dimensions;i++) {
            Cell cell = board.getBoard().get(i).get(col);
            if(cell.getCellState() == CellState.FILLED && cell.getPlayer().getSymbol() == currentPlayer.getSymbol()) {
                countSymbol++;
            }
        }
        if(countSymbol == dimensions) {
            return true;
        }
        countSymbol = 0;
        // check for the current col if all symbol filled by current player
        for(int i=0;i<dimensions;i++) {
            Cell cell = board.getBoard().get(row).get(i);
            if(cell.getCellState() == CellState.FILLED && cell.getPlayer().getSymbol() == currentPlayer.getSymbol()) {
                countSymbol++;
            }
        }
        if(countSymbol == dimensions) {
            return true;
        }
        // check for top diagonal
        /*
            - - 0
            - 0 -
            0 - -
         */
        if(isCellOnBottomDiagonal(row, col, dimensions)) {
            countSymbol = 0;
            for(int i=0;i<dimensions;i++) {
                Cell cell = board.getBoard().get(i).get(dimensions - i - 1);
                if(cell.getCellState() == CellState.FILLED && cell.getPlayer().getSymbol() == currentPlayer.getSymbol()) {
                    countSymbol++;
                }
            }
            if(countSymbol == dimensions) {
                return true;
            }
        }
        // check for bottom diagonal
        /*
            0 - -
            - 0 -
            - - 0
         */
        if(isCellOnTopDiagonal(row, col)) {
            countSymbol = 0;
            for(int i=0;i<dimensions;i++) {
                Cell cell = board.getBoard().get(i).get(i);
                if(cell.getCellState() == CellState.FILLED && cell.getPlayer().getSymbol() == currentPlayer.getSymbol()) {
                    countSymbol++;
                }
            }
            if(countSymbol == dimensions) {
                return true;
            }
        }
        return false;
    }
}
