package org.example;

public class Patient extends Person{
    private int idPatient;
    private String diagnosla;


    private statusPaitent statsPatient;
    public Patient(String name, int id, String password,int idpat,statusPaitent patStatus,String digansla) {
        super(name, id, password);
        this.idPatient = idpat;
        this.diagnosla = digansla;
        this.statsPatient = statusPaitent.STABLE;

    }
    // setters
    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public void setDiagnosla(String diagnosla) {
        this.diagnosla = diagnosla;
    }

    public void setStatsPatient(statusPaitent statsPatient) {
        this.statsPatient = statsPatient;
    }
    // getters
    public int getIdPatient() {
        return idPatient;
    }

    public String getDiagnosla() {
        return diagnosla;
    }

    public statusPaitent getStatsPatient() {
        return statsPatient;
    }
    @Override
    public String toString() {
        return "Patient{" +
                "idPatient=" + idPatient +
                ", diagnosla='" + diagnosla + '\'' +
                ", statsPatient=" + statsPatient +
                ", name=" + getName() +
                ", id=" + getId() +
                ", Password=" + getPassword() +

                '}';

    }
}
