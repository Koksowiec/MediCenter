package Main;

import Doctor.DoctorManager;
import Enums.Roles;
import Extensions.IntExtensions;
import Patient.PatientManager;

import java.util.Scanner;

public class MainMenu {
    private boolean StopExecution = false;

    private final Scanner Scanner = new Scanner(System.in);
    private final Patient.PatientManager PatientManager = new PatientManager();
    private final Doctor.DoctorManager DoctorManager = new DoctorManager();
    private final IntExtensions IntExtensions = new IntExtensions();

    ///<summary>
    /// Display main menu. Handle user selection for roles.
    /// </summary>
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

    ///<summary>
    /// Display available roles and return selected one.
    /// </summary>
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

    ///<summary>
    /// Display available options for Receptionist role and handle selection.
    /// </summary>
    private void DisplayReceptionistMenu(){
        int maxUserInputVal = 9;

        while(true)
        {
            System.out.println("MENU GŁÓWNE");
            System.out.println("1. Dodaj pacjenta.");
            System.out.println("2. Wyszukaj pacjenta po peselu.");
            System.out.println("3. Wyszukaj pacjentów po nazwisku.");
            System.out.println("4. Wyszukaj lekarza po ID.");
            System.out.println("5. Wyszukaj lekarzy po specjalizacji.");
            System.out.println("6. Wyświetl grafik lekarza.");
            System.out.println("7. Umów pacjenta na wizytę.");
            System.out.println("8. Zmień rolę.");
            System.out.println("9. Wyjdź.");
            System.out.printf("Wybierz numer zakładki: ");

            String userInput = this.Scanner.nextLine();
            int userChoice = ValidateUserInput(userInput, maxUserInputVal);

            HandleReceptionistInput(userChoice);

            if(userChoice == 8)
            {
                DisplayMainMenu();
                break;
            }
            else if(userChoice == 9)
            {
                StopExecution = true;
                break;
            }
        }
    }

    ///<summary>
    /// Display available options for HR role and handle selection.
    /// </summary>
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

    ///<summary>
    /// Display available options for Manager role and handle selection.
    /// </summary>
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

    ///<summary>
    /// Display correct logic after Receptionist selection.
    /// </summary>
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
            case 7:
                DoctorManager.ScheduleAppointmentByDoctorId();
                break;
            default:
                break;
        }
    }

    ///<summary>
    /// Display correct logic after HR selection.
    /// </summary>
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

    ///<summary>
    /// Display correct logic after Manager selection.
    /// </summary>
    private void HandleManagerInput(int userInput){
        switch (userInput){
            case 1:
                DoctorManager.AddScheduleByDoctorId();
                break;
            default:
                break;
        }
    }

    ///<summary>
    /// Validate if inputed by user option is aviable for current role. Parse it to a number and return.
    /// </summary>
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
}
