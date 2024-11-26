package Doctor;

import Enums.Specialization;
import Exceptions.DoctorAppointmentAlreadyExists;
import Exceptions.DoctorDoesntWorkOnThisDate;
import Extensions.LocalDateTimeExtensions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DoctorManager {
    public static List<Doctor> DoctorList = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);
    private final LocalDateTimeExtensions LocalDateTimeExtensions = new LocalDateTimeExtensions();

    private final DoctorManagerService DoctorManagerService = new DoctorManagerService();

    public DoctorManager(){
        Doctor doctor = new Doctor(
                "Jan",
                "Kowalski",
                "01234567890",
                "2000-01-01",
                "+48123123123",
                "kowalskij@test.com",
                "1",
                List.of(Specialization.CHIRURG));

        doctor.schedules.add(new DoctorSchedule(LocalDate.now(), LocalTime.parse("09:00"), LocalTime.parse("17:00")));
        doctor.schedules.add(new DoctorSchedule(LocalDate.now().plusDays(1), LocalTime.parse("09:00"), LocalTime.parse("17:00")));
        doctor.schedules.add(new DoctorSchedule(LocalDate.now().plusDays(2), LocalTime.parse("09:00"), LocalTime.parse("17:00")));
        doctor.schedules.add(new DoctorSchedule(LocalDate.now().plusDays(3), LocalTime.parse("09:00"), LocalTime.parse("17:00")));
        doctor.schedules.add(new DoctorSchedule(LocalDate.now().plusDays(5), LocalTime.parse("09:00"), LocalTime.parse("17:00")));

        doctor.appointments.add(new DoctorAppointment(doctor.doctorId, "1", LocalDateTime.of(LocalDate.now(), LocalTime.parse("09:45"))));
        doctor.appointments.add(new DoctorAppointment(doctor.doctorId, "1", LocalDateTime.of(LocalDate.now(), LocalTime.parse("13:00"))));

        DoctorList.add(doctor);
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

        while(!LocalDateTimeExtensions.isLocalDate(dateOfBirth)){
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

        String doctorId = Integer.toString(DoctorList.size() + 1);

        Doctor doctor = new Doctor(firstName, lastName, id, dateOfBirth, phoneNumber, mailAddress, doctorId, specializations);

        DoctorList.add(doctor);

        System.out.println(String.format("Lekarz utworzony poprawnie. Id: %s", doctorId));
    }

    public void AddScheduleByDoctorId(){
        System.out.println("DODAJ GRAFIK LEKARZA");

        System.out.printf("Podaj ID lekarza: ");
        String id = scanner.nextLine();

        for (Doctor doctor : DoctorList) {
            if (doctor.doctorId.equals(id)) {
                List<DoctorSchedule> doctorSchedules = new ArrayList<>();

                if(doctor.schedules != null)
                {
                    doctorSchedules = doctor.schedules;
                }

                System.out.printf("Podaj datę (yyyy-MM-dd): ");
                String date = scanner.nextLine();

                while(true)
                {
                    if(!LocalDateTimeExtensions.isLocalDate(date)) {
                        System.out.print("Wprowadzono zły format daty, podaj jeszcze raz: ");
                        date = scanner.nextLine();

                        continue;
                    }
                    break;
                }

                System.out.printf("Podaj godziny(hh:mm-hh:mm): ");
                String time = scanner.nextLine();
                String[] hours = time.split("-");

                while(true)
                {
                    if(Arrays.stream(hours).count() == 2 && LocalDateTimeExtensions.isLocalTime(hours[0]) && LocalDateTimeExtensions.isLocalTime(hours[1])){
                        LocalTime from = LocalTime.parse(hours[0]);
                        LocalTime to = LocalTime.parse(hours[1]);

                        if(to.isBefore(from) || from.equals(to))
                        {
                            System.out.printf("Wprowadzony zakres jest nieprawidłowy, podaj jeszcze raz: ");
                            time = scanner.nextLine();
                            hours = time.split("-");
                            continue;
                        }
                    }
                    break;
                }

                var newDoctorSchedule = new DoctorSchedule(LocalDate.parse(date), LocalTime.parse(hours[0]), LocalTime.parse(hours[1]));
                doctorSchedules.add(newDoctorSchedule);

                doctor.schedules = doctorSchedules;

                System.out.printf("Grafik dla lekarza o Id: %s utworzony: %s, %s\n", id, date, time);

                return;
            }
        }

        System.out.printf("Nie udało się znaleźć lekarza o podanym Id: %s\n", id);
    }

    public void UpdateDoctorSpecialization(){
        System.out.println("DODAJ SPECJALIZACJE LEKARZA");

        System.out.printf("Podaj ID lekarza: ");
        String id = scanner.nextLine();

        System.out.printf("Podaj specjalizacje lekarza: ");
        String userInputSpecialization = scanner.nextLine();
        Specialization specialization = Specialization.valueOf(userInputSpecialization.trim().toUpperCase());

        for (Doctor doctor : DoctorList) {
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

        for (Doctor doctor : DoctorList) {
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

        for (Doctor doctor : DoctorList) {
            if (doctor.specializations.contains(specialization)) {
                System.out.println(doctor.firstName + ", " + doctor.lastName + ", Specjalizacje: " + doctor.specializations);
            }
        }
    }

    public void DisplayDoctorSchedulesByDoctorId() {
        System.out.printf("Wprowadź ID: ");
        String id = scanner.nextLine();

        for (Doctor doctor : DoctorList) {
            if (doctor.doctorId.equals(id)) {
                if(doctor.schedules != null) {
                    // START: This can be moved to separate method
                    System.out.println("GRAFIK NA NAJBLIŻSZE 7 DNI: ");

                    for(int i = 0; i < 7; i++)
                    {
                        LocalDate today = LocalDate.now().plusDays(i);
                        DoctorSchedule todaysSchedule = doctor.schedules.stream()
                                .filter(schedule -> schedule.getDate().isEqual(today))
                                .findFirst()
                                .orElse(null);

                        if(todaysSchedule != null) {
                            System.out.printf("%s %s-%s\n", todaysSchedule.getDate(), todaysSchedule.from, todaysSchedule.to);
                        }
                        else{
                            System.out.println("Brak grafiku.");
                        }
                    }
                    // END
                    return;
                } else {
                    System.out.println("Lekarz z Id: " + id + " nie posiada grafiku na najbliższe 7 dni.");
                    return;
                }
            }
        }
        System.out.println("Lekarz z Id: " + id + " nie został znaleziony.");
    }

    public void ScheduleAppointmentByDoctorId(){
        System.out.printf("Wprowadź PESEL pacjenta: ");
        String patientId = scanner.nextLine();
        if(!DoctorManagerService.ValidatePatientId(patientId))
        {
            return;
        }

        System.out.printf("Wprowadź ID lekarza: ");
        String doctorId = scanner.nextLine();
        if(!DoctorManagerService.ValidateDoctorId(doctorId))
        {
            return;
        }

        System.out.printf("Wprowadź termin wizity (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        DoctorManagerService.ValidateVisitDate(date);

        Doctor doctor = DoctorManagerService.GetDoctorById(doctorId);

        if(DoctorManagerService.TryDisplaySchedule(date, doctor)){
            System.out.printf("Wprowadź godzinę wizity (HH:mm): ");
            String time = scanner.nextLine();
            DoctorManagerService.ValidateTime(time);

            if(!DoctorManagerService.TrySetAppointment(date, time, doctor, patientId))
            {
                System.out.println("Wizyta nie została umówiona...");
            }
        }

        System.out.println("W tym dniu nie znaleziono grafiku dla tego lekarza...");
    }
}