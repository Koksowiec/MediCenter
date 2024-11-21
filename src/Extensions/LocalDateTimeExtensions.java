package Extensions;

import java.time.LocalDate;
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
}
