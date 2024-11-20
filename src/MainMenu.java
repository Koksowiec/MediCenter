import java.util.Scanner;

public class MainMenu {

    public void DisplayMainMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("MENU GŁÓWNE");
        System.out.println("1. Zarządzaj pacjentami.");
        System.out.println("2. Zarządzaj personelem medycznym.");
        System.out.println("3. Zarządzaj kalendarzem.");
        System.out.println("4. Logowanie jako HR.");
        System.out.printf("Wybierz numer zakładki: ");

        int selectionMainMenu = scanner.nextInt();
        scanner.nextLine();

        if (selectionMainMenu == 1) {
            System.out.println("1. Zarządzaj pacjentami.");
            System.out.println("a. Dodaj pacjenta.");
            System.out.println("b. Usuń/edytuj pacjenta.");
            System.out.println("c. Szukaj pacjenta.");
        } else if (selectionMainMenu == 2) {
            System.out.println("2. Zarządzaj personelem medycznym.");
            System.out.println("a. Dodaj lekarza.");
            System.out.println("b. Usuń/edytuj lekarza.");
            System.out.println("c. Szukaj lekarza.");
        } else if (selectionMainMenu == 3) {
            System.out.println("3. Zarządzaj kalendarzem.");
            System.out.println("a. Dodaj wizytę.");
            System.out.println("b. Usuń/edytuj wizytę.");
            System.out.println("c. Szukaj wizyty.");
            System.out.println("d. Wyświetl grafik.");
        } else if (selectionMainMenu == 4) {
            DisplayHRMenu();
        } else {
            System.out.println("Wybierz poprawną liczbę.");
        }

        System.out.println("X Wróć do menu głównego.");
    }

    public void DisplayHRMenu() {
        Scanner scanner = new Scanner(System.in);
        DoctorManager doctorManager = new DoctorManager();

        System.out.println("ZALOGOWANO JAKO HR");
        System.out.println("1. Dodaj lekarza.");
        System.out.println("2. Wyświetl wszystkich lekarzy.");
        System.out.println("3. Edytuj specjalizacje lekarzowi.");
        System.out.println("4. Znajdź lekarzy po specjalizacji.");
        System.out.printf("Wybierz opcję: ");

        int hrSelection = scanner.nextInt();
        scanner.nextLine();

        if (hrSelection == 1) {
            doctorManager.AddDoctor();
        } else if (hrSelection == 2) {
            System.out.println("LISTA LEKARZY:");
            for (Doctor doctor : doctorManager.doctorList) {
                System.out.print("Specjalizacje: ");
                for (Specialization specialization : doctor.specializations) {
                    System.out.print(specialization + " ");
                }
                System.out.println();

            }
        }
        else if (hrSelection == 3) {
            System.out.println("ID lekarza, któremu chcesz dodać/usunąć specjalizację:");

            for (Doctor doctor : doctorManager.doctorList) {
                System.out.println("ID: " + doctor.id + ", Imię: " + doctor.firstName + ", Specjalizacje: ");
                for (Specialization specialization : doctor.specializations) {
                    System.out.print(specialization + " ");
                }
                System.out.println();
            }

            System.out.print("Podaj ID lekarza: ");
            String doctorId = scanner.nextLine();

            Doctor selectedDoctor = null;
            for (Doctor doctor : doctorManager.doctorList) {
                if (doctor.id.equals(doctorId)) {
                    selectedDoctor = doctor;
                    break;
                }
            }

            if (selectedDoctor == null) {
                System.out.println("Lekarz o podanym ID nie istnieje.");
                return;
            }

            System.out.println("Co chcesz zrobić?");
            System.out.println("1. Dodaj specjalizację");
            System.out.println("2. Usuń specjalizację");
            int operation = scanner.nextInt();

            if (operation == 1) {
                System.out.println("Dostępne specjalizacje:");
                for (Specialization specialization : Specialization.values()) {
                    System.out.println(specialization);
                }
                System.out.print("Wybierz specjalizację do dodania: ");
                String specializationInput = scanner.nextLine();
                Specialization specializationToAdd = Specialization.valueOf(specializationInput.toUpperCase());

                if (!selectedDoctor.specializations.contains(specializationToAdd)) {
                    selectedDoctor.specializations.add(specializationToAdd);
                    System.out.println("Specjalizacja dodana pomyślnie.");
                } else {
                    System.out.println("Lekarz już ma tę specjalizację.");
                }
            } else if (operation == 2) {
                System.out.println("Specjalizacje lekarza:");
                for (Specialization specialization : selectedDoctor.specializations) {
                    System.out.println(specialization);
                }
                System.out.print("Wybierz specjalizację do usunięcia: ");
                String specializationInput = scanner.nextLine();
                Specialization specializationToRemove = Specialization.valueOf(specializationInput.toUpperCase());

                if (selectedDoctor.specializations.contains(specializationToRemove)) {
                    selectedDoctor.specializations.remove(specializationToRemove);
                    System.out.println("Specjalizacja usunięta pomyślnie.");
                } else {
                    System.out.println("Lekarz nie ma tej specjalizacji.");
                }
            } else {
                System.out.println("Niepoprawna opcja.");
            }
        }
        if (hrSelection == 6) {
            System.out.println("Dostępne specjalizacje: ");
            for (Specialization specialization : Specialization.values()) {
                System.out.println(specialization);
            }
            System.out.print("Wybierz specjalizację, aby wyświetlić lekarzy: ");
            String specializationInput = scanner.nextLine();
            Specialization specialization = Specialization.valueOf(specializationInput.toUpperCase());

            DoctorManager.DisplayDoctorsBySpecialization(specialization);
        }

        else {
            System.out.println("Nieprawidłowy wybór.");
        }
    }
}
