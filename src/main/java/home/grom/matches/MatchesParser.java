package home.grom.matches;

import static home.grom.matches.RawMatchResultProcessingStrategies.charSoftSplitFailFastProcessingStrategy;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value @Builder
public class MatchesParser {

    @NonNull
    Team teamOnLeft;

    @NonNull
    Team teamOnRight;

    @NonNull
    @Builder.Default
    RawMatchResultProcessingStrategy matchProcessingStrategy = charSoftSplitFailFastProcessingStrategy(':');

    public static class MatchesParserBuilder {
        public MatchesParserBuilder teamOnLeft(@NonNull Team teamOnLeft) {
            if (teamOnLeft.equals(teamOnRight)) throw new IllegalArgumentException("Teams must not be same");
            this.teamOnLeft = teamOnLeft;
            return this;
        }

        public MatchesParserBuilder teamOnRight(@NonNull Team teamOnRight) {
            if (teamOnRight.equals(teamOnLeft)) throw new IllegalArgumentException("Teams must not be same");
            this.teamOnRight = teamOnRight;
            return this;
        }
    }
}
