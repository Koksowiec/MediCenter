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
        else if ((role.toLowerCase()).equals("lekarz")) {
            DoctorManager doctorManager = new DoctorManager();

            doctorManager.AddDoctor();

            Doctor exampleDoctor = new Doctor(
                    "D001",
                    "Anna",
                    "Nowak",
                    45,
                    "Kardiolog",
                    123456789,
                    "anna.nowak@example.com",
                    java.time.LocalDateTime.now()
            );
            doctorManager.DoctorList.add(exampleDoctor);

            doctorManager.DisplayDoctorById("01");

            doctorManager.DisplayDoctorsBySpecialization("Kardiolog");
        } else {
            System.out.printf("Nieznana rola. Dostępne role to: recepcjonista, lekarz.");
        }
    }
}