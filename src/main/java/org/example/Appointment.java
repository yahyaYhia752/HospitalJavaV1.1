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
    // اللي فالمشروع خلصت ذا انا لحد يلمسه
    // زبطوالثلاث كلاسات حق الحسابات

}
