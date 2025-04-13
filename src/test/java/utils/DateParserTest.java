package utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

public class DateParserTest {
    private static DateParser dateParser;
    private static final Clock fixedClock = Clock.fixed(Instant.parse("2025-04-12T00:00:00Z"), ZoneId.of("UTC"));

    @BeforeAll
    static void setup() {
        dateParser = new DateParser(fixedClock);
    }

    @Test
    public void testParseFreeFormatStandardFormat() {
        LocalDate expected = LocalDate.of(2023, 4, 1);
        assertEquals(expected, dateParser.parseFreeFormat("2023-04-01"));
    }

    @Test
    public void testParseFreeFormatAlternativeFormats() {
        assertEquals(LocalDate.of(2023, 4, 1), dateParser.parseFreeFormat("01-04-2023"));
        assertEquals(LocalDate.of(2023, 4, 1), dateParser.parseFreeFormat("04/01/2023"));
        assertEquals(LocalDate.of(2023, 4, 1), dateParser.parseFreeFormat("Apr 01, 2023"));
        assertEquals(LocalDate.of(2023, 4, 1), dateParser.parseFreeFormat("1st Apr 2023"));
    }

    @Test
    public void testParseFreeFormatNullReturnsToday() {
        LocalDate today = LocalDate.now(fixedClock);
        assertEquals(today, dateParser.parseFreeFormat("NULL"));
        assertEquals(today, dateParser.parseFreeFormat(""));
        assertEquals(today, dateParser.parseFreeFormat(" "));
        assertEquals(today, dateParser.parseFreeFormat(null));
    }

    @Test
    public void testInvalidDateThrows() {
        assertThrows(IllegalArgumentException.class, () -> dateParser.parseFreeFormat("NotADate"));
    }
}
