package org.example.strategies.winningstrategy;

import org.example.models.Board;
import org.example.models.Player;
import org.example.models.Cell;

public interface IGameWinningStrategy {
    boolean checkWinner(Board board, Player currentPlayer, Cell moveCell);
}
