package Doctor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class DoctorAppointment {
    public String doctorId;
    public String patientId;
    public LocalDateTime apoointmentDateTime;

    public DoctorAppointment(){
    }

    public DoctorAppointment(String doctorId, String patientId, LocalDateTime apoointmentDateTime) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.apoointmentDateTime = apoointmentDateTime;
    }

    // TODO: DODAJ GETTERY I SETTERY
}
