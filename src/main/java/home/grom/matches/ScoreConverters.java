package home.grom.matches;

import static java.lang.Integer.parseInt;

final class ScoreConverters {

    private ScoreConverters() {
        throw new AssertionError("Current class must not be initialized because it's Utility-class");
    }

    static ScoreConverter<String> splittingWithRegex(String regex) {
        return str -> {
            String[] scoresArray = str.split(regex);
            int leftScore = parseInt(scoresArray[0].trim());
            int rightScore = parseInt(scoresArray[1].trim());
            return ScorePair.create(leftScore, rightScore);
        } ;
    }

}
