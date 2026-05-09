package org.example;

import java.util.Scanner;

public class HospitalManager {
    private String ans;
    private static Scanner kbd = new Scanner(System.in);
    private Patient[] patients = new Patient[100];
    private Doctor[] doctors = new Doctor[100];
    private Appointment[] appointments = new Appointment[100];
    private BillingAccounts[] billingAccounts = new BillingAccounts[100];
    private static int paitentCount, doctorCount, appointmentCount, billingAccountCounts;
    // main methods
    public HospitalManager() throws Exception {
        String fileData = DataManage.getFile();
        if (fileData == null || fileData.trim().isEmpty()) return;
        String[] accounts = fileData.split("\n");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].trim().isEmpty()) continue;
            String type = accounts[i].split("\\{")[0];
            if (type.equals("Patient")) {
                patients[paitentCount++] = Patient.StringToPatient(accounts[i]);
            } else if (type.equals("Doctor")) {
                doctors[doctorCount++] = Doctor.StringToDoctor(accounts[i]);
            } else if (type.equals("Appointment")) {
                appointments[appointmentCount++] = Appointment.StringToAppointmet(accounts[i]);
            } else if (type.equals("BillingAccounts")) {
                billingAccounts[billingAccountCounts++] = BillingAccounts.StringToBillingAccounts(accounts[i]);
            }
        }
    }

    public String login() throws Exception {
        IO.println("Do you have an account? (yes/no/exit)");
        ans = kbd.nextLine();

        if (ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y")) {
            IO.println("Enter Username:");
            String username = kbd.nextLine();
            String found = DataManage.findAccount(username);
            if (found.equals("")) {
                IO.println("account not found, try again");
                return "";
            } else if (username.equals("exit")) {
                return "exit";
            }
            IO.println("Enter Password:");
            String password = kbd.nextLine();
            String account = DataManage.checkPassword(username, password);
            if (!account.equals("")) {
                IO.println("Logged in Successfully!");
                return account;
//                if (account.startsWith("Doctor")) return Doctor.StringToDoctor(account);
//                else ;
            } else {
                IO.println("wrong password!, try again");
                return "";
            }

        } else if (ans.equalsIgnoreCase("exit")) {
            return "exit";
        } else {
            IO.println("Choose account type [Doctor 'd' / Patient 'p']:");
            ans = kbd.nextLine();
            if (ans.equalsIgnoreCase("doctor") || ans.equalsIgnoreCase("d")) {
                IO.println("Create Username:");
                String username = kbd.nextLine();
                if (!DataManage.findAccount(username).equals("")){
                    IO.println("Username has been found, try another username!");
                    IO.println("Create Username:");
                    username = kbd.nextLine();
                }
                IO.println("Create Password:");
                String password = kbd.nextLine();
                String field = chooseField();
                double salary = getSalaryFromField(field);
                Doctor doc = new Doctor(username, Person.getCount(), password, Doctor.getCount(), field, salary);
                doctors[doctorCount++] = doc;
                DataManage.setDataReaded(DataManage.getFile() + doc.toString() + "\n");
                DataManage.setFile();
                IO.println("Account created! Welcome Dr. " + username);
                return doc.toString();

            } else {
                IO.println("Create Username:");
                String username = kbd.nextLine();
                if (search(username,"Patient") == null){
                    IO.println("Username has been found, try another username!");
                    IO.println("Create Username:");
                    username = kbd.nextLine();
                }
                IO.println("Create Password:");
                String password = kbd.nextLine();
                IO.println("What is your diagnosis?");
                String diagnosis = kbd.nextLine();
                IO.println("What is your current status?\n1. Stable\n2. Critical\n3. Discharged");
                StatusPaitent stats;
                switch (kbd.nextInt()) {
                    case 2: stats = StatusPaitent.CRITICAL; break;
                    case 3: stats = StatusPaitent.DISCHARGED; break;
                    default: stats = StatusPaitent.STABLE;
                }
                kbd.nextLine();
                Patient pat = new Patient(username, Person.getCount(), password, Patient.getCount(), stats, diagnosis);
                patients[paitentCount++] = pat;
                billingAccounts[billingAccountCounts++] = new BillingAccounts(billingAccountCounts, pat.getIdPatient(), 0, 0);
                DataManage.setDataReaded(DataManage.getFile() + pat.toString() + "\n");
                DataManage.setFile();
                IO.println("Account created! Welcome " + username);
                return pat.toString();
            }
        }
    }


    public void start() throws Exception {
        String user ;
        // "" means wrong username or password.
        do {
            user = login();
        } while (user.equals(""));
        Person pUser;
        if (user.equals("exit")) {
            IO.println("Program closed ");
        } else if (user.startsWith("Doctor")) {
            pUser = Doctor.StringToDoctor(user);
            showDoctorMenu((Doctor) pUser);

        } else if (user.startsWith("Patient")) {
            pUser = Patient.StringToPatient(user);
            IO.println(user);
            showPatientMenu((Patient) pUser);
        }

    }

    // menus
    public void showDoctorMenu(Doctor doc) throws Exception {
        int choice;
        do {
            IO.println("you logged in Dr."+doc.getName());
            IO.println("=== Doctor Menu ===\n" +
                    "1. View My Patients\n" +
                    "2. Search for a Patient\n" +
                    "3. View My Appointments\n" +
                    "4. Add Appointment\n" +
                    "5. Exit");
            choice = kbd.nextInt();
            kbd.nextLine();
            switch (choice) {
                case 1: viewDoctorPatients(doc); break;
                case 2: searchPatient(); break;
                case 3: viewDoctorAppointments(doc); break;
                case 4: addNewAppointment(doc); break;
                case 5: IO.println("have a good day, Dr. " + doc.getName()); break;
                default: IO.println("there is no choice with this number");
            }
        } while (choice != 5);
    }

    public void showPatientMenu(Patient pat) throws Exception {
        int choice;
        do {
            IO.println("");
            IO.println("=== Patient Menu ===\n" +
                    "1. View My Appointments\n" +
                    "2. Search for a Doctor\n" +
                    "3. View My Bill\n" +
                    "4. Exit");
            choice = kbd.nextInt();
            kbd.nextLine();
            switch (choice) {
                case 1: viewPatientAppointments(pat); break;
                case 2: searchDoctor(); break;
                case 3: viewBill(pat); break;
                case 4: IO.println("Goodbye " + pat.getName()); break;
                default: IO.println("there is no choice with this number");
            }
        } while (choice != 4);
    }

    // Doctor methods
    private String[] viewDoctorPatients(Doctor doc) {
        String[] found =new String[paitentCount];
        int count=0;
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getDoctor().getName().equals(doc.getName())) {
                found[count] = appointments[i].getPatient().getName();
                count++;
                IO.println(count +". "+found[i]);
            }
        }
        String[] finalArray = new String[count];
        if (count==0) {IO.println("No Patients found."); return null;}

        for (int i = 0; i < count; i++) {
            finalArray[i] = found[i];
        }
        return finalArray;

    }
    private void treatPatient(Doctor doc){
        String paitentnaname = kbd.nextLine();
        viewDoctorAppointments(doc);
    }
    private void searchPatient() {
        IO.println("Enter patient name:");
        String pName = kbd.nextLine();
        String[] found = search(pName,"Patient");
        for (int i=0;i<found.length;i++){
            System.out.println(i+". "+found[i]);
        }
    }

    private String[] viewDoctorAppointments(Doctor doc) {
        String[] found = new String[appointmentCount];
        int count=0;
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getDoctor().getName().equals(doc.getName())) {
                found[count] = appointments[i].getDetails();
                count++;
                IO.println(count +". "+found[i]);
            }
        }
        String[] finalArray = new String[count];
        if (count==0) {IO.println("No appointments found.");return null;}
        for (int i = 0; i < count; i++) {
            finalArray[i] = found[i];
        }
        return finalArray;
    }

    private Appointment addNewAppointment(Doctor doc) throws Exception {
        IO.println("Enter patient name:");
        String name = kbd.nextLine();
        Patient pat = null;
        for (int i = 0; i < paitentCount; i++) {
            if (patients[i].getName().toLowerCase().equals(name.toLowerCase())) {
                pat = patients[i];
                break;
            }
        }
        if (pat == null) { IO.println("Patient not found."); return null; }
        IO.println("Enter appointment date :");
        String date = getValidDate();
        Appointment ap = new Appointment(Appointment.getCount(), date, doc, pat);
        appointments[appointmentCount] = ap;
        appointmentCount++;
        DataManage.setDataReaded(DataManage.getFile() + ap.toString() + "\n");
        DataManage.setFile();
        IO.println("Appointment added!");
        return ap;
    }

    // patient methods
    private Appointment[] viewPatientAppointments(Patient pat) {
        Appointment[] found = new Appointment[appointmentCount];
        int count=0;
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getPatient().getName().equals(pat.getName())) {
                found[count] = appointments[i];
                count++;
            }
        }
        if (count == 0) {IO.println("No appointments found."); return null;}
        Appointment[] finalArray = new Appointment[count];
        for (int i = 0; i < count; i++) {
            finalArray[i] = found[i];
        }
        return finalArray;
    }

    private void searchDoctor() {
        if (doctorCount == 0) { IO.println("No doctors available."); return; }
        IO.println("Available Doctors: ");
        for (int i = 0; i < doctorCount; i++) {
            IO.println((i + 1) + ". Dr." + doctors[i].getName() + " - " + doctors[i].getField());
        }
    }

    private void viewBill(Patient pat) {
        for (int i = 0; i < billingAccountCounts; i++) {
            if (billingAccounts[i].getPatient() == pat.getIdPatient()) {
                IO.println(
                        "Your Bill: "+"\n"+
                                "Total: " + (billingAccounts[i].getTotalAmount())+"\n"+
                                "Paid: " + (billingAccounts[i].getAmountPaid())+"\n"+
                                "Balance: " + (billingAccounts[i].getBalance())
            );
                // return for not printing "no billing account found"
                return;
            }
        }
        IO.println("No billing account found.");
    }
    // needs
    private void saveUserWithString(String user) throws Exception{
        DataManage.setDataReaded(user);
        DataManage.addFile();
        DataManage.setDataReaded("");
    }
    private String[] search(String keyword,String type) {
        int count = 0;
        if (type.equals("Doctor")) {
            IO.println("Search for a Doctors...");
            String[] found = new String[doctorCount];
            //counter + set found
            for (int i = 0; i < doctorCount; i++) {
                if (doctors[i].getName().toLowerCase().contains(keyword.toLowerCase())) {
                    found[count++] = doctors[i].getName();
                }
            }
            //set finall data found
            String[] finalArray = new String[count];
            for (int i = 0; i < count; i++) {
                finalArray[i] = found[i];
            }
            return finalArray;
        }else if(type.equals("Patient")){
            IO.println("Search for a Patient...");
            String[] found = new String[paitentCount];
            // counter + set found
            for (int i = 0; i < paitentCount; i++) {
                if (patients[i].getName().toLowerCase().contains(keyword.toLowerCase())) {
                    found[count++] = patients[i].getName();
                }
            }
            // set finall data found
            String[] finalArray = new String[count];
            for (int i = 0; i < count; i++) {
                finalArray[i] = found[i];
            }
            return finalArray;
        }
        return null;
    }
    private String getValidDate() {
        int day, month, year;

        IO.println("Enter year (2026-2040):");
        while (true) {
            year = kbd.nextInt();
            if (year >= 2026 && year <= 2040) break;
            IO.println("ERROR: Year must be between 2025 and 2040!");
        }

        IO.println("Enter month (1-12):");
        while (true) {
            month = kbd.nextInt();
            if (month >= 1 && month <= 12) break;
            IO.println("ERROR: Month must be between 1 and 12!");
        }

        IO.println("Enter day (1-30):");
        while (true) {
            day = kbd.nextInt();
            if (day >= 1 && day <= 30) break;
            IO.println("ERROR: Day must be between 1 and 30!");
        }
        kbd.nextLine();
        return year + "-" + month + "-" + day;
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
        kbd.nextLine();
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
            default: return "General";
        }
    }

    public static double getSalaryFromField(String field) {
        switch (field) {
            case "Cardiology": return 15000;
            case "Neurology": return 14000;
            case "Orthopedics": return 13000;
            case "Pediatrics": return 10000;
            case "Dermatology": return 9000;
            case "Surgery": return 16000;
            case "Dentistry": return 8000;
            case "Ophthalmology": return 10000;
            case "Psychiatry": return 9000;
            default: return 7000;
        }
    }
}