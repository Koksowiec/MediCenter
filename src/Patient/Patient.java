package Patient;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Patient {
    public String firstName;
    public String lastName;
    public String id;
    public String dateOfBirth;
    public String age;
    public String phoneNumber;
    public String mailAddress;

    //konstruktor domyślny
    public Patient() {

    }

    //konstruktor zmyślny xd
    public Patient(String firstName, String lastName, String id, String dateOfBirth, String phoneNumber, String mailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.age = Integer.toString(Period.between(LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.now()).getYears());
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
    }

}
