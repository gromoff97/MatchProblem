package home.grom.matches;

import static home.grom.matches.InvalidDataProcessingAction.FAIL_FAST;

import java.util.function.Function;
import java.util.regex.Pattern;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value @Builder
public class RawMatchResultProcessingStrategy {

    @NonNull
    Pattern validationPattern;

    @NonNull
    Function<String, ScorePair> conversionFunction;

    @NonNull
    @Builder.Default
    InvalidDataProcessingAction onInvalidData = FAIL_FAST;

    public static class RawMatchResultProcessingStrategyBuilder {
        public RawMatchResultProcessingStrategyBuilder validationRegex(@NonNull String regex) {
            this.validationPattern = Pattern.compile(regex);
            return this;
        }
    }

}
