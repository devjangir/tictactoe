package org.example.models;

import org.example.strategies.winningstrategy.CommonWinningStrategy;
import org.example.strategies.winningstrategy.IGameWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Board board;
    List<Player> players;
    List<Move> moves;
    GameState gameState;
    int nextPlayerIndex;
    private Player winnerPlayer;

    private IGameWinningStrategy winningStrategy;

    public static Builder getBuilder() {
        return new Builder();
    }

    public void displayBoard() {
        this.board.display();
    }
    public Player getWinnerPlayer() {
        return winnerPlayer;
    }

    public void setWinnerPlayer(Player player) {
        this.winnerPlayer = player;
    }

    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return this.moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public void executeNextMove() {
        Player player = players.get(nextPlayerIndex);
        System.out.println("It is "+player.getName()+" 's turn");
        Move move = player.executeMove(this.board);
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        System.out.println("Move played at "+ row + ", "+col);
        // update the board
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoard().get(row).get(col).setPlayer(player);

        Move finalMove = new Move(player, board.getBoard().get(row).get(col));
        this.moves.add(finalMove);

        // check for winner
        if(winningStrategy.checkWinner(board, player, board.getBoard().get(row).get(col))){
            setGameState(GameState.ENDED);
            setWinnerPlayer(player);
        }
        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();
    }

    public static class Builder {
        private int dimensions;
        private List<Player> players;

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private boolean isValid() {
            if(dimensions < 3) {
                //TODO dimension should be greate than 3 return exceptions here
                return false;
            }
            if(players.size() != dimensions - 1) {
                // TODO return exception here
                // player should be equals than dimension - 1
                return false;
            }
            return true;
        }
        public Game build() {
            // check for validation
            if(!isValid()) {
                return null;
            }
            Game game = new Game();
            game.setPlayers(this.players);
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(dimensions));
            game.setGameState(GameState.INPROGRESS);
            game.winningStrategy = new CommonWinningStrategy();
            game.setNextPlayerIndex(0);
            return game;
        }
    }
}
