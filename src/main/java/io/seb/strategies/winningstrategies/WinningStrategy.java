package io.seb.strategies.winningstrategies;

import io.seb.models.Board;
import io.seb.models.Move;
import io.seb.models.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
