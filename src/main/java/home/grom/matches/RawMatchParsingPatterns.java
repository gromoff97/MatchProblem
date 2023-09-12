package home.grom.matches;

import java.util.regex.Pattern;

final class RawMatchParsingPatterns {

    private static final String REGEX_SPLIT_PATTERN_FORMAT = "^\\s*(0|[1-9]\\d*)\\s*%s\\s*(0|[1-9]\\d*)\\s*$";

    private RawMatchParsingPatterns() {
        throw new AssertionError("Current class must not be initialized because it's Utility-class");
    }

    /**
     * Если аргумент - знак дефиса, то следующие строки пройдут проверку.
     * "4-2"
     * " 2 -3"
     * "32- 4 "
     * <p>
     * Обобщая, происходит разделение по символу, не учитывая пробельные символы
     */
    public static Pattern softSplitValidationPattern(char c) {
        String resultRegex = String.format(REGEX_SPLIT_PATTERN_FORMAT, Pattern.quote(String.valueOf(c)));
        return Pattern.compile(resultRegex);
    }

}
