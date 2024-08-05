package io.seb.models;

import io.seb.exceptions.InvalidBotCountException;
import io.seb.exceptions.InvalidMoveException;
import io.seb.exceptions.InvalidPlayerCountException;
import io.seb.exceptions.InvalidPlayerSymbolException;
import io.seb.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {

    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerMoveIndex;
    private List<WinningStrategy> winningStrategies;

    public static Builder getBuilder() {
        return new Builder();
    }
    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.moves = new ArrayList<>();
        this.board = new Board(dimension);
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerMoveIndex = 0;
        this.winningStrategies = winningStrategies;
    }

    public void printBoard() {
        board.print();
    }

    private boolean validateMove(Move move) {
        Player player = move.getPlayer();
        Cell cell = move.getCell();
        int row = cell.getRow();
        int col = cell.getCol();

        if(row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()
            || board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)) {
            return false;
        }
        return true;

    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerMoveIndex);
        System.out.println("It is " + currentPlayer.getName() + "'s move.");

        // ask the current player for the move
        Move move = currentPlayer.makeMove(board);

        // before making the move, validate if the cell is empty or not
        if(!validateMove(move)) {
            throw new InvalidMoveException("Invalid move, please try again!");
        }

        // if valid move, we can execute on the board
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        Move finalMove = new Move(currentPlayer, cell);
        moves.add(finalMove);

        nextPlayerMoveIndex = (nextPlayerMoveIndex + 1) % players.size();

        if(checkWinner(finalMove)) {
            winner = currentPlayer;
            gameState = GameState.ENDED;
        } else if(moves.size() == board.getDimension() * board.getDimension()) {
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move) {
        // check the row, column and diagonal
        for(WinningStrategy winningStrategy : winningStrategies) {
            if(winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }


    public static class Builder {
        private List<Player> players;
        private int dimension;
        private GameState gameState;
        private List<WinningStrategy> winningStrategies;


        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setGameState(GameState gameState) {
            this.gameState = gameState;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        private void validateBotCount() throws InvalidBotCountException {
            int botCount = 0;
            for(Player player : players) {
                if(player.getPlayerType().equals(PlayerType.BOT)) {
                    botCount += 1;
                }
            }
            if(botCount > 1) {
                throw new InvalidBotCountException("Only 1 Bot is allowed per game.");
            }
        }

        private void validatePlayerCount() throws InvalidPlayerCountException {
            if(players.size() != dimension - 1) {
                throw new InvalidPlayerCountException("Number of players should be 1 less than the dimensions");
            }
        }

        private void validateUniquePlayerSymbol() throws InvalidPlayerSymbolException {
            HashSet<Character> symbolSet = new HashSet<>();
            for(Player player : players) {
                if(symbolSet.contains(player.getSymbol().getaChar())) {
                    throw new InvalidPlayerSymbolException("No 2 players can have the same Symbol. Please select a unique Symbol!");
                } else {
                    symbolSet.add(player.getSymbol().getaChar());
                }
            }
        }

        private void validate() throws InvalidBotCountException, InvalidPlayerCountException, InvalidPlayerSymbolException {
            validateBotCount();
            validatePlayerCount();
            validateUniquePlayerSymbol();
        }

        public Game build() throws InvalidBotCountException, InvalidPlayerCountException, InvalidPlayerSymbolException {
            // before building the game we should validate the game object
            validate();

            return new Game(
                    dimension,
                    players,
                    winningStrategies
            );
        }
    }

}
