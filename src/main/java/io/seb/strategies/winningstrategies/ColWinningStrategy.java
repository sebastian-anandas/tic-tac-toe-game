package io.seb.strategies.winningstrategies;

import io.seb.models.Board;
import io.seb.models.Move;
import io.seb.models.Player;
import io.seb.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy {

    private Map<Integer, Map<Symbol, Integer>> colCounts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {

        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!colCounts.containsKey(col)) {
            colCounts.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> colMap = colCounts.get(col);

        if(!colMap.containsKey(symbol)) {
            colMap.put(symbol, 0);
        }
        colMap.put(symbol, colMap.get(symbol) + 1);


        return colMap.get(symbol).equals(board.getDimension());
    }
}
