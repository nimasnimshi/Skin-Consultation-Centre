package westminsterskinconsultationmanager;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import static westminsterskinconsultationmanager.WestminsterSkinConsultationManager.*;

public class MainWindow extends JFrame implements ActionListener {
    //instance variables
    JFrame frame;
    JButton buttons,buttonExit,buttonBookConsultation,buttonview;
    JLabel label;
    JTable table;


    public MainWindow() {
        initialize();
    }

    public void initialize() {
        // Create the frame and set its properties
        frame = new JFrame("Skin Consultation Centre");
        frame.setSize(1200,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Create and add the label
        label = new JLabel("Skin Consultation Centre");
        label.setBounds(454,10,400,25);
        label.setFont(new Font("Arial",Font.PLAIN,20));
        frame.getContentPane().add(label);

        // Create and add the table
        table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(1120, 10*table.getRowHeight()));
        frame.getContentPane().add(new JScrollPane(table));

        // Create and add the scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 60, 1120, 300);
        frame.getContentPane().add(scrollPane);

        // Create and add the button1
        buttons = new JButton("Sort Alphabetically");
        buttons.addActionListener(this);
        buttons.setBounds(30,400,242,40);
        frame.getContentPane().add(buttons);

        buttonview = new JButton("View Consultation");
        buttonview.addActionListener(this);
        buttonview.setBounds(616,400,243,40);
        frame.getContentPane().add(buttonview);

        // Create and add the buttonQuit
        buttonExit = new JButton("Exit Program");
        buttonExit.addActionListener(this);
        buttonExit.setBounds(908,400,242,40);
        frame.getContentPane().add(buttonExit);

        // Create and add the button for booking a consultation
        buttonBookConsultation = new JButton("Book Consultation");
        buttonBookConsultation.addActionListener(this);
        buttonBookConsultation.setBounds(321, 400, 243, 40);
        frame.getContentPane().add(buttonBookConsultation);

        tableDisplay();
    }
    void tableDisplay() {
        ArrayList<Object[]> tableList = new ArrayList<Object[]>();
        for (int i = 0; i < 10; i++) {
            if (i < doctors.length && doctors[i] != null) {
                tableList.add(new Object[]{
                        doctors[i].getMedicalLicenseNumber(),
                        doctors[i].getName(),
                        doctors[i].getSurname(),
                        doctors[i].getDateOfBirth(),
                        doctors[i].getMobileNumber(),
                        doctors[i].getSpecialization()
                });
            }else {
                tableList.add(new Object[]{
                });
            }
        }
        table.setModel(new DefaultTableModel(
                tableList.toArray(new Object[tableList.size()][]),
                new String[] {" Medical Licence Number", " First Name", " Surname"," Date Of Birth"," Mobile Number"," Specialization"}));
    }



    public void actionPerformed(ActionEvent e) {


        String action = ((JButton) e.getSource()).getActionCommand();
        if(action.equals("Sort Alphabetically")) {
            Arrays.sort(doctors, 0, numDoctors, Comparator.comparing(Person::getName));
            tableDisplay();
        }

        if(action.equals("Book Consultation")) {
            new AddConsultation();
        }

        if(action.equals("View Consultation")) {
            ViewConsultations viewConsultations = new ViewConsultations();
            viewConsultations.setVisible(true);
        }

        if (action.equals("Exit Program")) {
            System.out.println();
            System.out.println("Successfully exited the program.");
            System.exit(0);
        }

    }
}