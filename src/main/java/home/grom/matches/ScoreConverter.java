package home.grom.matches;

import java.io.IOException;
import java.text.ParseException;

/**
 * Интерфейс, созданный для более упрощённого понимания конверсии.
 * @param <T> - тип, из которого можно получить пару очков.
 */
@FunctionalInterface
interface ScoreConverter<T> {
    ScorePair convertFrom(T type) throws ParseException, IOException;
}
