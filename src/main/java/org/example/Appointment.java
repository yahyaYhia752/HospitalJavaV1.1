package org.example;

public class Appointment {
    private int idAppointment;
    private String date;
    private Doctor doctor;
    private Patient patient;
    private static int count;
    public Appointment(int idAppointment, String date, Doctor doctor, Patient patient) {
        count += 1;
        this.idAppointment = idAppointment;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
    }

    public int getIdAppointment() {
        return idAppointment;
    }

    public static int getCount() {
        return count;
    }

    public void setIdAppointment(int idAppointment) {
        this.idAppointment = idAppointment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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


    public String getDetails() {
        return "Appointment{" +
                "idAppointment=" + idAppointment +
                ", date='" + date + '\'' +
                ", doctor=" + doctor.getName() +
                ", patient=" + patient.getName() +
                '}';
    }
    public void cancel(){
        this.idAppointment = 0;
        this.date = "canceled";
        this.doctor = null;
        this.patient = null;
    }
    public static Appointment StringToAppointmet(String line) {
        String properties = line.substring(line.indexOf("{") + 1, line.indexOf("}"));

        String[] parts = properties.split(", ");

        // setup properties one by one
        int idApp = Integer.parseInt(parts[0].split("=")[1]);
        String date = parts[1].split("=")[1].replace("'", "");
        String docName = parts[2].split("=")[1].replace("'", "");
        String paitName = parts[3].split("=")[1].replace("'", "");
        Doctor doctor = null;
        Patient patient = null;
        try {
            String dataDoc = DataManage.findAccount(docName);
            String dataPait = DataManage.findAccount(paitName);
            IO.println("{"+dataPait+"}, {"+dataDoc+"}");
            doctor = Doctor.StringToDoctor(dataDoc);
            patient = Patient.StringToPatient(dataPait);
        }
        catch (Exception e){
            IO.println("Some Errors While find Appointment: "+e.getMessage());
        }
        if ((doctor != null) || (patient != null)){
            return new Appointment(idApp, date,doctor, patient);
        }
        else{return null;}
    }
    @Override
    public String toString() {
        return "Appointment{" +
                "idAppointment=" + idAppointment +
                ", date='" + date + '\'' +
                ", doctor=" + doctor.getName() +
                ", patient=" + patient.getName() +
                '}';
    }
}
