import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Logowanie do systemu. Wybierz swoją rolę:");
        System.out.println("1. Recepcjonista");
        System.out.println("2. Lekarz");
        System.out.println("3. HR");
        System.out.printf("Wprowadź numer roli: ");

        int roleSelection = scanner.nextInt();
        scanner.nextLine();

        if (roleSelection == 1) {
            System.out.println("Zalogowano jako Recepcjonista.");
            PatientManager patientManager = new PatientManager();

            patientManager.AddPatient();
            patientManager.AddPatient();

            String id = "0123";
            patientManager.DisplayPatientById(id);

            String lastName = "Kowalski";
            patientManager.DisplayPatientsBySurname(lastName);

        } else if (roleSelection == 2) {
            System.out.println("Zalogowano jako Lekarz.");
            DoctorManager doctorManager = new DoctorManager();

            doctorManager.AddDoctor();

            Specialization specialization = Specialization.KARDIOLOG;
            Doctor exampleDoctor = new Doctor(
                    "D001",
                    "Anna",
                    "Nowak",
                    45,
                    new ArrayList<>(List.of(Specialization.KARDIOLOG)),
                    123456789,
                    "anna.nowak@example.com",
                    java.time.LocalDateTime.now()
            );
            doctorManager.doctorList.add(exampleDoctor);

            doctorManager.DisplayDoctorById("D001");

            doctorManager.DisplayDoctorsBySpecialization(Specialization.KARDIOLOG);

        } else if (roleSelection == 3) {
            System.out.println("Zalogowano jako HR.");
            MainMenu mainMenu = new MainMenu();
            mainMenu.DisplayMainMenu();

        } else {
            System.out.println("Nieznana rola. Wybierz poprawny numer.");
        }
    }
}
