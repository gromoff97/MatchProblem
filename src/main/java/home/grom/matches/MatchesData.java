package home.grom.matches;

import static home.grom.matches.InvalidDataProcessingAction.SKIP;
import static home.grom.matches.RawMatchResultParsingUtils.toMatchResultForRespectively;
import static home.grom.matches.InvalidDataProcessingAction.FAIL_FAST;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;
import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor(access = PRIVATE)
@EqualsAndHashCode
public final class MatchesData {
    private static final String PARSING_ERROR_MESSAGE_OUTPUT_FORMAT = "All matches in list must satisfy format '%s', but got:\n%s";

    @Getter(PACKAGE) List<MatchResult> matchResulList;

    public static MatchesData parseFrom(@NonNull List<String> stringList, @NonNull MatchesParser parser) {
        Pattern usedPattern = parser.getMatchProcessingStrategy().getValidationPattern();

        List<String> rawMatchesList = stringList.stream()
                .filter(s -> usedPattern.matcher(s).matches())
                .collect(toList());

        InvalidDataProcessingAction onInvalidData = parser.getMatchProcessingStrategy().getOnInvalidData();
        if (onInvalidData == FAIL_FAST && rawMatchesList.size() != stringList.size()) {
            List<String> invalidElements = new ArrayList<>(stringList);
            invalidElements.removeAll(rawMatchesList);
            throw new IllegalArgumentException(String.format(PARSING_ERROR_MESSAGE_OUTPUT_FORMAT, usedPattern, invalidElements));
        }

        if (onInvalidData != SKIP) {
            throw new AssertionError("Only these invalid data actions avaliable: " + List.of(FAIL_FAST, SKIP));
        }

        List<MatchResult> matchResultList = rawMatchesList.stream()
                .map(toMatchResultForRespectively(parser.getTeamOnLeft(), parser.getTeamOnRight(), parser.getMatchProcessingStrategy()))
                .collect(toUnmodifiableList());

        return new MatchesData(matchResultList);
    }
}
