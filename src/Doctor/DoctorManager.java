package Doctor;

import Enums.Specialization;
import Exceptions.DoctorAppointmentAlreadyExists;
import Extensions.LocalDateTimeExtensions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class DoctorManager {
    public List<Doctor> doctorList = new ArrayList<>();
    public List<DoctorAppointment> doctorAppointmentList = new ArrayList<>();

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

        doctor.schedules = Arrays.asList(
                new DoctorSchedule(LocalDate.now(), LocalTime.parse("09:00"), LocalTime.parse("17:00")),
                new DoctorSchedule(LocalDate.now().plusDays(1), LocalTime.parse("09:00"), LocalTime.parse("17:00")),
                new DoctorSchedule(LocalDate.now().plusDays(2), LocalTime.parse("09:00"), LocalTime.parse("17:00")),
                new DoctorSchedule(LocalDate.now().plusDays(3), LocalTime.parse("09:00"), LocalTime.parse("17:00")),
                new DoctorSchedule(LocalDate.now().plusDays(5), LocalTime.parse("09:00"), LocalTime.parse("17:00"))
        );


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

        System.out.println(String.format("Lekarz utworzony poprawnie. Id: %s", doctorId));
    }

    public void AddScheduleByDoctorId(){
        System.out.println("DODAJ GRAFIK LEKARZA");

        System.out.printf("Podaj ID lekarza: ");
        String id = scanner.nextLine();

        for (Doctor doctor : doctorList) {
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
                    if(!LocalDateTimeExtensions.isLocalDateTime(date)) {
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

                doctorSchedules.add(new DoctorSchedule(LocalDate.parse(date), LocalTime.parse(hours[0]), LocalTime.parse(hours[1])));

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
        String id = scanner.nextLine();

        System.out.printf("Wprowadź ID lekarza: ");
        String doctorId = scanner.nextLine();

        System.out.printf("Wprowadź termin wizity (yyyy-MM-dd HH:mm): ");
        String selectedDate = scanner.nextLine();

        try
        {
            for(DoctorAppointment doctorAppointment : doctorAppointmentList) {
                if (doctorAppointment.doctorId.equals(doctorId)) {
                    if(doctorAppointment.apoointmentDateTime.equals(selectedDate)){
                        throw new DoctorAppointmentAlreadyExists();
                    }
                }
            }

            doctorAppointmentList.add(new DoctorAppointment(doctorId, id, LocalDateTime.parse(selectedDate)));
        }
        catch(DoctorAppointmentAlreadyExists e)
        {
            System.out.printf(e.getMessage());
        }

    }
    /*
    Jak można wyrzucać exception dla tego kodu, refactor idea:
    - dodać własny Exception DoctorAppointmentException
    - Zmienić kod aby wyświetlał wszystkie godziny co 15 min ale obok tych z wizytą wypisywać 15:45 - wizyta
    - kiedy użytkownik wybierze dzień i godzinę gdzie jest juz wizyta dodać try catch i wyrzucić tam wyjątek
    - kiedy użytkownik wybierze dzień i godzinę z poza grafiku dodać try catch i wyrzucić tam wyjątek

    public void ScheduleAppointmentByDoctorId() {
        System.out.printf("Wprowadź PESEL pacjenta: ");
        String id = scanner.nextLine();

        System.out.printf("Wprowadź ID lekarza: ");
        String doctorId = scanner.nextLine();

        boolean canSchedule = DoctorManagerService.CanScheduleAppointment(doctorId, doctorList, doctorAppointmentList);

        if(canSchedule)
        {
            System.out.printf("Wprowadź dzień i godzine wizyty (dzien,godzina[09:00]): ");
            String date = scanner.nextLine();
            String[] dateTime = date.split(",");

            if(dateTime.length == 2) {
                String dayOfWeek = DoctorManagerService.ValidateDayOfWeek(dateTime[0]);
                String hour = DoctorManagerService.ValidateHour(dateTime[1]);

                if (!DoctorManagerService.DoesAppointmentExist(LocalTime.parse(hour), DayOfWeek.valueOf(dayOfWeek), doctorId, doctorAppointmentList)) {
                    DoctorAppointment doctorAppointment = new DoctorAppointment(doctorId, id, LocalTime.parse(hour), DayOfWeek.valueOf(dayOfWeek));
                    doctorAppointmentList.add(doctorAppointment);

                    System.out.println(String.format("Wizyta umówiona poprawnie na dzień %s na godzinę %s", dayOfWeek, hour));
                    return;
                }
            }
        }

        System.out.println("Nie udało się umówić wizyty...");
    }
    */
}