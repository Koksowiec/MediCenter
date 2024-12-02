package Patient;

import Extensions.LocalDateTimeExtensions;
import Main.MediCenterManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PatientManager {
    private final Scanner scanner = new Scanner(System.in);
    private final Extensions.LocalDateTimeExtensions LocalDateTimeExtensions = new LocalDateTimeExtensions();

    public PatientManager(){
        Patient patient = new Patient(
                "Jan",
                "Kowalski",
                "01234567890",
                "2000-01-01",
                "+48123123123",
                "kowalskij@test.com");

        MediCenterManager.addToPatientList(patient);
    }

    ///<summary>
    /// Add patient. Validate birthdate, phone number and email.
    /// </summary>
    public void AddPatient(){
        System.out.println("DODAJ PACJENTA");

        System.out.printf("Imię: ");
        String firstName = scanner.nextLine();

        System.out.printf("Nazwisko: ");
        String lastName = scanner.nextLine();

        System.out.printf("PESEL lub numer dowodu: ");
        String id = scanner.nextLine();

        System.out.printf("Data urodzin (yyyy-MM-dd): ");
        String dateOfBirth = scanner.nextLine();
        while(!LocalDateTimeExtensions.isLocalDate(dateOfBirth)){
            System.out.printf("Podano niepoprawną datę urodzin, podaj jeszcze raz (yyyy-MM-dd): ");
            dateOfBirth = scanner.nextLine();
        }

        System.out.printf("Numer telefonu (9 cyfr): ");
        String phoneNumber = scanner.nextLine();
        while(!ValidatePhoneNumber(phoneNumber)){
            System.out.printf("Podano niepoprawny numer telefonu, podaj jeszcze raz (9 cyfr): ");
            phoneNumber = scanner.nextLine();
        }

        System.out.printf("E-mail: ");
        String mailAddress = scanner.nextLine();
        while(!ValidateEmail(mailAddress)){
            System.out.printf("Podano niepoprawny adres e-mail, podaj jeszcze raz: ");
            mailAddress = scanner.nextLine();
        }

        Patient patient = new Patient(firstName, lastName, id, dateOfBirth, phoneNumber, mailAddress);

        MediCenterManager.addToPatientList(patient);

        System.out.println("Pacjent utworzony poprawnie.");
    }

    ///<summary>
    /// Display patient by id if it exists.
    /// </summary>
    public void DisplayPatientById(){
        System.out.printf("Wprowadź pesel: ");
        String id = scanner.nextLine();

        for (Patient patient : MediCenterManager.getPatientList()) {
            if(patient.getId().equals(id))
            {
                System.out.printf("Imie: %s, Nazwisko: %s, Id: %s, Wiek: %s, Numer telefonu: %s, E-mail: %s\n",
                        patient.getFirstName(),
                        patient.getLastName(),
                        patient.getId(),
                        patient.getAge(),
                        patient.getPhoneNumber(),
                        patient.getMailAddress());

                return;
            }
        }
        System.out.println("Pacjent o podanym id nie istnieje...");
    }

    ///<summary>
    /// Display patients with a surname if they exist.
    /// </summary>
    public void DisplayPatientsBySurname(){
        System.out.printf("Wprowadź nazwisko: ");
        String lastName = scanner.nextLine();

        boolean isFoundAny = false;
        for (Patient patient : MediCenterManager.getPatientList()) {
            if(patient.getLastName().equals(lastName))
            {
                System.out.printf("Imie: %s, Nazwisko: %s, Id: %s, Wiek: %s, Numer telefonu: %s, E-mail: %s\n",
                        patient.getFirstName(),
                        patient.getLastName(),
                        patient.getId(),
                        patient.getAge(),
                        patient.getPhoneNumber(),
                        patient.getMailAddress());
                isFoundAny = true;
            }
        }

        if(!isFoundAny)
        {
            System.out.println("Nie znaleziono pacjentów o podanym nazwisku...");
        }
    }

    private boolean ValidateEmail(String email){
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean ValidatePhoneNumber(String phoneNumber){
        String phoneRegex = "^\\d{9}$";
        return Pattern.matches(phoneRegex, phoneNumber);
    }
}
