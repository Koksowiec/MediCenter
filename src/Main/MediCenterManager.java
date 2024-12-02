package Main;

import Doctor.Doctor;
import Patient.Patient;

import java.util.ArrayList;
import java.util.List;

public class MediCenterManager {
    ///<summary>
    /// List contains all existing doctors. This could be redone with database access.
    /// </summary>
    private static List<Doctor> DoctorList = new ArrayList<>();

    ///<summary>
    /// List contains all existing patients. This could be redone with database access.
    /// </summary>
    public static List<Patient> PatientList = new ArrayList<>();

    public static List<Doctor> getDoctorList() {
        return DoctorList;
    }

    public static void addToDoctorList(Doctor doctor) {
        DoctorList.add(doctor);
    }

    public static List<Patient> getPatientList() {
        return PatientList;
    }

    public static void addToPatientList(Patient patient) {
        PatientList.add(patient);
    }

    ///<summary>
    /// Responsible for starting the medi center.
    /// </summary>
    public void StartMediCenter() {
        MainMenu mainMenu = new MainMenu();

        mainMenu.DisplayMainMenu();

        System.out.println("Wyłączono MediCenter...");
    }
}
