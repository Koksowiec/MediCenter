package Tests.Exceptions;

import Exceptions.DoctorDoesntWorkOnThisDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoctorDoesntWorkOnThisDateTest {
    private DoctorDoesntWorkOnThisDate exception;

    @BeforeEach
    void setUp() {
        exception = new DoctorDoesntWorkOnThisDate();
    }

    @Test
    void GetMessage_Returns_ValidMessage(){
        exception = assertThrows(DoctorDoesntWorkOnThisDate.class, () -> {
            throw new DoctorDoesntWorkOnThisDate();
        });

        assertEquals("Lekarz nie pracuje o tej godzinie.", exception.getMessage());
    }
}
