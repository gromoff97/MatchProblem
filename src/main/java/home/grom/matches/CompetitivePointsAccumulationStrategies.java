package home.grom.matches;

public final class CompetitivePointsAccumulationStrategies {

    public static final int DEFAULT_WIN_POINTS = 3;
    public static final int DEFAULT_LOSE_POINTS = 0;
    public static final int DEFAULT_TIE_POINTS = 1;

    private CompetitivePointsAccumulationStrategies() {
        throw new AssertionError("Current class must not be initialized because it's Utility-class");
    }

    public static CompetitivePointsAccumulationStrategy defaultAccumulationStrategy() {
        return CompetitivePointsAccumulationStrategy.builder()
                .onWinGain(DEFAULT_WIN_POINTS).onLoseGain(DEFAULT_LOSE_POINTS).onTieGain(DEFAULT_TIE_POINTS)
                .build();
    }
}
