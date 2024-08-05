package io.seb.strategies.botplayingstrategies;

import io.seb.models.Board;
import io.seb.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
