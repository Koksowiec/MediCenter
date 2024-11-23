import java.time.DayOfWeek;

public class DoctorSchedule {
    public DayOfWeek dayOfWeek;
    public String from;
    public String to;
    public boolean isWorkingDay = true;

    public DoctorSchedule() {
    }

    public DoctorSchedule(DayOfWeek dayOfWeek, boolean isWorkingDay) {
        this.dayOfWeek = dayOfWeek;
        this.isWorkingDay = isWorkingDay;
    }

    public DoctorSchedule(DayOfWeek dayOfWeek, String from, String to) {
        this.dayOfWeek = dayOfWeek;
        this.from = from;
        this.to = to;
    }
}
