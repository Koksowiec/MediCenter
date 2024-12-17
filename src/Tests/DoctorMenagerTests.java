package Tests;

import Enums.Specialization;
import Extensions.LocalDateTimeExtensions;
import Main.MediCenterManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class DoctorManagerTest {

    private DoctorManager doctorManager;

    @Mock
    private LocalDateTimeExtensions localDateTimeExtensions;

    @Mock
    private DoctorManagerService doctorManagerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        doctorManager = new DoctorManager();
        doctorManagerService = mock(DoctorManagerService.class);
        localDateTimeExtensions = mock(LocalDateTimeExtensions.class);

        //creating new list
        MediCenterManager.setDoctorList(new ArrayList<>());
    }

    @Test
    void testAddDoctor_ValidInput_ShouldAddDoctor() {
        // setting user inputs
        String[] inputs = {
                "Jan", "Kowalski", "1234567890", "1985-12-12", "123456789", "jan.kowalski@example.com", "CHIRURG"
        };
        simulateUserInput(inputs);

        when(localDateTimeExtensions.isLocalDate("1985-12-12")).thenReturn(true);
        when(doctorManagerService.ValidatePhoneNumber("123456789")).thenReturn(true);
        when(doctorManagerService.ValidateEmail("jan.kowalski@gmail.com")).thenReturn(true);
        when(doctorManagerService.ValidateDoctorMultipleSpecialization("CHIRURG"))
                .thenReturn(Set.of(Specialization.CHIRURG));

        doctorManager.AddDoctor();

        assertEquals(1, MediCenterManager.getDoctorList().size());
        Doctor addedDoctor = MediCenterManager.getDoctorList().get(0);
        assertEquals("Jan", addedDoctor.getFirstName());
        assertEquals("Kowalski", addedDoctor.getLastName());
        assertEquals("1234567890", addedDoctor.getId());
        assertEquals("123456789", addedDoctor.getPhoneNumber());
        assertEquals("jan.kowalski@example.com", addedDoctor.getMailAddress());
        assertTrue(addedDoctor.getSpecializations().contains(Specialization.CHIRURG));
    }

    @Test
    void testAddScheduleByDoctorId_ValidInput_ShouldAddSchedule() {
        Doctor doctor = new Doctor("Jan", "Kowalski", "1234567890", "1985-12-12", "123456789", "jan.kowalski@example.com", "1", Set.of(Specialization.CHIRURG));
        MediCenterManager.addToDoctorList(doctor);

        String[] inputs = {"1", "2024-12-20", "08:00-12:00"};
        simulateUserInput(inputs);

        when(localDateTimeExtensions.isLocalDate("2024-12-20")).thenReturn(true);
        when(localDateTimeExtensions.isLocalTime("08:05")).thenReturn(true);
        when(localDateTimeExtensions.isLocalTime("11:55")).thenReturn(true);

        doctorManager.AddScheduleByDoctorId();

        assertEquals(1, doctor.getSchedules().size());
        DoctorSchedule schedule = doctor.getSchedules().get(0);
        assertEquals(LocalDate.of(2024, 12, 20), schedule.getDate());
        assertEquals(LocalTime.of(8, 0), schedule.getFrom());
        assertEquals(LocalTime.of(12, 0), schedule.getTo());
    }

    @Test
    void testUpdateDoctorSpecialization_ValidInput_ShouldUpdateSpecialization() {
        Doctor doctor = new Doctor("Jan", "Kowalski", "1234567890", "1985-12-12", "123456789", "jan.kowalski@example.com", "1", Set.of(Specialization.CHIRURG));
        MediCenterManager.addToDoctorList(doctor);

        String[] inputs = {"1", "KARDIOLOG"};
        simulateUserInput(inputs);

        when(doctorManagerService.ValidateDoctorSpecialization("KARDIOLOG"))
                .thenReturn(Specialization.KARDIOLOG);

        doctorManager.UpdateDoctorSpecialization();

        assertTrue(doctor.getSpecializations().contains(Specialization.KARDIOLOG));
    }

    @Test
    void testDisplayDoctorById_ExistingId_ShouldDisplayDoctor() {
        Doctor doctor = new Doctor("Jan", "Kowalski", "1234567890", "1985-12-12", "123456789", "jan.kowalski@example.com", "1", Set.of(Specialization.CHIRURG));
        MediCenterManager.addToDoctorList(doctor);

        String[] inputs = {"1"};
        simulateUserInput(inputs);

        doctorManager.DisplayDoctorById();

        // just to be sure
        assertEquals("1", doctor.getDoctorId());
        assertEquals("Jan", doctor.getFirstName());
    }

    private void simulateUserInput(String[] inputs) {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn(inputs[0], Arrays.copyOfRange(inputs, 1, inputs.length));
    }
}
