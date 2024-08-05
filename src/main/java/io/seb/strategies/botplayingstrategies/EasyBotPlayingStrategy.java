package io.seb.strategies.botplayingstrategies;

import io.seb.models.Board;
import io.seb.models.Cell;
import io.seb.models.CellState;
import io.seb.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {

        for(List<Cell> cells : board.getBoard()) {
            for(Cell cell : cells) {
                if(cell.getCellState().equals(CellState.EMPTY)) {
                    return new Move(
                            null,
                            cell
                    );
                }
            }
        }

        return null;
    }
}
