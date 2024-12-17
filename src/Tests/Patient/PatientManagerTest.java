package Tests.Patient;

import Main.MediCenterManager;
import Patient.PatientManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class PatientManagerTest {
    private PatientManager patientManager;

    @BeforeEach
    void setUp() {
        patientManager = new PatientManager();
    }

    @Test
    void AddPatient_PatientIsAdded(){
        // Arrange
        // Ten kod "mockuje" userInput aby można było przetestować metodę
        String simulatedInputName = "Jan\nKowalski\n01234567890\n2000-01-01\n123123123\nkowalskij@test.com\n";
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act
        patientManager.AddPatient();

        // Assert
        boolean result = (long)MediCenterManager.getPatientList().size() > 1; //Sprawdź czy lista ma więcej niż defaultowego pacjenta
        assertTrue(result);

        System.setIn(System.in);
    }

    @Test
    void AddPatient_PatientIsNotAdded(){
        // Arrange
        String simulatedInputName = "Jan\nKowalski\n01234567890\n2000-13-01\n123123123\nkowalskij@test.com\n"; // Zła data
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act & Assert
        assertThrows(Exception.class, ()->patientManager.AddPatient());

        System.setIn(System.in);
    }

    @Test
    void DisplayPatientById_PatientIsDisplayed(){
        // Arrange
        String simulatedInputName = "1\n";
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act & Assert
        assertDoesNotThrow(()->patientManager.DisplayPatientById());

        System.setIn(System.in);
    }

    @Test
    void DisplayPatientsBySurname_PatientIsDisplayed(){
        // Arrange
        String simulatedInputName = "Kowalski\n";
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act & Assert
        assertDoesNotThrow(()->patientManager.DisplayPatientsBySurname());

        System.setIn(System.in);
    }
}
