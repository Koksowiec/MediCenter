package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

    class MainMenuTest {
        private MainMenu mainMenu;

        @BeforeEach
        void setUp() {
            mainMenu = new MainMenu();
        }

        @Test
        void testRoleSelector_ValidReceptionistRole() {
            String input = "1";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            Roles role = mainMenu.RoleSelector();

            assertEquals(Roles.Receptionist, role);
        }

        @Test
        void testRoleSelector_InvalidRole() {
            // Arrange
            String input = "99999999";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            // Act
            Roles role = mainMenu.RoleSelector();

            // Assert
            assertEquals(Roles.NoRole, role);
        }

        @Test
        void testValidateUserInput_ValidInput() {
            String input = "3";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            int result = mainMenu.ValidateUserInput(input, 5);

            assertEquals(3, result);
        }

        @Test
        void testValidateUserInput_InvalidInputOutOfRange() {
            String input = "6";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            int result = mainMenu.ValidateUserInput(input, 5);

            assertEquals(1, result);
        }
    }
}
