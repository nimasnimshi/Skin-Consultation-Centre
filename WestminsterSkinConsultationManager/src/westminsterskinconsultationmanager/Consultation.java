/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package westminsterskinconsultationmanager;

/**
 *
 * @author Nimas Nimshi
 */
import java.time.LocalDateTime;

public class Consultation {
    private LocalDateTime dateTime;
    private double cost;
    private String notes;
    private Doctor doctor;
    private Patient patient;

    public Consultation(LocalDateTime dateTime, double cost, Doctor doctor, Patient patient) {
        this.dateTime = dateTime;
        this.cost = cost;
        this.notes = "";
        this.doctor = doctor;
        this.patient = patient;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
































/*
public class Consultation {
    private Doctor doctor;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String mobileNumber;
    private String id;
    private int cost;
    private String notes;
    private Date dateTime;

    public Consultation() {
        this.doctor = null;
        this.name = "";
        this.surname = "";
        this.dateOfBirth = null;
        this.mobileNumber = "";
        this.id = "";
        this.cost = 0;
        this.notes = "";
        this.dateTime = null;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}

   /* public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    /*public boolean isAvailable(String date, String time) {
        // Convert the input date and time to LocalDateTime
        LocalDate localDate = LocalDate.parse(date);
        LocalTime localTime = LocalTime.parse(time);
        LocalDateTime consultationDateTime = LocalDateTime.of(localDate, localTime);

        // Iterate over the doctor's appointments
        for (Appointment appointment : doctor.getAppointments()) {
            // Check if the input date and time match any of the doctor's appointments
            if (appointment.getDateTime().isEqual(consultationDateTime)) {
                return false;
            }
        }

        // If none of the appointments match, the doctor is available
        return true;*/
   // }



/*public class Consultation {

    static String length;
    private Patient patient;
    private Doctor doctor;
    private Date dateTime;
    private double cost;
    private String notes;

    public Consultation() { //,
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
        this.cost = cost;
        this.notes = notes;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }


    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}*/

