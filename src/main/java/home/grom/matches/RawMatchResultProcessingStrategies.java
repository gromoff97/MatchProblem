package home.grom.matches;

import static home.grom.matches.InvalidDataProcessingAction.FAIL_FAST;
import static home.grom.matches.InvalidDataProcessingAction.SKIP;
import static home.grom.matches.RawMatchParsingPatterns.softSplitValidationPattern;
import static home.grom.matches.ScoreConverters.splittingWithRegex;

/**
 * Если нужен новый способ обработки данных, то добавлять его нужно сюда.
 * */
public final class RawMatchResultProcessingStrategies {

    private RawMatchResultProcessingStrategies() {
        throw new AssertionError("Current class must not be initialized because it's Utility-class");
    }

    public static RawMatchResultProcessingStrategy charSoftSplitFailFastProcessingStrategy(char c) {
        return RawMatchResultProcessingStrategy.builder()
                .validationPattern(softSplitValidationPattern(c))
                .converter(splittingWithRegex(String.valueOf(c)))
                .onInvalidData(FAIL_FAST)
                .build();
    }

    public static RawMatchResultProcessingStrategy charSoftSplitSkipProcessingStrategy(char c) {
        return RawMatchResultProcessingStrategy.builder()
                .validationPattern(softSplitValidationPattern(c))
                .converter(splittingWithRegex(String.valueOf(c)))
                .onInvalidData(SKIP)
                .build();
    }

}
