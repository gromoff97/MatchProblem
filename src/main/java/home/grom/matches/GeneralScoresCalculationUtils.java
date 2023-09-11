package home.grom.matches;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import lombok.NonNull;

final class GeneralScoresCalculationUtils {

    private GeneralScoresCalculationUtils() {
        throw new AssertionError("Current class must not be initialized because it's Utility-class");
    }

    static Map<Team, Integer> generateGeneralScoresTable(@NonNull List<MatchResult> matchResults, @NonNull CompetitivePointsAccumulationStrategy pointsAccumulationStrategy) {
        // Объявить таблицу с командами
        Map<Team, Integer> resultMap = matchResults.stream()
                .flatMap(mr -> Stream.of(mr.getFirstTeam(), mr.getSecondTeam()))
                .distinct().collect(toMap(identity(), team -> 0));

        // Инициализировать команды общими очками
        var accumulator = scoresAdditionAccumulator(pointsAccumulationStrategy);
        matchResults.forEach(matchResult -> accumulator.apply(resultMap, matchResult));

        return resultMap;
    }

    private static BiFunction<Map<Team, Integer>, MatchResult, Map<Team, Integer>> scoresAdditionAccumulator(@NonNull CompetitivePointsAccumulationStrategy pointsAccumulationStrategy) {
        return (map, mr) -> {
            if (mr.getFirstTeamScore() > mr.getSecondTeamScore()) {
                map.put(mr.getFirstTeam(), map.get(mr.getFirstTeam()) + pointsAccumulationStrategy.getOnWinGain());
                map.put(mr.getSecondTeam(), map.get(mr.getSecondTeam()) + pointsAccumulationStrategy.getOnLoseGain());
            }
            if (mr.getFirstTeamScore() < mr.getSecondTeamScore()) {
                map.put(mr.getFirstTeam(), map.get(mr.getFirstTeam()) + pointsAccumulationStrategy.getOnLoseGain());
                map.put(mr.getSecondTeam(), map.get(mr.getSecondTeam()) + pointsAccumulationStrategy.getOnWinGain());
            }
            if (mr.getFirstTeamScore() == mr.getSecondTeamScore()) {
                map.put(mr.getFirstTeam(), map.get(mr.getFirstTeam()) + pointsAccumulationStrategy.getOnTieGain());
                map.put(mr.getSecondTeam(), map.get(mr.getSecondTeam()) + pointsAccumulationStrategy.getOnTieGain());
            }
            return map;
        };
    }
}
