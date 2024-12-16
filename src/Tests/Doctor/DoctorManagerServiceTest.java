package Tests.Doctor;

import Doctor.DoctorManager;
import Doctor.DoctorManagerService;
import Enums.Specialization;
import Main.MediCenterManager;
import Patient.PatientManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorManagerServiceTest {
    private DoctorManagerService service;

    @BeforeEach
    void setUp() {
        service = new DoctorManagerService();
    }

    @Test
    void CreateDoctorOnInit_CreateDefaultDoctor(){
        // Arrange & Act
        DoctorManager doctorManager = new DoctorManager();

        // Assert
        assertFalse(MediCenterManager.getDoctorList().isEmpty());
    }

    @Test
    void ValidateDoctorSpecialization_ValidSpecialization(){
        // Arrange
        String userInput = "CHIRURG";

        // Act
        Specialization specialization = service.ValidateDoctorSpecialization(userInput);

        // Assert
        assertEquals(specialization, Specialization.CHIRURG);
    }

    @Test
    void ValidateDoctorMultipleSpecialization_ValidSpecializations(){
        // Arrange
        String userInput = "CHIRURG, KARDIOLOG";

        // Act
        Set<Specialization> specialization = service.ValidateDoctorMultipleSpecialization(userInput);

        // Assert
        assertFalse(specialization.isEmpty());
    }

    @Test
    void GetDoctorById_Returns_Doctor(){
        // Arrange
        String doctorId = "1";

        // Act
        DoctorManager doctorManager = new DoctorManager(); // Create example doctor with CreateDoctorOnInit
        Doctor.Doctor doctor = service.GetDoctorById(doctorId);

        // Assert
        assertNotNull(doctor);
    }

    @Test
    void ValidatePatientId_ValidPatientId(){
        // Arrange
        String id = "01234567890";

        // Act
        PatientManager patientManager = new PatientManager(); // Create example patient in constructor
        boolean result = service.ValidatePatientId(id);

        // Assert
        assertTrue(result);
    }

    @Test
    void ValidateDoctorId_ValidDoctorId(){
        // Arrange
        String id = "1";

        // Act
        DoctorManager doctorManager = new DoctorManager(); // Create example doctor with CreateDoctorOnInit
        boolean result = service.ValidateDoctorId(id);

        // Assert
        assertTrue(result);
    }

    @Test
    void TryDisplaySchedule_ValidSchedule(){
        // Arrange
        String date = LocalDate.now().toString();

        DoctorManager doctorManager = new DoctorManager(); // Create example doctor with CreateDoctorOnInit
        Doctor.Doctor doctor = MediCenterManager.getDoctorList().get(0);

        // Act
        boolean result = service.TryDisplaySchedule(date, doctor);

        // Assert
        assertTrue(result);
    }

    @Test
    void TrySetAppointment_ValidAppointment(){
        // Arrange
        String date = LocalDate.now().toString();
        String time = "10:00";

        DoctorManager doctorManager = new DoctorManager(); // Create example doctor with CreateDoctorOnInit
        Doctor.Doctor doctor = MediCenterManager.getDoctorList().get(0);

        String patientId = "01234567890";

        // Act
        boolean result = service.TrySetAppointment(date, time, doctor, patientId);

        // Assert
        assertTrue(result);
    }

    @Test
    void ValidateEmail_ValidEmail(){
        // Arrange
        String email = "test@test.com";

        // Act
        boolean result = service.ValidateEmail(email);

        // Assert
        assertTrue(result);
    }

    @Test
    void ValidatePhoneNumber_ValidPhoneNumber(){
        // Arrange
        String phoneNumber = "123456789";

        // Act
        boolean result = service.ValidatePhoneNumber(phoneNumber);

        // Assert
        assertTrue(result);
    }

}
