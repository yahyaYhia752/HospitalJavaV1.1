package org.example;


public class Doctor extends Person{
    // كلموه تكفى ..
    private int docId;
    private String field;
    private double salary;

    private static int count;

    public Doctor(String name, int id, String password, int docId, String field, double salary) {
        count +=1;
        super(name, id, password);
        this.docId = docId;
        this.field = field;
        this.salary = salary;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void assingPatient(Patient p,String date){
        Appointment ap = new Appointment(
                Appointment.getCount(),
                date,
                Doctor.this,
                p);
    }
    @Override
    public String toString() {
        String personStr = super.toString();
        String allPp = personStr.substring(personStr.indexOf("{") + 1, personStr.indexOf("}"));
        return "Doctor{" +
                allPp+
                ", docId=" + docId +
                ", field='" + field + '\'' +
                ", salary=" + salary +
                '}';
    }

}
