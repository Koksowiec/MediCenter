package Tests.Doctor;

import Doctor.Doctor;
import Doctor.DoctorManager;
import Doctor.DoctorSchedule;
import Enums.Specialization;
import Main.MediCenterManager;
import Patient.PatientManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorManagerTest {

    private DoctorManager doctorManager;

    @BeforeEach
    void setUp() {
        doctorManager = new DoctorManager();
    }

    @Test
    void AddDoctor_DoctorIsAdded(){
        // Arrange
        // Ten kod "mockuje" userInput aby można było przetestować metodę
        String simulatedInputName = "Jan\nKowalski\n01234567890\n2000-01-01\n123123123\nkowalskij@test.com\nCHIRURG\n";
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act
        doctorManager.AddDoctor();

        // Assert
        // Assert
        boolean result = (long) MediCenterManager.getDoctorList().size() > 1; //Sprawdź czy lista ma więcej niż defaultowego pacjenta
        assertTrue(result);

        System.setIn(System.in);
    }

    @Test
    void AddDoctor_DoctorIsNotAdded(){
        // Arrange
        // Ten kod "mockuje" userInput aby można było przetestować metodę
        String simulatedInputName = "Jan\nKowalski\n01234567890\n2000-13-01\n123123123\nkowalskij@test.com\nCHIRURG\n";
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act & Assert
        assertThrows(Exception.class, ()->doctorManager.AddDoctor());

        System.setIn(System.in);
    }

    @Test
    void AddScheduleByDoctorId_ScheduleIsAdded(){
        // Arrange
        LocalDate date = LocalDate.parse("2024-12-18");

        String simulatedInputName = "1\n2024-12-18\n09:00-18:00\n";
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act
        doctorManager.AddScheduleByDoctorId();

        // Assert
        var doctor = MediCenterManager.getDoctorList().stream().findFirst().orElse(null);

        DoctorSchedule todaysSchedule = doctor.getSchedules().stream()
                .filter(schedule -> schedule.getDate().isEqual(date))
                .findFirst()
                .orElse(null);

        assertTrue(todaysSchedule != null);
        System.setIn(System.in);
    }

    @Test
    void UpdateDoctorSpecialization_SpecializationIsUpdated(){
        // Arrange
        String simulatedInputName = "1\nKARDIOLOG\n";
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act
        doctorManager.UpdateDoctorSpecialization();

        // Assert
        var doctor = MediCenterManager.getDoctorList().stream().findFirst().orElse(null);
        var specializations = doctor.getSpecializations();

        assertTrue(specializations.contains(Specialization.KARDIOLOG));
        System.setIn(System.in);
    }

    @Test
    void DisplayDoctorById_DoctorIsDisplayed(){
        // Arrange
        String simulatedInputName = "1\n";
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act & Assert
        assertDoesNotThrow(()->doctorManager.DisplayDoctorById());
        System.setIn(System.in);
    }

    @Test
    void DisplayDoctorsBySpecialization_DoctorsAreDisplayed(){
        // Arrange
        String simulatedInputName = "CHIRURG\n";
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act & Assert
        assertDoesNotThrow(()->doctorManager.DisplayDoctorsBySpecialization());
        System.setIn(System.in);
    }

    @Test
    void DisplayDoctorSchedulesByDoctorId_ScheduleIsDisplayed(){
        // Arrange
        String simulatedInputName = "1\n";
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act & Assert
        assertDoesNotThrow(()->doctorManager.DisplayDoctorSchedulesByDoctorId());
        System.setIn(System.in);
    }

    @Test
    void ScheduleAppointmentByDoctorId_AppointmentIsScheduled(){
        // Arrange
        String date = LocalDate.now().toString();
        String simulatedInputName = "01234567890\n1\n" + date + "\n09:30\n";
        ByteArrayInputStream name = new ByteArrayInputStream(simulatedInputName.getBytes());
        System.setIn(name);

        // Act
        var patientManager = new PatientManager(); // Create patient
        doctorManager.ScheduleAppointmentByDoctorId();

        // Assert
        var doctor = MediCenterManager.getDoctorList().stream().findFirst().orElse(null);
        assertTrue(doctor.getAppointments().size() > 2);
    }
}
