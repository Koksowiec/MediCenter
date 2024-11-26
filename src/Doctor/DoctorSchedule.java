package Doctor;

import java.time.LocalDate;
import java.time.LocalTime;

public class DoctorSchedule {
    public LocalDate date;
    public LocalTime from;
    public LocalTime to;

    public DoctorSchedule() {
    }

    public DoctorSchedule(LocalDate date, LocalTime from, LocalTime to) {
        this.date = date;
        this.from = from;
        this.to = to;
    }

    // TODO: DODAJ GETTERY I SETTERY
    public LocalDate getDate() {
        return date;
    }
}
