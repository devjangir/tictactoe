package org.example.models;

import java.util.Scanner;

public class Player {
    char symbol;
    String name;
    PlayerType playerType;

    public Player(String name, char symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move executeMove(Board board) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the row number starting from 0:");
        int row = sc.nextInt();
        System.out.println("Enter the column number starting from 0:");
        int col = sc.nextInt();
        //TODO check validation
        return new Move(this, new Cell(row, col));
    }
}
