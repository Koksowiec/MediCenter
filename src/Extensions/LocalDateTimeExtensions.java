package Extensions;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeExtensions {
    public boolean isLocalDateTime(String localDateTime) {
        try {
            LocalDate.parse(localDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean isLocalTime(String localTime) {
        try {
            LocalTime.parse(localTime, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean isDayOfWeek(String dayOfWeek){
        try {
            DayOfWeek.valueOf(dayOfWeek);
        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
