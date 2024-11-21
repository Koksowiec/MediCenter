import Extensions.LocalDateTimeExtensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientManager {
    public List<Patient> PatientList = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);
    private final Extensions.LocalDateTimeExtensions LocalDateTimeExtensions = new LocalDateTimeExtensions();

    PatientManager(){
        Patient patient = new Patient(
                "Jan",
                "Kowalski",
                "01234567890",
                "2000-01-01",
                "+48123123123",
                "kowalskij@test.com");

        PatientList.add(patient);
    }

    public void AddPatient(){
        System.out.println("DODAJ PACJENTA");

        System.out.printf("Imię: ");
        String firstName = scanner.nextLine();

        System.out.printf("Nazwisko: ");
        String lastName = scanner.nextLine();

        System.out.printf("PESEL: ");
        String id = scanner.nextLine();

        System.out.printf("Data urodzin (yyyy-MM-dd): ");
        String dateOfBirth = scanner.nextLine();

        while(!LocalDateTimeExtensions.isLocalDateTime(dateOfBirth)){
            System.out.printf("Podano niepoprawną datę urodzin, podaj jeszcze raz (yyyy-MM-dd): ");
            dateOfBirth = scanner.nextLine();
        }

        System.out.printf("Numer telefonu: ");
        String phoneNumber = scanner.nextLine();

        System.out.printf("E-mail: ");
        String mailAddress = scanner.nextLine();

        Patient patient = new Patient(firstName, lastName, id, dateOfBirth, phoneNumber, mailAddress);

        PatientList.add(patient);

        System.out.println("Pacjent utworzony poprawnie.");
    }

    public void DisplayPatientById(){
        System.out.println("Wprowadź pesel: ");
        String id = scanner.nextLine();

        for (Patient patient : PatientList) {
            if(patient.id.equals(id))
            {
                System.out.println(patient.firstName + ", " + patient.lastName);
                return;
            }
        }
    }

    public void DisplayPatientsBySurname(){
        System.out.println("Wprowadź nazwisko: ");
        String lastName = scanner.nextLine();

        for (Patient patient : PatientList) {
            if(patient.lastName.equals(lastName))
            {
                System.out.println(patient.firstName + ", " + patient.lastName);
            }
        }
    }
}
