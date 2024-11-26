package Exceptions;

public class DoctorDoesntWorkOnThisDate extends Exception{
    @Override
    public String getMessage() {
        return "Lekarz nie pracuje o tej godzinie.";
    }
}
