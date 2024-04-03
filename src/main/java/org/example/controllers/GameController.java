package org.example.controllers;

import org.example.models.Game;
import org.example.models.GameState;
import org.example.models.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players) {
        return Game.getBuilder()
                .setDimensions(dimension)
                .setPlayers(players)
                .build();
    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public GameState getGameStatus(Game game) {
        return game.getGameState();
    }

    public void executeNextMove(Game game) {
        game.executeNextMove();
    }
}
