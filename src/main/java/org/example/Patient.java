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
