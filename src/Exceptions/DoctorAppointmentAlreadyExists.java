package Exceptions;

public class DoctorAppointmentAlreadyExists extends Exception{
    @Override
    public String getMessage() {
        return "Doctor appointment already exists for selected date";
    }
}
