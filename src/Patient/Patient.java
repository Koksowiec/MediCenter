package Patient;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Patient {
    private String firstName;
    private String lastName;
    private String id;
    private String dateOfBirth;
    private String age;
    private String phoneNumber;
    private String mailAddress;

    //konstruktor domyślny
    public Patient() {

    }

    //konstruktor zmyślny xd
    public Patient(String firstName, String lastName, String id, String dateOfBirth, String phoneNumber, String mailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.dateOfBirth = dateOfBirth; // TODO: TO MOŻE BYĆ OBLICZONE Z PESELU
        this.age = Integer.toString(Period.between(LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd")), LocalDate.now()).getYears());
        this.phoneNumber = phoneNumber;
        this.mailAddress = mailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
