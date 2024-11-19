import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorManager {
    public List<Doctor> DoctorList = new ArrayList<>();

    public void AddDoctor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("DODAJ LEKARZA");

        System.out.printf("ID: ");
        String id = scanner.nextLine();

        System.out.printf("Imię: ");
        String firstName = scanner.nextLine();

        System.out.printf("Nazwisko: ");
        String lastName = scanner.nextLine();

        System.out.printf("Wiek: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("Specjalizacja: ");
        String specialization = scanner.nextLine();

        System.out.printf("Numer telefonu: ");
        int phoneNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("E-mail: ");
        String mailAddress = scanner.nextLine();

        System.out.printf("Data zatrudnienia (yyyy-MM-dd): ");
        String hireDateInput = scanner.nextLine();
        LocalDateTime hireDate = LocalDateTime.parse(hireDateInput + "T00:00:00");

        Doctor doctor = new Doctor(id, firstName, lastName, age, specialization, phoneNumber, mailAddress, hireDate);

        DoctorList.add(doctor);

        System.out.println("Lekarz utworzony poprawnie.");
    }

    public void DisplayDoctorById(String id) {
        System.out.println("Wyświetl lekarza z ID: " + id);
        for (Doctor doctor : DoctorList) {
            if (doctor.id.equals(id)) {
                System.out.println(doctor.firstName + ", " + doctor.lastName + ", Specjalizacja: " + doctor.specialization);
                return;
            }
        }
        System.out.println("Lekarz z ID " + id + " nie został znaleziony.");
    }

    public void DisplayDoctorsBySpecialization(String specialization) {
        System.out.println("Wyświetl lekarzy z specjalizacją: " + specialization);
        for (Doctor doctor : DoctorList) {
            if (doctor.specialization.equalsIgnoreCase(specialization)) {
                System.out.println(doctor.firstName + ", " + doctor.lastName + ", Specjalizacja: " + doctor.specialization);
            }
        }
    }
}
