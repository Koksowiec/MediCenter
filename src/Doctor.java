import java.time.LocalDateTime;

public class Doctor {
    public String id;
    public String firstName;
    public String lastName;
    public int age;
    public String specialization;
    public int phoneNumber;
    public String mailAddress;
    public LocalDateTime hireDate;

    public Doctor() {

    }

    public Doctor(String id, String firstName, String lastName, int age, String specialization, int phoneNumber, String mailAddress, LocalDateTime hireDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
        this.hireDate = hireDate;
    }
}