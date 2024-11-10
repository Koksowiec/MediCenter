import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatientManager {
    public List<Patient> PatientList = new ArrayList<Patient>();

    public void AddPatient(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("DODAJ PACJENTA");

        System.out.printf("Imię: ");
        String firstName = scanner.nextLine();

        System.out.printf("Nazwisko: ");
        String lastName = scanner.nextLine();

        System.out.printf("PESEL: ");
        String id = scanner.nextLine();

        System.out.printf("Data urodzin: ");
        String dateOfBirth = scanner.nextLine();

        System.out.printf("Wiek: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("Numer telefonu: ");
        int phoneNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("E-mail: ");
        String mailAddress = scanner.nextLine();

        Patient patient = new Patient(firstName, lastName, id, dateOfBirth, age, phoneNumber, mailAddress);

        PatientList.add(patient);

        System.out.println("Pacjent utworzony poprawnie.");
    }

    public void DisplayPatientById(String id){
        System.out.println("Wyświetl pacjenta z PESELEM:  " + id);
        for (Patient patient : PatientList) {
            if(patient.id.equals(id))
            {
                System.out.println(patient.firstName + ", " + patient.lastName);
                return;
            }
        }
    }

    public void DisplayPatientsBySurname(String lastName){
        System.out.println("Wyświetl pacjentow z nazwiskiem:  " + lastName);
        for (Patient patient : PatientList) {
            if(patient.lastName.equals(lastName))
            {
                System.out.println(patient.firstName + ", " + patient.lastName);
            }
        }
    }
}
