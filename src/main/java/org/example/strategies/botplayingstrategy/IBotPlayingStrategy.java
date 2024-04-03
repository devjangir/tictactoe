package org.example.strategies.botplayingstrategy;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Player;

public interface IBotPlayingStrategy {
    Move executeMove(Player player, Board board);
}
