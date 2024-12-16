package Tests.Exceptions;

import Exceptions.DoctorAppointmentAlreadyExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoctorAppointmentAlreadyExistsTest {

    private DoctorAppointmentAlreadyExists exception;

    @BeforeEach
    void setUp() {
        exception = new DoctorAppointmentAlreadyExists();
    }

    @Test
    void GetMessage_Returns_ValidMessage(){
        exception = assertThrows(DoctorAppointmentAlreadyExists.class, () -> {
            throw new DoctorAppointmentAlreadyExists();
        });

        assertEquals("Lekarz ma już umówioną wizytę w tym terminie!", exception.getMessage());
    }
}
