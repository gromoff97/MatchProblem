package home.grom.matches;

import static home.grom.matches.CompetitivePointsAccumulationStrategies.defaultAccumulationStrategy;
import static home.grom.matches.GeneralScoresCalculationUtils.generateGeneralScoresTable;

import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public final class CompetitivePointsTable {
    private static final String TEAM_INFO_OUTPUT_FORMAT = "Team '%s' has following score: '%s'";

    @NonNull private final Map<Team, Integer> teamResultScoresMap;
    private boolean resultsAreAlreadyApplied = false;

    public static CompetitivePointsTable competitivePointsTableBasedOn(@NonNull MatchesData matchesData, CompetitivePointsAccumulationStrategy pointsAccumulationStrategy) {
        return new CompetitivePointsTable(generateGeneralScoresTable(matchesData.getMatchResulList(), pointsAccumulationStrategy));
    }

    public static CompetitivePointsTable competitivePointsTableBasedOn(@NonNull MatchesData matchesData) {
        return new CompetitivePointsTable(generateGeneralScoresTable(matchesData.getMatchResulList(), defaultAccumulationStrategy()));
    }

    public void showTable() {
        if (teamResultScoresMap.isEmpty()) {
            System.out.println("Competitive points table is empty!");
        }
        teamResultScoresMap.forEach((team, scores) -> {
            System.out.println(String.format(TEAM_INFO_OUTPUT_FORMAT, team.getTeamName(), scores));;
        });
    }

    public void applyPointsToParticipatingTeams() {
        if (resultsAreAlreadyApplied) return;
        teamResultScoresMap.forEach(Team::addCompetitiveScores);
        resultsAreAlreadyApplied = true;
    }
}
