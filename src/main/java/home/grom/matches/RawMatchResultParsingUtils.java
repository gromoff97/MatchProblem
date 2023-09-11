package home.grom.matches;

import java.util.function.Function;
import java.util.regex.Pattern;
import lombok.NonNull;

public final class RawMatchResultParsingUtils {

    private RawMatchResultParsingUtils() {
        throw new AssertionError("Current class must not be initialized because it's Utility-class");
    }

    static Function<String, MatchResult> toMatchResultForRespectively(@NonNull Team firstTeam, @NonNull Team secondTeam, @NonNull RawMatchResultProcessingStrategy strategy) {
        return s -> {
            validateRawMatchResult(s, strategy.getValidationPattern());
            ScorePair scorePair = strategy.getConversionFunction().apply(s);
            return new MatchResult(firstTeam, scorePair.getLeftScore(), secondTeam, scorePair.getRightScore());
        };
    }

    private static void validateRawMatchResult(String str, Pattern pattern) {
        if (!pattern.matcher(str).matches()) {
            String errorMessage = String.format("Can't parse string '%s' because it must satisfy following pattern '%s'", str, pattern);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
