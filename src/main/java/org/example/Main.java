package org.example;

import javax.swing.*;

public class Main {
    static void main() {
        String strAccount = new Patient(
                "yahya",
                10,
                "123456789",
                10,
                StatusPaitent.STABLE,
                "خاطره مكسور لول"
        ).toString();
        Patient idk = Patient.StringToPatient(strAccount);
        IO.println(idk.getName());
    }
}
