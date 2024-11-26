package Exceptions;

public class DoctorAppointmentAlreadyExists extends Exception{
    @Override
    public String getMessage() {
        return "Lekarz ma już umówioną wizytę w tym terminie!";
    }
}
