import Enums.Roles;
import Extensions.IntExtensions;
import java.util.Scanner;

public class MainMenu {
    private boolean StopExecution = false;

    private final Scanner Scanner = new Scanner(System.in);
    private final PatientManager PatientManager = new PatientManager();
    private final DoctorManager DoctorManager = new DoctorManager();
    private final IntExtensions IntExtensions = new IntExtensions();

    public void DisplayMainMenu(){
        System.out.println("LOGOWANIE DO SYSTEMU");

        while(!StopExecution)
        {
            Roles role = RoleSelector();

            if(role.equals(Roles.Receptionist))
            {
                DisplayReceptionistMenu();
            }
            else if (role.equals(Roles.HR))
            {
                DisplayHrMenu();
            }
            else if (role.equals(Roles.Manager))
            {
                DisplayManagerMenu();
            }
            else
            {
                System.out.println("Nie ma takiej roli...");
            }
        }
    }

    private Roles RoleSelector(){
        System.out.println("Wybierz swoją rolę:");
        System.out.println("1. Recepcjonista");
        System.out.println("2. HR");
        System.out.println("3. Kierownik placówki");
        System.out.printf("Wprowadź numer roli: ");

        int roleSelection = this.Scanner.nextInt();
        this.Scanner.nextLine();

        if (roleSelection == 1) {
            return Roles.Receptionist;
        } else if (roleSelection == 2) {
            return Roles.HR;
        } else if (roleSelection == 3) {
            return Roles.Manager;
        } else {
            return Roles.NoRole;
        }
    }

    private void DisplayReceptionistMenu(){
        int maxUserInputVal = 8;

        while(true)
        {
            System.out.println("MENU GŁÓWNE");
            System.out.println("1. Dodaj pacjenta.");
            System.out.println("2. Wyszukaj pacjenta po peselu.");
            System.out.println("3. Wyszukaj pacjentów po nazwisku.");
            System.out.println("4. Wyszukaj lekarza po ID.");
            System.out.println("5. Wyszukaj lekarzy po specjalizacji.");
            System.out.println("6. Wyświetl grafik lekarza.");
            System.out.println("7. Zmień rolę.");
            System.out.println("8. Wyjdź.");
            System.out.printf("Wybierz numer zakładki: ");

            String userInput = this.Scanner.nextLine();
            int userChoice = ValidateUserInput(userInput, maxUserInputVal);

            HandleReceptionistInput(userChoice);

            if(userChoice == 7)
            {
                DisplayMainMenu();
                break;
            }
            else if(userChoice == 8)
            {
                StopExecution = true;
                break;
            }
        }
    }

    private void DisplayHrMenu(){
        int maxUserInputVal = 4;

        while(true)
        {
            System.out.println("MENU GŁÓWNE");
            System.out.println("1. Dodaj lekarza.");
            System.out.println("2. Dodaj nową specjalizację lekarzowi.");
            System.out.println("3. Zmień rolę.");
            System.out.println("4. Wyjdź.");
            System.out.printf("Wybierz numer zakładki: ");

            String userInput = this.Scanner.nextLine();
            int userChoice = ValidateUserInput(userInput, maxUserInputVal);

            HandleHrInput(userChoice);

            if(userChoice == 3)
            {
                DisplayMainMenu();
                break;
            }
            else if(userChoice == 4)
            {
                StopExecution = true;
                break;
            }
        }
    }

    private void DisplayManagerMenu(){
        int maxUserInputVal = 3;

        while(true)
        {
            System.out.println("MENU GŁÓWNE");
            System.out.println("1. Dodaj grafik dla lekarza.");
            System.out.println("2. Zmień rolę.");
            System.out.println("3. Wyjdź.");
            System.out.printf("Wybierz numer zakładki: ");

            String userInput = this.Scanner.nextLine();
            int userChoice = ValidateUserInput(userInput, maxUserInputVal);

            HandleManagerInput(userChoice);

            if(userChoice == 2)
            {
                DisplayMainMenu();
                break;
            }
            else if(userChoice == 3)
            {
                StopExecution = true;
                break;
            }
        }
    }

    private void HandleReceptionistInput(int userInput){
        switch (userInput){
            case 1:
                PatientManager.AddPatient();
                break;
            case 2:
                PatientManager.DisplayPatientById();
                break;
            case 3:
                PatientManager.DisplayPatientsBySurname();
                break;
            case 4:
                DoctorManager.DisplayDoctorById();
                break;
            case 5:
                DoctorManager.DisplayDoctorsBySpecialization();
                break;
            case 6:
                DoctorManager.DisplayDoctorSchedulesByDoctorId();
                break;
            default:
                break;
        }
    }

    private void HandleHrInput(int userInput){
        switch (userInput){
            case 1:
                DoctorManager.AddDoctor();
                break;
            case 2:
                DoctorManager.UpdateDoctorSpecialization();
                break;
            default:
                break;
        }
    }

    private void HandleManagerInput(int userInput){
        switch (userInput){
            case 1:
                DoctorManager.AddScheduleByDoctorId();
                break;
            default:
                break;
        }
    }

    private int ValidateUserInput(String userInput, int maxUserInputVal){
        while(true)
        {
            if (IntExtensions.IsInteger(userInput)) {
                int cast = Integer.parseInt(userInput);

                if(cast <= maxUserInputVal && cast > 0)
                {
                    break;
                }
                else{
                    System.out.printf("Wybrana zakładka nie istnieje, wybierz jeszcze raz: ");
                    userInput = this.Scanner.nextLine();
                }
            }
            else{
                System.out.printf("Wprowadzony znak nie jest liczbą, wybierz jeszcze raz: ");
                userInput = this.Scanner.nextLine();
            }
        }

        return Integer.parseInt(userInput);
    }

    /*public void DisplayMainMenu() {
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
    }*/

    /*public void DisplayHRMenu() {
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

            //DoctorManager.DisplayDoctorsBySpecialization(specialization);
        }

        else {
            System.out.println("Nieprawidłowy wybór.");
        }
    }*/
}
