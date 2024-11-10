import java.time.LocalDateTime;

public class Patient {
    public String firstName;
    public String lastName;
    public String id;
    public String dateOfBirth;
    public int age;
    public int phoneNumber;
    public String mailAddress;

    //konstruktor domyślny
    public Patient() {

    }

    //konstruktor zmyślny xd
    public Patient(String firstName, String lastName, String id, String dateOfBirth, int age, int phoneNumber, String mailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
    }

}
