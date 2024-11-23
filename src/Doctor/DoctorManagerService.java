package Doctor;

import Extensions.LocalDateTimeExtensions;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class DoctorManagerService {
    private final Scanner scanner = new Scanner(System.in);
    private final Extensions.LocalDateTimeExtensions LocalDateTimeExtensions = new LocalDateTimeExtensions();

    public String ValidateDayOfWeek(String dayOfWeek){
        while(true)
        {
            if(LocalDateTimeExtensions.isDayOfWeek(dayOfWeek))
            {
                break;
            }

            System.out.print("Wprowadzono zły dzień tygodnia, podaj jeszcze raz: ");
            dayOfWeek = scanner.nextLine();
        }

        return dayOfWeek;
    }

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
}
