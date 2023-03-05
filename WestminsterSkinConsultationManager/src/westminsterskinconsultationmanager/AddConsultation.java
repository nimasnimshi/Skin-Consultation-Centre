package westminsterskinconsultationmanager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.*;
import java.time.LocalDateTime;

import static westminsterskinconsultationmanager.WestminsterSkinConsultationManager.doctors;

public class AddConsultation extends JFrame implements ActionListener {

    JLabel labelDoctor, labelDate, labelTime, labelPatientName, labelPatientSurname, labelPatientDOB, labelPatientMobile, labelPatientID, labelCost;
    JComboBox<String> comboBoxDoctor;
    JTextField fieldDate, fieldTime, fieldPatientName, fieldPatientSurname, fieldPatientDOB, fieldPatientMobile, fieldPatientID, fieldCost;
    JButton buttonCheckAvailability, buttonBookConsultation;

    public AddConsultation() {
        // Create and set the properties of the frame
        setTitle("Consultation Booking");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Initialize the components and add them to the frame
        labelDoctor = new JLabel("Select Doctor:");
        labelDoctor.setBounds(20, 20, 100, 25);
        add(labelDoctor);

        //<>(WestminsterSkinConsultationManager.doctors);
        comboBoxDoctor = new JComboBox();
        comboBoxDoctor.setBounds(180, 20, 200, 25);
        for (Doctor doctor : doctors) {
            if (doctor != null) {
                comboBoxDoctor.addItem(doctor.getName() + " " + doctor.getSurname());
            }
        }
        add(comboBoxDoctor);

        labelDate = new JLabel("Date [YYYY-MM-DD]:");
        labelDate.setBounds(20, 60, 100, 25);
        add(labelDate);

        fieldDate = new JTextField();
        fieldDate.setBounds(180, 60, 200, 25);
        add(fieldDate);

        labelTime = new JLabel("Time [HH:MM:SS]:");
        labelTime.setBounds(20, 100, 100, 25);
        add(labelTime);

        fieldTime = new JTextField();
        fieldTime.setBounds(180, 100, 200, 25);
        add(fieldTime);

        buttonCheckAvailability = new JButton("Check Availability");
        buttonCheckAvailability.setBounds(180, 140, 200, 25);
        buttonCheckAvailability.addActionListener(this);
        add(buttonCheckAvailability);

        //Initializing the rest of the components and adding them to the frame
        labelPatientName = new JLabel("Patient's Name:");
        labelPatientName.setBounds(20, 180, 100, 25);
        add(labelPatientName);

        fieldPatientName = new JTextField();
        fieldPatientName.setBounds(180, 180, 200, 25);
        add(fieldPatientName);

        labelPatientSurname = new JLabel("Patient's Surname:");
        labelPatientSurname.setBounds(20, 220, 100, 25);
        add(labelPatientSurname);

        fieldPatientSurname = new JTextField();
        fieldPatientSurname.setBounds(180, 220, 200, 25);
        add(fieldPatientSurname);

        labelPatientDOB = new JLabel("Patient's Date of Birth:");
        labelPatientDOB.setBounds(20, 260, 150, 25);
        add(labelPatientDOB);

        fieldPatientDOB = new JTextField();
        fieldPatientDOB.setBounds(180, 260, 200, 25);
        add(fieldPatientDOB);

        labelPatientMobile = new JLabel("Patient's Mobile Number :");
        labelPatientMobile.setBounds(20, 300, 150, 25);
        add(labelPatientMobile);

        fieldPatientMobile = new JTextField();
        fieldPatientMobile.setBounds(180, 300, 200, 25);
        add(fieldPatientMobile);

        labelPatientID = new JLabel("Patient's ID:");
        labelPatientID.setBounds(20, 340, 150, 25);
        add(labelPatientID);

        fieldPatientID = new JTextField();
        fieldPatientID.setBounds(180, 340, 200, 25);
        add(fieldPatientID);

        buttonBookConsultation = new JButton("Book Consultation");
        buttonBookConsultation.setBounds(20, 380, 360, 25);
        buttonBookConsultation.addActionListener(this);
        add(buttonBookConsultation);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonCheckAvailability) {
            String date = fieldDate.getText();
            String time = fieldTime.getText();
            int selectedIndex = comboBoxDoctor.getSelectedIndex();
            Doctor selectedDoctor = WestminsterSkinConsultationManager.doctors[selectedIndex];
            if(validateDate(date) && validateTime(time)){
                boolean available = checkAvailability(selectedDoctor, date, time);
                if(available){
                    JOptionPane.showMessageDialog(this, "The selected doctor is available at the selected date and time.");
                }else{
                    JOptionPane.showMessageDialog(this, "The selected doctor is not available at the selected date and time.");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Please enter a valid date and time.");
            }
        } else if (e.getSource() == buttonBookConsultation) {
            String date = fieldDate.getText();
            String time = fieldTime.getText();
            Doctor selectedDoctor = WestminsterSkinConsultationManager.doctors[comboBoxDoctor.getSelectedIndex()];
            if(validateDate(date) && validateTime(time) && !fieldPatientName.getText().isEmpty() && !fieldPatientSurname.getText().isEmpty() && !fieldPatientDOB.getText().isEmpty() && !fieldPatientMobile.getText().isEmpty() && !fieldPatientID.getText().isEmpty()){
                boolean available = checkAvailability(selectedDoctor, date, time);
                if (available) {
                    String name = fieldPatientName.getText();
                    String surname = fieldPatientSurname.getText();
                    String dob = fieldPatientDOB.getText();
                    String mobile = fieldPatientMobile.getText();
                    String id = fieldPatientID.getText();
                    Patient patient = new Patient(name, surname, dob, mobile, id);

                    LocalDateTime dateTime = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
                    Consultation consultation;

                    // Ask user for the duration of the consultation
                    String durationString = JOptionPane.showInputDialog(this, "Enter the duration of the consultation in hours:");
                    double duration = Double.parseDouble(durationString);
                    double cost;
                    if (patientIsNew(patient)) {
                        cost = 15 * duration;
                    } else {
                        cost = 25 * duration;
                    }

                    consultation = new Consultation(dateTime, cost, selectedDoctor, patient);
                    WestminsterSkinConsultationManager.consultations.add(consultation);
                    JOptionPane.showMessageDialog(this, "Consultation booked successfully!");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "The selected doctor is not available at the selected date and time or please fill all the fields.");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Please enter a valid date, time, patient's information and consultation cost.");
            }
        }
    }

