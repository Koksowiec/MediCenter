package Doctor;

import Enums.Specialization;
import Patient.Patient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Doctor extends Patient {
    private String doctorId;
    private Set<Specialization> specializations = new HashSet<>();
    private List<DoctorSchedule> schedules = new ArrayList<>();
    private List<DoctorAppointment> appointments = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String id, String dateOfBirth, String phoneNumber, String mailAddress, String doctorId, Set<Specialization> specializations) {
        super(firstName, lastName, id, dateOfBirth, phoneNumber, mailAddress);

        this.doctorId = doctorId;
        this.specializations = specializations;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public boolean setSpecializations(Specialization specializations) {
        return this.specializations.add(specializations);
    }

    public List<DoctorSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<DoctorSchedule> schedules) {
        this.schedules = schedules;
    }

    public List<DoctorAppointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<DoctorAppointment> appointments) {
        this.appointments = appointments;
    }

    public void setAppointments(DoctorAppointment appointments) {
        this.appointments.add(appointments);
    }
}
