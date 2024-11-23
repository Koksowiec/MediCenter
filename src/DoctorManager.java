import Enums.Specialization;
import Extensions.LocalDateTimeExtensions;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorManager {
    public List<Doctor> doctorList = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);
    private final LocalDateTimeExtensions LocalDateTimeExtensions = new LocalDateTimeExtensions();

    DoctorManager(){
        Doctor doctor = new Doctor(
                "Jan",
                "Kowalski",
                "01234567890",
                "2000-01-01",
                "+48123123123",
                "kowalskij@test.com",
                "1",
                List.of(Specialization.CHIRURG));

        doctorList.add(doctor);
    }

    public void AddDoctor() {
        System.out.println("DODAJ LEKARZA");

        System.out.printf("Imię: ");
        String firstName = scanner.nextLine();

        System.out.printf("Nazwisko: ");
        String lastName = scanner.nextLine();

        System.out.printf("PESEL: ");
        String id = scanner.nextLine();

        System.out.printf("Data urodzin (yyyy-MM-dd): ");
        String dateOfBirth = scanner.nextLine();

        while(!LocalDateTimeExtensions.isLocalDateTime(dateOfBirth)){
            System.out.printf("Podano niepoprawną datę urodzin, podaj jeszcze raz (yyyy-MM-dd): ");
            dateOfBirth = scanner.nextLine();
        }

        System.out.printf("Numer telefonu: ");
        String phoneNumber = scanner.nextLine();

        System.out.printf("E-mail: ");
        String mailAddress = scanner.nextLine();

        System.out.printf("Specjalizacje (oddzielone przecinkami): ");
        String specializationsInput = scanner.nextLine();

        String[] specializationsArray = specializationsInput.split(",");
        List<Specialization> specializations = new ArrayList<>();
        for (String specializationStr : specializationsArray) {
            specializations.add(Specialization.valueOf(specializationStr.trim().toUpperCase()));  // zamiana na enum
        }

        String doctorId = Integer.toString(doctorList.size() + 1);

        Doctor doctor = new Doctor(firstName, lastName, id, dateOfBirth, phoneNumber, mailAddress, doctorId, specializations);

        doctorList.add(doctor);

        System.out.println("Lekarz utworzony poprawnie.");
    }

    public void AddScheduleByDoctorId(){
        System.out.println("DODAJ GRAFIK LEKARZA");

        System.out.printf("Podaj ID lekarza: ");
        String id = scanner.nextLine();

        for (Doctor doctor : doctorList) {
            if (doctor.doctorId.equals(id)) {
                List<DoctorSchedule> doctorSchedules = new ArrayList<>();

                for(int i = 1; i <= 7; i++)
                {
                    DayOfWeek dayOfWeek = DayOfWeek.of(i);

                    System.out.printf(String.format("Podaj godziny pracy lekarza (od-do lub WOLNE) w dzień %s: ", dayOfWeek));
                    String userInput = scanner.nextLine();

                    DoctorSchedule doctorSchedule;
                    if(!userInput.equals("WOLNE"))
                    {
                        String[] hours = userInput.split("-");
                        doctorSchedule = new DoctorSchedule(dayOfWeek, hours[0], hours[1]);
                    }
                    else
                    {
                        doctorSchedule = new DoctorSchedule(dayOfWeek, false);
                    }

                    doctorSchedules.add(doctorSchedule);
                }

                doctor.schedules = doctorSchedules;
                return;
            }
        }
        System.out.println("Lekarz z ID " + id + " nie został znaleziony.");
    }

    public void UpdateDoctorSpecialization(){
        System.out.println("DODAJ SPECJALIZACJE LEKARZA");

        System.out.printf("Podaj ID lekarza: ");
        String id = scanner.nextLine();

        System.out.printf("Podaj specjalizacje lekarza: ");
        String userInputSpecialization = scanner.nextLine();
        Specialization specialization = Specialization.valueOf(userInputSpecialization.trim().toUpperCase());

        for (Doctor doctor : doctorList) {
            if (doctor.id.equals(id)) {
                doctor.specializations.add(specialization);

                System.out.println(doctor.firstName + ", " + doctor.lastName + ", Specjalizacje: " + doctor.specializations);
                return;
            }
        }
        System.out.println("Lekarz z ID " + id + " nie został znaleziony.");
    }

    public void DisplayDoctorById() {
        System.out.printf("Wprowadź ID: ");
        String id = scanner.nextLine();

        for (Doctor doctor : doctorList) {
            if (doctor.doctorId.equals(id)) {
                System.out.println(doctor.firstName + ", " + doctor.lastName + ", Specjalizacje: " + doctor.specializations);
                return;
            }
        }
        System.out.println("Lekarz z ID " + id + " nie został znaleziony.");
    }

    public void DisplayDoctorsBySpecialization() {
        System.out.printf("Wprowadź specjalizacje: ");
        String userInput = scanner.nextLine();

        Specialization specialization = Specialization.valueOf(userInput.trim().toUpperCase());

        for (Doctor doctor : doctorList) {
            if (doctor.specializations.contains(specialization)) {
                System.out.println(doctor.firstName + ", " + doctor.lastName + ", Specjalizacje: " + doctor.specializations);
            }
        }
    }

    public void DisplayDoctorSchedulesByDoctorId() {
        System.out.printf("Wprowadź ID: ");
        String id = scanner.nextLine();

        for (Doctor doctor : doctorList) {
            if (doctor.doctorId.equals(id)) {
                if(doctor.schedules != null) {
                    for (DoctorSchedule doctorSchedule : doctor.schedules) {
                        if (doctorSchedule.isWorkingDay) {
                            System.out.println(String.format("%s %s-%s", doctorSchedule.dayOfWeek, doctorSchedule.from, doctorSchedule.to));
                        } else {
                            System.out.println(String.format("%s WOLNE", doctorSchedule.dayOfWeek));
                        }
                    }
                    return;
                } else {
                    System.out.println("Lekarz z ID " + id + " nie posiada grafiku.");
                    return;
                }
            }
        }
        System.out.println("Lekarz z ID " + id + " nie został znaleziony.");
    }
}