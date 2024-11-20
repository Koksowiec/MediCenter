import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorManager {
    public List<Doctor> doctorList = new ArrayList<>();

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
        scanner.nextLine();  // consume newline character

        System.out.printf("Specjalizacje (oddzielone przecinkami): ");
        String specializationsInput = scanner.nextLine();
        String[] specializationsArray = specializationsInput.split(",");
        List<Specialization> specializations = new ArrayList<>();
        for (String specializationStr : specializationsArray) {
            specializations.add(Specialization.valueOf(specializationStr.trim().toUpperCase()));  // zamiana na enum
        }

        System.out.printf("Numer telefonu: ");
        int phoneNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.printf("E-mail: ");
        String mailAddress = scanner.nextLine();

        System.out.printf("Data zatrudnienia (yyyy-MM-dd): ");
        String hireDateInput = scanner.nextLine();
        LocalDateTime hireDate = LocalDateTime.parse(hireDateInput + "T00:00:00");

        Doctor doctor = new Doctor(id, firstName, lastName, age, specializations, phoneNumber, mailAddress, hireDate);

        doctorList.add(doctor);

        System.out.println("Lekarz utworzony poprawnie.");
    }

    public void DisplayDoctorById(String id) {
        System.out.println("Wyświetl lekarza z ID: " + id);
        for (Doctor doctor : doctorList) {
            if (doctor.id.equals(id)) {
                System.out.println(doctor.firstName + ", " + doctor.lastName + ", Specjalizacje: " + doctor.specializations);
                return;
            }
        }
        System.out.println("Lekarz z ID " + id + " nie został znaleziony.");
    }

    public void DisplayDoctorsBySpecialization(Specialization specialization) {
        System.out.println("Wyświetl lekarzy z specjalizacją: " + specialization);
        for (Doctor doctor : doctorList) {
            if (doctor.specializations.contains(specialization)) {
                System.out.println(doctor.firstName + ", " + doctor.lastName + ", Specjalizacja: " + doctor.specializations);
            }
        }
    }
}
