package org.example;
import javax.print.Doc;
import java.io.IOError;
import java.util.Scanner;
public class HospitalManager {
    private static Scanner kbd = new Scanner(System.in);
    private Patient[] patients;
    private Doctor[] doctors;
    private Appointment[] appointments;

    public HospitalManager(Patient[] patients, Doctor[] doctors, Appointment[] appointments) {
        this.patients = patients;
        this.doctors = doctors;
        this.appointments = appointments;
    }
    public
    public static String chooseField() {
        IO.println("Choose field:\n" +
                "1. Cardiology\n" +
                "2. Neurology\n" +
                "3. Orthopedics\n" +
                "4. Pediatrics\n" +
                "5. Dermatology\n" +
                "6. Surgery\n" +
                "7. Dentistry\n" +
                "8. Ophthalmology\n" +
                "9. Psychiatry\n" +
                "10. General");

        int choice = kbd.nextInt();

        switch (choice) {
            case 1: return "Cardiology";
            case 2: return "Neurology";
            case 3: return "Orthopedics";
            case 4: return "Pediatrics";
            case 5: return "Dermatology";
            case 6: return "Surgery";
            case 7: return "Dentistry";
            case 8: return "Ophthalmology";
            case 9: return "Psychiatry";
            case 10: return "Others";
            default: return "Others";
        }
    }
    public static double getSalaryFromField(String field) {
        switch (field) {
            case "Cardiology":return 15000;
            case "Neurology":return 14000;
            case "Orthopedics":return 13000;
            case "Pediatrics":return 10000;
            case "Dermatology":return 9000;
            case "Surgery":return 16000;
            case "Dentistry":return 8000;
            case "Ophthalmology":return 10000;
            case "Psychiatry":return 9000;
            case "Others":return 7000;
            default:return 5000;
        }
    }
    public String login() throws Exception{
        String ans;
        IO.println("Do you have account (yes/no):");
        ans = kbd.nextLine();
        if ((ans.toLowerCase().equals("yes")) || (ans.toLowerCase().equals("y"))){
            String password;
            String nameAccount;
            IO.println("Enter Username:");
            ans = kbd.nextLine();
            if (DataManage.findAccount(ans).equals("")){
                nameAccount = ans;
                IO.println("Enter Password:");
                ans = kbd.nextLine();
                password = ans;
                String ownAccount = DataManage.checkPassword(nameAccount,password);
                if (ownAccount.equals("")){
                    IO.println("Logged in Successfully! ");
                    return ownAccount;
                }
            }

        }
        else{

            IO.println("choose a Type of your account[Doctor'd'/Paitent'p']: ");
            ans = kbd.nextLine();
            if (ans.toLowerCase().equals("doctor")||ans.toLowerCase().equals("d")){
                String username;
                String password;
                String field;
                IO.println("Create Username: ");
                username = kbd.nextLine();
                IO.println("Create password: ");
                password = kbd.nextLine();
                field = chooseField();
                IO.println("Make a new Account for you Dr,"+username+". Please wait");
                doctors[doctors.length+1] = new Doctor(
                        username,
                        Person.getCount(),
                        password,
                        Doctor.
                        getCount(),
                        field,
                        getSalaryFromField(field));
                return doctors[doctors.length].toString();
            }
            // paitent
            else {
                String username,password,type;
                IO.println("Create Username: ");
                username = kbd.nextLine();
                IO.println("Create password: ");
                password = kbd.nextLine();
                IO.println("What is your diagnosis?");
                type = kbd.nextLine();
                StatusPaitent stats;
                IO.println("\"what is your current status?\"\n" +
                        "1. Stable\n" +
                        "2. Critical\n" +
                        "3. Discharged");
                switch (kbd.nextInt()){
                    case 2:
                        stats= StatusPaitent.CRITICAL;
                    case 3:
                        stats= StatusPaitent.DISCHARGED;
                    default:
                        stats = StatusPaitent.STABLE;
                }
                patients[patients.length+1] = new Patient(username,Person.getCount(),password,Patient.getCount(),stats,type);
                return patients[patients.length].toString();
            }
        }
        return "";
    }
}
