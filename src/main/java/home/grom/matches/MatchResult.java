package home.grom.matches;

import static lombok.AccessLevel.PACKAGE;

import lombok.Getter;
import lombok.Value;

@Value
@Getter(PACKAGE)
class MatchResult {
    Team firstTeam;
    int firstTeamScore;
    Team secondTeam;
    int secondTeamScore;
}
