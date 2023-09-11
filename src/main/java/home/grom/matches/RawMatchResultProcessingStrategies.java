package home.grom.matches;

import static home.grom.matches.InvalidDataProcessingAction.FAIL_FAST;
import static java.lang.Integer.parseInt;

import java.util.function.Function;
import java.util.regex.Pattern;

public final class RawMatchResultProcessingStrategies {

    public static final Pattern defaultRawMatchResultParsingPattern = Pattern.compile("^\\s*(0|[1-9]\\d*)\\s*:\\s*(0|[1-9]\\d*)\\s*$");
    public static final Function<String, ScorePair> defaultConversionFunction = defaultConversionFunction();

    private RawMatchResultProcessingStrategies() {
        throw new AssertionError("Current class must not be initialized because it's Utility-class");
    }

    public static RawMatchResultProcessingStrategy defaultRawMatchResultProcessingStrategy() {
        return RawMatchResultProcessingStrategy.builder()
                .validationPattern(defaultRawMatchResultParsingPattern)
                .conversionFunction(defaultConversionFunction)
                .onInvalidData(FAIL_FAST)
                .build();
    }

    private static Function<String, ScorePair> defaultConversionFunction() {
        return str -> {
            String[] scoresArray = str.split(":");
            int leftScore = parseInt(scoresArray[0]);
            int rightScore = parseInt(scoresArray[1]);
            return ScorePair.create(leftScore, rightScore);
        };
    }

}
