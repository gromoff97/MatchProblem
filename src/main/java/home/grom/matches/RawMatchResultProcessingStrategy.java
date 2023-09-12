package home.grom.matches;

import static home.grom.matches.InvalidDataProcessingAction.FAIL_FAST;
import static lombok.AccessLevel.PACKAGE;

import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

/**
 * Предоставляет возможность гибко обрабатывать сырые данные результатов матчей.
 * <p>
 * Важно учесть, что эта настройка - advanced-уровня, поскольку эта настройка требует
 * консистентности некоторых полей.
 * <p>
 * Скажем, если поле {@link #validationPattern}
 * "пропускает" результат матча, а {@link #converter} обрабатывает данные
 * не в соответствии с описанным выше шаблоном, то есть большой риск возникновения
 * исключения {@link MatchParsingConversionException}
 */
@Value
@Getter(PACKAGE)
@Builder
class RawMatchResultProcessingStrategy {

    /** {@link Pattern}, по которому должен проверяться каждый результат матча */
    @NonNull
    Pattern validationPattern;

    /** Описывает, каким образом из строки результата матча получить целочисленное представление очков */
    @NonNull
    ScoreConverter<String> converter;

    /** Описывает характер действий при появлении invalid-данных */
    @NonNull
    @Builder.Default
    InvalidDataProcessingAction onInvalidData = FAIL_FAST;

    public static class RawMatchResultProcessingStrategyBuilder {
        public RawMatchResultProcessingStrategyBuilder validationRegex(@NonNull String regex) {
            this.validationPattern = Pattern.compile(regex);
            return this;
        }
    }

}
