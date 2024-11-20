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
        } else {
            System.out.println("Nieprawidłowy wybór.");
        }
    }
}
