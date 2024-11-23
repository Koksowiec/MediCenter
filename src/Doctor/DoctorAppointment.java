package Doctor;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DoctorAppointment {
    public String doctorId;
    public String patientId;
    public LocalTime apointmentTime;
    public DayOfWeek dayOfWeek;

    public DoctorAppointment(){
    }

    public DoctorAppointment(String doctorId, String patientId, LocalTime apointmentTime, DayOfWeek dayOfWeek) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.apointmentTime = apointmentTime;
        this.dayOfWeek = dayOfWeek;
    }

    // TODO: DODAJ GETTERY I SETTERY
}
