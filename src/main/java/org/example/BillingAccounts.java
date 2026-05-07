package org.example;

public class BillingAccounts {
    private int accountId;
    private int patientID;
    private double totalAmount;
    private double amountPaid;
    // Constructor
    public BillingAccounts(int accountId, int patientID, double totalAmount, double amountPaid) {
        this.accountId = accountId;
        this.patientID = patientID;
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

    public int getPatient() {
        return patientID;
    }

    public void setPatient(int patientID) {
        this.patientID = patientID;
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

        int idAcc = Integer.parseInt(parts[0].split("=")[1]);
        int idPati = Integer.parseInt(parts[1].split("=")[1]);
        int totalA = Integer.parseInt(parts[2].split("=")[1]);
        int amountP = Integer.parseInt(parts[3].split("=")[1]);

        return new BillingAccounts(idAcc, idPati, totalA, amountP);
    }

    @Override
    public String toString() {
        return "BillingAccounts{" +
                "accountId=" + accountId +
                ", patientID=" + patientID +
                ", totalAmount=" + totalAmount +
                ", amountPaid=" + amountPaid +
                '}';
    }
}
