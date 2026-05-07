package org.example;

public class BillingAccounts {
    private int accountId;
    private Patient patient;
    private double totalAmount;
    private double amountPaid;
    // Constructor
    public BillingAccounts(int accountId, Patient patient, double totalAmount, double amountPaid) {
        this.accountId = accountId;
        this.patient = patient;
        this.totalAmount = totalAmount;
        this.amountPaid = amountPaid;
    }
    // Setters and Getters
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }
    // IllegalArgumentException = اخطاء
    public void addCharge(double amount) throws IllegalArgumentException {
        if (amount > 0) {
            totalAmount += amount;
        } else {
            throw new IllegalArgumentException("ERROR: you cant put negative Number!");
        }
    }

    public void makePayment(double amount) throws IllegalArgumentException {
        if (amount > 0) {
            totalAmount -= amount;
            amountPaid += amount;
        } else {
            throw new IllegalArgumentException("ERROR: you cant put negative Number!");
        }
    }

    public double getBalance(){
        return totalAmount - amountPaid;
    }
    // toString

    public static BillingAccounts StringToBillingAccounts(String line) {

        String properties = line.substring(line.indexOf("{") + 1, line.indexOf("}"));

        String[] parts = properties.split(", ");
        private int accountId;
        private Patient patient;
        private double totalAmount;
        private double amountPaid;

        int id = Integer.parseInt(parts[1].split("=")[1]);
        String accountId = parts[0].split("=")[1].replace("'", "");
        String password = parts[2].split("=")[1].replace("'", "");
        int idPatient = Integer.parseInt(parts[3].split("=")[1]);
        String diagnosla = parts[4].split("=")[1].replace("'", "");
        String statsStr = parts[5].split("=")[1].trim();

        return new Patient(name, id, password, idPatient, StatusPaitent.valueOf(statsStr),diagnosla,);
    }

    @Override
    public String toString() {
        return "BillingAccounts{" +
                "accountId=" + accountId +
                ", patient=" + patient +
                ", totalAmount=" + totalAmount +
                ", amountPaid=" + amountPaid +
                '}';
    }
}
