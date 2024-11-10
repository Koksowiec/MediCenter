import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //tu kiedys bedzie logowanie
    public static void main(String[] args) {
        System.out.println("MENU GŁÓWNE");
        System.out.println("1. Zarządzaj pacjentami.");
        System.out.println("2. Zarządzaj personelem medycznym.");
        System.out.println("3. Zarządzaj kalendarzem.");
        System.out.printf("Wybierz numer zakładki: ");

        Scanner scanner = new Scanner(System.in);
        int selectionMainMenu = scanner.nextInt();
        scanner.nextLine();

        if (selectionMainMenu == 1) {
            System.out.println("1. Zarządzaj pacjentami.");
            System.out.println("a. Dodaj pacjenta.");
            System.out.println("b. Usuń/edytuj pacjenta.");
            System.out.println("c. Szukaj pacjenta.");
        }
        else if (selectionMainMenu == 2) {
            System.out.println("2. Zarządzaj personelem medycznym.");
            System.out.println("a. Dodaj lekarza.");
            System.out.println("b. Usuń/edytuj lekarza.");
            System.out.println("c. Szukaj lekarza.");
        }
        else if (selectionMainMenu == 3) {
            System.out.println("3. Zarządzaj kalendarzem.");
            System.out.println("a. Dodaj wizytę.");
            System.out.println("b. Usuń/edytuj wizytę.");
            System.out.println("c. Szukaj wizyty.");
            System.out.println("d. Wyświetl grafik.");
        }
        else {
            System.out.println("Wybierz poprawną liczbę.");
        }
        System.out.println("X Wróć do menu głownego.");

        System.out.printf("Wybierz numer zakładki: ");


        String selectionOption = scanner.nextLine();
        selectionOption = selectionOption.toLowerCase();

        if (selectionMainMenu == 1) {
           if (selectionOption.equals("a")) {
               System.out.println("DODAJ PACJENTA");

               System.out.printf("Imię: ");
               String firstName = scanner.nextLine();

               System.out.printf("Nazwisko: ");
               String lastName = scanner.nextLine();

               System.out.printf("PESEL: ");
               int id = scanner.nextInt();
               scanner.nextLine();

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

               ArrayList<Patient> patientsList = new ArrayList<Patient>();
               patientsList.add(patient);
               for (Patient p : patientsList) {
                   System.out.println(p.firstName);
               }
           }
           else if (selectionOption.equals("b")) {
               System.out.println("USUŃ/EDYTUJ PACJENTA");
           }
           else if (selectionOption.equals("c")) {
               System.out.println("SZUKAJ PACJENTA");
           }
           else {System.out.println("Wybierz poprawny znak.");}
        }
        else if (selectionMainMenu == 2) {
            if (selectionOption == "a") {}
            else if (selectionOption == "b") {}
            else if (selectionOption == "c") {}
            else {System.out.println("Wybierz poprawny znak.");}
        }
        else if (selectionMainMenu == 3) {
            if (selectionOption == "a") {}
            else if (selectionOption == "b") {}
            else if (selectionOption == "c") {}
            else if (selectionOption == "d") {}
            else {System.out.println("Wybierz poprawny znak.");}
        }
        else {
            System.out.println("Wybierz poprawny znak.");
        }


    }
}