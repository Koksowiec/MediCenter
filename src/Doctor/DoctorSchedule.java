package Doctor;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DoctorSchedule {
    public DayOfWeek dayOfWeek;
    public LocalTime from;
    public LocalTime to;
    public boolean isWorkingDay = true;

    public DoctorSchedule() {
    }

    public DoctorSchedule(DayOfWeek dayOfWeek, boolean isWorkingDay) {
        this.dayOfWeek = dayOfWeek;
        this.isWorkingDay = isWorkingDay;
    }

    public DoctorSchedule(DayOfWeek dayOfWeek, LocalTime from, LocalTime to) {
        this.dayOfWeek = dayOfWeek;
        this.from = from;
        this.to = to;
    }
}
