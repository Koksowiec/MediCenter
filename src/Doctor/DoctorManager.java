package Doctor;

import Enums.Specialization;
import Extensions.LocalDateTimeExtensions;
import Main.MediCenterManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class DoctorManager {
    private final Scanner scanner = new Scanner(System.in);
    private final LocalDateTimeExtensions LocalDateTimeExtensions = new LocalDateTimeExtensions();
    private final DoctorManagerService DoctorManagerService = new DoctorManagerService();

    public DoctorManager(){
        DoctorManagerService.CreateDoctorOnInit();
    }

    /// <summary>
    /// Add doctor by user input. Validates birthdate, phone number, email and specialization.
    /// </summary>
    public void AddDoctor() {
        System.out.println("DODAJ LEKARZA");

        System.out.printf("Imię: ");
        String firstName = scanner.nextLine();

        System.out.printf("Nazwisko: ");
        String lastName = scanner.nextLine();

        System.out.printf("PESEL lub numer dowodu: ");
        String id = scanner.nextLine();

        System.out.printf("Data urodzin (yyyy-MM-dd): ");
        String dateOfBirth = scanner.nextLine();
        while(!LocalDateTimeExtensions.isLocalDate(dateOfBirth)){
            System.out.printf("Podano niepoprawną datę urodzin, podaj jeszcze raz (yyyy-MM-dd): ");
            dateOfBirth = scanner.nextLine();
        }

        System.out.printf("Numer telefonu (9-cyfr): ");
        String phoneNumber = scanner.nextLine();
        while(!DoctorManagerService.ValidatePhoneNumber(phoneNumber)){
            System.out.printf("Podano niepoprawny numer telefonu, podaj jeszcze raz (9 cyfr): ");
            phoneNumber = scanner.nextLine();
        }

        System.out.printf("E-mail: ");
        String mailAddress = scanner.nextLine();
        while(!DoctorManagerService.ValidateEmail(mailAddress)){
            System.out.printf("Podano niepoprawny adres e-mail, podaj jeszcze raz: ");
            mailAddress = scanner.nextLine();
        }

        DoctorManagerService.DisplayAvailableSpecialization();
        System.out.printf("\nSpecjalizacje (oddzielone przecinkami): ");
        String specializationsInput = scanner.nextLine();

        Set<Specialization> specializations = DoctorManagerService.ValidateDoctorMultipleSpecialization(specializationsInput);
        String doctorId = Integer.toString(MediCenterManager.getDoctorList().size() + 1);

        Doctor doctor = new Doctor(firstName, lastName, id, dateOfBirth, phoneNumber, mailAddress, doctorId, specializations);

        MediCenterManager.addToDoctorList(doctor);

        System.out.println(String.format("Lekarz utworzony poprawnie. Id: %s", doctorId));
    }

    /// <summary>
    /// Add doctors schedule by id. Check if doctor exists, and if schedule already exists.
    /// If it does add to id if not create new one. Validate input date and hour.
    /// </summary>
    public void AddScheduleByDoctorId(){
        System.out.println("DODAJ GRAFIK LEKARZA");

        System.out.printf("Podaj ID lekarza: ");
        String id = scanner.nextLine();

        for (Doctor doctor : MediCenterManager.getDoctorList()) {
            if (doctor.getDoctorId().equals(id)) {
                List<DoctorSchedule> doctorSchedules = new ArrayList<>();

                if(doctor.getSchedules() != null)
                {
                    doctorSchedules = doctor.getSchedules();
                }

                System.out.printf("Podaj datę (yyyy-MM-dd): ");
                String date = scanner.nextLine();

                // Validacja daty
                while(true)
                {
                    if(!LocalDateTimeExtensions.isLocalDate(date)) {
                        System.out.print("Wprowadzono zły format daty, podaj jeszcze raz: ");
                        date = scanner.nextLine();

                        continue;
                    }
                    break;
                }

                // Sprawdź czy grafik na dany dzień już istnieje
                for(DoctorSchedule doctorSchedule : doctor.getSchedules())
                {
                    if(doctorSchedule.getDate().equals(LocalDate.parse(date)))
                    {
                        System.out.println("Lekarz ma już grafik na ten dzień");
                        return;
                    }
                }

                // Validacja godzin, muszą być 2 i musza być w poprawnym formacie
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

                doctor.setSchedules(doctorSchedules);

                System.out.printf("Grafik dla lekarza o Id: %s utworzony: %s, %s\n", id, date, time);

                return;
            }
        }

        System.out.printf("Nie udało się znaleźć lekarza o podanym Id: %s\n", id);
    }

    /// <summary>
    /// Add doctors specialization. Validate inputed specialization and add if it doesn't exist already.
    /// </summary>
    public void UpdateDoctorSpecialization(){
        System.out.println("DODAJ SPECJALIZACJE LEKARZA");

        System.out.printf("Podaj ID lekarza: ");
        String id = scanner.nextLine();

        DoctorManagerService.DisplayAvailableSpecialization();
        System.out.printf("\nPodaj specjalizacje lekarza: ");
        String userInputSpecialization = scanner.nextLine();

        Specialization specialization = DoctorManagerService.ValidateDoctorSpecialization(userInputSpecialization);

        for (Doctor doctor : MediCenterManager.getDoctorList()) {
            if (doctor.getDoctorId().equals(id)) {
                if(!doctor.setSpecializations(specialization))
                {
                    System.out.println("Ten lekarz już ma taką specjalizację...");
                    return;
                }
                System.out.printf("Specjalizacja dodana poprawnie.");
                return;
            }
        }
        System.out.println("Lekarz z ID " + id + " nie został znaleziony.");
    }

    /// <summary>
    /// Display doctor by id.
    /// </summary>
    public void DisplayDoctorById() {
        System.out.printf("Wprowadź ID: ");
        String id = scanner.nextLine();

        for (Doctor doctor : MediCenterManager.getDoctorList()) {
            if (doctor.getDoctorId().equals(id)) {
                System.out.printf("Id: %s, Imie: %s, Nazwisko: %s, Id: %s, Wiek: %s, Numer telefonu: %s, E-mail: %s, Specjalizacje: %s\n",
                        doctor.getDoctorId(),
                        doctor.getFirstName(),
                        doctor.getLastName(),
                        doctor.getId(),
                        doctor.getAge(),
                        doctor.getPhoneNumber(),
                        doctor.getMailAddress(),
                        doctor.getSpecializations());
                return;
            }
        }
        System.out.println("Lekarz z ID " + id + " nie został znaleziony.");
    }

    /// <summary>
    /// Display all doctors with a given specialization.
    /// </summary>
    public void DisplayDoctorsBySpecialization() {
        DoctorManagerService.DisplayAvailableSpecialization();
        System.out.printf("\nWprowadź specjalizacje: ");
        String userInput = scanner.nextLine();

        Specialization specialization = DoctorManagerService.ValidateDoctorSpecialization(userInput);

        boolean isFoundAny = false;
        for (Doctor doctor : MediCenterManager.getDoctorList()) {
            if (doctor.getSpecializations().contains(specialization)) {
                System.out.printf("Id: %s, Imie: %s, Nazwisko: %s, Id: %s, Wiek: %s, Numer telefonu: %s, E-mail: %s, Specjalizacje: %s\n",
                        doctor.getDoctorId(),
                        doctor.getFirstName(),
                        doctor.getLastName(),
                        doctor.getId(),
                        doctor.getAge(),
                        doctor.getPhoneNumber(),
                        doctor.getMailAddress(),
                        doctor.getSpecializations());
                isFoundAny = true;
            }
        }

        if(!isFoundAny)
        {
            System.out.println("Nie ma lekarza z taką specjalizacją.");
        }
    }

    /// <summary>
    /// Display doctor schedule by id for the next 7 days.
    /// </summary>
    public void DisplayDoctorSchedulesByDoctorId() {
        System.out.printf("Wprowadź ID: ");
        String id = scanner.nextLine();

        for (Doctor doctor : MediCenterManager.getDoctorList()) {
            if (doctor.getDoctorId().equals(id)) {
                if(doctor.getSchedules() != null) {
                    // START: This can be moved to separate method
                    System.out.println("GRAFIK NA NAJBLIŻSZE 7 DNI: ");

                    for(int i = 0; i < 7; i++)
                    {
                        LocalDate today = LocalDate.now().plusDays(i);
                        DoctorSchedule todaysSchedule = doctor.getSchedules().stream()
                                .filter(schedule -> schedule.getDate().isEqual(today))
                                .findFirst()
                                .orElse(null);

                        if(todaysSchedule != null) {
                            System.out.printf("%s %s-%s\n", todaysSchedule.getDate(), todaysSchedule.getFrom(), todaysSchedule.getTo());
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

    /// <summary>
    /// Schedule an appointment by doctor id. Validate if patient and cotro exists. Validate appointment date.
    /// Try schedule appointment if the date and hour are correct. If not do nothing.
    /// </summary>
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