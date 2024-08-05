package io.seb;

import io.seb.controllers.GameController;
import io.seb.exceptions.InvalidBotCountException;
import io.seb.exceptions.InvalidMoveException;
import io.seb.exceptions.InvalidPlayerCountException;
import io.seb.exceptions.InvalidPlayerSymbolException;
import io.seb.models.*;
import io.seb.strategies.winningstrategies.ColWinningStrategy;
import io.seb.strategies.winningstrategies.DiagonalWinningStrategy;
import io.seb.strategies.winningstrategies.RowWinningStrategy;
import io.seb.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException, InvalidPlayerSymbolException, InvalidMoveException {

        System.out.println("Hello and welcome!");

//        Game game = Game.getBuilder()
//                .setGameState(GameState.NOT_STARTED)
//                .setDimension(3)
//                .setWinningStrategies(new ArrayList<>())
//                .setPlayers(null)
//                .build();

        int dimension = 3;

        List<Player> players = new ArrayList<>();
        players.add(new Player("Mohit", new Symbol('X'), PlayerType.HUMAN));
        players.add(new Player("Akash", new Symbol('O'), PlayerType.HUMAN));
//        players.add(new Player("John", new Symbol('#'), PlayerType.BOT));

        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColWinningStrategy(),
                new DiagonalWinningStrategy()
        );

        GameController gameController = new GameController();
        Game game = gameController.startGame(
                dimension,
                players,
                winningStrategies
        );

//        gameController.printBoard(game);

        while(game.getGameState().equals(GameState.IN_PROGRESS)) {
            // 1. print the board
            // 2. Ask user to choose where they want to make the move.

            gameController.printBoard(game);
            gameController.makeMove(game);
        }

        if(game.getGameState().equals(GameState.ENDED)) {
            System.out.println();
            System.out.println(gameController.getWinner(game).getName() + " has WON the game!!!");
            gameController.printBoard(game);
        } else {
            System.out.println("Game has been DRAWN.");
        }

    }
}