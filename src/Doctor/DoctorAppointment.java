package Doctor;

import java.time.LocalDateTime;

public class DoctorAppointment {
    private String doctorId;
    private String patientId;
    private LocalDateTime appointmentDateTime;

    public DoctorAppointment(){
    }

    public DoctorAppointment(String doctorId, String patientId, LocalDateTime appointmentDateTime) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDateTime = appointmentDateTime;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
}
