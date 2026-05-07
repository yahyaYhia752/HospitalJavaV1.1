package org.example;

public class Patient extends Person{
    private int idPatient;
    private String diagnosla;
    private static int count;
    private BillingAccounts payAcc;

    private StatusPaitent statsPatient;
    public Patient(String name, int id, String password, int idpat, StatusPaitent patStatus, String digansla,BillingAccounts payAcc) {
        count += 1;
        super(name, id, password);
        this.idPatient = idpat;
        this.diagnosla = digansla;
        this.statsPatient = StatusPaitent.STABLE;
        payAcc = new BillingAccounts(id,this,0,0);
    }
    // setters
    public void setIdPatient(int idPatient) {
        this.idPatient = idPatient;
    }

    public void setDiagnosla(String diagnosla) {
        this.diagnosla = diagnosla;
    }

    public void setStatsPatient(StatusPaitent statsPatient) {
        this.statsPatient = statsPatient;
    }
    // getters
    public int getIdPatient() {
        return idPatient;
    }

    public String getDiagnosla() {
        return diagnosla;
    }

    public StatusPaitent getStatsPatient() {
        return statsPatient;
    }

    public static int getCount() {
        return count;
    }

    //  Patient{name='yahya', id=10, password='123456789', idPatient=10, diagnosla='خاطره مكسور لول', statsPatient=STABLE}
    public static Patient StringToPatient(String line) {

        String properties = line.substring(line.indexOf("{") + 1, line.indexOf("}"));

        String[] parts = properties.split(", ");

        // setup properties one by one
        String name = parts[0].split("=")[1].replace("'", "");
        int id = Integer.parseInt(parts[1].split("=")[1]);
        String password = parts[2].split("=")[1].replace("'", "");
        int idPatient = Integer.parseInt(parts[3].split("=")[1]);
        String diagnosla = parts[4].split("=")[1].replace("'", "");
        String statsStr = parts[5].split("=")[1].trim();

        return new Patient(name, id, password, idPatient, StatusPaitent.valueOf(statsStr),diagnosla,);
    }
    @Override
    public String toString() {
        String personStr = super.toString();
        String allPp = personStr.substring(personStr.indexOf("{") + 1, personStr.indexOf("}"));
        return "Patient{" +
                allPp+
                ", idPatient=" + idPatient +
                ", diagnosla='" + diagnosla + '\'' +
                ", statsPatient=" + statsPatient +
                '}';

    }
}
