package home.grom.matches;

import lombok.Value;

@Value(staticConstructor = "create")
public class ScorePair {
    int leftScore;
    int rightScore;

    private ScorePair(int leftScore, int rightScore) {
        if (leftScore < 0) throw new IllegalArgumentException("left score must not be negative");
        if (rightScore < 0) throw new IllegalArgumentException("right score must not be negative");
        this.leftScore = leftScore;
        this.rightScore = rightScore;
    }
}
