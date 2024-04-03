package org.example.models;

import org.example.strategies.botplayingstrategy.IBotPlayingStrategy;

public class Bot extends Player {
    private BotDifficultyLevel botDifficultyLevel;
    private IBotPlayingStrategy botPlayingStrategy;
    public Bot(String name, char symbol, BotDifficultyLevel difficultyLevel, IBotPlayingStrategy botPlayingStrategy) {
        super(name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = difficultyLevel;
        this.botPlayingStrategy = botPlayingStrategy;
    }

    @Override
    public Move executeMove(Board board) {
        return botPlayingStrategy.executeMove(this, board);
    }
}
