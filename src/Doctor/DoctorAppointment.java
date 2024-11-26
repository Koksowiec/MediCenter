package Doctor;

import java.time.LocalDateTime;

public class DoctorAppointment {
    public String doctorId;
    public String patientId;
    public LocalDateTime appointmentDateTime;

    public DoctorAppointment(){
    }

    public DoctorAppointment(String doctorId, String patientId, LocalDateTime appointmentDateTime) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDateTime = appointmentDateTime;
    }

    // TODO: DODAJ GETTERY I SETTERY
}
