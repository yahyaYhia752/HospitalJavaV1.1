package org.example;
import javax.print.Doc;
import java.io.IOError;
import java.rmi.server.ExportException;
import java.util.Scanner;
public class HospitalManager {
    private String ans;
    private static Scanner kbd = new Scanner(System.in);
    private Patient[] patients = new Patient[100];
    private Doctor[] doctors = new Doctor[100];
    private Appointment[] appointments = new Appointment[100];
    private BillingAccounts[] billingAccounts = new BillingAccounts[100];
    private static int paitentCount,doctorCount,appointmentCount,billingAccountCounts;
    public HospitalManager() throws Exception {
        // Start Loading All Acounts
        String[] accounts = DataManage.getFile().split("\n");
        for (int i=0;i<accounts.length;i++){
            if (accounts[i].split("\\{")[0].equals("Paitent")){
                patients[paitentCount] = Patient.StringToPatient(accounts[i]);
                paitentCount++;
            } else if (accounts[i].split("\\{")[0].equals("Doctor")) {
                doctors[doctorCount] = Doctor.StringToDoctor(accounts[i]);
                doctorCount++;
            } else if (accounts[i].split("\\{")[0].equals("Appointment")) {
                appointments[appointmentCount] = Appointment.StringToAppointmet(accounts[i]);
                appointmentCount++;
            }else {
                billingAccounts[billingAccountCounts] = BillingAccounts.StringToBillingAccounts(accounts[i]);
                billingAccountCounts++;
            }
        }
    }

    public String login() throws Exception{
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

        } else if (ans.toLowerCase().equals("exit")) {
            return "";
        } else{
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
                        stats= StatusPaitent.CRITICAL;break;
                    case 3:
                        stats= StatusPaitent.DISCHARGED;break;
                    default:
                        stats = StatusPaitent.STABLE;break;
                }
                patients[patients.length+1] = new Patient(username,Person.getCount(),password,Patient.getCount(),stats,type);
                return patients[patients.length].toString();
            }
        }
        return "";
    }

    public void start() throws Exception {
        String accountStr = login();
        String typeAccount = accountStr.split("\\{")[0];
        Person userAccount;
        if (typeAccount.equals("Person")){
            userAccount = Patient.StringToPatient(accountStr);
        }else{
            userAccount = Patient.StringToPatient(accountStr);
        }
        if (!accountStr.equals("")) {
            if (userAccount instanceof Doctor){
            IO.println("Welcome! Dr." + userAccount.getName());
                IO.println("=== Doctor Menu ===\n" +
                        "1. View My Patients\n" +
                        "2. Search for a Patient\n" +
                        "3. View My Appointments\n" +
                        "4. Add Appointment\n" +
                        "5. Exit");

            }
            else {
                IO.println("Welcome! " + userAccount.getName());
                IO.println("=== Patient Menu ===\n" +
                        "1. View My Appointments\n" +
                        "2. Book Appointment with Doctor\n" +
                        "3. Search for a Doctor\n" +
                        "4. View My Bill\n" +
                        "5. Exit");
            }
        }
    }
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
}
