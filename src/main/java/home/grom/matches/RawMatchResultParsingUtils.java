package home.grom.matches;

import java.util.function.Function;
import java.util.regex.Pattern;
import lombok.NonNull;

final class RawMatchResultParsingUtils {

    private RawMatchResultParsingUtils() {
        throw new AssertionError("Current class must not be initialized because it's Utility-class");
    }

    static Function<String, MatchResult> toMatchResultForRespectively(@NonNull Team firstTeam, @NonNull Team secondTeam, @NonNull RawMatchResultProcessingStrategy strategy) {
        return s -> {
            try {
                validateRawMatchResult(s, strategy.getValidationPattern());
                ScorePair scorePair = strategy.getConverter().convertFrom(s);
                return new MatchResult(firstTeam, scorePair.getLeftScore(), secondTeam, scorePair.getRightScore());
            } catch (Exception e) {
                throw new MatchParsingConversionException(s, e);
            }
        };
    }

    private static void validateRawMatchResult(String str, Pattern pattern) {
        if (!pattern.matcher(str).matches()) {
            String errorMessage = String.format("Can't parse string '%s' because it must satisfy following pattern '%s'", str, pattern);
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
