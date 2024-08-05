package io.seb.strategies.winningstrategies;

import io.seb.models.Board;
import io.seb.models.Move;
import io.seb.models.Player;
import io.seb.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy {

    private Map<Symbol, Integer> leftDiagonal = new HashMap<>();
    private Map<Symbol, Integer> rightDiagonal = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        boolean isWinner = false;

        // left diagonal => row = col
        if(row == col) {
            if(!leftDiagonal.containsKey(symbol)) {
                leftDiagonal.put(symbol, 0);
            }
            leftDiagonal.put(symbol, leftDiagonal.get(symbol) + 1);

            if(leftDiagonal.get(symbol).equals(board.getDimension())) {
                isWinner  = true;
            }
        }

        // right diagonal => row + col = dimension - 1
        if(row + col == board.getDimension() - 1 ) {
            if(!rightDiagonal.containsKey(symbol)) {
                rightDiagonal.put(symbol, 0);
            }
            rightDiagonal.put(symbol, rightDiagonal.get(symbol) + 1);

            if(rightDiagonal.get(symbol).equals(board.getDimension())) {
                isWinner = true;
            }
         }

        return isWinner;
    }
}
