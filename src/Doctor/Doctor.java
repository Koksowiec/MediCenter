package Doctor;

import Enums.Specialization;
import Patient.Patient;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends Patient {
    public String doctorId;
    public List<Specialization> specializations = new ArrayList<>();

    public List<DoctorSchedule> schedules = new ArrayList<>();
    public List<DoctorAppointment> appointments = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String id, String dateOfBirth, String phoneNumber, String mailAddress, String doctorId, List<Specialization> specializations) {
        super(firstName, lastName, id, dateOfBirth, phoneNumber, mailAddress);

        this.doctorId = doctorId;
        this.specializations = specializations;
    }

    // TODO: DODAJ GETTERY I SETTERY
}