    private boolean checkAvailability(Doctor selectedDoctor, String date, String time) {
        LocalDateTime selectedDateTime = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
        for (Consultation consultation : WestminsterSkinConsultationManager.consultations) {
            if (consultation.getDoctor().equals(selectedDoctor) && consultation.getDateTime().equals(selectedDateTime)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validateTime(String time) {
        try {
            LocalTime.parse(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean patientIsNew(Patient patient) {
        for (Consultation consultation : WestminsterSkinConsultationManager.consultations) {
            if (consultation.getPatient().equals(patient)) {
                return false;
            }
        }
        return true;
    }
}
















































/*
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

public class AddConsultation extends JFrame implements ActionListener {
    // Number of consultations currently stored in the system
    int numConsultation = 0;
    JFrame frame;
    JButton buttonBookConsultation;
    JTextField textFieldName, textFieldSurname, textFieldDateOfBirth, textFieldMobileNumber, textFieldId, textFieldConsultationCost, textFieldNotes;
    JComboBox comboBoxDoctor;
    JLabel labelName, labelSurname, labelDateOfBirth, labelMobileNumber, labelId, labelDoctor, labelConsultationCost, labelNotes;
    JPanel panel;

    public AddConsultation() {
        //create and initialize the frame
        this.setTitle("Add Consultation");
        this.setBounds(50, 50, 1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the layout for the frame
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);

        // Create constraints for positioning elements in the layout
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 5, 10, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;

        // Create and add the labels and text fields for consultation form
        labelName = new JLabel("Name:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        this.add(labelName, constraints);
        textFieldName = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 0;
        this.add(textFieldName, constraints);

        labelSurname = new JLabel("Surname:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(labelSurname, constraints);
        textFieldSurname = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(textFieldSurname, constraints);

        labelDateOfBirth = new JLabel("Date of Birth:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        this.add(labelDateOfBirth, constraints);
        textFieldDateOfBirth = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 2;
        this.add(textFieldDateOfBirth, constraints);

        labelMobileNumber = new JLabel("Mobile Number:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        this.add(labelMobileNumber, constraints);
        textFieldMobileNumber = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 3;
        this.add(textFieldMobileNumber, constraints);

        labelId = new JLabel("ID:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        this.add(labelId, constraints);
        textFieldId = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 4;
        this.add(textFieldId, constraints);

        labelDoctor = new JLabel("Doctor:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        this.add(labelDoctor, constraints);
        comboBoxDoctor = new JComboBox();
        for (Doctor doctor : WestminsterSkinConsultationManager.doctors) {
            if (doctor != null) {
                comboBoxDoctor.addItem(doctor.getName() + " " + doctor.getSurname());
            }
        }
        constraints.gridx = 1;
        constraints.gridy = 5;
        this.add(comboBoxDoctor, constraints);

        labelConsultationCost = new JLabel("Consultation Cost:");
        constraints.gridx = 0;
        constraints.gridy = 6;
        this.add(labelConsultationCost, constraints);
        textFieldConsultationCost = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 6;
        this.add(textFieldConsultationCost, constraints);

        labelNotes = new JLabel("Notes:");
        constraints.gridx = 0;
        constraints.gridy = 7;
        this.add(labelNotes, constraints);
        textFieldNotes = new JTextField(30);
        constraints.gridx = 1;
        constraints.gridy = 7;
        this.add(textFieldNotes, constraints);

        buttonBookConsultation = new JButton("Book Consultation");
        constraints.gridx = 1;
        constraints.gridy = 8;
        this.add(buttonBookConsultation, constraints);
        buttonBookConsultation.addActionListener(this);

        // set the frame visible
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the selected doctor
        int doctorIndex = comboBoxDoctor.getSelectedIndex();
        Doctor selectedDoctor = WestminsterSkinConsultationManager.doctors[doctorIndex];

        // Check if the doctor is available
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date today = calendar.getTime();
        if (selectedDoctor.isAvailable(today)) {
            // Doctor is available, create a new consultation
            Consultation consultation = new Consultation();
            consultation.setDoctor(selectedDoctor);
            // Set patient information
            consultation.setName(textFieldName.getText());
            consultation.setSurname(textFieldSurname.getText());
            try {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date dateOfBirth = dateFormat.parse(textFieldDateOfBirth.getText());
                consultation.setDateOfBirth(dateOfBirth);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            consultation.setMobileNumber(textFieldMobileNumber.getText());
            consultation.setId(textFieldId.getText());
            consultation.setCost((int) Double.parseDouble(textFieldConsultationCost.getText()));
            consultation.setNotes(textFieldNotes.getText());
            // add consultation to the system
            Consultation[] consultations = WestminsterSkinConsultationManager.consultations;
            // update the number of consultations
            numConsultation++;
            // show a message to confirm that the consultation was added
            JOptionPane.showMessageDialog(this, "Consultation added successfully!");
        } else {
            // Doctor is not available
            JOptionPane.showMessageDialog(this, "Sorry, the selected doctor is not available today.");
        }
    }
}




















/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static westminsterskinconsultationmanager.Encryptor.encrypt;
import static westminsterskinconsultationmanager.WestminsterSkinConsultationManager.*;
import static westminsterskinconsultationmanager.WestminsterSkinConsultationManager.doctors;


public class AddConsultation extends JFrame implements ActionListener {
    // Number of consultations currently stored in the system
    int numConsultation = 0;
    JFrame frame;
    JButton button1,buttonQuit,buttonBookConsultation;
    JLabel label1;
    JTable table1;
    JTextField textFieldName, textFieldSurname, textFieldDateOfBirth, textFieldMobileNumber, textFieldId, textFieldConsultationCost, textFieldNotes;
    JComboBox comboBoxDoctor;
    JLabel labelName, labelSurname, labelDateOfBirth, labelMobileNumber, labelId, labelDoctor, labelConsultationCost, labelNotes;
    JPanel panel;

    public AddConsultation() {
        //create and initialize the frame
        frame = new JFrame("Add Consultation");
        frame.setBounds(50,50,1200,1600);
        frame.setTitle("Add Consultation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add the panel for consultation form
        panel = new JPanel();
        panel.setBounds(30, 480, 1120, 100);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        // Create and add the labels and text fields for consultation form
        labelName = new JLabel("Name:");
        panel.add(labelName);
        textFieldName = new JTextField(15);
        panel.add(textFieldName);

        labelSurname = new JLabel("Surname:");
        panel.add(labelSurname);
        textFieldSurname = new JTextField(15);
        panel.add(textFieldSurname);

        labelDateOfBirth = new JLabel("Date of Birth:");
        panel.add(labelDateOfBirth);
        textFieldDateOfBirth = new JTextField(15);
        panel.add(textFieldDateOfBirth);

        labelMobileNumber = new JLabel("Mobile Number:");
        panel.add(labelMobileNumber);
        textFieldMobileNumber = new JTextField(15);
        panel.add(textFieldMobileNumber);

        labelId = new JLabel("ID:");
        panel.add(labelId);
        textFieldId = new JTextField(15);
        panel.add(textFieldId);

        labelDoctor = new JLabel("Doctor:");
        panel.add(labelDoctor);
        comboBoxDoctor = new JComboBox();
        for (Doctor doctor : WestminsterSkinConsultationManager.doctors) {
            if (doctor != null) {
                comboBoxDoctor.addItem(doctor.getName() + " " + doctor.getSurname());
            }
        }
        panel.add(comboBoxDoctor);

        labelConsultationCost = new JLabel("Consultation Cost:");
        panel.add(labelConsultationCost);
        textFieldConsultationCost = new JTextField(15);
        panel.add(textFieldConsultationCost);

        labelNotes = new JLabel("Notes:");
        panel.add(labelNotes);
        textFieldNotes = new JTextField(30);
        panel.add(textFieldNotes);
        frame.setVisible(true);
        this.setVisible(true);

    }
        @Override
        public void actionPerformed(ActionEvent e) {

            // Get the selected doctor
            int doctorIndex = comboBoxDoctor.getSelectedIndex();
            Doctor selectedDoctor = doctors[doctorIndex];
            // Check if the doctor is available
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date today = calendar.getTime();
            if (selectedDoctor.isAvailable(today)) {
                // Doctor is available, create a new consultation
                Consultation consultation = new Consultation();
                consultation.setDoctor(selectedDoctor);
                // Set patient information
                consultation.setName(textFieldName.getText());
                consultation.setSurname(textFieldSurname.getText());
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date date = dateFormat.parse(textFieldDateOfBirth.getText());
                    consultation.setDateOfBirth(date);
                } catch (ParseException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultation.setMobileNumber(textFieldMobileNumber.getText());
                consultation.setId(textFieldId.getText());
                // Set consultation cost
                int cost = Integer.parseInt(textFieldConsultationCost.getText());
                consultation.setCost(cost);
                // Encrypt and set notes
                String notes = encrypt(textFieldNotes.getText());
                consultation.setNotes(notes);
                // Add the consultation to the system
                consultations[numConsultation] = consultation;
                numConsultation++;
                // Clear the consultation form
                textFieldName.setText("");
                textFieldSurname.setText("");
                textFieldDateOfBirth.setText("");
                textFieldMobileNumber.setText("");
                textFieldId.setText("");
                textFieldConsultationCost.setText("");
                textFieldNotes.setText("");
            } else {
                // Doctor is not available, find a different doctor who is available
                Consultation[] consultations = new Consultation[100];
                Random random = new Random();
                int randomDoctorIndex = random.nextInt(numDoctors);
                Doctor randomDoctor = doctors[randomDoctorIndex];
                while (!randomDoctor.isAvailable(today)) {
                    randomDoctorIndex = random.nextInt(numDoctors);
                    randomDoctor = doctors[randomDoctorIndex];
                }
                // Set the selected doctor to the available doctor
                comboBoxDoctor.setSelectedIndex(randomDoctorIndex);
            }
        }

}*/

