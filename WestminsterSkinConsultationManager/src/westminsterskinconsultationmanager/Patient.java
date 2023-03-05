/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package westminsterskinconsultationmanager;

/**
 *
 * @author Nimas Nimshi
 */
public class Patient extends Person {
    private String patientId;

    public Patient(String name, String surname, String dateOfBirth, String mobileNumber, String patientId) {
        super(name, surname, dateOfBirth, mobileNumber);
        this.patientId = patientId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

}

