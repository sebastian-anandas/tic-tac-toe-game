package io.seb.controllers;

import io.seb.exceptions.InvalidBotCountException;
import io.seb.exceptions.InvalidMoveException;
import io.seb.exceptions.InvalidPlayerCountException;
import io.seb.exceptions.InvalidPlayerSymbolException;
import io.seb.models.Game;
import io.seb.models.GameState;
import io.seb.models.Player;
import io.seb.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) throws InvalidBotCountException, InvalidPlayerCountException, InvalidPlayerSymbolException {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }

    public GameState getGameState(Game game) {
        return game.getGameState();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

}
