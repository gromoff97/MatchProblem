package home.grom;

import static home.grom.matches.CompetitivePointsAccumulationStrategies.DEFAULT_ON_LOSE_POINTS;
import static home.grom.matches.CompetitivePointsAccumulationStrategies.DEFAULT_ON_TIE_POINTS;
import static home.grom.matches.CompetitivePointsAccumulationStrategies.DEFAULT_ON_WIN_POINTS;
import static home.grom.matches.CompetitivePointsTable.competitivePointsTableBasedOn;
import static home.grom.matches.InvalidDataProcessingAction.SKIP;
import static home.grom.matches.RawMatchResultProcessingStrategies.defaultConversionFunction;
import static home.grom.matches.RawMatchResultProcessingStrategies.defaultRawMatchResultParsingPattern;

import home.grom.matches.CompetitivePointsAccumulationStrategies;
import home.grom.matches.CompetitivePointsAccumulationStrategy;
import home.grom.matches.CompetitivePointsTable;
import home.grom.matches.MatchesData;
import home.grom.matches.MatchesParser;
import home.grom.matches.RawMatchResultProcessingStrategy;
import home.grom.matches.SimpleTeam;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Команда хозяев и гостей
        SimpleTeam homeTeam = new SimpleTeam("Host Team");
        SimpleTeam roadTeam = new SimpleTeam("Road Team");

        // Данные результатов матчей
        List<String> rawMatchResults = List.of("3:1", "2:2", "0:1", "4:2", "3:a", "3- 12");

        // Настройка парсинга с указанием, какая команда в этом списке - слева, какая - справа
        // Дополнительно можно указать, как обрабатывать данные, и что делать, когда часть данных невалидна
        MatchesParser matchesParser = MatchesParser.builder()
                .teamOnLeft(homeTeam).teamOnRight(roadTeam)
                .matchProcessingStrategy(RawMatchResultProcessingStrategy.builder()
                        .validationPattern(defaultRawMatchResultParsingPattern)
                        .conversionFunction(defaultConversionFunction)
                        .onInvalidData(SKIP)
                        .build())
                .build();

        // "Пропарсить" данные
        MatchesData matchesData = MatchesData.parseFrom(rawMatchResults, matchesParser);

        // Стратегия прибавления competitive-очков командам
        CompetitivePointsAccumulationStrategy pointsAccumulationStrategy = CompetitivePointsAccumulationStrategy.builder()
                .onWinGain(DEFAULT_ON_WIN_POINTS).onTieGain(DEFAULT_ON_TIE_POINTS).onLoseGain(DEFAULT_ON_LOSE_POINTS)
                .build();

        // Сформировать таблицу comptetitive-очков участвовавших команд и отобразить их
        CompetitivePointsTable resultTable = competitivePointsTableBasedOn(matchesData, pointsAccumulationStrategy);
        resultTable.showTable();

        // Применить таблицу с competitive - очками к участвовавшим командам
        resultTable.applyPointsToParticipatingTeams();
    }
}
