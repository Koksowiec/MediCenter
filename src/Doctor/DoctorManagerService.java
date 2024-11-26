package Doctor;

import Exceptions.DoctorAppointmentAlreadyExists;
import Exceptions.DoctorDoesntWorkOnThisDate;
import Extensions.LocalDateTimeExtensions;
import Patient.PatientManager;
import Patient.Patient;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static Doctor.DoctorManager.DoctorList;

public class DoctorManagerService {
    private final Scanner scanner = new Scanner(System.in);
    private final Extensions.LocalDateTimeExtensions LocalDateTimeExtensions = new LocalDateTimeExtensions();

    public Doctor GetDoctorById(String doctorId) {
        return DoctorList.stream()
                .filter(d -> d.doctorId.equals(doctorId))
                .findFirst()
                .get();
    }

    public boolean ValidatePatientId(String id){
        Patient patient = PatientManager.PatientList.stream()
                .filter(p -> p.id.equals(id))
                .findFirst()
                .orElse(null);

        if(patient == null) {
            System.out.println("Nie ma takiego pacjenta!");
            return false;
        }

        return true;
    }

    public boolean ValidateDoctorId(String doctorId){
        Doctor doctor = DoctorList.stream()
                .filter(d -> d.doctorId.equals(doctorId))
                .findFirst()
                .orElse(null);

        if(doctor == null) {
            System.out.println("Nie ma takiego lekarza!");
            return false;
        }

        return true;
    }

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
    /// Pobierz grafik lekarza na dany dzień i wyświetl dostępne godziny co 15 min, jesli wizyta już istnieje to wypisz odpowiednią informację w trakcie dnia pracy lekarza.
    /// </summary>
    public boolean TryDisplaySchedule(String date, Doctor doctor){
        LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalTime start = LocalTime.parse("06:00", DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime end = LocalTime.parse("21:00", DateTimeFormatter.ofPattern("HH:mm"));

        DoctorSchedule schedule = doctor.schedules.stream()
                .filter(s -> s.getDate().equals(parsedDate))
                .findFirst()
                .orElse(null);

        if(schedule != null)
        {
            for(LocalTime i = schedule.from; !i.isAfter(schedule.to); i = i.plusMinutes(15))
            {
                LocalTime iterationTime = i;
                if(doctor.appointments.stream().anyMatch(a -> a.appointmentDateTime.toLocalDate().equals(parsedDate) && a.appointmentDateTime.toLocalTime().equals(iterationTime))){
                    System.out.printf("%s - Wizyta już umówiona\n", i);
                    continue;
                }

                System.out.println(i);
            }
            return true;
        }

        return false;
    }

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

    public boolean TrySetAppointment(String date, String time, Doctor doctor, String patientId){
        String selectedDateTime = date + " " + time;
        LocalDateTime parsedDateTime = LocalDateTime.parse(selectedDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        try
        {
            boolean isInDoctorWorkingTime = false;
            for(DoctorSchedule doctorSchedule : doctor.schedules)
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

            for(DoctorAppointment doctorAppointment : doctor.appointments) {
                if(doctorAppointment.appointmentDateTime.equals(parsedDateTime)){
                    throw new DoctorAppointmentAlreadyExists();
                }
            }

            doctor.appointments.add(new DoctorAppointment(doctor.id, patientId, parsedDateTime));

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

    /*
    public String ValidateHour(String hour){
        while(true)
        {
            if(LocalDateTimeExtensions.isLocalTime(hour))
            {
                break;
            }

            System.out.print("Wprowadzono nieprawidłowy format godziny, podaj jeszcze raz: ");
            hour = scanner.nextLine();
        }

        return hour;
    }

    public boolean DoesAppointmentExist(LocalTime time, DayOfWeek dayOfWeek, String doctorId, List<DoctorAppointment> doctorAppointmentList){
        for(DoctorAppointment appointment : doctorAppointmentList) {
            if(appointment.doctorId.equals(doctorId))
            {
                if(appointment.dayOfWeek.equals(dayOfWeek) && appointment.apointmentTime.equals(time)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean CanScheduleAppointment(String id, List<Doctor> doctorList, List<DoctorAppointment> doctorAppointmentList){
        for (Doctor doctor : doctorList) {
            if (doctor.doctorId.equals(id)) {
                if(doctor.schedules != null) {
                    DisplayDoctorAvailableAppointmentTimeByDoctor(doctor, doctorAppointmentList);
                    return true;
                } else {
                    System.out.println("Lekarz z ID " + id + " nie posiada grafiku.");
                    return false;
                }
            }
        }
        System.out.println("Lekarz z ID " + id + " nie został znaleziony.");
        return false;
    }

    private void DisplayDoctorAvailableAppointmentTimeByDoctor(Doctor doctor, List<DoctorAppointment> doctorAppointmentList){
        for (DoctorSchedule doctorSchedule : doctor.schedules) {
            if (doctorSchedule.isWorkingDay) {
                System.out.println(String.format("%s %s-%s", doctorSchedule.dayOfWeek, doctorSchedule.from.toString(), doctorSchedule.to.toString()));

                LocalTime currentTime = doctorSchedule.from;

                while(!currentTime.isAfter(doctorSchedule.to)){
                    if(!DoesAppointmentExist(currentTime, doctorSchedule.dayOfWeek, doctor.doctorId, doctorAppointmentList)) {
                        System.out.println(currentTime);
                    }
                    currentTime = currentTime.plusMinutes(15);
                }

            } else {
                System.out.println(String.format("%s WOLNE", doctorSchedule.dayOfWeek));
            }
        }
    }
    */
}
