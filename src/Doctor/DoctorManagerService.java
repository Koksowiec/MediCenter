package Doctor;

import Enums.Specialization;
import Exceptions.DoctorAppointmentAlreadyExists;
import Exceptions.DoctorDoesntWorkOnThisDate;
import Extensions.LocalDateTimeExtensions;
import Main.MediCenterManager;
import Patient.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class DoctorManagerService {
    private final Scanner scanner = new Scanner(System.in);
    private final Extensions.LocalDateTimeExtensions LocalDateTimeExtensions = new LocalDateTimeExtensions();

    ///<summary>
    /// Creates example user.
    /// </summary>
    public void CreateDoctorOnInit(){
        Set<Specialization> specialization = new HashSet<>();
        specialization.add(Specialization.CHIRURG);

        Doctor doctor = new Doctor(
                "Jan",
                "Kowalski",
                "01234567890",
                "2000-01-01",
                "+48123123123",
                "kowalskij@test.com",
                "1",
                specialization);

        List<DoctorSchedule> schedules = new ArrayList<>();
        schedules.add(new DoctorSchedule(LocalDate.now(), LocalTime.parse("09:00"), LocalTime.parse("17:00")));
        schedules.add(new DoctorSchedule(LocalDate.now().plusDays(1), LocalTime.parse("09:00"), LocalTime.parse("17:00")));
        schedules.add(new DoctorSchedule(LocalDate.now().plusDays(2), LocalTime.parse("09:00"), LocalTime.parse("17:00")));
        schedules.add(new DoctorSchedule(LocalDate.now().plusDays(3), LocalTime.parse("09:00"), LocalTime.parse("17:00")));
        schedules.add(new DoctorSchedule(LocalDate.now().plusDays(5), LocalTime.parse("09:00"), LocalTime.parse("17:00")));
        doctor.setSchedules(schedules);

        List<DoctorAppointment> appointments = new ArrayList<>();
        appointments.add(new DoctorAppointment(doctor.getDoctorId(), "1", LocalDateTime.of(LocalDate.now(), LocalTime.parse("09:45"))));
        appointments.add(new DoctorAppointment(doctor.getDoctorId(), "1", LocalDateTime.of(LocalDate.now(), LocalTime.parse("13:00"))));
        doctor.setAppointments(appointments);

        MediCenterManager.addToDoctorList(doctor);
    }

    ///<summary>
    /// Validates if specialization exists. If not asks user to input it again untill it is correct.
    /// </summary>
    public Specialization ValidateDoctorSpecialization(String userInput){
        Specialization specialization;
        while(true)
        {
            try{
                specialization = Specialization.valueOf(userInput.trim().toUpperCase());
                break;
            }
            catch(Exception e)
            {
                System.out.printf("Nieprawidłowa specjalizacja. Podaj jeszcze raz: ");
                userInput = scanner.nextLine();
                continue;
            }
        }

        return specialization;
    }

    ///<summary>
    /// Validates if specializations (possible more than one separated by comma) exists.
    /// If not asks user to input it again untill it is correct.
    /// </summary>
    public Set<Specialization> ValidateDoctorMultipleSpecialization(String userInput){
        Set<Specialization> specializations = new HashSet<>();

        while(true)
        {
            String[] specializationsArray = userInput.split(",");

            if(Arrays.stream(specializationsArray).findAny().isPresent())
            {
                try {
                    for (String specializationStr : specializationsArray) {
                        Specialization specialization = Specialization.valueOf(specializationStr.trim().toUpperCase()); // zamiana na enum
                        if(!specializations.add(specialization)) // dodanie do listy
                        {
                            System.out.printf("Nie udało się dodać specjalizacji %s do listy, taka specjalizacja już istnieje.\n", specialization);
                            specializations = new HashSet<>();
                            continue;
                        }
                    }
                    break;
                }
                catch(Exception e)
                {
                    System.out.printf("Nieprawidłowe specjalizacje. Podaj jeszcze raz (oddzielone przecinkami): ");
                    userInput = scanner.nextLine();
                    continue;
                }
            }
            else
            {
                System.out.printf("Nieprawidłowe specjalizacje. Podaj jeszcze raz (oddzielone przecinkami): ");
                userInput = scanner.nextLine();
                continue;
            }
        }

        return specializations;
    }

    ///<summary>
    /// Display the content of Specialization enum.
    /// </summary>
    public void DisplayAviableSpecialization(){
        System.out.printf("Specjalizacje do wyboru: ");
        Specialization[] possibleSpecializationValues = Specialization.values();
        for(Specialization specialization : possibleSpecializationValues){
            System.out.printf(specialization.toString() + ", ");
        }
    }

    ///<summary>
    /// Display the doctor by id. Doesn't validate it.
    /// </summary>
    public Doctor GetDoctorById(String doctorId) {
        return MediCenterManager.getDoctorList().stream()
                .filter(d -> d.getDoctorId().equals(doctorId))
                .findFirst()
                .get();
    }

    ///<summary>
    /// Validates if patient exists. Return true or false.
    /// </summary>
    public boolean ValidatePatientId(String id){
        Patient patient = MediCenterManager.getPatientList().stream()
                .filter(p -> p.id.equals(id))
                .findFirst()
                .orElse(null);

        if(patient == null) {
            System.out.println("Nie ma takiego pacjenta!");
            return false;
        }

        return true;
    }

    ///<summary>
    /// Validates if doctor exists. Return true or false.
    /// </summary>
    public boolean ValidateDoctorId(String doctorId){
        Doctor doctor = MediCenterManager.getDoctorList().stream()
                .filter(d -> d.getDoctorId().equals(doctorId))
                .findFirst()
                .orElse(null);

        if(doctor == null) {
            System.out.println("Nie ma takiego lekarza!");
            return false;
        }

        return true;
    }

    ///<summary>
    /// Validates if date is in a correct format.
    /// </summary>
    public void ValidateVisitDate(String date){
        while(true){
            if(!LocalDateTimeExtensions.isLocalDate(date))
            {
                System.out.printf("Wprowadzona data ma zły format, podaj jeszcze raz (yyyy-MM-dd): ");
                date = scanner.nextLine();

                continue;
            }
            break;
        }
    }

    ///<summary>
    /// Pobierz grafik lekarza na dany dzień i wyświetl dostępne godziny co 15 min,
    /// jesli wizyta już istnieje to wypisz odpowiednią informację w trakcie dnia pracy lekarza.
    /// </summary>
    public boolean TryDisplaySchedule(String date, Doctor doctor){
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalTime start = LocalTime.parse("06:00", DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime end = LocalTime.parse("21:00", DateTimeFormatter.ofPattern("HH:mm"));

        DoctorSchedule schedule = doctor.getSchedules().stream()
                .filter(s -> s.getDate().equals(parsedDate))
                .findFirst()
                .orElse(null);

        if(schedule != null)
        {
            for(LocalTime i = schedule.from; !i.isAfter(schedule.to); i = i.plusMinutes(15))
            {
                LocalTime iterationTime = i;
                if(doctor.getAppointments().stream().anyMatch(a -> a.appointmentDateTime.toLocalDate().equals(parsedDate) && a.appointmentDateTime.toLocalTime().equals(iterationTime))){
                    System.out.printf("%s - Wizyta już umówiona\n", i);
                    continue;
                }

                System.out.println(i);
            }
            return true;
        }

        return false;
    }

    ///<summary>
    /// Validates if time is in a correct format and if it is for every 15 minutes. (00, 15, 30, 45)
    /// </summary>
    public void ValidateTime(String time){
        while(true)
        {
            if(!LocalDateTimeExtensions.isLocalTime(time))
            {
                System.out.printf("Wprowadzona godzina ma zły format, podaj jeszcze raz (HH:mm): ");
                time = scanner.nextLine();

                continue;
            }

            // Sprawdź czy wybrana przez użytkownika godzina kończy się na 00, 15, 30, 45. Wizyty są co 15 min.
            LocalTime parsedTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            if(parsedTime.getMinute() % 15 != 0)
            {
                System.out.printf("Wprowadzona godzina ma zły format. Wizyty odbywają się co 15 minut, podaj jeszcze raz (HH:mm): ");
                time = scanner.nextLine();

                continue;
            }

            break;
        }
    }

    ///<summary>
    /// Tries to set an appointment if the date and time is in the doctors working schedule.
    /// Return false if something goes wrong.
    /// </summary>
    public boolean TrySetAppointment(String date, String time, Doctor doctor, String patientId){
        String selectedDateTime = date + " " + time;
        LocalDateTime parsedDateTime = LocalDateTime.parse(selectedDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        try
        {
            boolean isInDoctorWorkingTime = false;
            for(DoctorSchedule doctorSchedule : doctor.getSchedules())
            {
                if(doctorSchedule.getDate().equals(parsedDateTime.toLocalDate()))
                {
                    if(!parsedDateTime.toLocalTime().isBefore((doctorSchedule.from)) && !parsedDateTime.toLocalTime().isAfter((doctorSchedule.to)))
                    {
                        isInDoctorWorkingTime = true;
                        break;
                    }
                }
            }

            if(!isInDoctorWorkingTime)
            {
                throw new DoctorDoesntWorkOnThisDate();
            }

            for(DoctorAppointment doctorAppointment : doctor.getAppointments()) {
                if(doctorAppointment.appointmentDateTime.equals(parsedDateTime)){
                    throw new DoctorAppointmentAlreadyExists();
                }
            }

            doctor.setAppointments(new DoctorAppointment(doctor.id, patientId, parsedDateTime));

            System.out.println("Wizyta została umówiona.");

            return true;
        }
        catch(DoctorAppointmentAlreadyExists e)
        {
            System.out.println(e.getMessage());
        }
        catch(DoctorDoesntWorkOnThisDate e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    ///<summary>
    /// Validate email with regex. It should have special characters '@', '.' in this order.
    /// </summary>
    public boolean ValidateEmail(String email){
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(emailRegex, email);
    }

    ///<summary>
    /// Validate phone number with reges. It should have 9 characters.
    /// </summary>
    public boolean ValidatePhoneNumber(String phoneNumber){
        String phoneRegex = "^\\d{9}$";
        return Pattern.matches(phoneRegex, phoneNumber);
    }
}
