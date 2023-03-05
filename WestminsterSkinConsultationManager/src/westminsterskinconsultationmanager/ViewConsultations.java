package westminsterskinconsultationmanager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ViewConsultations extends JFrame {
    private JTable tableConsultations;
    private JScrollPane scrollPane;
    private JButton buttonBack;

    public ViewConsultations() {
        // Create and set the properties of the frame
        setTitle("View Consultations");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the table model and set the data
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[] { "Doctor", "Date", "Patient", "Cost" });
        for (Consultation consultation : WestminsterSkinConsultationManager.consultations) {
            if (consultation != null) {
                model.addRow(new Object[] {
                        consultation.getDoctor().getName() + " " + consultation.getDoctor().getSurname(),
                        consultation.getDateTime(),
                        consultation.getPatient().getName() + " " + consultation.getPatient().getSurname(),
                        consultation.getCost()
                });
            }
        }

        // Initialize the table and add it to the frame
        tableConsultations = new JTable(model);
        scrollPane = new JScrollPane(tableConsultations);
        add(scrollPane, BorderLayout.CENTER);

        // Initialize the "Back" button and add it to the frame
        buttonBack = new JButton("Back");
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to return to the previous menu
                if (e.getSource() == buttonBack) {
                    new MainWindow().setVisible(true);
                    ViewConsultations.this.dispose();
                }
            }
        });
        add(buttonBack, BorderLayout.SOUTH);
    }
}





