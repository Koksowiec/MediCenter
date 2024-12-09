package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalDateTimeExtensionsTest {
    private LocalDateTimeExtensions extensions;

    @BeforeEach
    void setUp() {
        extensions = new LocalDateTimeExtensions();
    }

    @Test
    void testIsLocalDate_ValidDate() {
        String input = "2024-12-05";

        boolean result = extensions.isLocalDate(input);

        assertTrue(result);
    }

    @Test
    void testIsLocalDate_InvalidDate() {
        String input = "2024-13-05"; // Niepoprawny miesiąc

        boolean result = extensions.isLocalDate(input);

        assertFalse(result);
    }

    @Test
    void testIsLocalDate_InvalidDate() {
        String input = "9999-12-05"; // Niepoprawny rok

        boolean result = extensions.isLocalDate(input);

        assertFalse(result);
    }

    @Test
    void testIsLocalDate_InvalidDate() {
        String input = "2024-12-35"; // Niepoprawny dzień

        boolean result = extensions.isLocalDate(input);

        assertFalse(result);
    }

    @Test
    void testIsLocalTime_ValidTime() {
        String input = "23:59";

        boolean result = extensions.isLocalTime(input);

        assertTrue(result);
    }

    @Test
    void testIsLocalTime_InvalidTime() {
        String input = "25:00"; // Niepoprawna godzina

        boolean result = extensions.isLocalTime(input);

        assertFalse(result);
    }

    @Test
    void testIsLocalTime_InvalidTime() {
        String input = "23:61"; // Niepoprawna minuta

        boolean result = extensions.isLocalTime(input);

        assertFalse(result);
    }

    @Test
    void testIsDayOfWeek_ValidDay() {
        String input = "MONDAY";

        boolean result = extensions.isDayOfWeek(input);

        assertTrue(result);
    }

    @Test
    void testIsDayOfWeek_InvalidDay() {
        String input = "FUNDAY"; // Nieistniejący dzień

        boolean result = extensions.isDayOfWeek(input);

        assertFalse(result);
    }
}