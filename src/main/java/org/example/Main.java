package org.example;

public class Main {
    public static void main(String[] args) {
        try {
            HospitalManager hospital = new HospitalManager();
            hospital.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}