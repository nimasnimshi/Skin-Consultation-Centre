
package westminsterskinconsultationmanager;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * @author Nimas Nimshi
 */
public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    // Maximum number of doctors that can be stored in the system
    private static final int MAX_DOCTORS = 10;
    // Array of Doctor objects that is used to store the doctors in the system.
    public static Doctor[] doctors = new Doctor[MAX_DOCTORS];
    // ArrayList of Consultation objects that is used to store all the consultations.
    public static ArrayList<Consultation> consultations = new ArrayList<>();
    // Number of doctors currently stored in the system
    public static int numDoctors = 0;
    // Scanner to read input from the user
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Create a new WestminsterSkinConsultationManager object
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        // Create a new Scanner to read input from the user
        Scanner sc = new Scanner(System.in);
        // Flag to keep the menu loop running
        boolean option = false;

        // Display the menu and prompt the user for a choice until the user chooses to exit
        while (!option) {  //a while loop that runs until the variable "option" is set to true.
            System.out.println("--- Skin Consultation System ---");
            System.out.println("1 | Add new doctor");
            System.out.println("2 | Delete doctor");
            System.out.println("3 | Print Doctor");
            System.out.println("4 | Save Information");
            System.out.println("5 | Load Information");
            System.out.println("6 | Open Gui");
            System.out.println("7 | Exit");
            System.out.print("Enter your choice: ");

            // Validate input
            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        manager.addDoctor();
                        break;
                    case 2:
                        manager.deleteDoctor();
                        break;
                    case 3:
                        manager.printDoctor();
                        break;
                    case 4:
                        manager.saveInformation();
                        break;
                    case 5:
                        manager.loadInformation();
                        break;
                    case 6:
                        manager.openGui();
                        break;
                    case 7:
                        option = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }
        }

    }

    @Override  //indicating that it is an override of a method from the SkinConsultationManager interface.
    public void addDoctor() {
        if (numDoctors >= MAX_DOCTORS) {  //If the maximum number of doctors has been reached, the method throws an IllegalStateException.
            throw new IllegalStateException("Cannot add more than " + MAX_DOCTORS + " doctors");
        }
        String name, surname, DateOfBirth, mobileNumber, medicalLicenceNumber, specialisation;

        while (true) {
            try {
                while (true) {
                    System.out.println("Enter the doctor's name: ");
                    name = sc.nextLine();
                    if (name.matches("[a-zA-Z]+")) {
                        break;
                    } else {
                        System.out.println("Error! First name should only contain alphabets.");
                    }
                }

                while (true) {
                    System.out.println("Enter the doctor's surname: ");
                    surname = sc.nextLine();
                    if (surname.matches("[a-zA-Z]+")) {
                        break;
                    } else {
                        System.out.println("Error! First name should only contain alphabets.");
                    }
                }

                while (true) {
                    try {
                        System.out.println("Enter the doctor's date of birth (dd-mm-yyyy): ");
                        SimpleDateFormat days = new SimpleDateFormat("dd-MM-yyyy");
                        DateOfBirth = days.format(days.parse(sc.nextLine()));
                        break;
                    } catch (ParseException e) {
                        System.out.println("Error! Invalid date format. Please enter date in the format dd-mm-yyyy.");
                    }
                }

                while (true) {
                    System.out.println("Enter the doctor's mobile number: ");
                    mobileNumber = sc.nextLine();
                    if (mobileNumber.length() == 10) {
                        break;
                    } else {
                        System.out.println("Mobile number should contain 10 numbers.");
                    }
                }

                while (true) {
                    System.out.println("Enter the doctor's medical licence number: ");
                    medicalLicenceNumber = sc.nextLine();
                    if (medicalLicenceNumber.matches("[0-9]+") && medicalLicenceNumber.length() == 3) {
                        break;
                    } else {
                        System.out.println("Medical licence number should only contain digits and should be of length 3. Please Try Again!");
                    }
                }

                while (true) {
                    System.out.println("Enter the doctor's specialisation: ");
                    specialisation = sc.nextLine();
                    if (specialisation.matches("[a-zA-Z]+")) {  //input validation to check first name should only contain alphabets
                        break;
                    } else {
                        System.out.println("Error! Specialisation should only contain alphabets.");
                    }
                }


                Doctor doctor = new Doctor(name, surname, DateOfBirth, mobileNumber, medicalLicenceNumber, specialisation);
                doctors[numDoctors] = doctor;
                numDoctors++;
                System.out.println("Doctor added successfully.");
                break;


            } catch (InputMismatchException e) {
                System.out.println("Error! Invalid input. Please try again.");
                sc.nextLine();
            }
        }
    }

    @Override
    public void deleteDoctor() {

        System.out.println("--- List of doctors ---");

        for (int i = 0; i < numDoctors; i++) {  //The method first prints out a list of the doctors
            Doctor doctor = doctors[i];
            System.out.println((i + 1) + ". " + doctor.getName() + " " + doctor.getSurname() + "  ML No :" + doctor.getMedicalLicenseNumber());
        }
        while (true) {
            System.out.println("Enter the medical licence number of the doctor to delete: ");
            String medicalLicenceNumber = sc.nextLine();

            int index = -1;
            for (int i = 0; i < numDoctors; i++) {
                Doctor doctor = doctors[i];
                if (doctor.getMedicalLicenseNumber().equals(medicalLicenceNumber)) { //check medical license number
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Doctor with medical licence number " + medicalLicenceNumber + " not found. Please Try Again!");
                return;
            }

            Doctor[] newDoctors = new Doctor[numDoctors-1];
            System.arraycopy(doctors, 0, newDoctors, 0, index); //method to copy the elements from the doctors array to the new newDoctors array.
            System.arraycopy(doctors, index + 1, newDoctors, index, numDoctors - 1 - index);
            doctors = newDoctors;
            numDoctors--; //doctor has been removed.
            System.out.println("Doctor Successfully deleted");
            System.out.println("Total number of doctors in the centre: " + numDoctors);
            break;
        }
    }

    @Override
    public void printDoctor() {
        Arrays.sort(doctors, 0, numDoctors, Comparator.comparing(Person::getSurname));

        for (int i = 0; i < numDoctors; i++) {
            Doctor doctor = doctors[i];
            System.out.println("--------------------------------------");
            System.out.println("               Doctor :"+(i+1));
            System.out.println("--------------------------------------");
            System.out.println("First Name            : " + doctor.getName());
            System.out.println("Surname               : " + doctor.getSurname());
            System.out.println("Date of birth         : " + doctor.getDateOfBirth());
            System.out.println("Mobile number         : " + doctor.getMobileNumber());
            System.out.println("Medical licence number: " + doctor.getMedicalLicenseNumber());
            System.out.println("Specialisation        : " + doctor.getSpecialization());
            System.out.println();
            System.out.println("---------------------------------------");
            System.out.println();
        }
    }

    @Override
    public void saveInformation() {    //used to write text to a character-output stream
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("information.txt"))) {
            bw.write("--- Doctors ---");
            bw.newLine();
            bw.write(" ");
            bw.newLine();
            for (int i = 0; i < numDoctors; i++) {
                Doctor doctor = doctors[i];
                bw.write("Name: " + doctor.getName());
                bw.newLine();
                bw.write("Surname: " + doctor.getSurname());
                bw.newLine();
                bw.write("Date of birth: " + doctor.getDateOfBirth());
                bw.newLine();
                bw.write("Mobile number: " + doctor.getMobileNumber());
                bw.newLine();
                bw.write("Medical licence number: " + doctor.getMedicalLicenseNumber());
                bw.newLine();
                bw.write("Specialisation: " + doctor.getSpecialization());
                bw.newLine();
                bw.write(" ");
                bw.newLine();
                bw.write("------------------------------------");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } System.out.println("Information Saved successfully.");
        System.out.println(" ");
    }

    @Override
    public void loadInformation() {
        try (BufferedReader br = new BufferedReader(new FileReader("information.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    @Override
    public void openGui() {
        MainWindow mainWindow = new MainWindow();
        mainWindow.frame.setVisible(true);
    }
}