package Doctor;

import Enums.Specialization;
import Patient.Patient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Doctor extends Patient {
    public String doctorId;
    public Set<Specialization> specializations = new HashSet<>();

    public List<DoctorSchedule> schedules = new ArrayList<>();
    public List<DoctorAppointment> appointments = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String id, String dateOfBirth, String phoneNumber, String mailAddress, String doctorId, Set<Specialization> specializations) {
        super(firstName, lastName, id, dateOfBirth, phoneNumber, mailAddress);

        this.doctorId = doctorId;
        this.specializations = specializations;
    }

    // TODO: DODAJ GETTERY I SETTERY
}
