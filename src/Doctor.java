import Enums.Specialization;

import java.util.List;
import java.time.LocalDateTime;

public class Doctor extends Patient{
    public String doctorId;
    public List<Specialization> specializations;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String id, String dateOfBirth, String phoneNumber, String mailAddress, String doctorId, List<Specialization> specializations) {
        super(firstName, lastName, id, dateOfBirth, phoneNumber, mailAddress);

        this.doctorId = doctorId;
        this.specializations = specializations;
    }
}