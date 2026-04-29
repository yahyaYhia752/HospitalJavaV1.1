package org.example;

public class Main {
    static void main() {
        Patient pat1 = new Patient(
                "ahmed",
                1,
                "1234567",
                1,
                statusPaitent.STABLE,
                "Headache"
        );
        IO.println(pat1.getPassword());
        IO.println(pat1.toString());
        pat1.setPassword("100");
        IO.println(pat1.toString());
    }
}
