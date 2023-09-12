package home.grom.matches;

public final class MatchParsingConversionException extends RuntimeException {
    private static final String EXCEPTION_MESSAGE_FORMAT = "Could not parse following string: '%s'";

    public MatchParsingConversionException(String parsedString, Throwable throwable) {
        super(String.format(EXCEPTION_MESSAGE_FORMAT, parsedString), throwable);
    }
}
