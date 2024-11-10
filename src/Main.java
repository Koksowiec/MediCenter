import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.printf("Podaj swoja role: ");

        Scanner scanner = new Scanner(System.in);
        String role = scanner.nextLine();

        if((role.toLowerCase()).equals("recepcjonista"))
        {
            PatientManager patientManager = new PatientManager();

            patientManager.AddPatient();
            patientManager.AddPatient();

            String id = "0123";
            patientManager.DisplayPatientById(id);

            String lastName = "Kowalski";
            patientManager.DisplayPatientsBySurname(lastName);
        }
        else{
            System.out.printf("To be implemented... ");
        }
    }
}