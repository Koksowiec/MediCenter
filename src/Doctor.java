import java.util.List;
import java.time.LocalDateTime;

public class Doctor {
    public String id;
    public String firstName;
    public String lastName;
    public int age;
    public List<Specialization> specializations;
    public int phoneNumber;
    public String mailAddress;
    public LocalDateTime hireDate;

    public Doctor() {
    }

    public Doctor(String id, String firstName, String lastName, int age, List<Specialization> specializations, int phoneNumber, String mailAddress, LocalDateTime hireDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.specializations = specializations;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
        this.hireDate = hireDate;
    }
}
