
package utils;

import org.apache.commons.lang3.StringUtils;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateParser {
    private final Clock clock;

    private static final Pattern[] DATE_AS_TEXT_PATTERNS = {
            Pattern.compile("(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[a-z]*\\s+([0-9]{1,2})(?:st|nd|rd|th)?\\s*,?\\s*([0-9]{4})",
    Pattern.CASE_INSENSITIVE),
            Pattern.compile("([0-9]{1,2})\\s*(?:st|nd|rd|th)?\\s*(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)[a-z]*[.,]?\\s*,?\\s*([0-9]{4})",
    Pattern.CASE_INSENSITIVE)
};

    private static final Map<String, Integer> MONTH_MAP = new HashMap<>();
    static {
        String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN",
                "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        for (int i = 0; i < months.length; i++) {
            MONTH_MAP.put(months[i], i + 1);
        }
    }

    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH)
    };

    public DateParser(final Clock clock) {
        this.clock = clock;
    }

    public LocalDate parseFreeFormat(String dateStr) {
        if (StringUtils.isBlank(dateStr) || dateStr.equalsIgnoreCase("NULL")) {
            return LocalDate.now(clock);
        }

        dateStr = dateStr.trim();

        for (Pattern pattern : DATE_AS_TEXT_PATTERNS) {
            try {
                Matcher matcher = pattern.matcher(dateStr);
                if (matcher.matches()) {
                    if (pattern == DATE_AS_TEXT_PATTERNS[0]) {
                        String month = matcher.group(1);
                        int day = Integer.parseInt(matcher.group(2));
                        int year = Integer.parseInt(matcher.group(3));
                        return LocalDate.of(year, MONTH_MAP.get(month.toUpperCase()), day);
                    } else {
                        int day = Integer.parseInt(matcher.group(1));
                        String month = matcher.group(2);
                        int year = Integer.parseInt(matcher.group(3));
                        return LocalDate.of(year, MONTH_MAP.get(month.toUpperCase()), day);
                    }
                }
            } catch (Exception ignored) {
            }
        }

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException ignored) {}
        }

        throw new IllegalArgumentException("Unrecognized date format: " + dateStr);
    }
}