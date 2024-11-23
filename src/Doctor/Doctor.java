package Doctor;

import Enums.Specialization;
import Patient.Patient;

import java.util.List;

public class Doctor extends Patient {
    public String doctorId;
    public List<Specialization> specializations;
    public List<DoctorSchedule> schedules;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String id, String dateOfBirth, String phoneNumber, String mailAddress, String doctorId, List<Specialization> specializations) {
        super(firstName, lastName, id, dateOfBirth, phoneNumber, mailAddress);

        this.doctorId = doctorId;
        this.specializations = specializations;
    }
}
