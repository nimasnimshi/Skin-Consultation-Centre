/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package westminsterskinconsultationmanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Nimas Nimshi
 */
public class Doctor extends Person {
    private String medicalLicenseNumber;
    private String specialization;
    private Object availability;

    public Doctor(String name, String surname, String dateOfBirth, String mobileNumber, String medicalLicenseNumber, String specialization) {
        super(name, surname, dateOfBirth, mobileNumber);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialization = specialization;
        this.availability = new ArrayList<>();
    }

    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<LocalDateTime> getAvailability() {
        return (List<LocalDateTime>) availability;
    }

    public void setAvailability(List<LocalDateTime> availability) {
        this.availability = availability;
    }
}





