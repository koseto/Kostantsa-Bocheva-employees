package utils;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

public class DateParserTest {

    @Test
    public void testParseFreeFormatStandardFormat() {
        LocalDate expected = LocalDate.of(2023, 4, 1);
        assertEquals(expected, DateParser.parseFreeFormat("2023-04-01"));
    }

    @Test
    public void testParseFreeFormatAlternativeFormats() {
        assertEquals(LocalDate.of(2023, 4, 1), DateParser.parseFreeFormat("01-04-2023"));
        assertEquals(LocalDate.of(2023, 4, 1), DateParser.parseFreeFormat("04/01/2023"));
        assertEquals(LocalDate.of(2023, 4, 1), DateParser.parseFreeFormat("Apr 01, 2023"));
        assertEquals(LocalDate.of(2023, 4, 1), DateParser.parseFreeFormat("1st Apr 2023"));
    }

    @Test
    public void testParseFreeFormatNullReturnsToday() {
        Clock fixedclock = Clock.fixed(Instant.parse("2025-04-12T00:00:00Z"), ZoneId.of("UTC"));
        LocalDate today = LocalDate.now(fixedclock);
        assertEquals(today, DateParser.parseFreeFormat("NULL"));
        assertEquals(today, DateParser.parseFreeFormat(""));
        assertEquals(today, DateParser.parseFreeFormat(" "));
        assertEquals(today, DateParser.parseFreeFormat(null));
    }

    @Test
    public void testInvalidDateThrows() {
        assertThrows(IllegalArgumentException.class, () -> DateParser.parseFreeFormat("NotADate"));
    }
}
