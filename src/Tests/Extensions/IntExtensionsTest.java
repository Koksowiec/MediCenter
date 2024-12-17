package Tests.Extensions;

import Extensions.IntExtensions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntExtensionsTest {

    private IntExtensions extensions;

    @BeforeEach
    void setUp() {
        extensions = new IntExtensions();
    }

    @Test
    void testIsInteger_ValidInt() {
        // Arrange
        String input = "123";

        // Act
        boolean result = extensions.IsInteger(input);

        // Assert
        assertTrue(result);
    }

    @Test
    void testIsInteger_InvalidInt() {
        // Arrange
        String input = "abc";

        // Act
        boolean result = extensions.IsInteger(input);

        // Assert
        assertFalse(result);
    }
}
