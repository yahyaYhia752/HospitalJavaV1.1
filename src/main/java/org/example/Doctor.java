package org.example;


import javax.print.Doc;

public class Doctor extends Person{
    // كلموه تكفى ..
    private int docId;
    private String field;
    private double salary;

    private static int count;

    public Doctor(String name, int id, String password, int docId, String field, double salary) {
        super(name, id, password);
        count +=1;
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
    // Doctor{name='DcAhmed', id=1009, password='0987654321', docId=1002, field='talking so much', salary=10400.0}
    public static Doctor StringToDoctor(String line) {
        String properties = line.substring(line.indexOf("{") + 1, line.indexOf("}"));

        String[] parts = properties.split(", ");

        String name = parts[0].split("=")[1].replace("'", "");

        int id = Integer.parseInt(parts[1].split("=")[1]);
        String password = parts[2].split("=")[1].replace("'", "");
        int idDoc = Integer.parseInt(parts[3].split("=")[1]);
        String field = parts[4].split("=")[1].replace("'", "");
        double salary = Double.parseDouble(parts[5].split("=")[1]);
        return new Doctor(name, id, password,idDoc,field,salary);
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
