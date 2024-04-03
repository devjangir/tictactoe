package org.example;

import org.example.controllers.GameController;
import org.example.models.*;
import org.example.strategies.botplayingstrategy.NormalPlayingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("What should be the dimension of the board?");
        int dimensions = sc.nextInt();

        System.out.println("Will there be any bot in the game? y/n");
        String isBot = sc.next();

        List<Player> players = new ArrayList<>();
        int playerCount = dimensions - 1;
        if(isBot.equals("y")) {
            playerCount -= 1;
        }

        for(int i=0;i<playerCount;i++) {
            System.out.println("What is the name of the player? "+(i + 1));
            String name = sc.next();

            System.out.println("What is the symbol for this player " + (i+1));
            char symbol = sc.next().charAt(0);

            players.add(new Player(name, symbol, PlayerType.HUMAN));
        }

        if(isBot.equals("y")) {
            System.out.println("What is the name of the bot? ");
            String name = sc.next();

            System.out.println("What is the symbol for the bot player ");
            char symbol = sc.next().charAt(0);

            players.add(new Bot(name, symbol, BotDifficultyLevel.EASY, new NormalPlayingStrategy()));
        }

        Game game = gameController.createGame(dimensions, players);
        while(game.getGameState() == GameState.INPROGRESS) {
            System.out.println("This is the current board");
            gameController.displayBoard(game);
            gameController.executeNextMove(game);
        }
        System.out.println("Game has ended, Result is:");
        if(game.getGameState() == GameState.ENDED) {
            System.out.println("Winner is : " + game.getWinnerPlayer().getName());
            gameController.displayBoard(game);
        }
    }
}