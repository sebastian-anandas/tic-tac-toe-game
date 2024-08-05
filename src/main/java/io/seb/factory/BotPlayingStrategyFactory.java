package io.seb.factory;

import io.seb.models.BotDifficultyLevel;
import io.seb.strategies.botplayingstrategies.BotPlayingStrategy;
import io.seb.strategies.botplayingstrategies.EasyBotPlayingStrategy;
import io.seb.strategies.botplayingstrategies.HardBotPlayingStrategy;
import io.seb.strategies.botplayingstrategies.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel botDifficultyLevel) {
        if(botDifficultyLevel.equals(BotDifficultyLevel.EASY)) {
            return new EasyBotPlayingStrategy();
        } else if(botDifficultyLevel.equals(BotDifficultyLevel.MEDIUM)) {
            return new MediumBotPlayingStrategy();
        } else if(botDifficultyLevel.equals(BotDifficultyLevel.HARD)) {
            return new HardBotPlayingStrategy();
        }

        return null;
    }
}
