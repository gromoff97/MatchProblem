package home.grom.matches;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@EqualsAndHashCode
@Getter
public abstract class Team {
    @NonNull
    private final String teamName;
    int competitiveScore = 0;

    void addCompetitiveScores(int competitiveScores) {
        if (competitiveScores < 0) throw new IllegalArgumentException("Competitive score must not be negative");
        this.competitiveScore += competitiveScores;
    }
}
