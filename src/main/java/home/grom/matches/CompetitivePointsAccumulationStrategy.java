package home.grom.matches;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value @Builder
public class CompetitivePointsAccumulationStrategy {

    @NonNull Integer onWinGain;
    @NonNull Integer onLoseGain;
    @NonNull Integer onTieGain;

    public static class CompetitivePointsAccumulationStrategyBuilder {
        public CompetitivePointsAccumulationStrategyBuilder onWinGain(int winningPoints) {
            if (winningPoints < 0) throw new IllegalArgumentException("Winning points must not be negative");
            this.onWinGain = winningPoints;
            return this;
        }

        public CompetitivePointsAccumulationStrategyBuilder onLoseGain(int losingPoints) {
            if (losingPoints < 0) throw new IllegalArgumentException("Losing points must not be negative");
            this.onLoseGain = losingPoints;
            return this;
        }

        public CompetitivePointsAccumulationStrategyBuilder onTieGain(int tiePoints) {
            if (tiePoints < 0) throw new IllegalArgumentException("Tie points must not be negative");
            this.onTieGain = tiePoints;
            return this;
        }
    }
}
